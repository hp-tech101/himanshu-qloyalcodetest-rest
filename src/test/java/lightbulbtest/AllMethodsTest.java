package lightbulbtest;

import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

//import com.jayway.restassured.RestAssured;
//import com.jayway.restassured.response.Response;

import base.BaseTest;
import common.test.ApiResources;
import framework.utils.ExtentUtil;
import framework.utils.Settings;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import static io.restassured.RestAssured.given;
//import static com.jayway.restassured.RestAssured.given;

@Listeners(base.TestListener.class)
public class AllMethodsTest extends BaseTest {
	

	@Test(groups="regression")
	public void getAllMethods() {
		RestAssured.baseURI=Settings.hostRestApi;
		String resource=ApiResources.getResourceAllMethods();
		
		ExtentUtil.fetchTest().info("Calling rest endpoint : "+RestAssured.baseURI+resource);
		Response response=
                given().
                param("Content-Type","application/json").
                when().
                get(resource).then().extract().response();
		
		int statusCode 			=response.statusCode();
		String responseAsString =response.asString();
		
		boolean codeMatch=(statusCode==200)?true:false;
		ExtentUtil.fetchTest().info("Response Code received  : "+statusCode);
		ExtentUtil.fetchTest().info("Expected Response Code 200 match : "+codeMatch);
		
		boolean startWithTextmatch=responseAsString.startsWith("Methods:");
		ExtentUtil.fetchTest().info("Expected : Response Starts with 'Methods:' : "+startWithTextmatch);
		
		boolean textContainsMatch=true;
		String[] contentMatches= {"/api/allmethods","/api/allmethods/off","/api/allmethods/on",
				"Valid values 1-60","Expects parameter in JSON formatted POST data"};
		for(String s : contentMatches) {
			ExtentUtil.fetchTest().info("Expected : Response contains '"+s+"' : "+responseAsString.contains(s));
			textContainsMatch=textContainsMatch && responseAsString.contains(s);
		}
		
		Assert.assertTrue((codeMatch && startWithTextmatch) &&  textContainsMatch);
		
	}
}
