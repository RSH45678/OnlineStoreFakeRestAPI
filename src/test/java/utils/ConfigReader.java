package utils;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class ConfigReader {
 
	Properties properties;
	private static final String CONFIG_FILE_PATH=".\\src\\test\\resources\\config.properties";
	
	//user defined method which will read data from properties file and return the data.
	
	public ConfigReader()
	{
		properties= new Properties();
		try (FileInputStream fileInputStream= new FileInputStream(CONFIG_FILE_PATH)) {
			properties.load(fileInputStream);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new RuntimeException("Failed to load config.properties file");
		}		
	}
	
	public String getProperty(String key)
	{
		return properties.getProperty(key);
	}
	
	public int getIntProperty(String key)
	{
		return Integer.parseInt(properties.getProperty(key));
	}
}
