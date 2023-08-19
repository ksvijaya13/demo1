package WithGherkinKeyWords;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import io.cucumber.java.en.Then;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import static org.hamcrest.Matchers.*;

public class CreateResourceSteps {

	private Response response;

	@Given("the base URI is {https://reqres.in/api}")

	public void setBaseURI(String baseURI) {
		RestAssured.baseURI = baseURI;
	}

	@When("I send a POST request to {/users}")
	public void sendPOSTRequest(String endpoint) {
		response = RestAssured.given()
				.post(endpoint);
	}

	@When("I set the request body as:")
	public void setRequestBody(String requestBody) {
		response = RestAssured.given()
				.contentType("application/json")
				.body(requestBody)
				.post();
	}

	@Then("the response status code should be {int}")
	public void verifyStatusCode(int statusCode) {
		response.then().statusCode(statusCode);
	}

	@Then("the response body should contain {string}")
	public void verifyResponseBody(String expectedBody) {
		response.then().body(containsString(expectedBody));
	}

}
