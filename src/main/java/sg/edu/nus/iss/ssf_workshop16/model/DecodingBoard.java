package sg.edu.nus.iss.ssf_workshop16.model;

import java.io.Serializable;

import jakarta.json.Json;
import jakarta.json.JsonNumber;
import jakarta.json.JsonObject;
import jakarta.json.JsonObjectBuilder;

public class DecodingBoard implements Serializable{
    private int totalCount;

    public int getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(int totalCount) {
        this.totalCount = totalCount;
    }

    public JsonObjectBuilder dbToJson(){
        return Json.createObjectBuilder()
        .add("total_count", this.getTotalCount());
    }

    public static DecodingBoard createDb(JsonObject json){
        DecodingBoard db = new DecodingBoard();

        JsonNumber totalCount = json.getJsonNumber("total_count");
        db.setTotalCount(totalCount.intValue());

        return db;
    }
}
