import java.io.File;
import java.io.FileNotFoundException;
import java.text.*;
import java.util.*;
import java.util.logging.*;

public class Proxy 
{
	static Logger 		logger = null;
	static FileHandler 	fh = null; 
	
	public static void main(String[] args) 
	{
		if (areArgsValid(args))
		{	
			initiateLogger();
			String configFilePath = args[0];				// Stores the path of the configuration file
			int portNo = Integer.parseInt(args[1]);			// Store the port where the proxy receives requests
		
			// Initiate the proxy and monitor the specified port
			new ProxyEngine(portNo, configFilePath);		
		}
	}

	// Initiates the logger
	public static void initiateLogger()
	{
		try
		{
			// We must add our log to the global log manager
			LogManager logManager = LogManager.getLogManager();
			logger = Logger.getLogger("Helper.log");
			logManager.addLogger(logger);
			
			// Create a log file with today's date as the name in append mode
			DateFormat dateFormat = new SimpleDateFormat("yyyyMMdd");
			Date date = new Date();
			FileHandler fh = new FileHandler(dateFormat.format(date) + ".xml", true);
			logger.addHandler(fh);
		}
		catch (Exception e)
		{
			System.out.println(e.getMessage());
		}
	}
	
	// 
	public static boolean areArgsValid(String[] args)
	{
		File f = null;
		
		// Check that we have enough arguments
		if (args.length != 2)
		{
			System.out.println("Insufficient arguments! Please specify a configuration file followed by the port address.");
			System.out.println("Example: \"Proxy config.txt 50000\"");
			return false;
		}
		else	// Read the arguments and check if they are in the right format
		{	
			try
			{
				f = new File(args[0]);

				if ( !f.canRead() )
					throw new FileNotFoundException();
				
				Integer.parseInt(args[1]);
			}
			catch (FileNotFoundException fe)
			{
				String filePath = null;
				
				if (f != null)
					filePath = f.getAbsolutePath();
				
				System.out.println("ERROR: File " + filePath + " does not exist or is not readable.");
				
				return false;
			}
			catch (Exception e)
			{
		        System.out.println("Invalid Arguments! Please ensure your arguments are in the proper format.");
				System.out.println("Example: \"Proxy config.txt 50000\"");		        
		        return false;
			}
		}
		
		return true;
	}
}
