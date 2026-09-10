package tests;

import api.business.AuthFacade;
import base.BaseApiTest;

import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import utils.ConfigProvider;
import utils.DataGeneration;
import utils.SessionContext;

public class ApiLoginTest extends BaseApiTest {

    private AuthFacade authFacade;

    @BeforeMethod
    public void setUpBusinessLogic() {
        authFacade = new AuthFacade();
    }

    private String randomEmail = DataGeneration.generateEmail();

    @Test(description = "LogIn with the known user")
    public void loginTest() {
        Response response = authFacade
                .emailRegister(randomEmail, ConfigProvider.getInstance().getPassword())
                .emailLogin(randomEmail, ConfigProvider.getInstance().getPassword());

        Assert.assertEquals(response.statusCode(), 200, "Login failed, response status is " + response.statusCode());
        Assert.assertNotNull(SessionContext.getToken(), "Token is null");
        Assert.assertFalse(SessionContext.getToken().isEmpty(), "Token is absent, user is not logged in");
    }

    @Test(description = "LogIn with invalid password")
    public void loginWithInvalidPassword() {
        String email = DataGeneration.generateEmail();
        String invalidPassword = "wrongPassword123";

        Response response = authFacade.emailLogin(email, invalidPassword);

        Assert.assertEquals(response.statusCode(), 200, "Expected HTTP status 200");

        Assert.assertFalse(response.jsonPath().getBoolean("status"), "Expected business status to be false");
        Assert.assertEquals(response.jsonPath().getInt("errorCode"), 412, "Expected custom error code 412");
    }
}
