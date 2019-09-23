package MutationTests;

import PayLoad.PayLoadAPI;
import PayLoad.PayLoadMutation;
import PayLoad.Utilities;
import Resources.*;
import Tests.BaseTest;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.Test;

public class ResetEngineCounterTest extends BaseTest {

    private static int EXPECTED_ENGINE_COUNTER = 10;

    private static final Logger log = LogManager.getLogger(ResetEngineCounterTest.class.getName());

    @Test
    public void resetEngineTest(){

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

        String payload1 = PayLoadMutation.getResetEngineCount(equipmentID);
        Response res1 = APIService.sendAPIRequest(payload1);

        JsonPath js1 = Utilities.RawToJSON(res1);
        log.info("The status code for reset engine counter mutation is: " + res1.getStatusCode());

        int newEngineCounter = js1.get("data.resetEngineCounter.runningHours");
        log.info("The new engine counter for the engine is: " + newEngineCounter);

        Assert.assertEquals(newEngineCounter, EXPECTED_ENGINE_COUNTER);

    }
}
