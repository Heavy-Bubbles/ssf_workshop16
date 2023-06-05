package sg.edu.nus.iss.ssf_workshop16.model;

import java.io.Serializable;

import jakarta.json.Json;
import jakarta.json.JsonNumber;
import jakarta.json.JsonObject;
import jakarta.json.JsonObjectBuilder;

// anything that needs to be transferred through network should be serializable
public class Types implements Serializable{

    // types array contains type and count
    private String type;
    private int count;

    // generate getter and setter
    public String getType() {
        return type;
    }
    public void setType(String type) {
        this.type = type;
    }
    public int getCount() {
        return count;
    }
    public void setCount(int count) {
        this.count = count;
    }

    // convert to JSON
    public JsonObjectBuilder typeToJson(){
        return Json.createObjectBuilder()
        .add("type", this.getType())
        .add("count", this.getCount());
    }

    // convert to Java Object
    public static Types createTypes(JsonObject json){
        Types types = new Types();
        // get json number -> get a string change to number value
        JsonNumber count = json.getJsonNumber("count");
        String type = json.getString("type");
        // converts the number value to int
        types.setCount(count.intValue());
        types.setType(type);
        return types;
    }
}
