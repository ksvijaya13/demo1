package WithGherkinKeyWords;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.Assert;

public class PostRequestTest {

	 // Set base URI before all tests
    @BeforeClass
    public void setup() {
        RestAssured.baseURI = "https://reqres.in/api";
    }
// post method using Gherkin Keywords
    @Test
    public void postRequest() {
        // Request payload
        String requestBody = "{\r\n" +
                "    \"name\": \"morpheus\",\r\n" +
                "    \"job\": \"leader\"\r\n" +
                "}";

        // Send POST request and capture the response
        Response response = RestAssured.given()
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
	

}
