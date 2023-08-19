package WithGherkinKeyWords;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import junit.framework.Assert;
import static io.restassured.RestAssured.given;

public class PostRequest {
	
	@BeforeClass             //initiate basic uri before the post method implementation
	public static void SetUp() {
		RestAssured.baseURI="https://reqres.in/api";
	
	@Test
	public void postTest(){
			//Initiate Payload
			String RequestBody="{\r\n"
					+ "    \"name\": \"morpheus\",\r\n"
					+ "    \"job\": \"leader\"\r\n"
					+ "}";
			//to call pre-requisite script
			Response response=given()
					.contentType(ContentType.JSON)
					.body(RequestBody)
					//to call the Post method
					.when()
					.post("/users")
					//to extract the response 
					.then()
					.extract().response();
			//print Resonse details
			System.out.println("Status Code:"+response.getStatusCode());
			System.out.println("ResponseBody:"+response.getBody().asString());
			System.out.println("Status Line:"+response.getStatusLine());// TO print status code and status
	
			//To add Assertions
			Assert.assertEquals(201, response.getStatusCode());
			Assert.assertEquals(1,response.jsonPath().getInt("2"));
			Assert.assertEquals("morpheus", response.jsonPath().getString("name"));
			Assert.assertEquals("leader", response.jsonPath().getString("job"));
			
		}
		
	}

}
