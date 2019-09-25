package MutationTests;

import PayLoad.PayLoadAPI;
import PayLoad.PayLoadMutation;
import Resources.Utilities;
import Resources.*;
import Tests.BaseTest;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.Test;

public class CreateMaintenanceJob extends BaseTest {

    private static String EXPECTED_STATUS = "CREATED";
    private static final Logger log = LogManager.getLogger(CreateMaintenanceJob.class.getName());

    @Test
    public void createMaintenanceJob(){

        log.info("Sending the graphQL request to the server");
        //Get the equipment id for the next query
        String payload = PayLoadAPI.getEquipment();
        Response res = APIService.sendAPIRequest(payload);

        JsonPath js = Utilities.RawToJSON(res);
        String equipmentID = js.get("data.equipment[0]._id");

        if (equipmentID == null) {
            Assert.fail("We do not have any equipment");
        }
        log.info("The equipment id is: " + equipmentID); //Use this equipment id in the maintenance issue query

        String payload1 = PayLoadAPI.getMaintenanceIssues(equipmentID);
        Response res1 = APIService.sendAPIRequest(payload1);

        JsonPath js1 = Utilities.RawToJSON(res1);
        int maintenanceIssuesCount = js1.get("data.maintenanceIssues.size()");
        log.info("The number of maintenance issues are: " + maintenanceIssuesCount);

        if (maintenanceIssuesCount > 0) {

            String id = js1.get("data.maintenanceIssues[0]._id"); //Use this maintenance issue id to create the job

            String payload2 = PayLoadMutation.createMaintenanceJob(id);
            Response res2 = APIService.sendAPIRequest(payload2);

            JsonPath js2 = Utilities.RawToJSON(res2);
            log.info("The status code for this mutation is: " + res2.getStatusCode());

            String status = js2.get("data.createMaintenanceJob.status");
            String id2 = js2.get("data.createMaintenanceJob._id");
            System.out.println(res2.asString());
            log.info("The created maintenance job id is: " + id2);
            log.info("The created maintenance job status is: " + status);

            Assert.assertNotNull(id2);
            Assert.assertEquals(status, EXPECTED_STATUS);
        }
        else {
            log.error("There is no maintenance issues");
        }

    }
}
