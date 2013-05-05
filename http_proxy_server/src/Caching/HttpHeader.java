package Caching;


abstract public class HttpHeader {
	public void initialize(String header) {
		
	}
	public abstract int parseHeader(String strToParse);
}
