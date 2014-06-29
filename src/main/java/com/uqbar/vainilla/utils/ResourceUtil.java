package com.uqbar.vainilla.utils;

import java.util.Locale;
import java.util.ResourceBundle;

public class ResourceUtil {

	private static ResourceBundle properties = ResourceBundle.getBundle("config",new Locale("es"));

	public static String getResourceString(String name){
		return properties.getString(name);
	}
	
	public static int getResourceInt(String name){
		return Integer.parseInt(properties.getString(name));
	}
	
	public static double getResourceDouble(String name){
		return Double.parseDouble(properties.getString(name));
	}
	
	
}
