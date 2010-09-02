package br.com.tg.guiUtil;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;

import br.com.tg.guiUtil.Formatter;

public class CalendarFormatter implements Formatter {
	private final static SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");

	@Override
	public String format(Object obj) {
		Calendar cal = (Calendar) obj;
		return formatter.format(cal.getTime());
	}

	@Override
	public String getName() {
		return "dataNascimento";
	}

	@Override
	public Object parse(String s) {
		Calendar cal = new GregorianCalendar();
		try {
			cal.setTime(formatter.parse(s));
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return cal;
	}
}