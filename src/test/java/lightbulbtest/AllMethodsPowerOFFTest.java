package lightbulbtest;

import static io.restassured.RestAssured.given;
//import static com.jayway.restassured.RestAssured.given;
import java.io.File;
import java.io.IOException;

import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.fasterxml.jackson.databind.JsonNode;
import com.github.fge.jsonschema.core.exceptions.ProcessingException;
import com.github.fge.jsonschema.main.JsonSchema;
//import com.jayway.restassured.RestAssured;
//import com.jayway.restassured.path.json.JsonPath;
//import com.jayway.restassured.response.Response;

import base.BaseTest;
import common.test.ApiResources;
import dataproviders.TestDataFile;
import framework.utils.ExtentUtil;
import framework.utils.SchemaValidator;
import framework.utils.Settings;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
@Listeners(base.TestListener.class)
public class AllMethodsPowerOFFTest extends BaseTest {
	
	
	@Test(groups="regression")
	public void getRequestPowerOff_Valid_userId() {
		RestAssured.baseURI=Settings.hostRestApi;
		String resource=ApiResources.getResourcePowerOFF();
		
		String userId=ApiResources.getUserId();
		
		ExtentUtil.fetchTest().info("Calling endpoint with userId("+userId+"): "+RestAssured.baseURI+resource);
		
		Response response=
                given().
                param("Content-Type","application/json").
                param("userId",userId).
                when().
                get(resource).then().extract().response();
		
		int statusCode 			=response.statusCode();
		JsonPath responseAsJsonPath= response.jsonPath();
		
		boolean codeMatch=(statusCode==200)?true:false;
		ExtentUtil.fetchTest().info("Response Code received  : "+statusCode);
		ExtentUtil.fetchTest().info("Expected : Response Code 200 match : "+codeMatch);

		boolean schemaMatch=compareJsonResponseWithSchema(response);
		ExtentUtil.fetchTest().info("Expected : Json Response matches the schema("+Settings.jsonSchema+") : "+schemaMatch);
		
		String msg="Switch set successfully";
		boolean responseValueMatch=response.jsonPath().get("Result").toString().contentEquals(msg);
		ExtentUtil.fetchTest().info("Expected : Json response contains '"+msg+"' : "+responseValueMatch);
		
		Assert.assertTrue((codeMatch && schemaMatch) && responseValueMatch);
		
	}
	
	@Test(dataProviderClass=TestDataFile.class, dataProvider="poweroff400", groups="regression")
	public void getRequestPowerOff_Invalid_userId(String userId) {
		RestAssured.baseURI=Settings.hostRestApi;
		String resource=ApiResources.getResourcePowerOFF();
		ExtentUtil.fetchTest().info("Calling endpoint with userId(\""+userId+"\") : "+RestAssured.baseURI+resource);
		 
			
		Response response=
                given().
                param("Content-Type","application/json").
                param("userId",userId).
                when().
                get(resource).then().extract().response();
		
		int statusCode 			=response.statusCode();
		String responseMsg      =response.asString().trim();
		
		boolean codeMatch=(statusCode==400)?true:false;
		ExtentUtil.fetchTest().info("Response Code received  : "+statusCode);
		ExtentUtil.fetchTest().info("Expected : Response Code 400 match : "+codeMatch);
			
		String msg="Required userId header not found";
		boolean responseValueMatch=responseMsg.contentEquals(msg);
		ExtentUtil.fetchTest().info("Expected : Response is '"+msg+"' : "+responseValueMatch);
		
		Assert.assertTrue(codeMatch && responseValueMatch);
	}

}
