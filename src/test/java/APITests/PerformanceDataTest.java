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

public class PerformanceDataTest {

    private static final Logger log = LogManager.getLogger(PerformanceDataTest.class.getName());

    @BeforeTest
    public void getData() throws IOException {

        Prerequisite.prefillData();
    }

    @Test
    public void perfDataTest() {

        log.info("Sending the graphQL request to the server");
        String payload = PayLoad.getPerformanceData();
        Response res = APIService.sendAPIRequest(payload);

        JsonPath js = Utilities.RawToJSON(res);
        String chartType = js.get("data.performanceData.chartType");
        log.info("The chart type is: " + chartType);
        
        int perfDataCount = js.get("data.performanceData.data.size()");
        log.info("The number of data available is: " + perfDataCount);

        for(int i = 0; i < perfDataCount; i++) {

            String sensorsReadingCount = js.get("data.performanceData.data[" + i + "].sensorIdentifier");
            Assert.assertNotNull(sensorsReadingCount);
        }
    }

}
