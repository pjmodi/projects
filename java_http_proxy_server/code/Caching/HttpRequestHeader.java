package Caching;

import java.util.StringTokenizer;

public class HttpRequestHeader extends HttpHeader {
	enum RequestHeaderTypes {
		Accept,
		Accept_Charset,
		Accept_Encoding,
		Accept_Language,
		Authorization,
		Expect,
		From,
		Host,
		If_Match,
		If_Modified_Since,
		If_None_Match,
		If_Range,
		If_Unmodified_Since,
		Max_Forwards,
		Proxy_Authorization,
		Range,
		Referer,
		TE,
		User_Agent
	}
	RequestHeaderTypes 	headerType;
	String				headerData;
	
	public HttpRequestHeader() {
		headerData = new String();
	}
	public int parseHeader(String strToParse) throws IllegalArgumentException {
		int retCode = 0;
		StringTokenizer st = new StringTokenizer(strToParse, ":");
		String tempStr = st.nextToken();
		tempStr.trim();
		try {
			
			headerType = RequestHeaderTypes.valueOf(tempStr.replace('-', '_'));
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
	public String getStrReqHeaderType() {
		return headerType.toString();
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