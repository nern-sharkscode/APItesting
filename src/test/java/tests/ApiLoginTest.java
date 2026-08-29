package tests;

import api.business.AuthFacade;
import base.BaseApiTest;

import com.microsoft.playwright.APIResponse;
import org.testng.Assert;
import org.testng.annotations.Test;
import utils.ConfigProvider;
import utils.SessionContext;

public class ApiLoginTest extends BaseApiTest {
    @Test(description ="LogIn with the known user")
    public void loginTest() throws Exception {
        AuthFacade authFacade = new AuthFacade(request);
        APIResponse response = authFacade.emailLogin(ConfigProvider.getEmail(), ConfigProvider.getPassword());

        Assert.assertEquals(response.status(), 200, "Login failed, response status is " + response.status());
        System.out.println(response.text());
        Assert.assertFalse(SessionContext.getToken().isEmpty(), "Token is absent, user is not logged in");

    }
}
