package Caching;

import java.util.StringTokenizer;

public class HttpResponseHeader extends HttpHeader {
	enum ResponseHeaderTypes {
		Accept_Ranges,
		Age,
		ETag,
		Location,
		Proxy_Authenticate,
		Retry_After,
		Server,
		Vary,
		WWW_Authenticate
	}
	ResponseHeaderTypes headerType;
	String				headerData;
	
	public HttpResponseHeader() {
		headerData = new String();
	}
	
	public int parseHeader(String strToParse) throws IllegalArgumentException {
		int retCode = 0;
		StringTokenizer st = new StringTokenizer(strToParse, "=");
		String tempStr = st.nextToken();
		tempStr.trim();
		try {
			headerType = ResponseHeaderTypes.valueOf(tempStr.replace("-", "_"));
		}
		catch(IllegalArgumentException ex) {
			throw ex;
		}
		headerData = st.nextToken();
		while(st.hasMoreElements())
			headerData = headerData + "=" + st.nextToken();
		
		headerData.trim();
		return retCode;
	}
	public String getStrResHeaderType() {
		String tempStr = headerType.toString();
		tempStr = tempStr.replace('_', '-');
		return tempStr;
	}
	public String getHeaderData() {
		return headerData;
	}
	public String toString() {
		String retStr = headerType.toString();
		retStr = retStr.replace('_', '-');
		retStr = retStr.concat("=");
		retStr = retStr.concat(headerData);
		return retStr;
	}
}
