package MutationTests;

import Resources.APIService;
import PayLoad.PayLoadMutation;
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

public class CreateCompletedMaintenanceJob extends BaseTest {

    private static String EXPECTED_STATUS = "PERFORMED";
    private static final Logger log = LogManager.getLogger(CreateCompletedMaintenanceJob.class.getName());

    @Test
    public void createCompletedMaintenance()  {

        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date date = new Date();
        String todaysDate = dateFormat.format(date);

        log.info("Sending the graphQL request to the server");
        String payload = PayLoadMutation.createCompletedMaintenance(todaysDate);
        Response res = APIService.sendAPIRequest(payload);

        JsonPath js = Utilities.RawToJSON(res);
        log.info("The status code for this mutation is: " + res.getStatusCode());

        String status = js.get("data.createCompletedMaintenanceJob.status");
        String id = js.get("data.createCompletedMaintenanceJob._id");
        log.info("The created completed maintenance job id is: " + id);
        log.info("The created completed maintenance job status is: " + status);

        Assert.assertNotNull(id);
        Assert.assertEquals(status, EXPECTED_STATUS);
    }

}
