package Day05;  // All methos (Post, Put, Get, delete) using Gherkin KeyWords

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.Assert;

public class RestAssuredRequests {

	// Set base URI before all tests
	@BeforeClass
	public void setup() {
		RestAssured.baseURI = "https://reqres.in/api";
	}

	@Test(priority=1)
	public void postRequest() {
		// Request payload
		String requestBody = "{\r\n" +
				"    \"name\": \"morpheus\",\r\n" +
				"    \"job\": \"leader\"\r\n" +
				"}";

		// Send POST request and capture the response
		Response response = RestAssured.given()
				.header("Authorization","--- Bearer token value----")
				.contentType(ContentType.JSON)
				.body(requestBody)
				.when()
				.post("/users")
				.then()
				.extract().response();

		// Assertions
		Assert.assertEquals(201, response.getStatusCode());
		Assert.assertEquals("morpheus", response.jsonPath().getString("name"));
		Assert.assertEquals("leader", response.jsonPath().getString("job"));

		// Print response body
		System.out.println("Response body: " + response.getBody().asString());
	}

	@Test (priority=2)
	public void putRequest() {
		// Request payload
		String requestBody = "{\r\n" +
				"    \"name\": \"morpheus\",\r\n" +
				"    \"job\": \"zion resident\"\r\n" +
				"}";

		// Send PUT request and capture the response
		Response response = RestAssured.given()
				.contentType(ContentType.JSON)
				.body(requestBody)
				.when()
				.put("/users/2")
				.then()
				.extract().response();

		// Assertions
		Assert.assertEquals(200, response.getStatusCode());
		Assert.assertEquals("morpheus", response.jsonPath().getString("name"));
		Assert.assertEquals("zion resident", response.jsonPath().getString("job"));

		// Print status code, name, and job
		System.out.println("Status Code: " + response.getStatusCode());
		System.out.println("Name: " + response.jsonPath().getString("name"));
		System.out.println("Job: " + response.jsonPath().getString("job"));

		// Print response body
		System.out.println("Response body: " + response.getBody().asString());
	}

	@Test(priority=3)
	public void getRequest() {
		// Send GET request and capture the response
		Response response = RestAssured.given()
				.when()
				.get("/users/2")
				.then()
				.extract().response();

		// Assertions
		Assert.assertEquals(200, response.getStatusCode());
		Assert.assertEquals("Janet", response.jsonPath().getString("data.first_name"));
		Assert.assertEquals("Weaver", response.jsonPath().getString("data.last_name"));

		// Print status code, first name, and last name
		System.out.println("Status Code: " + response.getStatusCode());
		System.out.println("First Name: " + response.jsonPath().getString("data.first_name"));
		System.out.println("Last Name: " + response.jsonPath().getString("data.last_name"));

		// Print response body
		System.out.println("Response body: " + response.getBody().asString());
	}

	@Test(priority=4)
	public void deleteRequest() {
		// Send DELETE request and capture the response
		Response response = RestAssured.given()
				.when()
				.delete("/users/2")
				.then()
				.extract().response();

		// Assertion
		Assert.assertEquals(204, response.getStatusCode());

		// Print status code
		System.out.println("Status Code: " + response.getStatusCode());
	}	
}
