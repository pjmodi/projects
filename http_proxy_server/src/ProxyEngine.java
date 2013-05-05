import java.io.*;
import java.net.*;
import java.util.ArrayList;

import Caching.*;
import MimeContent.Mime;
import MimeContent.MimeEntry;


public class ProxyEngine {

	private static final int 	BUFFER_SIZE = 32768;
	protected ServerSocket	 	proxySocket;
	protected Socket			clientSocket;
	protected OutputStream		output;
	protected Object[]			filters;	
	
	public ProxyEngine(int portNo, String configFilePath)
	{ 	
		try 
		{
			// Get the configuration 
			ConfigFile configFile = new ConfigFile(configFilePath);
			filters = configFile.getEntries(); 
			
			proxySocket = new ServerSocket(portNo);
		} 
		catch (IOException ex) 
		{
			//ex.printStackTrace();
		}
		
		while(true)
		{
			try 
			{
				System.out.println("\nWaiting for a connection...");
				clientSocket = proxySocket.accept();
				
				if (clientSocket.isConnected())
				{
					output = clientSocket.getOutputStream();
					System.out.println("Connection request accepted from " 
							+ clientSocket.getInetAddress().getHostAddress());
						
					HttpRequest httpRequest = new HttpRequest(clientSocket.getInputStream());
					System.out.println("Request for " + httpRequest.getAbsoluteURL());
					
					if (isAllowedPerFilters(httpRequest)) 
					{
						CachedEntry cache = new CachedEntry();
	
						if(cache.isResponseCachedForRequest(httpRequest) && !cache.isCacheExpired(httpRequest)) 
						{
							System.out.println("Rendering from cache " + cache.getCachedFilename(httpRequest));
							byte[] response = cache.getCachedHttpResponse(httpRequest);
							copyStream(response, response.length, output);
						}
						else 
						{ 
							String requestURI = httpRequest.getRequestLine().getRequestURI();
							URL requestURL = null;
							
							if(httpRequest.getRequestLine().getPortNumber().equals(80)) 
							{
								requestURL = new URL (requestURI);
								URLConnection connection = requestURL.openConnection();
								HttpResponse httpResponse = new HttpResponse(connection);
								System.out.println("Response cached as " + cache.getCachedFilename(httpRequest));
								
								byte [] response = null;
								if(httpResponse.isResponseCachable()) 
								{
									try
									{
										cache.cacheHttpResponse(httpRequest, httpResponse);		
										response = cache.getCachedHttpResponse(httpRequest);
									}
									catch (IOException e)
									{
										response = httpResponse.getBytes();
										Proxy.logger.finest(e.toString());
									}
								}
								else
									response = httpResponse.getBytes();
								
								copyStream(response, response.length, output);
							}			
						}
					}
					else
					{
						byte[] forbiddenResponse = HttpResponse.generateForbiddenResponse();
						copyStream(forbiddenResponse, forbiddenResponse.length, output);
					}
				}
			} 
			catch (FileNotFoundException fe)
			{
				Proxy.logger.warning(fe.toString());
			}
			catch (Exception e) 
			{
				Proxy.logger.warning(e.toString());
				//e.printStackTrace();
			}
			finally
			{
				try 
				{ 
					// We must close the socket to the client before we start accepting new requests
					if (clientSocket != null)
						clientSocket.close();  
				}
				catch (IOException e) 
				{ 
					Proxy.logger.warning(e.toString());
				}
			}
		}
	}
	
 
	// This function checks if the recd. request can be allowed as per the filters
	// set in the config file
	public boolean isAllowedPerFilters(HttpRequest request) throws Exception 
	{
		String mimeFromHeadRequest = request.makeHeadRequest();
		
		try
		{
			if (mimeFromHeadRequest != null)
			{
				// Get the requested mime type
				Mime requestedType = new Mime(mimeFromHeadRequest);
				
				// Get the requested host as a URL
				StringBuffer host = new StringBuffer("http://");   // host will never have the protocol
				host.append(request.getRequestHeaderByType("Host").trim());
				
				// We first collect all the filters for this host in an arraylist
				ArrayList entriesPerHost = new ArrayList();
				for(int x=0; x<filters.length; x++)
				{
					MimeEntry entry = (MimeEntry) filters[x];
					if (host.toString().equalsIgnoreCase(entry.getURL()))
							entriesPerHost.add(entry.getMime());
				}
				
				entriesPerHost.trimToSize();
				Object[] mimesNotAllowed = (Object[]) entriesPerHost.toArray();
				Mime allEntriesUnderMimeType = new Mime(requestedType.getMimeType(),"*");
				Mime allMimeTypes = new Mime("*","*");
				
				// We can than parse this collection to match any of the filters
				for(int x=0; x<mimesNotAllowed.length; x++)
				{
					Mime entry = (Mime) mimesNotAllowed[x];
					if (entry.equals(requestedType)
						|| entry.equals(allEntriesUnderMimeType)
						|| entry.equals(allMimeTypes))
					{
						System.out.println("Request forbidden as Host = " + host.toString() 
								+ " and Content-Type = " +  mimeFromHeadRequest);
						return false;
					}
				}
			}
		}
		catch(Exception ex)
		{
			throw ex;
		}
		
		return true;		
	}
	
	
	// This function copies the bytes from the input stream to the output stream
	public void copyStream(byte[] input, int inputLength, OutputStream output) throws IOException
	{
		DataOutputStream outputWriter = null;
		try 
		{
			outputWriter = new DataOutputStream(output);
			outputWriter.write(input, 0, inputLength);
		}
		finally
		{
			if(outputWriter != null)
				outputWriter.close();
			
			if(output != null)
				output.close();
		}
	}	
	
	
	// This function copies the bytes from the input stream to the output stream
	public void copyStream(InputStream input, OutputStream output) throws IOException
	{
		DataOutputStream outputWriter = null;
		
		try 
		{
			// Open the client's OutputStream
			outputWriter = new DataOutputStream(output);
			
			// Copy bytes in batches to that stream
			byte buffer[] = new byte[ BUFFER_SIZE ];
			int bytesRead = input.read( buffer, 0, BUFFER_SIZE );

			while ( bytesRead != -1 )
			{
				outputWriter.write( buffer, 0, bytesRead );
				bytesRead = input.read( buffer, 0, BUFFER_SIZE );
			}
		}
		finally
		{
			if (input != null)
				input.close();
			
			if (output != null)
				output.close();
			
			if (outputWriter != null) 
				outputWriter.close();
		} 
	}
	
	// Destructor
	protected void finalize() throws IOException
	{
		if (proxySocket != null)
			proxySocket.close();
		
		if (output != null)
			output.close();
		
		if (clientSocket != null)
			clientSocket.close();
	}
}
