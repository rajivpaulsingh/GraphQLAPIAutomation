package Tests;

import Resources.Prerequisite;
import org.testng.annotations.BeforeTest;

import java.io.IOException;

public class BaseTest {

    @BeforeTest
    protected void getData() throws IOException {

        Prerequisite.prefillData();
    }
}
