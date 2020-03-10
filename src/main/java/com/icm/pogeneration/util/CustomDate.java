package com.icm.pogeneration.util;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class CustomDate {

	   // Get current date format like 'February 20th, 2018'
		public static String getPODate(String dates){ 
			Date date=null;
			String currentdate = null;
			try { 
				date = new SimpleDateFormat("MM/dd/yyyy").parse(dates);
				 SimpleDateFormat tmp = new SimpleDateFormat("MMMM d");

				    currentdate = tmp.format(date);
				    currentdate = currentdate.substring(0, 1).toUpperCase() + currentdate.substring(1);

				    if(date.getDate()>10 && date.getDate()<14)
				    	currentdate = currentdate + "th, ";
				    else{
				        if(currentdate.endsWith("1")) currentdate = currentdate + "st, ";
				        else if(currentdate.endsWith("2")) currentdate = currentdate + "nd, ";
				        else if(currentdate.endsWith("3")) currentdate = currentdate + "rd, ";
				        else currentdate = currentdate + "th, ";
				    }

				    tmp = new SimpleDateFormat("yyyy");
				    currentdate = currentdate + tmp.format(date);
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}  
		   

		    return currentdate;
		}

		// Get current date format like 'dd-MM-yyyy'
		public static String getCurrentDate(){
			 DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
			 Date date = new Date();
			 return dateFormat.format(date);
		}
		
		// Get current date format like 'mm/dd/yyyy'
		public static String getCurrentDates(String date){
			SimpleDateFormat format1 = new SimpleDateFormat("MM/dd/yyyy");
    	    SimpleDateFormat format2 = new SimpleDateFormat("dd-MM-yyyy");
    	    Date dates = null;
			try {
				dates = format1.parse(date);
			} catch (ParseException e) {
				e.printStackTrace();
			}
			return format2.format(dates);
		}
				
		// Get current date format like 'ddMMyyyy'
		public static String getFileCurrentDate(String date) {
			SimpleDateFormat format1 = new SimpleDateFormat("MM/dd/yyyy");
    	    SimpleDateFormat format2 = new SimpleDateFormat("ddMMyyyy");
    	    Date dates = null;
			try {
				dates = format1.parse(date);
			} catch (ParseException e) {
				e.printStackTrace();
			}
			return format2.format(dates);
		}
		
		// get download link expire date within 360 days
		public static String getExpireDate() {
		     DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
			 Date date1 = new Date();
			 Calendar c = Calendar.getInstance(); 
			 c.setTime(date1); 
			 c.add(Calendar.DATE, 360);
			 date1 = c.getTime();
			 return dateFormat.format(date1);
		}
		
		public static String validateDate(String expDate) throws ParseException {
			String status = null;
			SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
	        Date date1 = sdf.parse(getCurrentDate());
	        Date date2 = sdf.parse(expDate);
	
	        if (date1.compareTo(date2) > 0) {
	            return status="exp";
	        } else if (date1.compareTo(date2) < 0) {
	            return status="val";
	        } else if (date1.compareTo(date2) == 0) {
	            return status="eql";
	        }
			return status;
		}
}
