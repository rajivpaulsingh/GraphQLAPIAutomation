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

public class PerformanceDataTest extends BaseTest {

    private static final Logger log = LogManager.getLogger(PerformanceDataTest.class.getName());

    @Test
    public void perfDataTest() {

        log.info("Sending the graphQL request to the server");
        String payload = PayLoadAPI.getPerformanceData();
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
