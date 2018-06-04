package lightbulbtest;

import static io.restassured.RestAssured.given;
//import static com.jayway.restassured.RestAssured.given;
import java.util.HashMap;
import java.util.Map;

import org.json.simple.JSONObject;
import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

/*import com.jayway.restassured.RestAssured;
import com.jayway.restassured.http.ContentType;
import com.jayway.restassured.response.Response;
*/
import base.BaseTest;
import common.test.ApiResources;
import dataproviders.TestDataFile;
import framework.utils.ExtentUtil;
import framework.utils.Settings;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;


import io.restassured.response.Response;

@Listeners(base.TestListener.class)
public class AllMethodsPowerONTest extends BaseTest {
	
	//@Test(dataProviderClass=TestDataFile.class, dataProvider="poweron200", groups="regression")
	public void postRequestPowerOn_Valid_range(String power) {
		RestAssured.baseURI=Settings.hostRestApi;
		String resource=ApiResources.getResourcePowerOn();
		
		String userId=ApiResources.getUserId();
		
				
		ExtentUtil.fetchTest().info("Calling endpoint with userId("+userId+"): "+RestAssured.baseURI+resource);
		JSONObject postJsonMsg=ApiResources.getPostRequesttBody(power);
		
				ExtentUtil.fetchTest().info("Request Body : "+postJsonMsg);
		Response response=
                given().
                header("content-type", "application/json").
                header("userid",userId).
                body(postJsonMsg.toJSONString()).
                when().
                post(resource);
		
		int statusCode 			=response.statusCode();
		String responseAsString =response.asString();
		
		boolean codeMatch=(statusCode==200)?true:false;
		ExtentUtil.fetchTest().info("Response Code received  : "+statusCode);
		ExtentUtil.fetchTest().info("Expected : Response Code 200 match : "+codeMatch);
        
		if(statusCode==200) {
			boolean schemaMatch=compareJsonResponseWithSchema(response);
			ExtentUtil.fetchTest().info("Expected : Json Response matches the schema("+Settings.jsonSchema+") : "+schemaMatch);
			
			String contentType=response.getContentType();
			ExtentUtil.fetchTest().info("Content-Type received  : "+contentType);
			boolean contentTypeMatch=contentType.contentEquals("application/json");
			ExtentUtil.fetchTest().info("Expected : Content-Type 'application/json' match  : "+contentTypeMatch);
			
			String msg="Switch & custom power set successfully";
			boolean responseValueMatch=response.jsonPath().get("Result").toString().contentEquals(msg);
			ExtentUtil.fetchTest().info("Expected : Json response contains '"+msg+"' : "+responseValueMatch);
			
			Assert.assertTrue((codeMatch && schemaMatch) && responseValueMatch);
		} else {
			ExtentUtil.fetchTest().info("Response received: "+responseAsString);
			Assert.assertTrue(codeMatch);
		}
		
        
		
	}
	
	
	
	
	@Test(dataProviderClass=TestDataFile.class, dataProvider="poweron200invalid", groups="regression")
	public void postRequestPowerOn_InValid_range(String power) {
		RestAssured.baseURI=Settings.hostRestApi;
		String resource=ApiResources.getResourcePowerOn();
		
		String userId=ApiResources.getUserId();
		
		ExtentUtil.fetchTest().info("Calling endpoint with userId("+userId+") : "+RestAssured.baseURI+resource);
		JSONObject postJsonMsg=ApiResources.getPostRequesttBody(power);
		ExtentUtil.fetchTest().info("Request Body : "+postJsonMsg);
				
		Response response=
                given().
                contentType(ContentType.JSON).
                header("Content-Type", "application/json").
                header("userid",userId).
                body(postJsonMsg.toJSONString()).
                when().
                post(resource);
		
		int statusCode 			=response.statusCode();
		String responseAsString =response.asString();
		boolean codeMatch=(statusCode==400)?true:false;
		ExtentUtil.fetchTest().info("Response Code received  : "+statusCode);
		ExtentUtil.fetchTest().info("Expected : Response Code 400 match : "+codeMatch);
        
		if(statusCode==400) {
			
			boolean textContainsMatch=true;
			String[] contentMatches= {"/api/allmethods","/api/allmethods/off","/api/allmethods/on",
					"Valid values 1-60","Expects parameter in JSON formatted POST data"};
			for(String s : contentMatches) {
				ExtentUtil.fetchTest().info("Expected : Response contains '"+s+"' : "+responseAsString.contains(s));
				textContainsMatch=textContainsMatch && responseAsString.contains(s);
			}
			if(!textContainsMatch) 
				ExtentUtil.fetchTest().info("Response received: "+responseAsString);
			Assert.assertTrue(codeMatch && textContainsMatch);
		} else {
			Assert.assertTrue(codeMatch);
		}
		
        
		
	}

	
}
