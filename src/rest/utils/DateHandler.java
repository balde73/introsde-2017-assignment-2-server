package rest.utils;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateHandler {
	
	Date date;
	
	public DateHandler(String startDate) {
		super();
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		if(startDate==null)
			this.date = null;
		try {
			this.date = df.parse(startDate);
		} catch (ParseException e) {
			e.printStackTrace();
		}
	}
	
	public boolean isValid() {
		return this.date!=null;
	}
 
	public boolean isAfter(Date date) {
		if(date==null)
			return false;
		return this.date.after(date);
	}
	
	public boolean isBefore(Date date) {
		if(date==null)
			return false;
		return this.date.before(date);
	}
}
