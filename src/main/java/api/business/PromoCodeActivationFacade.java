package api.business;

import api.models.request.PromoCodeRequest;
import api.services.PromoCodeService;
import com.microsoft.playwright.APIRequestContext;
import com.microsoft.playwright.APIResponse;
import io.qameta.allure.Step;

public class PromoCodeActivationFacade {
    private PromoCodeService promoCodeActivationService;

    public PromoCodeActivationFacade(APIRequestContext request){
        this.promoCodeActivationService = new PromoCodeService(request);

    }
    @Step("Activate promo code: {promoCode}")
    public APIResponse promoActivation(String promoCode){
        PromoCodeRequest payload = new PromoCodeRequest(promoCode);
        return promoCodeActivationService.postApplyPromoCode(payload);
    }

}
