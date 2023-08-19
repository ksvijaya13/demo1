package Day09;

import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.response.Response;

public class GetExample {

	@Test
  public void getResource()
  {
		//initiate the request and store the response
	Response response=RestAssured.get("https://reqres.in/api/users/5");
	int StatusCode=response.getStatusCode();
	System.out.println("Status Code:"+ StatusCode);
	
	//convert the response into String and print the response
	String ResponseBody=response.getBody().asString();
	System.out.println("Response Body:"+ ResponseBody);
	
	
	
  }

	
}
