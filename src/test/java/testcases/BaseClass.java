package testcases;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintStream;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

import org.testng.annotations.BeforeClass;

import io.restassured.RestAssured;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import routes.Routes;
import utils.ConfigReader;

public class BaseClass {

	ConfigReader configReader;
	
	//For logging
	RequestLoggingFilter requestLoggingFilter;
	ResponseLoggingFilter responseLoggingFilter;
	
	@BeforeClass
	public void setup() throws FileNotFoundException
	{
		RestAssured.baseURI=Routes.BASE_URL;
		configReader= new ConfigReader();
		
		// Setup filters for logging
		FileOutputStream fos = new FileOutputStream(".\\logs\\test_logging.log");
		PrintStream log = new PrintStream(fos, true);
		requestLoggingFilter = new RequestLoggingFilter(log);
		responseLoggingFilter = new ResponseLoggingFilter(log);
		RestAssured.filters(requestLoggingFilter, responseLoggingFilter);
	}
	
	//Helper method to check if a list is sorted in descending order(products)
	
		boolean isSortedDescending(List<Integer> list)
		{
			for(int i=0;i<list.size()-1;i++)
			{
				if(list.get(i)<list.get(i+1))
				{
					return false;
				}
			}
			return true;
		}
		
	//Helper method to check if a list is sorted in ascending order(products)
				
		boolean isSortedAscending(List<Integer> list)
		{
			for(int i=0;i<list.size()-1;i++)
			{
				if(list.get(i)>list.get(i+1))
				{
					return false;
				}
			}
			return true;
		}
		
	//Helper method to check dates fall within the specific range
	public static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd");//DateTimeFormatter is required to convert date into yyyy-mm-dd format.
    
    public boolean validateCartDatesWithinRange(List<String> cartDates, String startDate, String endDate) {
       
    	LocalDate start = LocalDate.parse(startDate, FORMATTER);//Here formatter will remove the timestamp from received date and parse method will convert date into local date format.
    	
        LocalDate end = LocalDate.parse(endDate, FORMATTER);

        for (String dateTime : cartDates) 
        	{
            LocalDate cartDate = LocalDate.parse(dateTime.substring(0, 10), FORMATTER);//eliminating timestamp using substring. "yyyy-mm-dd"<---- here string length is 10. thats why substring(0,10) is taken.
            if (cartDate.isBefore(start) || cartDate.isAfter(end)) {
                return false; // Immediately return false if any cart date is out of range
            }
        }
        return true; // All dates are within range
    }
}
