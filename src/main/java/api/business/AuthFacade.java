package api.business;

import api.models.request.LoginRequest;
import api.models.request.RegistrationRequest;
import api.services.AuthService;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.microsoft.playwright.APIRequestContext;
import com.microsoft.playwright.APIResponse;
import io.qameta.allure.Step;
import utils.SessionContext;

public class AuthFacade {
    private AuthService authService;

    public AuthFacade(APIRequestContext request) {
        this.authService = new AuthService(request);
    }


    @Step("Authorize user with email: {email}")
    public APIResponse emailLogin(String email, String password) throws Exception {
        LoginRequest payload = new LoginRequest(email, password);

        APIResponse response = authService.postLogin(payload);

        ObjectMapper mapper = new ObjectMapper();
        JsonNode jsonResponse = mapper.readTree(response.text());

        if (jsonResponse.path("status").asBoolean()) {
            String token = jsonResponse.path("user").path("token").asText();
            SessionContext.setToken(token);
        } else {
            throw new RuntimeException("Login failed" + "Status: " + response.status() + " Body: " + response.text());
        }

        return response;
    }

    @Step("Register user with email: {email}")
    public APIResponse emailRegister(String email, String password) throws Exception {
        RegistrationRequest payload = new RegistrationRequest(email, password);
        APIResponse response = authService.postRegistration(payload);
        System.out.println(response.text());

        ObjectMapper mapper = new ObjectMapper();
        JsonNode jsonResponse = mapper.readTree(response.text());

        if (jsonResponse.path("status").asBoolean()) {
            String token = jsonResponse.path("user").path("token").asText();
            SessionContext.setToken(token);
        } else {
            throw new RuntimeException("Registration failed: " + response.text());
        }

        return response;
    }

    @Step("Logout current user")
    public void logOut(){
        SessionContext.clear();
    }
}
