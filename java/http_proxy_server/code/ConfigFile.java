import java.io.*;
import java.util.ArrayList;

import MimeContent.MimeEntry;

public class ConfigFile 
{
	static final String 	WILDCARD = "*";
	static final String		COMMENT	= "#";
	
	File 					f;
	
	public ConfigFile(String filePath) 
	{
		f = new File(filePath);
	}
	
	public Object[] getEntries()
	{
		ArrayList 		entries = new ArrayList();		
		String		 	strLine = null;
				
		try 
		{
			FileReader fr = new FileReader(f);
	    	BufferedReader br = new BufferedReader(fr);
	    	
	    	// While there are lines that need to be parsed in the file
	    	while ( (strLine = br.readLine() ) != null )
	    	{  			    		
	    		StringBuffer 	url = null;
	    		MimeEntry		entry = null;
	    		
	    		// We have to ignore commented lines or blank lines
        		if ( strLine.trim().startsWith(COMMENT) || strLine.trim().length() == 0 )
        			continue;
        		
    			// We split a valid entry by white-spaces
    			String[] tokens = strLine.trim().toLowerCase().split("\\s");
    			
    			// For every token in the line
    			for (int x=0; x<tokens.length; x++)
    		    {
    				// Ignore blank tokens
    				if ( tokens[x].trim().length() == 0)
    					continue;
    				
    				// Validate and store URL's
    				if (  tokens[x].trim().startsWith("http") || tokens[x].trim().startsWith("www") )
    				{	
    					url = new StringBuffer(tokens[x].trim());
    					// Strip out trailing forward slash, if any
    					if (url.toString().endsWith("/"))
    						url.replace(url.length() - 1, url.length(), "");

						// If the URL is the only thing on the line... add it
						if (tokens.length == 1)
							entry = new MimeEntry(url, WILDCARD, WILDCARD);
    				}
    				
    				else if ( tokens[x].trim().equalsIgnoreCase(WILDCARD) && url != null)
    					entry = new MimeEntry(url, WILDCARD, WILDCARD);
					
    				else if ( ( tokens[x].trim().indexOf("/") >= 0 ) && url != null )
    				{
						String[] mimeData = tokens[x].split("/");
						entry = new MimeEntry(url, mimeData[0], mimeData[1]);
					}
 				
					if (entry != null && entry.isValidEntry())
						entries.add(entry);
				}	
	        }
	    } 
		catch (FileNotFoundException e) 
	    {
	        //e.printStackTrace();
	    } 
	    catch (IOException e) 
	    {
	        //e.printStackTrace();
	    }

    	entries.trimToSize();
    	return (Object[]) entries.toArray();
	}
}
