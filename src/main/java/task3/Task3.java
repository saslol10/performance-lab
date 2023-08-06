package task3;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.PrintWriter;


// Input tests.json values.json
// Output report.json

public class Task3 {
    public static void main(String[] args) {

        String testsFile =  args[0]; // "src/main/java/task3/tests.json";
        String valuesFile = args[1]; // "src/main/java/task3/values.json";
        String reportFile = "report.json"; // "src/main/java/task3/report.json";

        Gson gson = new Gson();

        try {
            JsonObject testsObject;
            FileReader testsReader = new FileReader(testsFile);
            testsObject = gson.fromJson(testsReader, JsonObject.class);
           // System.out.println(testsObject);

            JsonObject valuesObject;
            FileReader valuesReader = new FileReader(valuesFile);
            valuesObject = gson.fromJson(valuesReader, JsonObject.class);
            //System.out.println(valuesObject);

            if (testsObject.has("tests")) {
                JsonArray testsArray = testsObject.getAsJsonArray("tests");
                //System.out.println(testsArray);
                JsonArray valuesArray = valuesObject.getAsJsonArray("values");
                //System.out.println(valuesArray);
                for (JsonElement testElement : testsArray) {
                    //System.out.println(testElement);
                    if (testElement.isJsonObject())
                    {
                        JsonObject testObject = testElement.getAsJsonObject();
                        //System.out.println(testObject);
                        if(testObject.has("id")){
                            for (JsonElement valueElement : valuesArray){
                                if(valueElement.isJsonObject()){
                                    JsonObject valueObject = valueElement.getAsJsonObject();
                                    if(testObject.has("value") && valueObject.has("value")) {
                                        if (testObject.get("id").getAsString().equals(valueObject.get("id").getAsString())) {
                                            testObject.addProperty("value", valueObject.get("value").getAsString());
                                            //System.out.println(testObject);
                                            if(testObject.has("values")){
                                                updateValueOfValues(testObject, valuesArray);
                                            }
                                        }
                                    }
                                    //System.out.println(valueObject);
                                }
                            }
                        }

                    }

                }

            }

            PrintWriter writer = new PrintWriter(new FileWriter(reportFile));
            //System.out.println(testsObject);
            String jsonString = gson.toJson(testsObject);
            writer.write(jsonString);
            writer.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public static void updateValueOfValues(JsonObject testObject, JsonArray valuesArray){
        JsonArray valueOfValues = testObject.getAsJsonArray("values");
        for (JsonElement valueOfValuesElement : valueOfValues) {
            //System.out.println(testElement);
            if (valueOfValuesElement.isJsonObject()) {
                JsonObject valuesObject = valueOfValuesElement.getAsJsonObject();
                if (valuesObject.has("id")) {
                    for (JsonElement valueElement : valuesArray) {
                        if (valueElement.isJsonObject()) {
                            JsonObject valueObject = valueElement.getAsJsonObject();
                            if (valuesObject.has("value") && valueObject.has("value")) {
                                if (valuesObject.get("id").getAsString().equals(valueObject.get("id").getAsString())) {
                                    valuesObject.addProperty("value", valueObject.get("value").getAsString());

                                }
                            }
                        }
                    }
                }
                //System.out.println(valuesObject);
                if (valuesObject.has("values")) {
                    updateValueOfValues(valuesObject, valuesArray);
                }
            }
        }

    }
}
