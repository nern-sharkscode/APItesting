package tests;

import api.business.AuthFacade;
import base.BaseApiTest;
import com.microsoft.playwright.APIResponse;
import org.testng.Assert;
import org.testng.annotations.Test;
import utils.ConfigProvider;
import utils.DataGeneration;
import utils.SessionContext;

public class ApiRegistrationTest extends BaseApiTest {
    @Test(description ="Registration with random email")
    public void registrationTest() throws Exception {
        AuthFacade registrationFacade = new AuthFacade(request);
        DataGeneration generateEmail = new DataGeneration();
        APIResponse response = registrationFacade.emailRegister(generateEmail.generateEmail(), ConfigProvider.getPassword());
        Assert.assertEquals(response.status(), 200, "Registration failed");
        Assert.assertNotNull(SessionContext.getToken(), "Token was not saved to session context");
    }
}
