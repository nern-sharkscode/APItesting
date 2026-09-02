package api.business;

import api.models.request.LoginRequest;
import api.models.request.RegistrationRequest;
import api.models.response.AuthResponse;
import api.services.AuthService;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.qameta.allure.Step;
import io.restassured.response.Response;
import utils.SessionContext;

public class AuthFacade {
    private AuthService authService;

    public AuthFacade() {
        this.authService = new AuthService();
    }


    @Step("Authorize user with email: {email}")
    public Response emailLogin(String email, String password) {
        LoginRequest payload = new LoginRequest(email, password);
        Response response = authService.postLogin(payload);

        ObjectMapper mapper = new ObjectMapper();

        try {
            AuthResponse authResponse = mapper.readValue(response.asString(), AuthResponse.class);
            if (authResponse.status) {
                SessionContext.setToken(authResponse.user.token);
            } else {
                throw new RuntimeException("Login failed. Status: " + response.statusCode() + " Body: " + response.asString());
            }
        } catch (Exception e) {
            throw new RuntimeException("Failed to parse login response: " + e.getMessage());
        }

        return response;
    }

    @Step("Register user with email: {email}")
    public Response emailRegister(String email, String password) {
        RegistrationRequest payload = new RegistrationRequest(email, password);
        Response response = authService.postRegistration(payload);

        ObjectMapper mapper = new ObjectMapper();

        try {
            AuthResponse authResponse = mapper.readValue(response.asString(), AuthResponse.class);
            if (authResponse.status) {
                SessionContext.setToken(authResponse.user.token);
            } else {
                throw new RuntimeException("Registration failed. Status: " + response.statusCode() + " Body: " + response.asString());
            }
        } catch (Exception e) {
            throw new RuntimeException("Failed to parse registration response: " + e.getMessage());
        }

        return response;
    }

    @Step("Logout current user")
    public void logOut() {
        SessionContext.clear();
    }
}
