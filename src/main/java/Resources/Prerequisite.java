package Resources;

import org.apache.log4j.BasicConfigurator;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import static io.restassured.RestAssured.baseURI;

public class Prerequisite {

    public static void prefillData() throws IOException {

        Properties prop = new Properties();

        FileInputStream fis = new FileInputStream
                (System.getProperty("user.dir") + "/src/main/java/Resources/env.properties");
        prop.load(fis);
        BasicConfigurator.configure();
        baseURI = prop.getProperty("HOST");

    }
}
