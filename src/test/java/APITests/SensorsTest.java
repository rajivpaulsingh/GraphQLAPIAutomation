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

public class SensorsTest {

    private static final Logger log = LogManager.getLogger(SensorsTest.class.getName());

    @BeforeTest
    public void getData() throws IOException {

        Prerequisite.prefillData();
    }

    @Test
    public void sensorsTest() {

        log.info("Sending the graphQL request to the server");
        String payload = PayLoad.getSensorsData();
        Response res = APIService.sendAPIRequest(payload);

        JsonPath js = Utilities.RawToJSON(res);
        log.info("The status code for sensors api is: " + res.getStatusCode());

        int count = js.get("data.sensors.size()");
        log.info("The total number of sensors are: " + count);

        String sensorName = js.get("data.sensors[5]").toString();
        int totalAnomalies = 0;

        for(int i = 0; i < count; i++) {

            if(js.get("data.sensors[" + i + "].hasAnomaly").toString().equalsIgnoreCase("true")) {
                log.error("Found an anomaly with " + i + "th sensor with name: " + js.get("data.sensors[" + i + "].name"));
                totalAnomalies++;
            }
        }
        Assert.assertNotNull(sensorName);
        if(totalAnomalies > 0) {
            log.error("There are " + totalAnomalies + " anomaly/anomalies");
            Assert.fail("Test failed because of anomaly/anomalies. Please check the logs.");
        }
    }

}
