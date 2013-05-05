import axis.BookWebService.*;

public class BookClientStub {
    public static void main(String [] args) throws Exception {
	String method = args[0];
	BookWebServicePortTypeServiceLocator locator = new BookWebServicePortTypeServiceLocator();
        axis.BookWebService.BookWebServicePortType stub = locator.getBookWebService();
	
	System.out.println("Argument is:  "+ args[1]);
    	if (method.equals("getBooks")) {
		String result[];
		result = stub.getBooks(args[1]);
		System.out.println("Number of books from this Author: " + result.length);
		for (int i = 0; i< result.length ; i++){
			System.out.println("Book " + i+1 + ":" + result[i]);
		}        
    	}else if (method.equals("getISBN")){
		String isbn = stub.getISBN(args[1]);
		System.out.println("ISBN number of Book " + args[1] + " is " + isbn);
	}else if (method.equals("getTitle")){
		String title = stub.getTitle(args[1]);
		System.out.println("Title of book with  ISBN " + args[1] +" is " + title);
	}else if (method.equals("getTitles")) {
		String titles[];
		String isbn[];
		int numargs = Integer.parseInt(args[1]);
		isbn = new String[numargs];
		for (int i =0 ; i < numargs; i++){
		    isbn[i] = args[i+2];
		}
		titles = stub.getTitles(isbn); 
		System.out.println("Books ISBN :\t\t  Books Title");
		for (int i = 0; i < titles.length ; i++){
		    System.out.println(isbn[i] + ":\t\t" + titles[i]);
                }
	}else if (method.equals("getAuthorsByTitle")){
		String authors[];
		String title = args[1];
		authors = stub.getAuthorsByTitle(title);
		System.out.println("Number of authors of book  with Title: " + args[1] + " is " + authors.length);
                for (int i = 0; i< authors.length ; i++){
		    System.out.println("Author " + (i+1) + ":" + authors[i]);
                }
	}else if (method.equals("getPublisherByTitle")){
		String publisher = stub.getPublisherByTitle(args[1]);
		System.out.println("Publisher of Book tiled " + args[1] + " is " + publisher);
	}else if (method.equals("getEditionByTitle")){
		String edition = stub.getEditionByTitle(args[1]);
		System.out.println("Edition is :" + args[1]);
		System.out.println(edition);	
	}else if (method.equals("getPriceByTitle")){
		String price = stub.getPriceByTitle(args[1]);
		System.out.println("Price for book titled " + args[1] + "is " + price);
	} else if (method.equals("getPublicationYearByTitle")) {
		String year = stub.getPublicationYearByTitle(args[1]);
		System.out.println("PublicationYear for book titled " + args[1] + "is " + year);
	} else if (method.equals("getAuthorsByISBN")) {
	        String authors[];
		String isbn = args[1];
		authors = stub.getAuthorsByISBN(isbn);
		for (int i = 0; authors != null && i< authors.length ; i++){
                    System.out.println("Author Name " + i + ":" + authors[i]);
                }
	} else if (method.equals("getPublisherByISBN")){
		String publisher = stub.getPublisherByISBN(args[1]);
		System.out.println("Publisher for book ISBN" + args[1] + " is " + publisher);
	}else if (method.equals("getEditionByISBN")){
		String edition = stub.getEditionByISBN(args[1]);
		System.out.println("Edition for book ISBN: " + args[1] + " is " + edition);
	}else if (method.equals("getPriceByISBN")){
		String price = stub.getPriceByISBN(args[1]);
		System.out.println("Price for book with ISBN " + args[1] + " is " + price);
	}else if (method.equals("getPublicationYearByISBN")){
		String year = stub.getPublicationYearByISBN(args[1]);
		System.out.println("PublicationYear for book ISBN " + args[1] + " is " + year);
	}
   }
}
