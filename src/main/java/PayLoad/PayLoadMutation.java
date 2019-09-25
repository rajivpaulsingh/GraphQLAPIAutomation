package PayLoad;

import Resources.Utilities;

public class PayLoadMutation {

    public static String saveFeedback() {

        String value = "mutation saveFeedback ($userFeedback: UserFeedbackInput!){\n" +
                "  saveFeedback (userFeedback: $userFeedback) {\n" +
                "    _id\n" +
                "    name\n" +
                "    rank\n" +
                "    createdDate\n" +
                "    vesselId\n" +
                "    feedback\n" +
                "  }\n" +
                "}";

        String variables = "{\n" +
                "  \"userFeedback\": {\n" +
                "    \"name\": \"Test Feedback\",\n" +
                "    \"rank\": \"QA\",\n" +
                "    \"feedback\": \"This feedback was created by automation\"\n" +
                "  }\n" +
                "}";

        String payload = Utilities.graphqlWithVariablesToJsonString(value, variables);
        return payload;
    }

    public static String resetEngineCount(String equipmentID) {

        String value = "mutation resetEngineCounter ($inputRunningHours: RunningHoursInput!) {\n" +
                "  resetEngineCounter (inputRunningHours: $inputRunningHours) {\n" +
                "    _id\n" +
                "    model\n" +
                "    name\n" +
                "    serialNumber\n" +
                "    manufacturer\n" +
                "    generatorType\n" +
                "    firingOrder\n" +
                "    isOnline\n" +
                "    imageUrl\n" +
                "    \n" +
                "    subEquipment\n" +
                "    {\n" +
                "      name\n" +
                "    }\n" +
                "    runningHours\n" +
                "  }\n" +
                "}";

        String variables = "{\n" +
                "  \"inputRunningHours\": {\n" +
                "    \"newRunningHours\": 10,\n" +
                "    \"id\": \"" + equipmentID + "\"\n" +
                "  }\n" +
                "}";

        String payload = Utilities.graphqlWithVariablesToJsonString(value, variables);
        return payload;
    }

    public static String updatimulationConfig() {

        String value = "mutation updateSimulationConfig($config: SimulatorConfigInput!) {\n" +
                "  updateSimulator(config: $config) {\n" +
                "    sensors\n" +
                "    {\n" +
                "      TT_0112\n" +
                "      {\n" +
                "        operator\n" +
                "        value\n" +
                "      }\n" +
                "      TT_0110\n" +
                "      {\n" +
                "        operator\n" +
                "        value\n" +
                "      }\n" +
                "      TT_0111\n" +
                "      {\n" +
                "        operator\n" +
                "        value\n" +
                "      }\n" +
                "      TT_0115\n" +
                "      {\n" +
                "        operator\n" +
                "        value\n" +
                "      }\n" +
                "      PT_015001\n" +
                "      {\n" +
                "        operator\n" +
                "        value\n" +
                "      }\n" +
                "    }\n" +
                "  }\n" +
                "}";

        String variables = "{  \n" +
                "  \"config\": {\n" +
                "    \"sensors\": {\n" +
                "      \"TT_0112\": {\n" +
                "        \"operator\": \"EQUAL\",\n" +
                "        \"value\": 88\n" +
                "      },\n" +
                "      \"TT_0110\": {\n" +
                "        \"operator\": \"EQUAL\",\n" +
                "        \"value\": 88\n" +
                "      },\n" +
                "      \"TT_0111\": {\n" +
                "        \"operator\": \"EQUAL\",\n" +
                "        \"value\": 88\n" +
                "      },\n" +
                "      \"PT_015001\": {\n" +
                "        \"operator\": \"EQUAL\",\n" +
                "        \"value\": 88  \n" +
                "      }\n" +
                "    }\n" +
                "  }\n" +
                "}";

        String payload = Utilities.graphqlWithVariablesToJsonString(value, variables);
        return payload;
    }

    public static String updateDiagnosticsTracking () {

        String value = "mutation updateDiagnosticsTracking ($diagnosticsTracking:DiagnosticAnomaliesInput!) {\n" +
                "  updateDiagnosticsTracking (diagnosticsTracking:$diagnosticsTracking){\n" +
                "    _id\n" +
                "    _rev\n" +
                "    vesselId\n" +
                "    anomalies {\n" +
                "      deviceId\n" +
                "      description\n" +
                "      metric\n" +
                "      duration\n" +
                "      acknowledgement\n" +
                "    }\n" +
                "  }\n" +
                "}";

        String variables = "{\n" +
                "  \"diagnosticsTracking\": {\n" +
                "    \"deviceId\": \"test\",\n" +
                "    \"description\": \"testing\",\n" +
                "    \"metric\": \"MILLISECONDS\",\n" +
                "    \"duration\": 50000,\n" +
                "    \"acknowledgement\": \"ADDRESSED\"\n" +
                "  }\n" +
                "}";

        String payload = Utilities.graphqlWithVariablesToJsonString(value, variables);
        return payload;
    }

    public static String createCompletedMaintenance (String date) {

        String value = "mutation createCompletedMaintenanceJob ($userIssueMaintenance: MaintenanceJobUserIssueInput!){\n" +
                "  createCompletedMaintenanceJob(userIssueMaintenance: $userIssueMaintenance)\n" +
                "   {\n" +
                "    _id\n" +
                "    title\n" +
                "    status\n" +
                "    description\n" +
                "    shipName\n" +
                "    equipmentName\n" +
                "    materials\n" +
                "    {\n" +
                "      name\n" +
                "      quantity\n" +
                "    }\n" +
                "  }\n" +
                "}";

        String variables = "{\n" +
                "  \"userIssueMaintenance\": {\n" +
                "    \"sensorSnapshots\": {\n" +
                "        \"_id\": \"01DE5VTVHE9GRQZMF9M3T8QPN9\",\n" +
                "        \"identifier\":\"ZT-016001\",\n" +
                "        \"name\":\"Engine speed\",\n" +
                "        \"reading\": {\n" +
                "          \"timestamp\":\"" + date + "T17:26:00.106Z\",\n" +
                "          \"value\":70,\n" +
                "          \"unitType\":\"RPM\"\n" +
                "        }\n" +
                "      }\n" +
                "    }\n" +
                "}";

        String payload = Utilities.graphqlWithVariablesToJsonString(value, variables);
        return payload;
    }

    public static String createMaintenanceJob (String id) {

        String value = "mutation CreateMaintenanceJob ($maintenanceIssueId: ID!) {\n" +
                "  createMaintenanceJob (maintenanceIssueId: $maintenanceIssueId) {\n" +
                "    _id\n" +
                "    title\n" +
                "    name\n" +
                "    status\n" +
                "    manualUrl\n" +
                "    equipmentName\n" +
                "    documentNumber\n" +
                "    scheduledDate\n" +
                "    priority\n" +
                "    shipName\n" +
                "    issue {\n" +
                "      id\n" +
                "      isProblemFixed\n" +
                "    }\n" +
                "  }\n" +
                "}";

        String variables = "{\n" +
                "  \"maintenanceIssueId\" : \"" + id + "\"\n" +
                "}";

        String payload = Utilities.graphqlWithVariablesToJsonString(value, variables);
        return payload;
    }

}
