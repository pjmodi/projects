package Caching;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.NoSuchElementException;
import java.util.StringTokenizer;
import java.util.Vector;
import java.util.Iterator;
import java.util.Date;


public class HttpRequest {
	enum HttpHeaderType {
		General_Header,
		Request_Header,
		Entity_Header
	}
	
	HttpRequestLine 			requestLine;
	String						absoluteStrURL;
	Vector<HttpGeneralHeader> 	genHdrs;
	Vector<HttpRequestHeader> 	reqHdrs;
	Vector<HttpEntityHeader>  	entityHdrs;
	Date						requestTime;
	// HttpHeader 		header; //Fix this; HttpHeader is an abstract class
	
	
	public HttpRequest(InputStream ipStream) {
		requestLine = new HttpRequestLine();
		genHdrs = new Vector<HttpGeneralHeader> (); 
		reqHdrs = new Vector<HttpRequestHeader> ();
		entityHdrs = new Vector<HttpEntityHeader> (); 
		setRequestTime();
		String strRequest = new String();
		String inputLine = new String();
		BufferedReader bufReader = new BufferedReader(new InputStreamReader(ipStream));
		try {
			while((inputLine = bufReader.readLine()) != null) {
				strRequest = strRequest + inputLine + "\r\n";
				try {
					StringTokenizer tok = new StringTokenizer(inputLine);
					tok.nextToken();
				} catch (Exception e) {
                    break;
				}
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
		}
		parseHttpRequest(strRequest);
	}
	
	public void setRequestTime() {
		requestTime = new Date();
	}
	public Date getRequestTime() {
		return requestTime;
	}
	public int parseHttpRequest(String request) {
		int retCode = 0;
		/* Format of HTTP 1.1 Request  
		 * Request = Request-Line             
         *          *(( general-header        
         *                | request-header         
         *                | entity-header ) CRLF)  
         *            CRLF
         *            [ message-body ]          
		 *
		 * 
		 */
		StringTokenizer st = new StringTokenizer(request, "\r\n");
		//Request begins with Request-Line
		requestLine.initialize(st.nextToken());
		
		//Request may have zero or more headers
		boolean requestProcessed = false;
		while(!requestProcessed)
		{
			String header = null;
			try {
				header = st.nextToken();
				
			} catch (NoSuchElementException ex) {
				break;
			}
			
			if (header.length() == 0)
				break;
			try {
				// Could be a general/request/entity header
				HttpGeneralHeader genHdr = new HttpGeneralHeader();
				genHdr.parseHeader(header);
				genHdrs.addElement(genHdr);
			}
			catch (IllegalArgumentException ex) {
				try {
					// Could be a request/entity header
					HttpRequestHeader reqHdr = new HttpRequestHeader();
					reqHdr.parseHeader(header);
					reqHdrs.addElement(reqHdr);
				}
				catch (IllegalArgumentException ex1) {
					try {
						// Could be a entity header
						HttpEntityHeader entityHdr = new HttpEntityHeader();
						entityHdr.parseHeader(header);
						entityHdrs.addElement(entityHdr);
					}
					catch (IllegalArgumentException ex2) {
						// Unrecognized header. Ignore it.
					}
				}
			}
		}
		String host = getRequestHeaderByType("Host").toLowerCase().trim();
		
		// If the URI we have recd. is not absolute then we prefix the host
		if (requestLine.getRequestURI().indexOf(host) == -1)
		{
			StringBuffer absoluteURI = new StringBuffer(host);
			absoluteURI.append(requestLine.getRequestURI());
			absoluteStrURL = absoluteURI.toString();
		}
		else 
			absoluteStrURL = requestLine.getRequestURI();
		
		return retCode;
	}
	public String getAbsoluteURLFromHttpRequest(String request) {
		parseHttpRequest(request);
		return absoluteStrURL;
	}
	public String getAbsoluteURL() {
		return absoluteStrURL;
	}
	public HttpRequestLine getRequestLine() {
		return requestLine;
	}
	public String makeHeadRequest() throws IOException
    {
		URL headRequestURL = new URL(getAbsoluteURL());
		
		HttpURLConnection connection = (HttpURLConnection) headRequestURL.openConnection();
		connection.setRequestMethod("HEAD");
		connection.setRequestProperty("Connection", "Close");
		connection.connect();
		
		String mime = connection.getContentType();
		if (mime.indexOf("; ") > -1)
		{
			String[] mimeData = mime.split("; ");
			return mimeData[0];
		}
		
		return mime;
    }	
	public String getRequestHeaderByType(String headerType)
	{
		Iterator<HttpRequestHeader> itr = reqHdrs.iterator();
		while(itr.hasNext()) 
		{
			HttpRequestHeader tempReqHdr = itr.next();

			if(tempReqHdr.getStrReqHeaderType().compareTo(headerType) == 0)
				return tempReqHdr.getHeaderData();
		}
		
		return null;
	}
}
