package APITests;

import Resources.APIService;
import PayLoad.PayLoadAPI;
import Resources.Utilities;
import Tests.BaseTest;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.Test;

public class AppTest extends BaseTest {

    private static final Logger log = LogManager.getLogger(AppTest.class.getName());

    @Test
    public void appTest() {

        log.info("Sending the graphQL request to the server");

        String payload = PayLoadAPI.getApp();
        Response res = APIService.sendAPIRequest(payload);

        JsonPath js = Utilities.RawToJSON(res);
        String appName = js.get("data.app.latestVersion.appName");
        String appVersion = js.get("data.app.latestVersion.appVersion");

        log.info("The status code for app api is: " + res.getStatusCode());
        log.info("The App name is: " + appName);
        log.info("The App version is: " + appVersion);

        Assert.assertNotNull(appName);
        Assert.assertNotNull(appVersion);

    }

}