package Caching;

import java.util.StringTokenizer;


public class HttpResponseLine {
	String 	httpVersion;
	Integer statusCode;
	String	reasonPhase;
	
	public void initialize(String requestLine) {
		// Eliminate everything till '='
		StringTokenizer st ;
		String newRequestLine = requestLine; 
		if(requestLine.contains("=")) {
			st = new StringTokenizer(requestLine, "=");
			newRequestLine = st.nextToken();
			if(st.hasMoreElements())
				newRequestLine = st.nextToken();
		}
		
		// Eliminate ']' and '['
		newRequestLine = newRequestLine.replace('[', ' ');
		newRequestLine = newRequestLine.replace(']', ' ');
		
		// Now parse the response line
		st = new StringTokenizer(newRequestLine, " ");
		httpVersion = st.nextToken();
		try {
			statusCode = Integer.parseInt(st.nextToken());
		} catch (NumberFormatException ex) {
			// TODO:
		}
		if(st.hasMoreTokens())
			reasonPhase = st.nextToken();
	}
	public String toString() {
		return(httpVersion + " " + statusCode.toString() +" " + reasonPhase);
	}
}
