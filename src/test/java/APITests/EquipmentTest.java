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

public class EquipmentTest extends BaseTest {

    private static final Logger log = LogManager.getLogger(EquipmentTest.class.getName());

    @Test
    public void equipTest() {

        log.info("Sending the graphQL request to the server");

        String payload = PayLoadAPI.getEquipment();
        Response res = APIService.sendAPIRequest(payload);

        JsonPath js = Utilities.RawToJSON(res);
        String name = js.get("data.equipment[0].name");
        String model = js.get("data.equipment[0].model");
        String id = js.get("data.equipment[0]._id");

        log.info("The status code for equipment api is: " + res.getStatusCode());
        log.info("The Equipment name is: " + name);
        log.info("The Equipment id is: " + id);
        log.info("The Equipment model is: " + model);

        Assert.assertNotNull(id);

    }

}