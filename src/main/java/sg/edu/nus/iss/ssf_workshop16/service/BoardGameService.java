package sg.edu.nus.iss.ssf_workshop16.service;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import sg.edu.nus.iss.ssf_workshop16.model.Mastermind;
import sg.edu.nus.iss.ssf_workshop16.repository.BoardgameRepo;



@Service
public class BoardGameService {
    @Autowired
    private BoardgameRepo bgRepo;

    public int saveGame(final Mastermind mastermind){
        return bgRepo.saveGame(mastermind);
    }

    public Mastermind findById(final String mastermindId) throws IOException{
        return bgRepo.findById(mastermindId);
    }

    public int updateBoardgame (final Mastermind mastermind){
        return bgRepo.updateBoardgame(mastermind);
    }
}
