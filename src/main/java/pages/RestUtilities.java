package pages;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class RestUtilities extends HomePage{
    public static Response getCall(){
        return RestAssured.get("https://reqres.in/api/users?page=2");
    }
    public static Response postWithoutAppURL(String url, String body) {
        RequestSpecBuilder builder = new RequestSpecBuilder();
        builder.addHeader("Content-Type", ContentType.JSON.toString());
        builder.addHeader("accept", ContentType.JSON.toString());
        builder.setBody(body);
        builder.log(LogDetail.ALL);
        RequestSpecification requestSpec = builder.build();
        return RestAssured
                .expect()
                .given()
                .spec(requestSpec)
                .post(url);
    }
}
