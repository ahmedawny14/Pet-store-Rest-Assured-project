package utilities.dataReader;

 import com.jayway.jsonpath.JsonPath;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
 import org.json.simple.parser.ParseException;

 import java.io.FileNotFoundException;
 import java.io.FileReader;
 import java.io.IOException;
 import java.util.List;

public class JsonReader {

    String jsonReader;
    String jsonFileName;
    String fileDataPath = "src/test/resources/testData/json/";

    public JsonReader(String jsonFileName) throws IOException, ParseException {
        this.jsonFileName = jsonFileName;

            JSONObject data = (JSONObject) new JSONParser().parse(new FileReader(fileDataPath + jsonFileName + ".json"));
            jsonReader = data.toJSONString();


    }


    public String getJsonData(String jsonPath) {
        Object value = JsonPath.read(jsonReader, jsonPath);
        return String.valueOf(value);
    }

    public List<String> getJsonArrayData(String jsonPath) {
        return JsonPath.read(jsonReader, jsonPath);
    }

}