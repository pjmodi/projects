package Caching;

import java.util.StringTokenizer;

	
public class HttpGeneralHeader extends HttpHeader {
	enum GeneralHeaderTypes { Cache_Control, 
		Connection, 
		Date, 
		Pragma,
		Trailer,
		Transfer_Encoding,
		Upgrade,
		Via,
		Warning };
		
	GeneralHeaderTypes 	headerType;
	String				headerData;
	
	public HttpGeneralHeader() {
		headerData = new String();

	}
	public int parseHeader(String strToParse) throws IllegalArgumentException {
		int retCode = 0;
		StringTokenizer st = new StringTokenizer(strToParse, "=");
		String tempStr = st.nextToken();
		tempStr.trim();
		try {
			
			headerType = GeneralHeaderTypes.valueOf(tempStr.replace('-', '_'));
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
	
	public String getStrGenHeaderType() {
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