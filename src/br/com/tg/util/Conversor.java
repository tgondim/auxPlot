package br.com.tg.util;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class Conversor {

	public static Calendar stringToCalendar(String strData) throws ParseException {
		Calendar calendar = Calendar.getInstance();
		DateFormat formatter = new SimpleDateFormat("dd-mm-yyyy");
		calendar.setTime((Date)formatter.parse(strData));
		return calendar;
	}
	
}
