package org.acme.resources;

import io.quarkus.test.junit.QuarkusTest;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.parsing.Parser;
import io.restassured.response.Response;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

@QuarkusTest
public class WeatherResourceTest {
    public static Response doGetRequest(String endpoint) {
        RestAssured.defaultParser = Parser.JSON;

        return
            given().headers("Content-Type", ContentType.JSON, "Accept", ContentType.JSON).
                when().get(endpoint).
                then().contentType(ContentType.JSON).extract().response();
    }

    @Test
    public void testGetWeatherEndpoint() {
        Response response = doGetRequest("/weather?zip=junk");
        String temperature = response.jsonPath().getString("temperature");
        String zip = response.jsonPath().getString("zip");
        assertEquals("0.0", temperature, "Temperature should not be set");
        assertNull(zip, "Zip should be null");
    }
}