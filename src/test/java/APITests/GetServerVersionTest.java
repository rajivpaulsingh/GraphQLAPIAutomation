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

public class GetServerVersionTest {

    Properties prop = new Properties();
    EncoderConfig encoderConfig = new EncoderConfig();
    private static final Logger log = LogManager.getLogger(GetServerVersionTest.class.getName());

    @BeforeTest
    public void getData() throws IOException {

        FileInputStream fis = new FileInputStream
                (System.getProperty("user.dir") + "/src/main/java/Resources/env.properties");
        prop.load(fis);
        BasicConfigurator.configure();
        baseURI = prop.getProperty("HOST");

    }

    @Test
    public void versionTest() throws IOException {

        log.info("Sending the graphQL request to the server");
        Response res = given().
                header("Content-Type", "application/json").
                config(RestAssured.config().encoderConfig(encoderConfig.appendDefaultContentCharsetToContentTypeIfUndefined(false).encodeContentTypeAs("application/graphql", ContentType.TEXT))).
                body(PayLoad.getServerVersion()).

                when().
                post(baseURI).

                then().
                assertThat().statusCode(200).and().contentType(ContentType.JSON).

                extract().response();
        JsonPath js = Utilities.RawToJSON(res);
        String version = js.get("data.getServerVersion.commitShortSHA");

        log.info("The status code for getServerVersion api is: " + res.getStatusCode());
        log.info("The current server versions: " + js.get("data.getServerVersion.commitShortSHA"));

        Assert.assertNotNull(version);

    }

}