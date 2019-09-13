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


public class MaintenanceJobTest {

    private static final Logger log = LogManager.getLogger(MaintenanceJobTest.class.getName());

    @BeforeTest
    public void getData() throws IOException {

        Prerequisite.prefillData();
    }

    @Test
    public void maintenanceJobTest(){

        log.info("Sending the graphQL request to the server");
        //Get the equipment id for the next query
        String payload = PayLoad.getEquipment();
        Response res = APIService.sendAPIRequest(payload);

        JsonPath js = Utilities.RawToJSON(res);
        String equipmentID = js.get("data.equipment[0]._id");

        if (equipmentID == null) {
            Assert.fail("We do not have any equipment id");
        }
        log.info("The equipment id is: " + equipmentID); //Use this equipment id in the maintenance job api query

        String payload1 = PayLoad.getMaintenanceJob(equipmentID);
        Response res1 = APIService.sendAPIRequest(payload1);

        JsonPath js1 = Utilities.RawToJSON(res1);
        int maintenanceJobs = js1.get("data.plannedMaintenance.maintenanceJobs.size()");
        log.info("The number of planned maintenance jobs are: " + maintenanceJobs);

        String plannedMaintenanceID = js1.get("data.plannedMaintenance.maintenanceJobs[0]._id");
        log.info("The planned maintenance id is: " + plannedMaintenanceID);

        String plannedMaintenanceName = js1.get("data.plannedMaintenance.maintenanceJobs[0].name");
        log.info("The planned maintenance name is: " + plannedMaintenanceName);

        String status = js1.get("data.plannedMaintenance.maintenanceJobs[0].status");
        log.info("The status of the planned maintenance is: " + status);

    }

}
