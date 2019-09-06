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

public class SensorsTest {

    Properties prop = new Properties();
    EncoderConfig encoderConfig = new EncoderConfig();
    private static final Logger log = LogManager.getLogger(SensorsTest.class.getName());

    @BeforeTest
    public void getData() throws IOException {

        FileInputStream fis = new FileInputStream
                (System.getProperty("user.dir") + "/src/main/java/Resources/env.properties");
        prop.load(fis);
        BasicConfigurator.configure();
        baseURI = prop.getProperty("HOST");
    }

    @Test
    public void sensorsTest() throws IOException {

        log.info("Sending the graphQL request to the server");
        Response res = given().
                header("Content-Type", "application/json").
                config(RestAssured.config().encoderConfig(encoderConfig.appendDefaultContentCharsetToContentTypeIfUndefined(false).encodeContentTypeAs("application/graphql", ContentType.TEXT))).
                body(PayLoad.getSensorsData()).

                when().
                post(baseURI).

                then().
                assertThat().statusCode(200).and().contentType(ContentType.JSON).

                extract().response();

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
            Assert.fail("Test failed because of anomaly");
        }
    }

}
