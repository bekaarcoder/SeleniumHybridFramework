package com.hybridframework.utilities;

import java.io.File;
import java.io.FileInputStream;
import java.util.Properties;

public class ReadConfig {
	
	Properties prop;
	
	public ReadConfig() {
		File src = new File("./Configuration/config.properties");
		
		try {
			FileInputStream fis = new FileInputStream(src);
			prop = new Properties();
			prop.load(fis);
		} catch(Exception e) {
			System.out.println("Exception: " + e.getMessage());
		}
	}
	
	public String getBaseUrl() {
		String url = prop.getProperty("baseUrl");
		return url;
	}
	
	public String getUsername() {
		String username = prop.getProperty("username");
		return username;
	}
	
	public String getPassword() {
		String password = prop.getProperty("password");
		return password;
	}
	
	public String getChromeDriver() {
		String driver = prop.getProperty("chromepath");
		return driver;
	}
	
	public String getFirefoxDriver() {
		String driver = prop.getProperty("firefoxpath");
		return driver;
	}

}
