package MimeContent;
import java.net.URL;

public class MimeEntry 
{
	URL 	myUrl;
	Mime 	mime;
	
	public MimeEntry(URL myUrl, Mime mime) 
	{
		this.myUrl = myUrl;
		this.mime = mime;
	}
	
	public MimeEntry(StringBuffer url, String mimeType, String mimeSubType)
	{
		try
		{
			if ( !url.toString().startsWith("http://") )
				url.insert(0, "http://");
			
			myUrl = new URL(url.toString());
			
			mime = new Mime(new MimeType(mimeType), new MimeSubtype(mimeSubType));
		}
		catch (Exception e)
		{
			
		}
	}
	
	public String getURL()
	{
		return myUrl.toString();
	}
	
	public Mime getMime()
	{
		return mime;
	}
	
	public boolean isValidEntry () 
	{
		return mime.isValidMime();
	}
}
