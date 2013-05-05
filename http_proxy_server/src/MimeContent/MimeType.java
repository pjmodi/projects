package MimeContent;

public class MimeType 
{
	String mimeType;
	
	public MimeType (String mimeType) 
	{
		this.mimeType = mimeType;
	}
	
	public boolean isValidMimeType()
	{
		if ( mimeType.equals("text") || 
			 mimeType.equals("application") ||
			 mimeType.equals("image") ||
			 mimeType.equals("graphics") ||
			 mimeType.equals("drawing") ||
			 mimeType.equals("x-model") ||
			 mimeType.equals("x-world") ||
			 mimeType.equals("message") ||
			 mimeType.equals("workbook") ||
			 mimeType.equals("x-script") ||
			 mimeType.equals("x-form") ||
			 mimeType.equals("x-conference") ||
			 mimeType.equals("video") ||
			 mimeType.equals("x-music") ||
			 mimeType.equals("audio") ||
			 mimeType.equals("vector") ||
			 mimeType.equals("*")
			)
			return true;
		
		return false;
	}
	
	public boolean isValidMImeType (String mimeType) 
	{
		if ( 	mimeType.equals("text") || 
				 mimeType.equals("application") ||
				 mimeType.equals("image") ||
				 mimeType.equals("graphics") ||
				 mimeType.equals("drawing") ||
				 mimeType.equals("x-model") ||
				 mimeType.equals("x-world") ||
				 mimeType.equals("message") ||
				 mimeType.equals("workbook") ||
				 mimeType.equals("x-script") ||
				 mimeType.equals("x-form") ||
				 mimeType.equals("x-conference") ||
				 mimeType.equals("video") ||
				 mimeType.equals("x-music") ||
				 mimeType.equals("audio") ||
				 mimeType.equals("vector") ||
				 mimeType.equals("*")
				)
				return true;
			
		return false;
	}
	
	/*
	public MimeType[] getAllMimeTypes () 
	{
		MimeType[] mimeTypes = new MimeType[] {
				new MimeType("text"),
				new MimeType("application"),
				new MimeType("image"),
				new MimeType("graphics"),
				new MimeType("*")
				};
		
		return mimeTypes;
	}
	*/
	
	public String getMimeType () 
	{
		return mimeType;
	}
}
