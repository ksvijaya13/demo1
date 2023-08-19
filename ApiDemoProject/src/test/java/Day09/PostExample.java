package Day09;

import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class PostExample {
	@Test
	public void createResource() {
		
		// to initiate the payload
//	String requestBody = "{\"name\":\"vijaya\":,\"age\":25}";
//	String requestBody = "{\"name\":\"vi\":,\"page\":001:}";
		
	String requestBody = "{\"page\":\"2\":,\"total\":001:}";

		
		// given() keyword is used to initiate the pre-requisite i/p data or script
		RequestSpecification request=RestAssured.given();
				request.header("ContentType","application/json");
				request.body("requestBody");
				
				//to call the method and store the response
				Response response=request.post("https://reqres.in/api/users");
				
				//performing validation & print the values
				int statusCode=response.getStatusCode();
				System.out.println("statusCode:"+statusCode);
				
				//to convert responseBody into string
				String responseBody=response.getBody().asString();
				
				//print the response body
				System.out.println("ResponseBody:"+responseBody);
				
										
		
	}

}
