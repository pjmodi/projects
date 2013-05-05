package Caching;

import java.io.IOException;
import java.io.InputStream;
import java.net.URLConnection;
import java.text.DateFormat;
import java.text.ParseException;
import java.util.Map;
import java.util.Set;
import java.util.StringTokenizer;
import java.util.Vector;
import java.util.Iterator;
import java.util.Date;
import java.text.SimpleDateFormat;


public class HttpResponse {
	static final int			MAX_SIZE_OF_RESPONSE = 32768;
	static String				NewLine = "\r\n";
	
	HttpResponseLine 			responseLine;
	Vector<HttpGeneralHeader> 	genHdrs;
	Vector<HttpResponseHeader> 	resHdrs;
	Vector<HttpEntityHeader>  	entityHdrs;
	byte[]						data;
	int							dataLength;
	int							httpResponseLength;
	String						dataPath;


	public HttpResponse () {
		/*
		 * Initialize private variables to default value
		 */
		responseLine = new HttpResponseLine();
		genHdrs = new Vector<HttpGeneralHeader>();
		resHdrs = new Vector<HttpResponseHeader>();
		entityHdrs = new Vector<HttpEntityHeader>();
		data = new byte[MAX_SIZE_OF_RESPONSE];
		dataLength = httpResponseLength = 0;
		dataPath = null;
	}
	
	public HttpResponse(URLConnection urlConnection) throws IOException {
		/*
		 * The constructor reads HTTP 1.1 response through URLConnection
		 * stream and parses the response. 
		 */
		responseLine = new HttpResponseLine();
		genHdrs = new Vector<HttpGeneralHeader>();
		resHdrs = new Vector<HttpResponseHeader>();
		entityHdrs = new Vector<HttpEntityHeader>();
		
		/*
		 * Loop through all HTTP response headers and store them
		 * in byte[] array ('response'). Terminate each
		 * response header with newline ("\r\n" in case of windows)
		 */
		Map map = urlConnection.getHeaderFields();
        Set set = map.entrySet();
        Iterator iterator = set.iterator();
        
        byte[] response = new byte[MAX_SIZE_OF_RESPONSE];
        int responseOffset = 0;
        
        while (iterator.hasNext()) {
        	byte[] header = iterator.next().toString().getBytes();
        	System.arraycopy(header, 0, 
            		response, responseOffset, header.length);
        	System.arraycopy(NewLine.getBytes(), 0, 
        			response, responseOffset + header.length, NewLine.length());
        	responseOffset += header.length + NewLine.length();
        }
        /*
         * Insert a new line once all headers are parsed.
         * This marks the beginning of data in HTTP response.
         */
        System.arraycopy(NewLine.getBytes(), 0, response, responseOffset, NewLine.length());
        responseOffset += NewLine.length();
        
        /*
         * Read through the data of the HTTP response
         * and append it to 'response' byte [].
         */
		InputStream ipStream = urlConnection.getInputStream();
		
		/*
		 * Assume that the data in HTTP response is
		 * MAX_SIZE_OF_RESPONSE bytes long. 
		 */
		int responseLength =  MAX_SIZE_OF_RESPONSE;
		byte [] tempArray = new byte[responseLength];
		
		int index = ipStream.read(tempArray, 0, tempArray.length);
		
        while ( (index != -1)) {
        	/*
        	 * In case the data is more than MAX_SIZE_OF_RESPONSE bytes
        	 * long, extend the array length dynamically.
        	 */
        	if((responseOffset + index) > responseLength) {
        		int prevResponseLength = responseLength;
        		responseLength *= 2;
        		byte[] temp = new byte[responseLength];
        		System.arraycopy(response, 0, temp, 0, prevResponseLength);
        		response = temp;
        	}
        	System.arraycopy(tempArray, 0, response, responseOffset, index);
        	responseOffset += index;
        	index = ipStream.read( tempArray, 0, tempArray.length);
        }
		parseResponse(response, responseOffset);
	}
	
