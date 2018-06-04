package base;


import java.io.IOException;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeSuite;

import framework.utils.ConfigReader;

public class BaseTest extends UtilityMethods {
	
	@BeforeSuite
	public void setupSuite() {
		System.out.println("@BeforeSuite");
		try {
			ConfigReader.PopulateSettings();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	
	@BeforeClass
	public void setup() {
		System.out.println("@BeforeClass");
		try {
			ConfigReader.PopulateSettings();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
}
