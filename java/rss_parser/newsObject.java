    /*
      NEWSOBJECT IS NEWS ITEM MADE FROM ONE NEWS  
      TITLE: STANDS FOR TITLE
      DESCRIPTION: DESCRIPTION
      SOURCE: SOURCE OF NEWS ITEM. THIS WILL BE USEFUL 
              IN RENDERING ON HTML.
    */

    public class newsObject
    {
	private String title = null;
	private String description = null;
	private String link = null;

	/*
	  CONSTRUCTOR WITH VALUES 
	*/

	public newsObject()
	{
	}

	public void setTitle(String title)
	{
		this.title = title;
	}

	public void setDescription(String description)
	{
		this.description = description;
	}

	public void setLink(String link)
	{
		this.link = link;
	}

	public String getTitle()
	{
		return title;		
	}

	public String getDescription()
	{
		return description;		
	}

	public String getLink()
	{
		return link;		
	}
    }