 	public void parseResponse(byte[] response, int responseLength) {
		/* Format of HTTP 1.1 Request  
		 * Request = Response-Line             
         *          *(( general-header        
         *                | response-header         
         *                | entity-header ) CRLF)  
         *            CRLF
         *            [ message-body ]          
		 *
		 * 
		 */
		int startOffset, endOffset;
		startOffset = endOffset = 0;
		httpResponseLength = 0;
		
		/*
		 * Each request header is terminated by "\r\n". Use it as a
		 * delimiter to separate headers.
		 * TODO: Windows specific code !
		 */
		for(endOffset = startOffset; endOffset < response.length - 1; endOffset++)
			if(response[endOffset]==0xD && response[endOffset+1]==0xA)
				break;
		
		/*
		 * Parse the response line
		 */
		responseLine.initialize(new String(response, startOffset, endOffset - startOffset));
		startOffset = endOffset;

		//Request may have zero or more headers
		boolean responseProcessed = false;
		while(!responseProcessed)
		{
			startOffset = endOffset + 2; // Skip CR & LF
			for(endOffset = startOffset; endOffset < response.length - 1; endOffset++)
				if(response[endOffset]==0xD && response[endOffset + 1]==0xA)
					break;
			// Two consecutive CR-LF symbolize end of Http response headers 
			if(endOffset - startOffset == 0)
				break;
			
			String header = new String(response, startOffset, endOffset - startOffset);
			
			
			try {
				// Could be a general/request/entity header
				HttpGeneralHeader genHdr = new HttpGeneralHeader();
				genHdr.parseHeader(header);
				genHdrs.addElement(genHdr);
				httpResponseLength += header.toString().length() + 2;
			}
			catch (IllegalArgumentException ex) {
				try {
					// Could be a request/entity header
					HttpResponseHeader resHdr = new HttpResponseHeader();
					resHdr.parseHeader(header);
					resHdrs.addElement(resHdr);
					httpResponseLength += header.toString().length() + 2;
				}
				catch (IllegalArgumentException ex1) {
					try {
						// Could be a entity header
						HttpEntityHeader entityHdr = new HttpEntityHeader();
						entityHdr.parseHeader(header);
						entityHdrs.addElement(entityHdr);
						httpResponseLength += header.toString().length() + 2;
					}
					catch (IllegalArgumentException ex2) {
						// Unknown header encountered. Ignore it !
					}
				}
			}
		}
		
		endOffset += 2; // Skip \r\n
		/*
		 * After response headers, comes the actual HTTP response
		 */
		dataLength = responseLength - endOffset;
		httpResponseLength += dataLength + 2;
		data = new byte[dataLength];
		System.arraycopy(response, endOffset, data, 0, dataLength);
	}
	
