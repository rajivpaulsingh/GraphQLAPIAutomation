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

public class LiveDataTest {

    private static final Logger log = LogManager.getLogger(LiveDataTest.class.getName());

    @BeforeTest
    public void getData() throws IOException {

        Prerequisite.prefillData();
    }

    @Test
    public void liveDataTest() {

        log.info("Sending the graphQL request to the server");
        String payload = PayLoad.getLiveData();
        Response res = APIService.sendAPIRequest(payload);

        JsonPath js = Utilities.RawToJSON(res);

        int cylinders = js.get("data.liveData.data.size()");
        log.info("We have " + cylinders + " cylinders ");

        if (cylinders > 0) {

            for(int i = 0; i < cylinders; i++) {

                int values = js.get("data.liveData.data[+ " + i + "].values.size()");
                if(values > 0) {
                    String compressionIndex = js.get("data.liveData.data[+ " + i + "].compressionIndex").toString();
                    String compressionPressure = js.get("data.liveData.data[+ " + i + "].compressionPressure").toString();
                    String maxPressure = js.get("data.liveData.data[+ " + i + "].maximumPressure").toString();
                    String maxIndex = js.get("data.liveData.data[+ " + i + "].maximumIndex").toString();
                    log.info("Compression index of cylinder " + i + " is: " + compressionIndex);
                    log.info("Compression pressure of cylinder " + i + " is: " + compressionPressure);
                    log.info("Maximum pressure of cylinder " + i + " is: " + maxPressure);
                    log.info("Maximum index of cylinder " + i + " is: " + maxIndex);
                }
                else {
                    log.error("None of the cylinders are showing any values");
                }
            }
        }
    }

}