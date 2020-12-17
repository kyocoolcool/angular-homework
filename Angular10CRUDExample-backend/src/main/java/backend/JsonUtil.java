package backend;

import backend.bean.Product;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class JsonUtil {
    public static void writeJson() throws IOException {
        JSONObject productObject = new JSONObject();
        productObject.put("id", 1);
        productObject.put("name", "Java book");
        productObject.put("description", "good project");
        productObject.put("available", true);
        JSONArray productList = new JSONArray();
        productList.add(productObject);
        //Write JSON file
        try (FileWriter file = new FileWriter("products2.json")) {
            file.write(productList.toJSONString());
            file.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static List<Product> readJson() throws IOException, ParseException {
        JSONParser jsonParser = new JSONParser();
        try (FileReader reader = new FileReader("products3.json")) {
            Object obj = jsonParser.parse(reader);
            JSONArray productList = (JSONArray) obj;
            System.out.println(productList);
            List<Product> list = new ArrayList<Product>();
            productList.forEach(product ->list.add(parseProductObject((JSONObject) product)));
            System.out.println(list);
            return list;
        }
    }

    private static Product parseProductObject(JSONObject product) {
        Long id = (Long) product.get("id");
        String name = (String) product.get("name");
        String description = (String) product.get("description");
        Boolean available = (Boolean) product.get("available");
        return new Product(id,name,description,available);
    }
}
