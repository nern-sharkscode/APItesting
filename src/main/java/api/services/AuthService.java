package api.services;

import api.models.request.LoginRequest;
import api.models.request.RegistrationRequest;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class AuthService {

    public Response postLogin(LoginRequest payload) {
        return RestAssured
                .given()
                .contentType(ContentType.JSON)
                .body(payload)
                .when()
                .post("/auth/login");
    }

    public Response postRegistration(RegistrationRequest payload) {
        return RestAssured
                .given()
                .contentType(ContentType.JSON)
                .body(payload)
                .when()
                .post("/auth/v2/register");
    }

}
