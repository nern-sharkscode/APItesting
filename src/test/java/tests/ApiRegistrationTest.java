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

public class ApiRegistrationTest extends BaseApiTest {
    private AuthFacade authFacade;
    private String randomEmail = DataGeneration.generateEmail();

    @BeforeMethod
    public void setUpBusinessLogic() {
        authFacade = new AuthFacade();
    }

    @Test(description ="Registration with random email")
    public void registrationTest(){
        Response response = authFacade
                .emailRegister(randomEmail, ConfigProvider.getInstance().getPassword())
                .getResponse();;

        Assert.assertEquals(response.statusCode(), 200, "Registration failed");
        Assert.assertNotNull(SessionContext.getToken(), "Token was not saved to session context");
    }
}
