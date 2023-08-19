package DataDrivenTest;

import java.io.IOException;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class DataDrivenTest1 {
	@DataProvider(name="userData")
	public object[][] provideUserData() throws IOException{}
	
	String filePath="----";
	CSVReader csvReader =new CSVReader(new FileReader (----));
	String[] line;
	int rowCount=0;
	int columnCount=0;
	
	//count the no.of rows and columns in a CSV file
	while ((line = csvReader.readNext()) != null) {
        rowCount++;
        columnCount = line.length;
    }

    Object[][] data = new Object[rowCount][columnCount];
    csvReader.close();

    // Read the data from the CSV file
    csvReader = new CSVReader(new FileReader(----));
    int rowIndex = 0;
    while ((line = csvReader.readNext()) != null) {
        for (int columnIndex = 0; columnIndex < columnCount; columnIndex++) {
            data[rowIndex][columnIndex] = line[columnIndex];
        }
        rowIndex++;
    }
    csvReader.close();

    return data;

@Test(dataProvider = "userData")
public void testPostMethod(String firstName, String lastName, String age) {
    RestAssured.baseURI = "https://api.example.com"; // Replace with your API base URL

    Response response = given()
            .contentType(ContentType.JSON)
            .body("{\"firstName\":\"" + firstName + "\",\"lastName\":\"" + lastName + "\",\"age\":" + age + "}")
            .when()
            .post("/users")
            .then()
            .statusCode(201) // Replace with the expected status code
            .extract().response();

    // Additional assertions or verifications
    response.then().body("firstName", equalTo(firstName));
    response.then().body("lastName", equalTo(lastName));
    response.then().body("age", equalTo(Integer.valueOf(age)));

    // Additional assertions or verifications can be added here
}

@Test(dataProvider = "userData")
public void testPutMethod(String firstName, String lastName, String age) {
    RestAssured.baseURI = "https://api.example.com"; // Replace with your API base URL

    Response response = given()
            .contentType(ContentType.JSON)
            .body("{\"firstName\":\"" + firstName + "\",\"lastName\":\"" + lastName + "\",\"age\":" + age + "}")
            .when()
            .put("/users/{id}", 123) // Replace with the specific user ID to update
            .then()
            .statusCode(200) // Replace with the expected status code
            .extract().response();

    // Additional assertions or verifications
    response.then().body("firstName", equalTo(firstName));
    response.then().body("lastName", equalTo(lastName));
    response.then().body("age", equalTo(Integer.valueOf(age)));

    // Additional assertions or verifications can be added here
}
}
	

}
