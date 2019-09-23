package PayLoad;

public class PayLoadMutation {

    public static String getSaveFeedback() {

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

    public static String getResetEngineCount(String equipmentID) {

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

    public static String getUpdatedSimulation() {

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

}
