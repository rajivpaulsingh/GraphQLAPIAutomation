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

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class GetDiagnosticTrackingTest extends BaseTest {

    private static final Logger log = LogManager.getLogger(GetDiagnosticTrackingTest.class.getName());

    @Test
    public void diagnosticTrackingTest() {

        DateFormat dateFormatForAPI = new SimpleDateFormat("yyyyMMdd");
        DateFormat dateFormatForValidation = new SimpleDateFormat("yyyy-MM-dd");

        Date dateForAPI = new Date();
        Date dateForValidation = new Date();

        String todaysDate = dateFormatForAPI.format(dateForAPI);
        String expectedDate = dateFormatForValidation.format(dateForValidation);

        log.info("Sending the graphQL request to the server");
        String payload = PayLoadAPI.getDiagnosticsTracking(todaysDate);
        Response res = APIService.sendAPIRequest(payload);

        JsonPath js = Utilities.RawToJSON(res);
        System.out.println(res.asString());
        String vesseldID = js.get("data.getDiagnosticTracking.vesselId");
        String actualDate = js.get("data.getDiagnosticTracking.startDate");

        log.info("The vessel id is: " + vesseldID);
        Assert.assertEquals(actualDate, expectedDate);

    }

}