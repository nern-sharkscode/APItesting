package tests;

import api.business.AuthFacade;
import api.business.PromoCodeActivationFacade;
import api.models.request.PromoCodeRequest;
import base.BaseApiTest;
import com.microsoft.playwright.APIResponse;
import com.microsoft.playwright.options.RequestOptions;
import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import org.testng.Assert;
import org.testng.annotations.Test;
import utils.ConfigProvider;

@Epic("SlotCity API Testing")
@Feature("Promo Code Activation")
public class ApiPromoCodeActivationTest extends BaseApiTest {
    @Test(description = "Verify successful promo code activation")
    @Description("The test performs API authorization and attempts to activate a valid promo code")
    public void testActivationPromo() throws Exception {
        AuthFacade authFacade = new AuthFacade(request);
        authFacade.emailLogin(ConfigProvider.getEmail(), ConfigProvider.getPassword());

        PromoCodeActivationFacade promoFacade = new PromoCodeActivationFacade(request);
        APIResponse promoResponse = promoFacade.promoActivation("EDC26BD6");

        System.out.println("Response for promocode activation: " + promoResponse.text());
        Assert.assertEquals(promoResponse.status(), 200, "Promocode is failed to activate");
    }
}
