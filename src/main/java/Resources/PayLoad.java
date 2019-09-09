package Resources;

import org.json.JSONObject;

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

    public static String getMaintenanceHistory() {

        String value = "query {\n" +
                "  maintenanceHistory\n" +
                "  {\n" +
                "    cursor\n" +
                "    overdueCount\n" +
                "    maintenanceJobs\n" +
                "    {\n" +
                "      _id\n" +
                "      title\n" +
                "      name\n" +
                "      shipName\n" +
                "      manualUrl\n" +
                "      description\n" +
                "      scheduledDate\n" +
                "      performedDate\n" +
                "      completedDate\n" +
                "    }\n" +
                "  }\n" +
                "}";

        String payload = Utilities.graphqlToJsonString(value);
        return payload;
    }

    public static String getMaintenanceIssues() {

        String value = "query {\n" +
                "  maintenanceIssues(filter: {\n" +
                "  equipmentId: \"01DE8GT5GMXAPN5JKH8486Z7A2\"\n" +
                " }) {\n" +
                "    _id\n" +
                "    name\n" +
                "    likelihood\n" +
                "    checks {\n" +
                "      name\n" +
                "      value\n" +
                "    }\n" +
                "    correctiveActions {\n" +
                "      name\n" +
                "    }\n" +
                "  }\n" +
                "}";

        String variables = "{\n" +
                " \"issuesFilter\": {\n" +
                "  \"equipmentId\": \"01DE8GT5GMXAPN5JKH8486Z7A2\"\n" +
                " }\n" +
                "}";

        String payload = Utilities.graphqlWithVariablesToJsonString(value, variables);
        return payload;
    }

}
