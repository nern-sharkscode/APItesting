package tests;

import api.business.AuthFacade;
import base.BaseApiTest;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;
import utils.ConfigProvider;
import utils.DataGeneration;
import utils.SessionContext;

public class ApiRegistrationTest extends BaseApiTest {
    @Test(description ="Registration with random email")
    public void registrationTest(){
        AuthFacade registrationFacade = new AuthFacade();
        DataGeneration generateEmail = new DataGeneration();
        Response response = registrationFacade.emailRegister(generateEmail.generateEmail(), ConfigProvider.getPassword());
        Assert.assertEquals(response.statusCode(), 200, "Registration failed");
        Assert.assertNotNull(SessionContext.getToken(), "Token was not saved to session context");
    }
}
