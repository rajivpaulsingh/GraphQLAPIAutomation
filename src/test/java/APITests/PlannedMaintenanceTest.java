package APITests;

import Resources.APIService;
import Resources.PayLoad;
import Resources.Prerequisite;
import Resources.Utilities;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.io.IOException;

public class PlannedMaintenanceTest {

    private static final Logger log = LogManager.getLogger(PlannedMaintenanceTest.class.getName());

    @BeforeTest
    public void getData() throws IOException {

        Prerequisite.prefillData();
    }

    @Test
    public void plannedMaintenanceTest() {

        log.info("Sending the graphQL request to the server");
        String payload = PayLoad.getPlannedMaintenance();
        Response res = APIService.sendAPIRequest(payload);

        JsonPath js = Utilities.RawToJSON(res);
        int overDue = js.get("data.plannedMaintenance.overdueCount");
        log.info("The overdue maintenance job count is: " + overDue);

        int maintenanceJobs = js.get("data.plannedMaintenance.maintenanceJobs.size()");
        log.info("The number of planned maintenance jobs are: " + maintenanceJobs);

        if (maintenanceJobs > 0) {

            for(int i = 0; i < maintenanceJobs; i++) {

                String id = js.get("data.plannedMaintenance.maintenanceJobs[" + i + "]._id");
                String name = js.get("data.plannedMaintenance.maintenanceJobs[" + i + "].name");

                log.info("The id of the planned maintenance job is: " + id);
                log.info("The name of the planned maintenance job is: " + name);
            }
        }
    }

}
