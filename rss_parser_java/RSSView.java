import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.util.ArrayList;

public class RSSView extends HttpServlet 
{
    public static final String CNN_URL = "http://rss.cnn.com/rss/cnn_world.rss";
    public static final String ABC_URL = "http://feedproxy.google.com/AbcNews_International";
    public static final String NBC_URL = "http://rss.msnbc.msn.com/id/3032091/device/rss/rss.xml";
    public static final String BBC_URL = "http://newsrss.bbc.co.uk/rss/newsonline_uk_edition/world/rss.xml";

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException
    {
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        out.println("<html>");
        out.println("<head>");
        out.println("<title>CSci 5131: Assignment 5</title>");
        out.println("</head>");
        out.println("<body>");

        String[] FeedItems = request.getParameterValues("RssFeeds");
	String filterKeywords = request.getParameter("searchwords");
	String filterMode = request.getParameter("searchmode");

        int noOfItems = 0;     
	if (FeedItems != null)        
	        noOfItems = FeedItems.length; 

	if (filterKeywords == null)        
		filterKeywords = "";
	else
	{
		filterKeywords = filterKeywords.trim().replaceAll("\\s+", "+");
	}

	if (filterMode == null)        
		filterMode = "";

	boolean all = filterMode.equals("ALL") ? true : false;

	if (noOfItems == 0)
	{
		out.println("No RSS Feeds selected");
	}        
        else
        {
		// For each Feed that has been selected
		for(int i=0; i<noOfItems; i++)
		{
			// Set the appropriate URL
			String url = null;
			if (FeedItems[i].equals("CNN"))
				url = CNN_URL;
			else if (FeedItems[i].equals("ABC"))
				url = ABC_URL;
			else if (FeedItems[i].equals("NBC"))
				url = NBC_URL;
			else if (FeedItems[i].equals("BBC"))
				url = BBC_URL;

			if (url != null)
			{
				// Get the RSS Feed items
				getData x = new getData(url);
				ArrayList<newsObject> news = x.fetchData(filterKeywords, all);

				// Print the feed items
				printFeed(FeedItems[i], news, out);
			}
		}
	}
       
        out.println("</body>");
        out.println("</html>");
    }

 
    public void printFeed(String feedName, ArrayList<newsObject> news, PrintWriter out)
    {
	out.println("<b>RSS Feed from " + feedName + ":</b><br>");

	for (newsObject newsItem:news)
	{
		out.println("<p>");
		out.println("Title: <a href='" + newsItem.getLink() + "'>" + newsItem.getTitle() + "</a><br>");
		out.println("Description: " + newsItem.getDescription() + "<br>");
		out.println("</p>");
	}

	out.println("<br><hr><br><br>");
    }
}
