package base;

import com.microsoft.playwright.APIRequest;
import com.microsoft.playwright.APIRequestContext;
import com.microsoft.playwright.Playwright;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import utils.ConfigProvider;

import java.util.HashMap;
import java.util.Map;

public class BaseApiTest {
    protected Playwright playwright;
    protected APIRequestContext request;

    @BeforeClass
    public void setupApiContext() {
        Map<String, String> env = new HashMap<>();
        env.put("PLAYWRIGHT_SKIP_BROWSER_DOWNLOAD", "1");
        playwright = Playwright.create(new Playwright.CreateOptions().setEnv(env));

        Map<String, String> headers = new HashMap<>();
        headers.put("Content-Type", "application/json");
        headers.put("Accept", "application/json");

        request = playwright.request().newContext(new APIRequest.NewContextOptions()
                .setBaseURL(ConfigProvider.getBaseUrl())
                .setExtraHTTPHeaders(headers));
    }

    @AfterClass
    public void tearDownApiContext() {
        request.dispose();
        playwright.close();
    }
}
