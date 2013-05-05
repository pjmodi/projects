import org.apache.axis.client.Call;
import org.apache.axis.client.Service;
import org.apache.axis.encoding.XMLType;
import org.apache.axis.utils.Options;
import org.apache.axis.soap.SOAPConstants;
import javax.xml.rpc.ParameterMode;
import java.io.*;

public class BookClientDynamic
{
   public static void main(String[] args) throws Exception 
   {   
      String endpoint = "http://swedishchef.cs.umn.edu:8080/axis/BookWebService.jws";
      Call call = new Call(endpoint);
      call.setSOAPVersion(SOAPConstants.SOAP12_CONSTANTS);

      String method = args[0];
      Object params[]; 

		if(method.equals("getBooks"))
		{
			params = new Object[] {args[1]}; 
		   String[] result = (String[]) call.invoke(method, params);
			PrintMultiResult("Books by " + params[0], result); 
		}
		else if(method.equals("getISBN"))
		{
			params = new Object[] {args[1]}; 
		   String result = (String) call.invoke(method, params);
			printSingleResult("ISBN of Title: " + params[0], result); 
		}
		else if(method.equals("getTitle"))
		{
			params = new Object[] {args[1]}; 
		   String result = (String) call.invoke(method, params);
			printSingleResult("Title of ISBN: " + params[0], result);
		}		
		else if(method.equals("getTitles"))
		{
			String[] isbns = buildParams(args);
			params = new Object[] {isbns};
		   String[] result = (String[]) call.invoke(method, params);

			for(int i=0; i<result.length; i++)
				printSingleResult("Title of ISBN: " + args[i+1], result[i]);
		}
		else if(method.equals("getAuthorsByTitle"))
		{
			params = new Object[] {args[1]}; 
		   String[] result = (String[]) call.invoke(method, params);
			PrintMultiResult("Authors of " + params[0], result); 
		}	
		else if(method.equals("getPublisherByTitle"))
		{
			params = new Object[] {args[1]}; 
		   String result = (String) call.invoke(method, params);
			printSingleResult("Publisher of " + params[0], result); 
		}	
		else if(method.equals("getEditionByTitle"))
		{
			params = new Object[] {args[1]}; 
		   String result = (String) call.invoke(method, params);
			printSingleResult("Edition of " + params[0], result); 
		}	
		else if(method.equals("getPriceByTitle"))
		{
			params = new Object[] {args[1]}; 
		   String result = (String) call.invoke(method, params);
			printSingleResult("Price of " + params[0], result); 
		}	
		else if(method.equals("getPublicationYearByTitle"))
		{
			params = new Object[] {args[1]}; 
		   String result = (String) call.invoke(method, params);
			printSingleResult("Publication Year of " + params[0], result); 
		}	
		else if(method.equals("getAuthorsByISBN"))
		{
			params = new Object[] {args[1]}; 
		   String[] result = (String[]) call.invoke(method, params);
			PrintMultiResult("Authors of " + params[0], result); 
		}	
		else if(method.equals("getPublisherByISBN"))
		{
			params = new Object[] {args[1]}; 
		   String result = (String) call.invoke(method, params);
			printSingleResult("Publisher of " + params[0], result); 
		}	
		else if(method.equals("getEditionByISBN"))
		{
			params = new Object[] {args[1]}; 
		   String result = (String) call.invoke(method, params);
			printSingleResult("Edition of " + params[0], result); 
		}	
		else if(method.equals("getPriceByISBN"))
		{
			params = new Object[] {args[1]}; 
		   String result = (String) call.invoke(method, params);
			printSingleResult("Price of " + params[0], result); 
		}	
		else if(method.equals("getPublicationYearByISBN"))
		{
			params = new Object[] {args[1]}; 
		   String result = (String) call.invoke(method, params);
			printSingleResult("Publication Year of " + params[0], result); 
		}					
   }
   
   public static String[] buildParams(String[] args)
   {
		String[] params = new String[args.length - 1];

		for(int i=1; i<args.length; i++)
		{
			params[i-1] = args[i];
		}

		return params;
   }

   public static void printSingleResult(String msg, String result)
   {
   	System.out.println(msg.toUpperCase());
   	System.out.println("---");
   	System.out.println(result);
   }
   
   public static void PrintMultiResult(String msg, String[] result)
   {
   	System.out.println(msg.toUpperCase());
   	System.out.println("---");
   	
   	if (result.length == 0)
   		System.out.println("No entries found.");
   	else
   	 	for ( int k=0; k<result.length; k++ ) 
				System.out.println(result[k]);
   }
}
