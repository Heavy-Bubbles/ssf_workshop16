package sg.edu.nus.iss.ssf_workshop16.contoller;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import sg.edu.nus.iss.ssf_workshop16.model.Mastermind;
import sg.edu.nus.iss.ssf_workshop16.service.BoardGameService;


@RestController
@RequestMapping (path = "/api/boardgame",
consumes = MediaType.APPLICATION_JSON_VALUE,
produces = MediaType.APPLICATION_JSON_VALUE)
public class BoardgameController {
    @Autowired
    private BoardGameService bgService;

    @PostMapping 
    public ResponseEntity<String> createBoardgame (@RequestBody Mastermind mastermind){
        int insertCount = bgService.saveGame(mastermind);
        Mastermind result = new Mastermind();
        result.setId(mastermind.getId());
        result.setInsertCount(insertCount);

        // this is optional
        // if failed to insert
        if (insertCount == 0){
            return ResponseEntity
                .status(HttpStatus.INTERNAL_SERVER_ERROR)
                .contentType(MediaType.APPLICATION_JSON)
                .body(result.mastermindToJsonInsert().toString());
        } else {
            return ResponseEntity
                .status(HttpStatus.CREATED)
                .contentType(MediaType.APPLICATION_JSON)
                .body(result.mastermindToJsonInsert().toString());
        }
    }

    @GetMapping(path = "{mastermindId}")
    public ResponseEntity<String> getBoardgame (@PathVariable String mastermindId) throws IOException{
        Mastermind mastermind = bgService.findById(mastermindId);
        if (mastermind == null || mastermind.getName() == null){
            return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .contentType(MediaType.APPLICATION_JSON)
                .body("");
        } else{
            return ResponseEntity
                .status(HttpStatus.OK)
                .contentType(MediaType.APPLICATION_JSON)
                .body(mastermind.mastermindToJson().toString());
        }
    }

    @PutMapping(path = "{mastermindId}")
    public ResponseEntity <String> updateBoardGame(
        @RequestBody Mastermind mastermind,
        @PathVariable String mastermindId,
        @RequestParam (defaultValue = "false") boolean isUpsert)
        throws IOException{
            Mastermind result = null;
            mastermind.setUpsert(isUpsert);
            if (!isUpsert){
                result = bgService.findById(mastermindId);
                if (result == null){
                    return ResponseEntity
                        .status(HttpStatus.BAD_REQUEST)
                        .contentType(MediaType.APPLICATION_JSON)
                        .body("");
                }
            }
            mastermind.setId(mastermindId);
            int updateCount = bgService.updateBoardgame(mastermind);
            mastermind.setUpdateCount(updateCount);
            return ResponseEntity
                .status(HttpStatus.OK)
                .contentType(MediaType.APPLICATION_JSON)
                .body(mastermind.mastermindToJsonUpdate().toString());

    }
}
