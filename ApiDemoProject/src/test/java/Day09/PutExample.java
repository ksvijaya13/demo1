package Day09;

import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class PutExample {
	@Test
	public void updateResoure() {
		String requestBody = "{\"name\":\"Jaya\":,\"age\":30}";
		
		//"RequestSpecification" is a package used to initiate a request/method
		RequestSpecification request=RestAssured.given();
		request.header("ContentType","applicaiton/JSON");
		request.body(requestBody);
		
		//call the updateResource method and store the response
		Response response=request.put("https://reqres.in/api/users/2");
		
		int StatusCode= response.getStatusCode();
		System.out.println("StatusCode:"+StatusCode);
		
		//convert the Response into String format and print the response
		String responseBoday=response.getBody().asString();
		System.out.println("responseBody:"+responseBoday);
		
	}

}
