package framework.utils;

import java.util.HashMap;
import java.util.Map;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

public class ExtentUtil {
	static ExtentReports extentReports=null;
	static Map<Integer, ExtentTest> extentTestMap = new HashMap<>();
	
	public static ExtentReports createReporter(String fileName) {
		
		System.out.println("filename = "+fileName);
		//HTML Reporter
		ExtentHtmlReporter extentHtmlReporter = null;
		extentHtmlReporter = new ExtentHtmlReporter(fileName);
		extentHtmlReporter.config().setTheme(Theme.STANDARD);
		extentHtmlReporter.config().setReportName("Report for Lightbulb-qloyalcodetest");
		extentHtmlReporter.config().setChartVisibilityOnOpen(true);
		
		//ExtentX Reporter
		extentReports = new ExtentReports();
		extentReports.attachReporter(extentHtmlReporter);
	
		
		return extentReports;
	}
	
	
	public static ExtentReports getExtentReports() {
		return extentReports;
	}
	
	public static void saveReporter() {
		if(extentReports != null) {
			extentReports.flush();
		}
	}
	
	public static synchronized ExtentTest createTest(String testName) {
		ExtentTest extentTest=extentReports.createTest(testName);
		extentTestMap.put((int)(Thread.currentThread().getId()),extentTest);
		return extentTest;
	}
	
	public static synchronized ExtentTest createTest(String testName, String testDesc) {
		ExtentTest extentTest=extentReports.createTest(testName,testDesc);
		extentTestMap.put((int)(Thread.currentThread().getId()),extentTest);
		return extentTest;
	}
	
	public static synchronized ExtentTest fetchTest() {
		return extentTestMap.get((int) (Thread.currentThread().getId()));
	}
	
}
