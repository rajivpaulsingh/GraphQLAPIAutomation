package Resources;

import java.io.File;

public class PayLoad {

    public static String getSimulatorConfig() {

        String value = "query{\n" +
                "  getSimulatorConfig {\n" +
                "    sensors {\n" +
                "      TT_0110 {\n" +
                "        operator\n" +
                "        value\n" +
                "      }\n" +
                "      PT_015001 {\n" +
                "        operator\n" +
                "        value\n" +
                "      }\n" +
                "    }\n" +
                "    engineRpm {\n" +
                "      rpm\n" +
                "    }\n" +
                "    cylinderPressure {\n" +
                "      compression\n" +
                "      maximum\n" +
                "    }\n" +
                "  }\n" +
                "}";

        String payload = Utilities.graphqlToJsonString(value);
        return payload;
    }

    public static String getServerVersion() {

        String value = "query{\n" +
                "  getServerVersion {\n" +
                "    commitShortSHA\n" +
                "    commitTimeISO\n" +
                "    dateDeployed\n" +
                "  }\n" +
                "}";

        String payload = Utilities.graphqlToJsonString(value);
        return payload;
    }

    public static String getSensorsData() {

        String value = "query{\n" +
                "  sensors {\n" +
                "    _id\n" +
                "    identifier\n" +
                "    name\n" +
                "    unitType\n" +
                "    reading {\n" +
                "      timestamp\n" +
                "      value\n" +
                "      unitType\n" +
                "    }\n" +
                "    hasAnomaly\n" +
                "  }\n" +
                "}";

        String payload = Utilities.graphqlToJsonString(value);
        return payload;
    }

    public static String getEquipment() {

        String value = "query {\n" +
                "  equipment\n" +
                "  {\n" +
                "    _id\n" +
                "    model\n" +
                "    name\n" +
                "    serialNumber\n" +
                "    generatorType\n" +
                "    manufacturer\n" +
                "    runningHours\n" +
                "  }\n" +
                "}";

        String payload = Utilities.graphqlToJsonString(value);
        return payload;

    }

}
