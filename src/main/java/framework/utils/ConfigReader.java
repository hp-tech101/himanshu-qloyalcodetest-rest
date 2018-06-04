package framework.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class ConfigReader {

	public static void PopulateSettings() throws IOException {
		ConfigReader reader = new ConfigReader();
		reader.readTestConfig();
		reader.readEnvConfig();
	}
	
	private void readTestConfig() throws IOException {
		Properties p = new Properties();
		//p.load(getClass().getResourceAsStream("./resources/test.properties"));
		File initialFile = new File("./resources/test.properties");
		if(initialFile.exists()) {
			System.out.println("file found - test.properties");
			p.load(new FileInputStream(initialFile));
		} else {
			System.out.println("file not found - test.properties");
		}
		
		
		Settings.app = p.getProperty("app");
		Settings.reportPath = p.getProperty("reportPath");
		Settings.LogPath = p.getProperty("LogPath");
		Settings.BrowserType = p.getProperty("BrowserType");
		Settings.htmlReport = p.getProperty("htmlReport");
		Settings.jsonSchema = p.getProperty("jsonSchema");
	}
	
	private void readEnvConfig() throws IOException {
		Properties p = new Properties();
		//p.load(getClass().getResourceAsStream("./resources/test.properties"));
		File initialFile = new File("./resources/env.properties");
		if(initialFile.exists()) {
			System.out.println("file found - env.properties");
			p.load(new FileInputStream(initialFile));
		} else {
			System.out.println("file not found - env.properties");
		}
		String env=p.getProperty("currEnv");
		Settings.hostRestApi = p.getProperty("hostRestApi_"+env);
		
	}
}
