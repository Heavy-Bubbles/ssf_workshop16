package sg.edu.nus.iss.ssf_workshop16.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import jakarta.json.Json;
import jakarta.json.JsonArray;
import jakarta.json.JsonArrayBuilder;
import jakarta.json.JsonNumber;
import jakarta.json.JsonObject;
import jakarta.json.JsonObjectBuilder;

public class Pegs implements Serializable{
    private int totslCount;
    private List<Types> types;

    public int getTotslCount() {
        return totslCount;
    }
    public void setTotslCount(int totslCount) {
        this.totslCount = totslCount;
    }
    public List<Types> getTypes() {
        return types;
    }
    public void setTypes(List<Types> types) {
        this.types = types;
    }
    
    // convert to JSON. First need to create the types array using array builder and populate it, 
    // then add the list and total count to object builder
    public JsonObjectBuilder pegsToJson(){
        
        JsonArrayBuilder arrayBuilder = Json.createArrayBuilder();
        List<JsonObjectBuilder> listOfTypes = this.getTypes()
            // initiate stream
            .stream()
            // convert each type to json
            .map(type -> type.typeToJson())
            // put converted type to list
            .toList();

        // add list of types into array builder
        for (JsonObjectBuilder type : listOfTypes){
            arrayBuilder.add(type);
        }
       
        return Json.createObjectBuilder()
        .add("total_count", this.getTotslCount())
        .add("types", arrayBuilder);
    }

    public static Pegs createPegs(JsonObject json){
        Pegs pegs = new Pegs();
        List<Types> result = new ArrayList<Types>();

        JsonNumber totalCount = json.getJsonNumber("total_count");
        JsonArray types = json.getJsonArray("types");

        pegs.setTotslCount(totalCount.intValue());
        for (int i = 0; i < types.size(); i++){
            JsonObject type = types.getJsonObject(i);
            Types t = Types.createTypes(type);
            result.add(t);
        }
        pegs.setTypes(result);

        return pegs;
        
    }

}
