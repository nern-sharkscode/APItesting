package api.services;

import api.models.request.LogOutRequest;
import api.models.request.LoginRequest;
import api.models.request.RegistrationRequest;
import com.microsoft.playwright.APIRequestContext;
import com.microsoft.playwright.APIResponse;
import com.microsoft.playwright.options.RequestOptions;
import utils.SessionContext;

public class AuthService {
    private APIRequestContext request;

    public AuthService(APIRequestContext request) {
        this.request = request;
    }

    public APIResponse postLogin(LoginRequest payload) {
        return request.post("/auth/login", RequestOptions.create().setData(payload));
    }

    public APIResponse postRegistration(RegistrationRequest payload){
        return request.post("/auth/v2/register", RequestOptions.create()
                .setHeader("Authorization", "Bearer " + SessionContext.getToken())
                .setData(payload));
    }

    public APIResponse postLogOut(LogOutRequest payload){
        return request.post("/auth/logout", RequestOptions.create().setData(payload));
    }
}
