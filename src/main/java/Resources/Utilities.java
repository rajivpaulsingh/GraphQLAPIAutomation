package Resources;

import io.restassured.path.json.JsonPath;
import io.restassured.path.xml.XmlPath;
import io.restassured.response.Response;
import org.json.JSONObject;

public class Utilities {

    public static XmlPath RawToXML(Response res) {

        String response = res.asString();
        XmlPath x = new XmlPath(response);
        return x;
    }

    public static JsonPath RawToJSON (Response res) {

        String response = res.asString();
        JsonPath x = new JsonPath(response);
        return x;
    }

    public static String graphqlToJsonString(String payload){
        JSONObject json = new JSONObject();
        json.put("query", payload);
        return  json.toString();
    }
}
