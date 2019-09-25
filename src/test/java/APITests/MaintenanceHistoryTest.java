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

public class MaintenanceHistoryTest extends BaseTest {

    private static final Logger log = LogManager.getLogger(MaintenanceHistoryTest.class.getName());

    @Test
    public void maintenanceHistoryTest() {

        log.info("Sending the graphQL request to the server");
        String payload = PayLoadAPI.getMaintenanceHistory();
        Response res = APIService.sendAPIRequest(payload);

        JsonPath js = Utilities.RawToJSON(res);
        int maintenanceJobsCount = js.get("data.maintenanceHistory.maintenanceJobs.size()");
        log.info("The number of active maintenance jobs are: " + maintenanceJobsCount);

        log.info("The maintenance job names are: ");
        for(int i = 0; i < maintenanceJobsCount; i++) {

            String id = js.get("data.maintenanceHistory.maintenanceJobs[" + i +"]._id");
            String name = js.get("data.maintenanceHistory.maintenanceJobs[" + i +"].name");

            Assert.assertNotNull(id);
            Assert.assertNotNull(name);
            log.info(name);

        }
    }

}