	public byte[] getBytes() {
		/*
		 * This function does the reverse of parseResponse.
		 * It bundles the parsed response back to byte[]. 
		 */
		String strResponse = responseLine.toString();
		
		byte [] byteResponse = new byte[MAX_SIZE_OF_RESPONSE];
	
		int offset = 0;
		
		/*
		 * First is the response Line terminated by newline
		 */
		System.arraycopy(strResponse.getBytes(), 0, byteResponse, 0, strResponse.getBytes().length);
		offset += strResponse.getBytes().length;
		System.arraycopy("\r\n".getBytes(), 0, byteResponse, offset, 2);
		offset += 2;

		/*
		 * Next follows the response headers.
		 */
		
		/*
		 * Loop through all General headers.
		 */
		Iterator<HttpGeneralHeader> itrGenHdr = genHdrs.iterator();
		
		while(itrGenHdr.hasNext())
		{
			HttpGeneralHeader tempGenHdr= itrGenHdr.next();
			String tempStr = tempGenHdr.toString();
			byte[] srcArray = tempStr.getBytes();
			System.arraycopy(srcArray, 0, 
					byteResponse, offset, srcArray.length);
			offset += srcArray.length;
			/*
			 * Each header is terminated by newline
			 */
			System.arraycopy("\r\n".getBytes(), 0, byteResponse, offset , 2);
			offset += 2;
		}

		/*
		 * Loop through all the response headers.
		 */
		Iterator<HttpResponseHeader> itrResHdr = resHdrs.iterator();
		
		while(itrResHdr.hasNext())
		{
			HttpResponseHeader tempResHdr = itrResHdr.next();
			String tempStr = tempResHdr.toString();
			byte[] srcArray = tempStr.getBytes();
			System.arraycopy(srcArray, 0, 
					byteResponse, offset, srcArray.length);
			offset += srcArray.length;
			System.arraycopy("\r\n".getBytes(), 0, byteResponse, offset, 2);
			offset += 2;
		}

		/*
		 * Loop through all entity headers.
		 */
		Iterator<HttpEntityHeader> itrEntityHdr = entityHdrs.iterator();
		
		while(itrEntityHdr.hasNext())
		{
			HttpEntityHeader tempEntityHdr = itrEntityHdr.next();
			String tempStr = tempEntityHdr.toString();
			byte[] srcArray = tempStr.getBytes();
			System.arraycopy(srcArray, 0, 
					byteResponse, offset, srcArray.length);
			offset += srcArray.length;
			System.arraycopy("\r\n".getBytes(), 0, byteResponse, offset, 2);
			offset += 2;
		}
		/*
		 * Extra new line indicates the beginning of data
		 */
		System.arraycopy("\r\n".getBytes(), 0, byteResponse, offset, 2);
		
		int responseLength = MAX_SIZE_OF_RESPONSE;
		if(responseLength < (offset + 2 + dataLength)) {
			int prevResponseLength = responseLength;
			/*
			 * Dynamically increase the size of the data array
			 * in case the response > MAX_SIZE_OF_RESPONSE
			 */
			while(responseLength < (offset+2 + dataLength))
				responseLength *= 2;
			byte[] temp = new byte[responseLength];
			System.arraycopy(byteResponse, 0, temp, 0, prevResponseLength);
			byteResponse = temp;
		}

		System.arraycopy(data, 0, byteResponse, offset+2, dataLength);
		httpResponseLength = offset+2 + dataLength;
		return byteResponse;
	}
	
	public boolean isResponseCachable() {
		/*
		 * Validate Cache-Control General header to determine
		 * if the response can be cached.
		 */
		Iterator<HttpGeneralHeader> itrGenHdr = genHdrs.iterator();
		
		while(itrGenHdr.hasNext()) {
			HttpGeneralHeader tempGenHdr= itrGenHdr.next();
			if(tempGenHdr.getStrGenHeaderType().compareTo("Cache-Control") == 0) {
				String headerData = tempGenHdr.getHeaderData();
				if(headerData.contains("no-cache")) 
					return false;
				if(headerData.contains("must-revalidate"))
					return false;
				if(headerData.contains("proxy-revalidate"))
					return false;
			}
		}
		
		return true;
	}
	
	public Date getResponseTime() {
		/*
		 * Extract the 'Date' General header to retrieve the
		 * time of response.
		 */
		Iterator<HttpGeneralHeader> itrGenHdr = genHdrs.iterator();
		Date date = new Date(0);
		while(itrGenHdr.hasNext()) {
			HttpGeneralHeader tempGenHdr = itrGenHdr.next();
			if(tempGenHdr.getStrGenHeaderType().compareTo("Date") == 0) {
				/*
				 *  Parse the date. HTTP 1.1 specifies date in
				 *  "E, dd MMM yyyy HH:mm:ss z" format 
				 *  (For example: Mon, 08 Sep 2003 15:37:44 GMT)
				 */
				
				DateFormat formatter = new SimpleDateFormat(
						"E, dd MMM yyyy HH:mm:ss z");
				String headerData = tempGenHdr.getHeaderData();
				/*
				 * It was found during debugging that the 
				 * header data was enclosed within '[' and ']'.
				 * Strip off these to extract the actual data.
				 */
				headerData = headerData.replace('[', ' ');
				headerData = headerData.replace(']', ' ');
				headerData = headerData.trim();
				try {
					return(formatter.parse(headerData));
					
				} catch (ParseException e) {
					// TODO Auto-generated catch block
					//e.printStackTrace();
					return date;
				}
			}
		}
		return date;
	}
	
