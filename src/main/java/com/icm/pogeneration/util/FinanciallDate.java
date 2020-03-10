package com.icm.pogeneration.util;

import java.util.Calendar;
import java.util.Date;

/**
 * 
 * @author raju
 * Get Financial Session Year
 */
public class FinanciallDate {

	private static final int FIRST_FISCAL_MONTH = Calendar.APRIL;// STart from [ 0 to 11 ]

	private Calendar calendarDate;

	public FinanciallDate(Calendar calendarDate) {
		this.calendarDate = calendarDate;
	}

	public FinanciallDate(Date date) {
		this.calendarDate = Calendar.getInstance();
		this.calendarDate.setTime(date);
	}

	public int getFiscalMonth() {
		int month = calendarDate.get(Calendar.MONTH);
		int result = ((month - FIRST_FISCAL_MONTH - 1) % 12) + 1;
		if (result < 0) {
			result += 12;
		}
		return result;
	}

	public int getFiscalYear() {
		int month = calendarDate.get(Calendar.MONTH);
		int year = calendarDate.get(Calendar.YEAR);
		return ((month > FIRST_FISCAL_MONTH) ? year : (year - 1));
	}

	public int getCalendarYear() {
		return calendarDate.get(Calendar.YEAR);
	}

	/*public static void main(String[] args) {
		getFinancialDate(Calendar.getInstance());
		// displayFinancialDate(setDate(2017, 3, 1));
	}*/

	private static Calendar setDate(int year, int month, int day) {
		Calendar calendar = Calendar.getInstance();
		calendar.set(Calendar.YEAR, year);
		calendar.set(Calendar.MONTH, month);
		calendar.set(Calendar.DAY_OF_MONTH, day);
		return calendar;
	}

	public static String getFinancialDate(Calendar calendar) {
		FinanciallDate fiscalDate = new FinanciallDate(calendar);
		int year = fiscalDate.getFiscalYear();
		return (year + "-" + (year + 1));
	}
}
