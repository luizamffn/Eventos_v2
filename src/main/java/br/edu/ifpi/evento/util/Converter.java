package br.edu.ifpi.evento.util;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Converter {
	public static String dateToStrFormatoBrasileiro(Date data) {
		if (data == null) {
			return "";
		}
		SimpleDateFormat dt = new SimpleDateFormat("dd/MM/yyyy");
		return dt.format(data);
	}
	
	public static String datetimeToStr(Date data) {
		if (data == null)
			return "";
		SimpleDateFormat dt = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
		return dt.format(data);
	}
}
