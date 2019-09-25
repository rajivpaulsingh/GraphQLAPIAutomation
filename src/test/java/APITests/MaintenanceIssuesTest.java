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

public class MaintenanceIssuesTest extends BaseTest {

    private static final Logger log = LogManager.getLogger(MaintenanceIssuesTest.class.getName());

    @Test
    public void maintenanceIssuesTest() {

        log.info("Sending the graphQL request to the server");

        //Get the equipment id for the next query
        String payload = PayLoadAPI.getEquipment();
        Response res = APIService.sendAPIRequest(payload);

        JsonPath js = Utilities.RawToJSON(res);
        String equipmentID = js.get("data.equipment[0]._id");

        if (equipmentID == null) {
            Assert.fail("We do not have any equipment");
        }
        log.info("The equipment id is: " + equipmentID); //Use this equipment id in the maintenance job api query

        String payload1 = PayLoadAPI.getMaintenanceIssues(equipmentID);
        Response res1 = APIService.sendAPIRequest(payload1);

        JsonPath js1 = Utilities.RawToJSON(res1);
        int maintenanceIssuesCount = js1.get("data.maintenanceIssues.size()");
        log.info("The number of maintenance issues are: " + maintenanceIssuesCount);

        if (maintenanceIssuesCount > 0) {
            for(int i = 0; i < maintenanceIssuesCount; i++) {

                String id = js1.get("data.maintenanceIssues[" + i +"]._id");
                String name = js1.get("data.maintenanceIssues[" + i +"].name");

                Assert.assertNotNull(id);
                Assert.assertNotNull(name);
                log.info("The maintenance issues id: " + id);
                log.info("The maintenance issues name: " + name);

            }
        }
    }

}
