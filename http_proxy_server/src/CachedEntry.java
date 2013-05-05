import java.util.*;
import java.io.*;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import Caching.HttpRequest;
import Caching.HttpResponse;

public class CachedEntry {
	public static String cachingDir = "temp_cache";
	
	public CachedEntry() {
		File cacheDir = new File(cachingDir);
		if(!cacheDir.exists())
			cacheDir.mkdir();
	}
	public String getCachedFilename(HttpRequest request) {
		MessageDigest 	msgDigest = null;
		
		//Obtain the absolute URL
		String key = request.getAbsoluteURL();
		// Convert everything to lower case to obtain unique checksum
		key.toLowerCase();
		// Calculate SHA1 checksum
		try {
			msgDigest = MessageDigest.getInstance("SHA-1");
		} catch (NoSuchAlgorithmException e1) {
			//e1.printStackTrace();
		}
		msgDigest.update(key.getBytes());
		byte [] digest = msgDigest.digest();
		String hexStr = "";
		for (int i = 0; i < digest.length; i++) {
			hexStr +=  Integer.toString( ( digest[i] & 0xff ) + 0x100, 16).substring( 1 );
		}
		return cachingDir+"/"+hexStr;
	}
	
	public int cacheHttpResponse(HttpRequest request, 
			HttpResponse response) throws IOException{
		
		int 				retCode = 0;
		byte[] 				byteArray = null;
		FileOutputStream 	writer = null;
		/* 
		 * Cache the data in file whose name is SHA-1 hash of the
		 * absolute URL of the HttpRequest. There are two reasons 
		 * when there already exists a file with the same name.
		 * 1. Response for the request is cached but is now out-dated. 
		 *    Hence we purge the cache i.e, discard the cached response.
		 * 2. SHA-1 collision: Very unlikely. We ignore the likelihood
		 *    of this case. The case is handled by purging the cached
		 *    response.
		 */
		String hexStr = getCachedFilename(request);
		try {
			writer = new FileOutputStream (hexStr, false);
		} catch (IOException e1) {
			//e1.printStackTrace();
		}

		try {
			
			/*
			 * Cache the response
			 */
           	byteArray = response.getBytes();
           	int responseLength = response.getResponseLength();
           	writer.write( byteArray, 0, responseLength);
           	
		} catch (IOException e) {
			//e.printStackTrace();
			/*
			 * Delete the earlier created cached file in case of exception 
			 */
			(new File(hexStr)).delete();
		}
		finally {
			byteArray = null;
			if(writer != null)
				writer.close();

		}
		return retCode;
	}
	
	public boolean isResponseCachedForRequest(HttpRequest request) {
		/*
		 * Check if the cached file exists
		 */
		String hexStr = getCachedFilename(request);
		File cachedFile = new File(hexStr);
		if(cachedFile.canRead())
			return true;
		return false;
	}
	
	public byte[] getCachedHttpResponse(HttpRequest request) throws IOException {

		FileInputStream 	reader 	= null;
		String 				hexStr  = getCachedFilename(request);
		byte[] 				buffer 	= null;
		File 				cachedFile = new File(hexStr);
		byte[] 				cachedData = null;
		
		try {
			reader = new FileInputStream (hexStr);
		} catch (IOException e1) {
			//e1.printStackTrace();
			/*
			 * Cached response does not exist
			 */
			return null;
		}
		try {
			int retCode = 0;
			int offset  = 0;
			cachedData  = new byte[(int)cachedFile.length()];
			buffer 	    = new byte[80];
			
			/*
			 * Read the response from the cache
			 */
			while((retCode = reader.read(buffer, 0, buffer.length)) != -1) {
				System.arraycopy(buffer, 0, cachedData, offset, retCode);
				offset += retCode;
			}
			int i;
			/*
			 * Strip off the header. End of header is identified by consecutive
			 * newlines.
			 */
			for(i=0; i < offset - 4; i++) 
				if(cachedData[i] == '\r' && cachedData[i+1] == '\n' &&
						cachedData[i+2] == '\r' && cachedData[i+3] == '\n')
					break;
			/*
			 * Return the data field of the HttpResponse only
			 */
			byte[] dataArray = new byte[offset - i - 4];
			System.arraycopy(cachedData, i + 4, dataArray, 0, offset - i - 4);
			reader.close();
			return dataArray;
			
		} catch(IOException ex) {
			//ex.printStackTrace();
		}
		finally {
			if(reader != null)
				reader.close();
		}
		return null;
	}
	
	public byte[] getCachedHttpResponseComplete(
			HttpRequest request) throws IOException{
		
		FileInputStream 	reader = null;
		String hexStr = getCachedFilename(request);
		try {
			reader = new FileInputStream (hexStr);
		} catch (IOException e1) {
			//e1.printStackTrace();
		}
		
		File cachedFile = new File(hexStr);
		byte[] cachedData = new byte[(int)cachedFile.length()];
		
		if(cachedFile.length() == 0)
			throw new IOException();
		
		byte[] buffer = new byte[80];
		try {
			int retCode = 0;
			int offset = 0;
			/*
			 * Read the whole cached response
			 */
			
			while((retCode = reader.read(buffer, 0, buffer.length)) != -1) {
				System.arraycopy(buffer, 0, cachedData, offset, retCode);
				offset += retCode;
			}
		} catch(IOException ex) {
			//ex.printStackTrace();
		}
		finally {
			if(reader != null)
				reader.close();
		}
		return cachedData;
	}
	
	public boolean isCacheExpired(HttpRequest request) {
		
		byte[] byteResponse = null;
		try {
			byteResponse = getCachedHttpResponseComplete(request);
		} catch (IOException e) {
			/*
			 *  Irretrievable cache response is equivalent to
			 *  stale cached data in which case the response is 
			 *  fetched from the server.
			 */
			//e.printStackTrace();
			return true;
		}
		HttpResponse response = new HttpResponse();
		response.parseResponse(byteResponse, byteResponse.length);
		
		/*
		 * Calculate the age of the cached response. 
		 * Refer Section 13.2.3 of HTTP 1.1 RFC (2616)
		 */
		
		long apparentAge;
		File cachedFile = new File(getCachedFilename(request));

		if((cachedFile.lastModified() - 
				response.getResponseTime().getTime()) <= 0)
			apparentAge = 0;
		else
			apparentAge = (cachedFile.lastModified() - 
					response.getResponseTime().getTime())/1000;
		
		long ageValue = response.getAge().longValue();
		long correctedReceivedAge;
		if(apparentAge > ageValue)
			correctedReceivedAge =apparentAge;
		else
			correctedReceivedAge = ageValue;
		long responseDelay = (request.getRequestTime().getTime() - 
				cachedFile.lastModified())/1000 ;
		long correctedInitialAge = correctedReceivedAge + responseDelay;
		Date now = new Date();
		long residentTime = (now.getTime() - cachedFile.lastModified())/1000;
		long currentAge = correctedInitialAge + residentTime;
		
		/*
		 * Calculate freshness life-time of HTTP response.
		 * Refer section 13.2.4 of HTTP 1.1 RFC (2616)
		 */
		long freshnessLifetime;
		if(response.getMaxAgeValue().compareTo(0) != 0)
			freshnessLifetime = response.getMaxAgeValue();
		else
			freshnessLifetime = (response.getCacheExpirationDate().getTime() - 
					cachedFile.lastModified())/1000;
		if(freshnessLifetime > currentAge)
			return false;
		return true;
	}

}
