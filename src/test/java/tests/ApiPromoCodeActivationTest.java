package tests;

import api.business.AuthFacade;
import api.business.PromoCodeActivationFacade;
import base.BaseApiTest;
import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;
import utils.ConfigProvider;
import utils.DataGeneration;

@Epic("SlotCity API Testing")
@Feature("Promo Code Activation")
public class ApiPromoCodeActivationTest extends BaseApiTest {
    private static final String PROMO_CODE = "EDC26BD6";
    @Test(description = "Verify successful promo code activation")
    @Description("The test performs API authorization and attempts to activate a valid promo code")
    public void testActivationPromo() {
        AuthFacade authFacade = new AuthFacade();
        String randomEmail = DataGeneration.generateEmail();
        authFacade.emailRegister(randomEmail, ConfigProvider.getPassword());
        authFacade.emailLogin(randomEmail, ConfigProvider.getPassword());

        PromoCodeActivationFacade promoFacade = new PromoCodeActivationFacade();
        Response promoResponse = promoFacade.promoActivation(PROMO_CODE);

        System.out.println("Response for promocode activation: " + promoResponse.asString());
        Assert.assertEquals(promoResponse.statusCode(), 200, "Promocode is failed to activate");
    }
}
