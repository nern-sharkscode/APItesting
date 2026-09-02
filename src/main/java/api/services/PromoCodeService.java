package api.services;

import api.models.request.PromoCodeRequest;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import utils.AllureLogger;
import utils.SessionContext;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import static io.restassured.RestAssured.given;

public class PromoCodeService {
    private static final Logger logger = LogManager.getLogger(PromoCodeService.class);

    public PromoCodeService() {
    }

    public Response postApplyPromoCode(PromoCodeRequest payload) {
        logger.info("[POST] /apiv2/promocodes/activate | Code: {}", payload.code);
        AllureLogger.logToAllure("Request Payload", "Code: " + payload.code);

        Response response = given()
                .contentType(ContentType.JSON)
                .header("Authorization", "Bearer " + SessionContext.getToken())
                .body(payload)
                .when()
                .post("/apiv2/promocodes/activate");

        logger.info("[RESPONSE] Status: {}", response.statusCode());
        return response;
    }
}