	public Integer getAge() {
		/*
		 * Extract the 'Age' General header to retrieve the
		 * age of the response.
		 */
		Iterator<HttpResponseHeader> itrResHdr = resHdrs.iterator();
		Integer age = new Integer(0);
		while(itrResHdr.hasNext()) {
			HttpResponseHeader tempResHdr = itrResHdr.next();
			if(tempResHdr.getStrResHeaderType().compareTo("Age") == 0) {
				String headerData = tempResHdr.getHeaderData();
				/*
				 * It was found during debugging that the 
				 * header data was enclosed within '[' and ']'.
				 * Strip off these to extract the actual data.
				 */
				
				headerData = headerData.replace('[', ' ');
				headerData = headerData.replace(']', ' ');
				headerData = headerData.trim();
				try {
					return(Integer.parseInt(headerData));
				} catch (NumberFormatException ex) {
					//ex.printStackTrace();
					return 0;
				}
			}
		}
		return age;
	}
	
	public Integer getMaxAgeValue() {
		/*
		 * Extract the 'Max-Age' General header to retrieve the
		 * Max-Age of the response.
		 */
		Iterator<HttpGeneralHeader> itrGenHdr = genHdrs.iterator();
		Integer maxAge = new Integer(0);
		while(itrGenHdr.hasNext()) {
			HttpGeneralHeader tempGenHdr = itrGenHdr.next();
			if(tempGenHdr.getStrGenHeaderType().compareTo("Cache-Control") == 0) {
				String headerData = tempGenHdr.getHeaderData();
				/*
				 * It was found during debugging that the 
				 * header data was enclosed within '[' and ']'.
				 * Strip off these to extract the actual data.
				 */
				headerData = headerData.replace('[', ' ');
				headerData = headerData.replace(']', ' ');
				headerData = headerData.trim();
				if(headerData.contains("max-age")) {
					StringTokenizer st = new StringTokenizer(headerData, "=");
					String token = st.nextToken();
					token = st.nextToken();
					token = token.trim();
					/* 
					 * Cumbersome to use string tokenizer any further
					 * for obtaining max-age. The delimiter can be any
					 * one of the following ',', ']'. Instead rely on
					 * ascii comparison for number extraction.
					 */
					int i;
					for(i = 0; i < token.length() && token.charAt(i) >= 48
						&& token.charAt(i) <=57; i++);
					try {
						return (Integer.parseInt(token.substring(0, i)));
					} catch(NumberFormatException ex) {
						//ex.printStackTrace();
						return 0;
					}
				}
			}
		}
		return maxAge;
	}
	
	public Date getCacheExpirationDate() {
		Iterator<HttpEntityHeader> itrEntityHdr = entityHdrs.iterator();
		Date date = new Date();
		while(itrEntityHdr.hasNext()) {
			
			HttpEntityHeader entityHeader = itrEntityHdr.next();
			
			if(entityHeader.getStrGenHeaderType().compareTo("Expires") == 0) {
				
				DateFormat formatter = new SimpleDateFormat(
						"E, dd MMM yyyy HH:mm:ss z");
				
				String headerData = entityHeader.getHeaderData();
				try {
					Integer temp;
					headerData = headerData.replace('[', ' ');
					headerData = headerData.replace(']', ' ');
					headerData = headerData.trim();
					if(-1 == Integer.parseInt(headerData))
						return new Date(Integer.MAX_VALUE); // Response never expires
				} catch (NumberFormatException ex) {
					/*
					 * It was found during debugging that the 
					 * header data was enclosed within '[' and ']'.
					 * Strip off these to extract the actual data.
					 */
					try {
						return(formatter.parse(headerData));
					} catch (ParseException e) {
						// TODO Auto-generated catch block
						//e.printStackTrace();
						return new Date(0); // Response expired
					}
				}
			}
		}
		return date;
	}
	
	public int getResponseLength() {
		return httpResponseLength;
	}
	
	public static byte[] generateForbiddenResponse()
	{
		String msg = "<html><head><meta http-equiv=\"content-type\" content=\"text/html; charset=ISO-8859-1\"></head><body><h1>You are trying to visit a blocked site!</h1></body></html>";
        StringBuffer errorMessage = new StringBuffer("HTTP/1.1 403 Forbidden\r\n"); 
        errorMessage.append("Content-Type: text/html\r\n");
        errorMessage.append("Content-Length: ");
        errorMessage.append(msg.length());
        errorMessage.append("\r\n\r\n");
        errorMessage.append(msg);
        return errorMessage.toString().getBytes();
	}
}
