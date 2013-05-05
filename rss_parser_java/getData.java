import java.net.*;
import java.io.*;
import javax.xml.parsers.*;
import org.w3c.dom.*;
import java.util.ArrayList;


public class getData
{
    
    /*
      URL: URL FROM WHERE DATA IS TO BE FETCHED
      XMLDOM: OBJECT IN WHICH DATA IS RETRIEVED
      NEWS: ARRAYLIST OF NEWSOBJECT
    */

    String url;
    Document xmlDOM; 
    ArrayList<newsObject> news; 

    /*
      CONSTRUCTOR OF CLASS getDATA...
    */

    public getData(String feedUrl)
    {
	url = new String(feedUrl);
	xmlDOM = null;
	news = new ArrayList<newsObject>();
    }
    
    /*
      FETCHDATA FUNCTION:
      HEART OF WHOLE THING:
      PARAMETER: COMMA DELIMITED FILTER STRING
                 FLAG FOR ALL OR ANY SELECTION 
      RETURNS: ARRAYLIST OF NEWS ITEMS WHICH CAN BE RENDERED
               ON HTML PAGE. 
		 
     OPENS THE URL CONNECTION...
     READ THE DATA AND CREATE A DOM OBJECT
     READ ITEM... EACH ITEM CONTimport javax.servlet.*;
import javax.servlet.http.*;AINS TITLE, LINK, DESCRIPTION ETC...
     READ EACH OF THEM AND SEE IF IT IS OF MY INTEREST..
     IF YES THEN ADD TO NEWSOBJECT 
     AT THE END OF ITEM .. ADD ITEM TO ARRAYLIST NEWS...

     ALSO INTEGRATE THE FILTER LOGIC IN THIS FUNCTION. 
     */
    public ArrayList<newsObject> fetchData(String filter, boolean all) 
    {
	boolean filterOn = false;
	String[] keywords = null;
	String splitPattern = "[+]";
	
	try
	{
	    URL rssData = new URL(url);
	    DocumentBuilderFactory docBuilderFactory =   DocumentBuilderFactory.newInstance();
	    DocumentBuilder docBuilder = docBuilderFactory.newDocumentBuilder();
	    xmlDOM = docBuilder.parse ( rssData.openStream() );
	}
	catch (Exception e){
	    e.printStackTrace();
	}

	// Have search terms been specified?
	if (filter.trim().length() > 0)
		filterOn = true;

	if (filterOn)
	{
		// Process the keywords
		if (! filter.equalsIgnoreCase(""))
		{
		    filter = filter.toLowerCase();
		    keywords = filter.split(splitPattern);
		}
	}

	NodeList itemNodeList = xmlDOM.getElementsByTagName( "item" );

	for (int i = 0; i<  itemNodeList.getLength();  i++ ) 
	{
	    Node item = itemNodeList.item( i );
	    NodeList itemContentList = item.getChildNodes();
	    newsObject newsItem = new newsObject(); 
	 
	    for (int j = 0; j< itemContentList.getLength();  j++ ) 
	    {
		Node content =  itemContentList.item( j );

		if (content.getNodeName().equals( "title" ) ) 
		{
		    Node child = content.getFirstChild();

		    if (!filterOn || passFilter(keywords, all, child.getNodeValue()))
		    {
			newsItem.setTitle(child.getNodeValue());
		    }
		    else
		    {
			break;
		    }
		}
		else if (content.getNodeName().equals( "description" ) ) 
		{
		    Node child = content.getFirstChild();
		    newsItem.setDescription(child.getNodeValue());
		}
		else if (content.getNodeName().equals( "link" ) ) 
		{
                    Node child = content.getFirstChild();
                    newsItem.setLink(child.getNodeValue());
                }
	    }

	    if (newsItem.getTitle() != null)
		 news.add(newsItem);
	}

	return news;
    }

    public boolean passFilter(String[] keywords, boolean all, String title )
    {
	if (all)
	{
	    for (int i = 0; i< keywords.length ; i++ )
	    {
		// ALL: We return false if any of the keywords are missing
		if ( title.toLowerCase().indexOf(keywords[i]) == -1)
		{
		    return false;
		}
	    }

	    return true;
	}
	else // if any
	{
	    for (int i = 0; i< keywords.length ; i++ )
	    {
		// ANY: We return true if any of the keywords are present
		if (title.toLowerCase().indexOf(keywords[i]) != -1 )
		{
		    return true;
		}
	    }

	    return false;
	}
    }
}
