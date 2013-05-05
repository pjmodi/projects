package MimeContent;

public class MimeSubtype 
{
	String mimeSubtype;

	public MimeSubtype(String subtype) 
	{
		mimeSubtype = subtype;
	}
	
	public boolean isValidMimeSubtype(String subtype) 
	{
		if(		subtype.equals("html") ||
				subtype.equals("plain") ||
				subtype.equals("richtext") ||
				subtype.equals("x-setext") ||
				subtype.equals("enriched") ||
				subtype.equals("tab-separated-values") ||
				subtype.equals("sgml") ||
				subtype.equals("x-speech") ||
				subtype.equals("css") ||
				subtype.equals("gif") ||
				subtype.equals("x-bitmap") ||
				subtype.equals("x-png") ||
				subtype.equals("ief") ||
				subtype.equals("jpeg") ||
				subtype.equals("tiff") ||
				subtype.equals("rgb") ||
				subtype.equals("x-rgb") ||
				subtype.equals("g3fax") ||
				subtype.equals("x-xwindowdump") ||
				subtype.equals("x-pict") ||
				subtype.equals("x-portable-pixmap") ||
				subtype.equals("x-ms-bmp") ||
				subtype.equals("x-cmu-raster") ||
				subtype.equals("x-photo-cd") ||
				subtype.equals("cgm") ||
				subtype.equals("naplps") ||
				subtype.equals("x-cals") ||
				subtype.equals("fif") ||
				subtype.equals("x-mgx-dsf") ||
				subtype.equals("x-cmx") ||
				subtype.equals("wavelet") ||
				subtype.equals("vnd.dwg") ||
				subtype.equals("x-dwg") ||
				subtype.equals("vnd.dxf") ||
				subtype.equals("x-dxf") ||
				subtype.equals("vnd.svf") ||
				subtype.equals("x-sgi-bw") ||
				subtype.equals("x-sgi-rgba") ||
				subtype.equals("x-eps") ||
				subtype.equals("*")
				)
				return true;
		
		return false;
	}
	
	public boolean isSubtypeValidForMimeType(MimeType mimeType, String subtype) 
	{
		if (mimeType.getMimeType().equals("text")) 
		{
			if (subtype.equals("html") ||
				subtype.equals("plain") ||
				subtype.equals("richtext") ||
				subtype.equals("x-setext") ||
				subtype.equals("enriched") ||
				subtype.equals("tab-separated-values") ||
				subtype.equals("sgml") ||
				subtype.equals("x-speech") ||
				subtype.equals("css") ||
				subtype.equals("*")
				)
				return true;
		}
		else if (mimeType.getMimeType().equals("image")) 
		{
			if (subtype.equals("gif") ||
				subtype.equals("x-bitmap") ||
				subtype.equals("x-png") ||
				subtype.equals("ief") ||
				subtype.equals("jpeg") ||
				subtype.equals("tiff") ||
				subtype.equals("rgb") ||
				subtype.equals("x-rgb") ||
				subtype.equals("g3fax") ||
				subtype.equals("x-xwindowdump") ||
				subtype.equals("x-pict") ||
				subtype.equals("x-portable-pixmap") ||
				subtype.equals("x-ms-bmp") ||
				subtype.equals("x-cmu-raster") ||
				subtype.equals("x-photo-cd") ||
				subtype.equals("cgm") ||
				subtype.equals("naplps") ||
				subtype.equals("x-cals") ||
				subtype.equals("fif") ||
				subtype.equals("x-mgx-dsf") ||
				subtype.equals("x-cmx") ||
				subtype.equals("wavelet") ||
				subtype.equals("vnd.dwg") ||
				subtype.equals("x-dwg") ||
				subtype.equals("vnd.dxf") ||
				subtype.equals("x-dxf") ||
				subtype.equals("vnd.svf") ||
				subtype.equals("x-sgi-bw") ||
				subtype.equals("x-sgi-rgba") ||
				subtype.equals("x-eps") ||
				subtype.equals("*")
				)
				return true;
		}
		else if (mimeType.getMimeType().equals("*"))
		{
			if (subtype.equals("*"))
				return true;
		}
		
		return false;
	}
	
	public String getMimeSubType () 
	{
		return mimeSubtype;
	}
}

