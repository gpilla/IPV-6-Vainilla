package com.uqbar.vainilla.utils;

import java.util.ResourceBundle;

public class ResourceUtil {

	private static ResourceBundle properties = ResourceBundle.getBundle("config");

	public static String getResourceString(String name){
		return properties.getString(name);
	}
	
	public static int getResourceInt(String name){
		return Integer.parseInt(properties.getString(name));
	}
	
	
}
