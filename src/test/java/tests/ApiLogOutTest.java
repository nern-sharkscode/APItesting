package tests;

import api.business.AuthFacade;
import base.BaseApiTest;
import org.testng.Assert;
import org.testng.annotations.Test;
import utils.ConfigProvider;
import utils.DataGeneration;
import utils.SessionContext;

public class ApiLogOutTest extends BaseApiTest {
    @Test(description ="LogOut current user")
    public void logOutTest() {
        AuthFacade authFacade = new AuthFacade();
        String randomEmail = DataGeneration.generateEmail();
        authFacade.emailRegister(randomEmail, ConfigProvider.getInstance().getPassword());
        var loginResponse = authFacade.emailLogin(randomEmail, ConfigProvider.getInstance().getPassword());
        Assert.assertEquals(loginResponse.statusCode(),200,"Login failed");

        authFacade.logOut();
        Assert.assertNull(SessionContext.getToken(),"Token should be null after logout");
    }

}
