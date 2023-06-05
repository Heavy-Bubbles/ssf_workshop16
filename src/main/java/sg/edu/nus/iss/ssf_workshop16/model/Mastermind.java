package sg.edu.nus.iss.ssf_workshop16.model;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;
import java.security.SecureRandom;

import jakarta.json.Json;
import jakarta.json.JsonObject;
// no object builder as you are building the final object
import jakarta.json.JsonReader;

public class Mastermind implements Serializable{

    private String name;
    private Pieces pieces;
    private String id;
    private int insertCount;
    private int updateCount;
    private boolean isUpsert;

    public String getName() {
        return name;
    }


    public void setName(String name) {
        this.name = name;
    }


    public Pieces getPieces() {
        return pieces;
    }


    public void setPieces(Pieces pieces) {
        this.pieces = pieces;
    }


    public String getId() {
        return id;
    }


    public void setId(String id) {
        this.id = id;
    }


    public int getInsertCount() {
        return insertCount;
    }


    public void setInsertCount(int insertCount) {
        this.insertCount = insertCount;
    }


    public int getUpdateCount() {
        return updateCount;
    }


    public void setUpdateCount(int updateCount) {
        this.updateCount = updateCount;
    }


    public boolean isUpsert() {
        return isUpsert;
    }


    public void setUpsert(boolean isUpsert) {
        this.isUpsert = isUpsert;
    }

    

    public Mastermind() {
        this.id = generateID(8);
    }


    // generate ID method
    public synchronized String generateID(int maxChars){
        SecureRandom sr = new SecureRandom();
        StringBuilder sb = new StringBuilder();
        while (sb.length() < maxChars){
            sb.append(Integer.toHexString(sr.nextInt()));
        }
        return sb.toString().substring(0, maxChars);

    }

    
    public JsonObject mastermindToJson(){
        return Json.createObjectBuilder()
            .add("name", this.getName())
            .add("pieces", this.getPieces().piecesToJson())
            .add("id", this.getId())
            .build();
    }

    public JsonObject mastermindToJsonInsert(){
        return Json.createObjectBuilder()
            .add("id", this.getId())
            .add("insert_count", this.getInsertCount())
            .build();
    }

    public JsonObject mastermindToJsonUpdate(){
        return Json.createObjectBuilder()
            .add("id", this.getId())
            .add("update_count", this.getUpdateCount())
            .build();
    }

    // String because json is read as String object
    public static Mastermind createMastermind(String json) throws IOException{
        Mastermind mastermind = new Mastermind();

        if (json != null){
            try (InputStream is = new ByteArrayInputStream(json.getBytes())){
                JsonReader reader = Json.createReader(is);
                JsonObject object = reader.readObject();
                mastermind.setName(object.getString("name"));
                mastermind.setPieces(Pieces.createPieces(object.getJsonObject("pieces")));
            }
        }

        return mastermind;

    }

    @Override
    public String toString(){
        return this.getId() + " " + this.getName();
    }
}
