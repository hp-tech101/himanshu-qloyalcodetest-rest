package base;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import com.google.common.io.Files;
import framework.utils.ExtentUtil;
import framework.utils.Settings;

public class TestListener implements ITestListener {


	public void onStart(ITestContext arg0) {
		System.out.println("calling onStart");
		  if(ExtentUtil.getExtentReports()==null) {
			  System.out.println("calling onStart -- is null the first time "+Settings.htmlReport);
			  String htmlFile="./artefacts/reports/ExecutionReport.html";
			  File report=new File(htmlFile);
			  
			  if(report.exists()) {
				  try {
					Files.copy(report, new File("./artefacts/archive/ExecutionReport.html_"+System.currentTimeMillis()));
				} catch (IOException e) {
					e.printStackTrace();
				}
			  }
			  ExtentUtil.createReporter(htmlFile);  
		  } 
	}

	public void onFinish(ITestContext arg0) {

		  ExtentUtil.saveReporter();

	}

	

	public void onTestSuccess(ITestResult arg0) {

		ExtentUtil.fetchTest().pass("PASSED :-)");
	  }



	public void onTestFailure(ITestResult arg0) {

		 ExtentUtil.fetchTest().fail("FAILED :-(");
		System.out.println("onTestFailer");

	}

	

	public void onTestStart(ITestResult tr) {

		ExtentUtil.createTest(tr.getMethod().getMethodName());
		String[] categories=tr.getMethod().getGroups();
		for(String category: categories) {
			ExtentUtil.fetchTest().assignCategory(category);	
		}
		
		

	}

	

	public void onTestSkipped(ITestResult tr) {

		ExtentUtil.fetchTest().skip("Skipping - "+tr.getMethod().getMethodName());

	}

	

	@Override
	public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
		// TODO Auto-generated method stub
		
	}

	
	


}
