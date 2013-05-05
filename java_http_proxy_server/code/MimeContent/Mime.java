package MimeContent;

public class Mime 
{
	MimeType 	mimeType;
	MimeSubtype mimeSubtype;

	public Mime(String mime)
	{
		String[] mimeDetails = mime.trim().split("/");
		this.mimeType = new MimeType(mimeDetails[0]);
		this.mimeSubtype = new MimeSubtype(mimeDetails[1]);
	}
	
	public Mime(String mimeType, String mimeSubtype)
	{
		this.mimeType = new MimeType(mimeType);
		this.mimeSubtype = new MimeSubtype(mimeSubtype);
	}
	
	public Mime(MimeType mimeType, MimeSubtype mimeSubtype) 
	{
		this.mimeType = mimeType;
		this.mimeSubtype = mimeSubtype;
	}
	
	public boolean equals(Mime anotherMime)
	{
		if (this.mimeType.getMimeType().equalsIgnoreCase(anotherMime.mimeType.getMimeType()) &&
				this.mimeSubtype.getMimeSubType().equalsIgnoreCase(anotherMime.mimeSubtype.getMimeSubType()) )
			return true;
		
		return false;
	}
	
	public String getMimeType()
	{
		return mimeType.getMimeType();
	}
	
	public boolean isValidMime(MimeType mimeType, MimeSubtype mimeSubtype)
	{
		return (mimeType.isValidMimeType()&& mimeSubtype.isSubtypeValidForMimeType(mimeType, mimeSubtype.getMimeSubType()));
	}
	
	public boolean isValidMime()
	{
		return (mimeType.isValidMimeType()&& mimeSubtype.isSubtypeValidForMimeType(mimeType, mimeSubtype.getMimeSubType()));
	}
}
