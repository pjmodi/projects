package Caching;

import java.util.StringTokenizer;


public class HttpRequestLine {
	String method;
	String requestURI;
	Integer portNumber;
	String httpVersion;
	
	public void initialize(String requestLine)
	{
		StringTokenizer st = new StringTokenizer(requestLine.trim(), " ");
		method = st.nextToken().toUpperCase();
		requestURI = st.nextToken();
		httpVersion = st.nextToken().toUpperCase();
		
		StringBuffer sb = new StringBuffer(requestURI);
		int httpIndex = sb.indexOf("http://");
		if (httpIndex > -1)		
			sb = sb.replace(httpIndex, httpIndex + 7, "");  // we want to strip out http://
			
		try {
			int i;
			int index = sb.indexOf(":") + 1;
			for(i=index; i < sb.length() && sb.charAt(i) >=48 && sb.charAt(i) <= 57; i++);
			portNumber = Integer.parseInt(sb.substring(index, i));
		} catch (Exception  ex){
			/*
			 * If port number is not specified, its the default (=80).
			 */
			portNumber = 80;
		}

	}
	public String getRequestURI() {
		return requestURI;
	}
	public String getMethod(){
		return method;
	}
	public String getHttpVersion() {
		return httpVersion;
	}
	public Integer getPortNumber() {
		return portNumber;
	}
}
