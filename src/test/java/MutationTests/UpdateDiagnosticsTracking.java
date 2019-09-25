package MutationTests;

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

public class UpdateDiagnosticsTracking extends BaseTest {

    private static int EXPECTED_DURATION = 50000;
    private static String EXPECTED_ACKNOWLEDGEMENT = "ADDRESSED";

    private static final Logger log = LogManager.getLogger(UpdateDiagnosticsTracking.class.getName());

    @Test
    public void updateDiagnosticsTest(){

        log.info("Sending the graphQL request to the server");
        String payload = PayLoadMutation.updateDiagnosticsTracking();
        Response res = APIService.sendAPIRequest(payload);

        JsonPath js = Utilities.RawToJSON(res);
        log.info("The status code for update diagnostics tracking mutation is: " + res.getStatusCode());

        int numberOfAnomalies = js.get("data.updateDiagnosticsTracking.anomalies.size()");
        log.info("The number of anomalies found are: " + numberOfAnomalies);

        if (numberOfAnomalies > 0) {

            for(int i = 0; i < numberOfAnomalies; i++) {

                String acknowledgement = js.get("data.updateDiagnosticsTracking.anomalies[" + i + "].acknowledgement");
                int actualDuration = js.get("data.updateDiagnosticsTracking.anomalies[" + i + "].duration");
                if (acknowledgement.equalsIgnoreCase("ADDRESSED") && (actualDuration == EXPECTED_DURATION)) {

                    Assert.assertEquals(actualDuration, EXPECTED_DURATION);
                    Assert.assertEquals(acknowledgement, EXPECTED_ACKNOWLEDGEMENT);
                }
            }
        }

    }
}
