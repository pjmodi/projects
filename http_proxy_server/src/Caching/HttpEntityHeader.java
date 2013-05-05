package Caching;

import java.util.StringTokenizer;

public class HttpEntityHeader extends HttpHeader{
	enum EntityHeaderTypes {
		Allow,
		Content_Encoding,
		Content_Language,
		Content_Length,
		Content_Location,
		Content_MD5,
		Content_Range,
		Content_Type,
		Expires,
		Last_Modified,
		extension_header
	}
	EntityHeaderTypes 	headerType;
	String				headerData;
	
	public HttpEntityHeader () {
		headerData = new String();
	}
	
	public int parseHeader(String strToParse) throws IllegalArgumentException {
		int retCode = 0;
		StringTokenizer st = new StringTokenizer(strToParse, "=");
		String tempStr = st.nextToken();
		tempStr.trim();
		try {
			
			headerType = EntityHeaderTypes.valueOf(tempStr.replace("-", "_"));
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
	
	public String getStrGenHeaderType() {
		String tempStr = headerType.toString();
		tempStr = tempStr.replace('_', '-');
		return tempStr;
	}
}
