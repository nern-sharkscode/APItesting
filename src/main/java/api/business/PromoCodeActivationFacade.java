package api.business;

import api.models.request.PromoCodeRequest;
import api.services.PromoCodeService;
import io.qameta.allure.Step;
import io.restassured.response.Response;

public class PromoCodeActivationFacade {
    private PromoCodeService promoCodeActivationService;

    public PromoCodeActivationFacade(){
        this.promoCodeActivationService = new PromoCodeService();

    }
    @Step("Activate promo code: {promoCode}")
    public Response promoActivation(String promoCode){
        PromoCodeRequest payload = new PromoCodeRequest(promoCode);
        return promoCodeActivationService.postApplyPromoCode(payload);
    }

}
