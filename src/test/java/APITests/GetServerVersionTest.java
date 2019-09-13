package APITests;

import Resources.APIService;
import Resources.PayLoad;
import Resources.Prerequisite;
import Resources.Utilities;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.io.IOException;

public class GetServerVersionTest {

    private static final Logger log = LogManager.getLogger(GetServerVersionTest.class.getName());

    @BeforeTest
    public void getData() throws IOException {

        Prerequisite.prefillData();
    }

    @Test
    public void versionTest() {

        log.info("Sending the graphQL request to the server");
        String payload = PayLoad.getServerVersion();
        Response res = APIService.sendAPIRequest(payload);

        JsonPath js = Utilities.RawToJSON(res);
        String version = js.get("data.getServerVersion.commitShortSHA");

        log.info("The status code for getServerVersion api is: " + res.getStatusCode());
        log.info("The current server versions: " + js.get("data.getServerVersion.commitShortSHA"));

        Assert.assertNotNull(version);

    }

}