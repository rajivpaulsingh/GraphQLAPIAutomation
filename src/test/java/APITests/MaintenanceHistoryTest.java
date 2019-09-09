package APITests;

import Resources.PayLoad;
import Resources.Utilities;
import io.restassured.RestAssured;
import io.restassured.config.EncoderConfig;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import static io.restassured.RestAssured.baseURI;
import static io.restassured.RestAssured.given;

public class MaintenanceHistoryTest {

    Properties prop = new Properties();
    EncoderConfig encoderConfig = new EncoderConfig();
    private static final Logger log = LogManager.getLogger(MaintenanceHistoryTest.class.getName());

    @BeforeTest
    public void getData() throws IOException {

        FileInputStream fis = new FileInputStream
                (System.getProperty("user.dir") + "/src/main/java/Resources/env.properties");
        prop.load(fis);
        BasicConfigurator.configure();
        baseURI = prop.getProperty("HOST");

    }

    @Test
    public void maintenanceHistoryTest() throws IOException {

        log.info("Sending the graphQL request to the server");
        Response res = given().
                header("Content-Type", "application/json").
                config(RestAssured.config().encoderConfig(encoderConfig.appendDefaultContentCharsetToContentTypeIfUndefined(false).encodeContentTypeAs("application/graphql", ContentType.TEXT))).
                body(PayLoad.getMaintenanceHistory()).

                when().
                post(baseURI).

                then().
                assertThat().statusCode(200).and().contentType(ContentType.JSON).

                extract().response();
        JsonPath js = Utilities.RawToJSON(res);

        int maintenanceJobsCount = js.get("data.maintenanceHistory.maintenanceJobs.size()");
        log.info("The number of active maintenance jobs are: " + maintenanceJobsCount);

        log.info("The maintenance job names are: ");
        for(int i = 0; i < maintenanceJobsCount; i++) {

            String id = js.get("data.maintenanceHistory.maintenanceJobs[" + i +"]._id");
            String name = js.get("data.maintenanceHistory.maintenanceJobs[" + i +"].name");

            Assert.assertNotNull(id);
            Assert.assertNotNull(name);
            log.info(name);

        }
    }

}
