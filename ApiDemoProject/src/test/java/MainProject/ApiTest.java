package MainProject;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
//import com.aventstack.extentreports.reporter.ExtentHtmlReporter;

public class ApiTest {
    
//    private ExtentHtmlReporter htmlReporter;
    private ExtentReports extent;
    private ExtentTest test;

    @BeforeMethod
    public void setUp() {
//        htmlReporter = new ExtentHtmlReporter("test-output/ExtentReport.html");
        extent = new ExtentReports();
//        extent.attachReporter(htmlReporter);
        test = extent.createTest(this.getClass().getSimpleName());
    }

    @AfterMethod
    public void tearDown() {
        extent.flush();
    }

    @Test
    public void testPostMethod() {
        String requestBody = "{\"name\":\"John Doe\",\"job\":\"Engineer\"}";
        given()
            .header("Content-Type", "application/json")
            .body(requestBody)
        .when()
            .post("https://reqres.in/api/users")
        .then()
            .statusCode(201)
            .body("name", equalTo("John Doe"))
            .body("job", equalTo("Engineer"));
            
        test.log(Status.PASS, "Post method test passed");
    }

    @Test
    public void testPutMethod() {
        String requestBody = "{\"name\":\"Jane Doe\",\"job\":\"Doctor\"}";
        given()
            .header("Content-Type", "application/json")
            .body(requestBody)
        .when()
            .put("https://reqres.in/api/users/2")
        .then()
            .statusCode(200)
            .body("name", equalTo("Jane Doe"))
            .body("job", equalTo("Doctor"));
            
        test.log(Status.PASS, "Put method test passed");
    }

    @Test
    public void testGetMethod() {
        given()
        .when()
            .get("https://reqres.in/api/users/2")
        .then()
            .statusCode(200)
            .body("data.id", equalTo(2))
            .body("data.email", equalTo("janet.weaver@reqres.in"));
            
        test.log(Status.PASS, "Get method test passed");
    }

    @Test
    public void testDeleteMethod() {
        when()
            .delete("https://reqres.in/api/users/2")
        .then()
            .statusCode(204);
            
        test.log(Status.PASS, "Delete method test passed");
    }
}