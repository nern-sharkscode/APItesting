package api.services;

import api.models.request.PromoCodeRequest;
import com.microsoft.playwright.APIRequestContext;
import com.microsoft.playwright.APIResponse;
import com.microsoft.playwright.options.RequestOptions;
import utils.AllureLogger;
import utils.SessionContext;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class PromoCodeService {
    private APIRequestContext request;
    private static final Logger logger = LogManager.getLogger(PromoCodeService.class);

    public PromoCodeService(APIRequestContext request) {
        this.request = request;
    }

    public APIResponse postApplyPromoCode(PromoCodeRequest payload) {
        logger.info("[POST] /apiv2/promocodes/activate | Code: {}", payload.code);
        AllureLogger.logToAllure("Request Payload", "Code: " + payload.code);

        APIResponse response = request.post("/apiv2/promocodes/activate",
                RequestOptions.create()
                        .setHeader("Authorization", "Bearer " + SessionContext.getToken())
                        .setData(payload)
        );
        logger.info("[RESPONSE] Status: {}", response.status());
        return response;
    }
}
