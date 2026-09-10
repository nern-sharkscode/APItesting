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
    private Response lastResponse;

    @Step("Authorize user with email: {email}")
    public Response emailLogin(String email, String password) {
        LoginRequest payload = LoginRequest.builder()
                .email(email)
                .password(password)
                .build();

        Response response = authService.postLogin(payload);

        if (response.statusCode() == 200) {
            AuthResponse authResponse = response.as(AuthResponse.class);
            if (authResponse.status) {
                SessionContext.setToken(authResponse.user.token);
            }
        }
        return response;
    }

    @Step("Register user with email: {email}")
    public AuthFacade emailRegister(String email, String password) {
        RegistrationRequest payload = RegistrationRequest.builder()
                .email(email)
                .password(password)
                .build();

        this.lastResponse = authService.postRegistration(payload);
        parseAndStoreToken(this.lastResponse, "Registration");

        return this;
    }

    @Step("Logout current user")
    public void logOut() {
        SessionContext.clear();
    }

    public Response getResponse() {
        return this.lastResponse;
    }

    private AuthService authService;

    public AuthFacade() {
        this.authService = new AuthService();
    }

    private void parseAndStoreToken(Response response, String action) {
        ObjectMapper mapper = new ObjectMapper();
        try {
            AuthResponse authResponse = mapper.readValue(response.asString(), AuthResponse.class);
            if (authResponse.status) {
                SessionContext.setToken(authResponse.user.token);
            } else {
                throw new RuntimeException(action + " failed. Status: " + response.statusCode()
                        + " Body: " + response.asString());
            }
        } catch (Exception e) {
            throw new RuntimeException("Failed to parse " + action.toLowerCase() + " response: " + e.getMessage());
        }
    }
}
