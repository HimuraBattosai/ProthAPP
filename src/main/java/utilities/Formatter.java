package utilities;

import java.sql.Time;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;

public class Formatter 
{
	public static Date getDateFromString(String dateString, String dateFormat)
	{
	    SimpleDateFormat format = new SimpleDateFormat(dateFormat);
	    java.util.Date parsed = null;
	    try 
	    {
	        parsed = format.parse(dateString);
	    } catch (ParseException e) {
	        e.printStackTrace();
	    }
	    Date date = new Date(parsed.getTime());
	    return date;
	}
	
	public static LocalDateTime getTimeFromString(String timeString, String timeFormat)
	{
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern(timeFormat);
		LocalDateTime time = LocalDateTime.parse(timeString, formatter);
		
		return time;
	}

}
