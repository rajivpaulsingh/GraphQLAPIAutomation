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

public class MaintenanceIssuesTest {

    private static final Logger log = LogManager.getLogger(MaintenanceIssuesTest.class.getName());

    @BeforeTest
    public void getData() throws IOException {

        Prerequisite.prefillData();
    }

    @Test
    public void maintenanceIssuesTest() {

        log.info("Sending the graphQL request to the server");
        String payload = PayLoad.getMaintenanceIssues();
        Response res = APIService.sendAPIRequest(payload);

        JsonPath js = Utilities.RawToJSON(res);
        int maintenanceIssuesCount = js.get("data.maintenanceIssues.size()");
        log.info("The number of maintenance issues are: " + maintenanceIssuesCount);

        if (maintenanceIssuesCount > 0) {
            for(int i = 0; i < maintenanceIssuesCount; i++) {

                String id = js.get("data.maintenanceIssues[" + i +"]._id");
                String name = js.get("data.maintenanceIssues[" + i +"].name");

                Assert.assertNotNull(id);
                Assert.assertNotNull(name);
                log.info("The maintenance issues id: " + id);
                log.info("The maintenance issues name: " + name);

            }
        }
    }

}
