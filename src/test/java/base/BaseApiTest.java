package base;

import io.restassured.RestAssured;
import org.testng.annotations.BeforeClass;
import utils.ConfigProvider;

public class BaseApiTest {

    @BeforeClass
    public void setupApiContext() {
        RestAssured.baseURI = ConfigProvider.getBaseUrl();
    }
}
