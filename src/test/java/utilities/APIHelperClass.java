package utilities;

import java.util.HashMap;
import org.json.simple.JSONObject;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class APIHelperClass {
	int statusCode;
	JSONObject requestParams, responseParams;
	private final String baseURL = "https://api.trello.com/1";

	public Response postRequest(String requestPath, HashMap<String, String> data) {
		RestAssured.baseURI = baseURL;
		RequestSpecification request = RestAssured.given();
		requestParams = new JSONObject(data);
		request.header("Content-Type", "application/json");
		request.body(requestParams.toJSONString());
		Response response = request.post(requestPath);
		return response;
	}
	
	
	public Response getRequest(String requestPath, HashMap<String, String> data) {
		RestAssured.baseURI = baseURL;
		RequestSpecification request = RestAssured.given();
		request.header("Content-Type", "application/json");
		requestParams = new JSONObject(data);
		request.body(requestParams.toJSONString());
		Response response = request.get(requestPath);
		System.out.println(response);
		return response;
	}

	public Response putRequest(String requestPath, HashMap<String, String> data) {
		Response response;
		RestAssured.baseURI = baseURL;
		RequestSpecification request = RestAssured.given();
		request.header("Content-Type", "application/json");
		requestParams = new JSONObject(data);
		request.body(requestParams.toJSONString());
		response = request.put(requestPath);
		return response;
	}
	
}