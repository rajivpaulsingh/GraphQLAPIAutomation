package MutationTests;

import Resources.APIService;
import PayLoad.PayLoadMutation;
import Resources.Utilities;
import Tests.BaseTest;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.testng.annotations.Test;

public class SaveFeedbackTest extends BaseTest {

    private static final Logger log = LogManager.getLogger(SaveFeedbackTest.class.getName());

    @Test
    public void saveFeedbackTest()  {

        log.info("Sending the graphQL request to the server");
        String payload = PayLoadMutation.saveFeedback();
        Response res = APIService.sendAPIRequest(payload);

        JsonPath js = Utilities.RawToJSON(res);
        log.info("The status code for save feedback mutation is: " + res.getStatusCode());

        String feedbackID = js.get("data.saveFeedback._id");
        log.info("The created feedback id is: " + feedbackID);

        String feedbackName = js.get("data.saveFeedback.name");
        log.info("The created feedback name is: " + feedbackName);

    }

}
