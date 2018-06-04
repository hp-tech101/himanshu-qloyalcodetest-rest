package base;

import java.io.File;
import java.io.IOException;

import com.fasterxml.jackson.databind.JsonNode;
import com.github.fge.jsonschema.core.exceptions.ProcessingException;
import com.github.fge.jsonschema.main.JsonSchema;
//import com.jayway.restassured.response.Response;

import framework.utils.SchemaValidator;
import framework.utils.Settings;
import io.restassured.response.Response;

public class UtilityMethods {

public boolean compareJsonResponseWithSchema(Response response) {
	JsonSchema schema = null;
	try {
		schema=SchemaValidator.getSchemaNode(new File(Settings.jsonSchema));
	} catch (IOException | ProcessingException e) {
		e.printStackTrace();
	}
	JsonNode node = null;
	try {
		node=SchemaValidator.getJsonNode(response.asString());
	} catch (IOException e) {
		e.printStackTrace();
	}
	boolean schemaMatch=false;
	try {
		schemaMatch = SchemaValidator.isJsonValid(schema, node);
	} catch (ProcessingException e) {
		e.printStackTrace();
	}
	
	return schemaMatch;
}
}
