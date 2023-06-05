package sg.edu.nus.iss.ssf_workshop16.model;

import java.io.Serializable;

import jakarta.json.Json;
import jakarta.json.JsonNumber;
import jakarta.json.JsonObject;
import jakarta.json.JsonObjectBuilder;

public class Rulebook implements Serializable{
    public int total_count;
    public String file;

    public int getTotal_count() {
        return total_count;
    }
    public void setTotal_count(int total_count) {
        this.total_count = total_count;
    }
    public String getFile() {
        return file;
    }
    public void setFile(String file) {
        this.file = file;
    }

    public JsonObjectBuilder rulebookToJson(){
        return Json.createObjectBuilder()
            .add("total_count", this.getTotal_count())
            .add("file", this.getFile());
    }

    public static Rulebook createRulebook (JsonObject o){
        Rulebook rulebook = new Rulebook();
        JsonNumber totalCount = o.getJsonNumber("total_count");
        String file = o.getString("file");
        rulebook.setTotal_count(totalCount.intValue());
        rulebook.setFile(file);
        return rulebook;
    }
    
    
}
