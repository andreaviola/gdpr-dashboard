package it.ing.av.gdpr.gdprdashboard.util;

import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtil {

	public static final SimpleDateFormat sdfDate = new SimpleDateFormat("dd/MM/yyyy");
	public static final SimpleDateFormat sdfDatetime = new SimpleDateFormat("dd/MM/yyyy HH:mm");
	public static final SimpleDateFormat sdfMonthYear = new SimpleDateFormat("MM/yyyy");

	public static final SimpleDateFormat sdfyyyyMMddHHmmss = new SimpleDateFormat("yyyyMMddHHmmss");
	public static final SimpleDateFormat sdfyyyyMMdd = new SimpleDateFormat("yyyyMMdd");
	public static final SimpleDateFormat sdfyyyyMM = new SimpleDateFormat("yyyyMM");
	// public static final SimpleDateFormat sdfyyyyMMddImport = new
	// SimpleDateFormat("yyyy-MM-dd");
	// public static final SimpleDateFormat sdfyyyyMMddHHmmssImport = new
	// SimpleDateFormat("yyyy-MM-dd-HHmmss-SSS");

	// public static final SimpleDateFormat sdfyyyyMMddXml = new
	// SimpleDateFormat("yyyy-MM-dd");
	// public static final SimpleDateFormat sdfyyyyMMddHHmmssXml = new
	// SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");

	public static final String dateToString(Date date, SimpleDateFormat pattern) {
		try {
			return pattern.format(date);
		} catch (Exception e) {
		}
		return null;
	}

	public static final Date stringToDate(String dateStr, SimpleDateFormat pattern) {
		try {
			return pattern.parse(dateStr);
		} catch (Exception e) {
		}
		return null;
	}

	public static final String stringToString(String date, SimpleDateFormat patternIn, SimpleDateFormat patternOut) {
		Date data = stringToDate(date, patternIn);
		return dateToString(data, patternOut);
	}
	
	public static final Date dateToDate(Date date, SimpleDateFormat patternIn, SimpleDateFormat patternOut) {
		String data = dateToString(date, patternIn);
		return stringToDate(data, patternOut);
	}
	
}
