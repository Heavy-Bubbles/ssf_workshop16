package sg.edu.nus.iss.ssf_workshop16.model;

import java.io.Serializable;

import jakarta.json.Json;
import jakarta.json.JsonObject;
import jakarta.json.JsonObjectBuilder;

public class Pieces implements Serializable {

    private DecodingBoard decoding_board;
    private Pegs pegs;
    private Rulebook rulebook;

    public DecodingBoard getDecoding_board() {
        return decoding_board;
    }
    public void setDecoding_board(DecodingBoard decoding_board) {
        this.decoding_board = decoding_board;
    }
    public Pegs getPegs() {
        return pegs;
    }
    public void setPegs(Pegs pegs) {
        this.pegs = pegs;
    }
    public Rulebook getRulebook() {
        return rulebook;
    }
    public void setRulebook(Rulebook rulebook) {
        this.rulebook = rulebook;
    }

    public JsonObjectBuilder piecesToJson(){
        return Json.createObjectBuilder()
            .add("decoding_board", this.getDecoding_board().dbToJson())
            .add("pegs", this.getPegs().pegsToJson())
            .add("rulebook", this.getRulebook().rulebookToJson());
    }

    public static Pieces createPieces(JsonObject o){
        Pieces pieces = new Pieces();
        JsonObject decodingBoard = o.getJsonObject("decoding_board");
        JsonObject pegs = o.getJsonObject("pegs");
        JsonObject rulebook = o.getJsonObject("rulebook");
        pieces.decoding_board = DecodingBoard.createDb(decodingBoard);
        pieces.pegs = Pegs.createPegs(pegs);
        pieces.rulebook = Rulebook.createRulebook(rulebook);
        return pieces;

    }
    

}
