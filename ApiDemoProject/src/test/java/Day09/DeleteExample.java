package Day09;

import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.response.Response;

public class DeleteExample {
	@Test
	public void DeleteTest() {
		
		Response response=RestAssured.delete("https://reqres.in/api/users/5");
		int StatusCode=response.getStatusCode();
		System.out.println("Status Code:"+StatusCode);
		
		//read the response and convert into String & Printing
		String ResponseBody=response.getBody().asString();
		System.out.println("ResponseBody:"+ResponseBody);
	}

}
