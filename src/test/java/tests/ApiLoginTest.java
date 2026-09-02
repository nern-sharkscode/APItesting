package tests;

import api.business.AuthFacade;
import base.BaseApiTest;

import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;
import utils.ConfigProvider;
import utils.DataGeneration;
import utils.SessionContext;

public class ApiLoginTest extends BaseApiTest {
    @Test(description ="LogIn with the known user")
    public void loginTest() {
        AuthFacade authFacade = new AuthFacade();
        String randomEmail = DataGeneration.generateEmail();
        authFacade.emailRegister(randomEmail, ConfigProvider.getPassword());
        Response response = authFacade.emailLogin(randomEmail, ConfigProvider.getPassword());

        Assert.assertEquals(response.statusCode(), 200, "Login failed, response status is " + response.statusCode());
        System.out.println(response.asString());
        Assert.assertFalse(SessionContext.getToken().isEmpty(), "Token is absent, user is not logged in");

    }
}
