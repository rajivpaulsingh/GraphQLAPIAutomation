package APITests;

import Resources.APIService;
import PayLoad.PayLoadAPI;
import PayLoad.Utilities;
import Tests.BaseTest;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.Test;

public class SimulatorConfigTest extends BaseTest {

    private static final Logger log = LogManager.getLogger(SimulatorConfigTest.class.getName());

    @Test
    public void configTest() {

        log.info("Sending the graphQL request to the server");
        String payload = PayLoadAPI.getSimulatorConfig();
        Response res = APIService.sendAPIRequest(payload);

        JsonPath js = Utilities.RawToJSON(res);
        int engineRpm = js.get("data.getSimulatorConfig.engineRpm.rpm");
        int cylinderPressureCompression = js.get("data.getSimulatorConfig.cylinderPressure.compression");
        int cylinderPressureMax = js.get("data.getSimulatorConfig.cylinderPressure.maximum");

        log.info("The status code for getSimulatorConfig api is: " + res.getStatusCode());
        log.info("The engine rpm is: " + engineRpm);
        log.info("The Cylinder Pressure compression is: " + cylinderPressureCompression);
        log.info("The Cylinder Pressure maximum is: " + cylinderPressureMax);

        Assert.assertNotNull(engineRpm);
        Assert.assertNotNull(cylinderPressureCompression);
        Assert.assertNotNull(cylinderPressureMax);

    }

}
