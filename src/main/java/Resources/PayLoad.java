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

    public static String getPerformanceData() {

        String value = "query getPerformanceData($chartsFilter: PerformanceDataFilter!) {\n" +
                " performanceData(filter: $chartsFilter) {\n" +
                "   data {\n" +
                "     sensorIdentifier\n" +
                "     name\n" +
                "     unitType\n" +
                "     readingType\n" +
                "     values\n" +
                "   }\n" +
                "   startDate\n" +
                "   endDate\n" +
                "   chartType\n" +
                "   scale\n" +
                " }\n" +
                "}";

        String variables = "{\n" +
                "  \"chartsFilter\": {\n" +
                "    \"chartType\": \"CYLINDER_PRESSURE_HISTORY\"\n" +
                "  }\n" +
                "}";

        String payload = Utilities.graphqlWithVariablesToJsonString(value, variables);
        return payload;
    }

    public static String getPlannedMaintenance() {

        String value = "query getPlannedMaintenance{\n" +
                " plannedMaintenance(limit:10){\n" +
                "    overdueCount\n" +
                "    cursor\n" +
                "    maintenanceJobs {\n" +
                "      _id,\n" +
                "      title\n" +
                "      name\n" +
                "      findings\n" +
                "      manualUrl\n" +
                "      status\n" +
                "      scheduledDate\n" +
                "      performedDate\n" +
                "      completedDate\n" +
                "      isOverdue\n" +
                "    }\n" +
                "  }\n" +
                "}";

        String payload = Utilities.graphqlToJsonString(value);
        return payload;
    }

    public static String getLiveData() {

        String value = "query getLiveData($liveChartsFilter: LiveDataFilter!) {\n" +
                " liveData(filter: $liveChartsFilter) {\n" +
                "   data {\n" +
                "     sensorIdentifier\n" +
                "     name\n" +
                "     unitType\n" +
                "     readingType\n" +
                "     values\n" +
                "     ... on CylinderPressureSensorLiveData {\n" +
                "       compressionIndex\n" +
                "       compressionPressure\n" +
                "       maximumPressure\n" +
                "       maximumIndex\n" +
                "     }\n" +
                "   }\n" +
                "   chartType\n" +
                " }\n" +
                "}";

        String variables = "{\n" +
                " \"liveChartsFilter\":\n" +
                " {\n" +
                "   \"chartType\": \"CYLINDER_PRESSURE_LIVE\"\n" +
                " }\n" +
                "}";

        String payload = Utilities.graphqlWithVariablesToJsonString(value, variables);
        return payload;
    }

    public static String getMaintenanceJob(String equipmentID) {

        String value = "query GetMaintenanceJobs($equipmentId: String!){\n" +
                "  plannedMaintenance(filter:{equipmentId:$equipmentId}){\n" +
                "    maintenanceJobs{\n" +
                "      _id,\n" +
                "    title\n" +
                "    name\n" +
                "    status\n" +
                "    manualUrl\n" +
                "    scheduledDate\n" +
                "    statusUpdatedDate\n" +
                "    completedDate\n" +
                "    findings\n" +
                "    materials {\n" +
                "      name\n" +
                "      quantity\n" +
                "    }\n" +
                "     measurements {\n" +
                "      preMeasurements {\n" +
                "        ...MaintenanceMeasurementFieldFragment\n" +
                "      }\n" +
                "      maintenance {\n" +
                "       ...MaintenanceMeasurementFieldFragment\n" +
                "      }\n" +
                "      postMeasurements {\n" +
                "       ...MaintenanceMeasurementFieldFragment\n" +
                "      }\n" +
                "      visualCheck {\n" +
                "        ...MaintenanceMeasurementFieldFragment\n" +
                "      }\n" +
                "      notes {\n" +
                "        ...MaintenanceMeasurementFieldFragment\n" +
                "      }\n" +
                "    }\n" +
                "    }\n" +
                "    \n" +
                "  }\n" +
                "}\n" +
                "fragment MaintenanceMeasurementFieldFragment on MaintenanceMeasurementField {\n" +
                "  key\n" +
                "  type\n" +
                "  label\n" +
                "  isRequired\n" +
                "  ... on MaintenanceMeasurementCheckboxField {\n" +
                "    checkboxFieldValue : value\n" +
                "  }\n" +
                "  ... on MaintenanceMeasurementNumberField {\n" +
                "   numberFieldValue : value\n" +
                "  unitType\n" +
                "  }\n" +
                "  ... on MaintenanceMeasurementTextField {\n" +
                "   textFieldValue : value\n" +
                "  }\n" +
                "  ... on MaintenanceMeasurementToggleField {\n" +
                "   toggleFieldValue : value\n" +
                "  }\n" +
                "  ... on MaintenanceMeasurementImageField {\n" +
                "    imageFieldValue : value\n" +
                "  }\n" +
                " \n" +
                "}";

        String variables = "{\n" +
                " \"equipmentId\": \"" + equipmentID + "\"\n" +
                "}";

        String payload = Utilities.graphqlWithVariablesToJsonString(value, variables);
        return payload;
    }

}
