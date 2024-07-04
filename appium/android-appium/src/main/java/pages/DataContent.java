package pages;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import java.io.FileReader;
import java.io.IOException;

public class DataContent {

    DataContent() {
        throw new UnsupportedOperationException("This is a utility class and cannot be instantiated");
    }

    public static Object getData() throws IOException, ParseException {
        String FilePath = System.getProperty("user.dir")+ "/src/test/resources/datafile.json";

        //Read the JSON file
        JSONParser parser = new JSONParser();
        Object obj = parser.parse(new FileReader(FilePath));
        return (JSONObject) obj;
    }
}
