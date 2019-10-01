package com.rapaasch.service;

import com.rapaasch.dao.GamesDao;
import com.rapaasch.dao.RoundsDao;
import com.rapaasch.dto.Games;
import com.rapaasch.dto.Rounds;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.Date;
import java.sql.Timestamp;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
public class CowsAndBullsServiceImpl {
    private String results;
    private int numE;
    private int numP;

    @Autowired
    RoundsDao rounds;

    @Autowired
    GamesDao games;

    public Games getGameById(int id) {
        return games.getGameById(id);
    }

    public List<Rounds> getRoundsByGameId(int gameId) {
        return rounds.getAllForGame(gameId);
    }

    public List<Games> getAllGames() {
        List<Games> allGames =  games.getAll();
        for (Games game : allGames){
            if (game.isSolved() == false) {
                game.setAnswer("****");
            } else {
            }
        } return allGames;
    }

    public Games createGame() {
        Games game = new Games();
        game.setAnswer(calcAnswer());
        game.setSolved(false);
        return games.addGame(game);
    }

    public Rounds newRound(Rounds round){
        Games game = games.getGameById(round.getGameId());
        Timestamp ts = new Timestamp( new Date().getTime());
        round.setResult(calcResults(game, round));
        round.setGameId(round.getGameId());
        round.setGuess(round.getGuess());
        round.setTimestamp(ts);
        try {
            return rounds.addRound(round);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    public String calcAnswer(){
        Integer[] array = new Integer[]{1, 2, 3, 4, 5, 6, 7, 8, 9};
        List<Integer> list = Arrays.asList(array);
        Collections.shuffle(list);
        Integer[] newArray = Arrays.copyOfRange(array, 0, 4);
        StringBuilder sb = new StringBuilder();
        for (Integer integer : newArray) {
            sb.append(integer);
        }
        String answer = ((String) sb.toString());
        return answer;
     }

    public String calcResults(Games game, Rounds round) {
        numP = 0;
        numE = 0;
        String[] two = game.getAnswer().split("");
        String[] one = round.getGuess().split("");
        List<String> list = Arrays.asList(two);
        for (int i = 0; i < one.length; i++) {
            if (one[i].equals(two[i])) {
                numE++;
            } else if (list.contains(one[i])) {
                numP++;
            }

        }
        results = ("E:" + numE + ":P:" + (numP));
        if (round.getGuess().equals(game.getAnswer())) {
            game.setSolved(true);
            games.updateGame(game);
        }
        return results;
    }

}
