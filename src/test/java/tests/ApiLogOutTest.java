package tests;

import api.business.AuthFacade;
import base.BaseApiTest;
import org.testng.Assert;
import org.testng.annotations.Test;
import utils.ConfigProvider;
import utils.SessionContext;

public class ApiLogOutTest extends BaseApiTest {
    @Test(description ="LogOut current user")
    public void logOutTest() throws Exception{
        AuthFacade authFacade = new AuthFacade(request);
        var loginResponse = authFacade.emailLogin(ConfigProvider.getEmail(), ConfigProvider.getPassword());
        Assert.assertEquals(loginResponse.status(),200,"Login failed");

        authFacade.logOut();
        Assert.assertNull(SessionContext.getToken(),"Token should be null after logout");
    }

}
