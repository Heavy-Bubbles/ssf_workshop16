package sg.edu.nus.iss.ssf_workshop16.repository;


import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;

import sg.edu.nus.iss.ssf_workshop16.model.Mastermind;


@Repository
public class BoardgameRepo {
    @Autowired RedisTemplate<String, String> template;  
    
    public int saveGame (final Mastermind mastermind){
        template.opsForValue()
            .set(mastermind.getId(), mastermind.mastermindToJson().toString());
        String result = (String) template
            .opsForValue().get(mastermind.getId());
        if (result != null){
            return 1;
        }
        return 0;
    }

    public Mastermind findById(final String mastermindId) throws IOException{
        String jsonStrVal = (String) template
                                    .opsForValue()
                                    .get(mastermindId);
        Mastermind mastermind = Mastermind.createMastermind(jsonStrVal);
        return mastermind;
    }

    public int updateBoardgame(final Mastermind mastermind){
        String result = (String) template.opsForValue()
            .get(mastermind.getId());
        if (mastermind.isUpsert()){
            if (result != null){
                template.opsForValue().set(mastermind.getId(),
                mastermind.mastermindToJson().toString());
            } else {
                mastermind.setId(mastermind.generateID(8));
                template.opsForValue()
                    .setIfAbsent(mastermind.getId()
                    ,mastermind.mastermindToJson().toString());
            }
        } else {
            if (result != null){
                template.opsForValue().set(mastermind.getId(),
                    mastermind.mastermindToJson().toString());
            }
        }

        result = (String) template.opsForValue().get(mastermind.getId());
        if (result != null){
            return 1;
        }
        return 0;
    }
}
