package com.android.notification.common;

import java.io.IOException;
import java.util.Properties;

/**
 * 
 * @author marciocamurati
 *
 */
public class ApplicationProperties {
	
	private static Properties properties = null;
	
	static {
		properties = new Properties();
		try {
			properties.load(ApplicationProperties.class.getResourceAsStream("/application.properties"));
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}
	
	public static String get(String key) {
		return properties.getProperty(key);
	}

	public static int getAsInt(String key) {
		return Integer.parseInt(get(key));
	}

}
