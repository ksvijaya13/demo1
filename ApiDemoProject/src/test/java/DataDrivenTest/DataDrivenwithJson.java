package DataDrivenTest;

import com.google.gson.Gson;
import com.google.gson.JsonIOException;
import com.google.gson.JsonSyntaxException;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.response.ResponseBody;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.Path;

import org.testng.annotations.Test;

public class DataDrivenwithJson {

	@Test
	public void sendPostRequest() {

		//Provide the JSON file path
		String jsonFilePath= "C:\\Users\\ravih\\OneDrive\\Desktop\\Postman Collection\\testdata1.json";

		//Reading data from JSON file
		String jsonData = "";


		try {
			jsonData=new String(Files.readAllBytes(Paths.get(jsonFilePath)));

		}

		catch (IOException e){
			// TODO: handle exception
			e.printStackTrace();
		}

			//Replace Dynamic values
			//			String postCode="12345";
			//			String pin="4567";
			//			String street="Ameerpet";

			String name="morpheus";
			String job="leader";

			//			jsonData=jsonData.replace("{{postCode}}", postCode)
			//					.replace("{{pin}}", pin)
			//					.replace("{{street}}", street);

			jsonData=jsonData.replace("{{name}}", name)
					.replace("{{job}}", job);

			//Send post method
			Response response = RestAssured.given()
					.contentType(ContentType.JSON)
					.body(jsonData)
					.when()
					.post("https://reqres.in/api/users");
			

				//Printing the Response and status code
			String ResponseBody= response.getBody().asString();
			
			int StatusCode=response.getStatusCode();
			
			System.out.println("Status code is:"+StatusCode);	
			System.out.println("Response Body is:"+ResponseBody);
				
				
	}
}

