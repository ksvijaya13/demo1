package RestUtils;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class RestUtils {
	private static final String BASE_URL = "https://reqres.in/api";

    public static String getBaseUrl() {
        return BASE_URL;
    }

    public static Response sendPostRequest(String endpoint, String requestBody) {
        return RestAssured.given()
                .baseUri(getBaseUrl())
                .contentType(ContentType.JSON)
                .body(requestBody)
                .post(endpoint);
    }

    public static Response sendPutRequest(String endpoint, String requestBody) {
        return RestAssured.given()
                .baseUri(getBaseUrl())
                .contentType(ContentType.JSON)
                .body(requestBody)
                .put(endpoint);
    }

    public static Response sendGetRequest(String endpoint) {
        return RestAssured.given()
                .baseUri(getBaseUrl())
                .get(endpoint);
    }

    public static Response sendDeleteRequest(String endpoint) {
        return RestAssured.given()
                .baseUri(getBaseUrl())
                .delete(endpoint);
    }
}