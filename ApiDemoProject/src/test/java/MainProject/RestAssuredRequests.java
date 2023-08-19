package MainProject;

import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

import com.aventstack.extentreports.reporter.ExtentHtmlReporter;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;

import RestUtils.RestUtils;
import io.restassured.RestAssured;
import io.restassured.response.Response;

public class RestAssuredRequests {
    private ExtentReports extent;
    private ExtentTest test;

    @BeforeSuite
    public void beforeSuite() {
        extent = new ExtentReports();
        String reportPath = "test-output/ExtentReport.html";
        ExtentHtmlReporter htmlReporter = new ExtentHtmlReporter(reportPath);
        extent.attachReporter(htmlReporter);
    }

    @BeforeClass
    public void setup() {
        RestAssured.baseURI = "https://reqres.in/api";
    }

    @Test
    public void postRequest() {
        test = extent.createTest("POST Request Test");
        String requestBody = "{\r\n" +
                "    \"name\": \"morpheus\",\r\n" +
                "    \"job\": \"leader\"\r\n" +
                "}";
        Response response = RestUtils.sendPostRequest("/users", requestBody);

        // Assertions
        Assert.assertEquals(201, response.getStatusCode());
        Assert.assertEquals("morpheus", response.jsonPath().getString("name"));
        Assert.assertEquals("leader", response.jsonPath().getString("job"));

        // Logging the test status
        test.log(Status.PASS, "Test passed");

        // Print response body
        test.info("Response body: " + response.getBody().asString());
    }

    @Test
    public void putRequest() {
        
    	test = extent.createTest("PUT Request Test");
        
    	String requestBody = "{\r\n" +
                "    \"name\": \"morpheus\",\r\n" +
                "    \"job\": \"zion resident\"\r\n" +
                "}";
        Response response = RestUtils.sendPutRequest("/users/2", requestBody);

        // Assertions
        Assert.assertEquals(200, response.getStatusCode());
        Assert.assertEquals("morpheus", response.jsonPath().getString("name"));
        Assert.assertEquals("zion resident", response.jsonPath().getString("job"));

        // Logging the test status
        test.log(Status.PASS, "Test passed");

        // Print response body
        test.info("Response body: " + response.getBody().asString());
    }

    @Test
    public void getRequest() {
        test = extent.createTest("GET Request Test");        // To generate the Test Report
        Response response = RestUtils.sendGetRequest("/users/2");

        // Assertions
        Assert.assertEquals(200, response.getStatusCode());
        Assert.assertEquals("Janet", response.jsonPath().getString("data.first_name"));
        Assert.assertEquals("Weaver", response.jsonPath().getString("data.last_name"));

        // Logging the test status
        test.log(Status.PASS, "Test passed");

        // Print response body
        test.info("Response body: " + response.getBody().asString());
    }

    @Test
    public void deleteRequest() {
        test = extent.createTest("DELETE Request Test");
        Response response = RestUtils.sendDeleteRequest("/users/2");

        // Assertion
        Assert.assertEquals(204, response.getStatusCode());

        // Logging the test status
        test.log(Status.PASS, "Test passed");

        // Print response body
        test.info("Response body: " + response.getBody().asString());
    }

    @AfterClass
    public void afterClass() {
        extent.flush();
    }

    @AfterSuite
    public void afterSuite() {
        extent.close();
    }
}