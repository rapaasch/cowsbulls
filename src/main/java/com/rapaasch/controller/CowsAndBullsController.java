package com.rapaasch.controller;

import com.rapaasch.dto.Games;
import com.rapaasch.dto.Rounds;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import com.rapaasch.service.CowsAndBullsServiceImpl;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/api")
public class CowsAndBullsController {
    @Autowired
    CowsAndBullsServiceImpl service;

    @GetMapping("/games")
    List<Games> getAllGames(){
        return service.getAllGames();
    }

    @GetMapping("/rounds/{gameId}")
    List<Rounds> getRoundsByGame(@PathVariable("gameId") int gameId){
        return service.getRoundsByGameId(gameId);
    }

    @GetMapping("/games/{id}")
    Games getById(@PathVariable("id") int id){
        return service.getGameById(id);
    }

    @PostMapping("/new")
    @ResponseStatus(HttpStatus.CREATED)
    public int create(){
        Games game = service.createGame();
        return game.getId();
    }

    @PostMapping
    public Rounds createGuess(@RequestBody Rounds round){
        return service.newRound(round);
    }

}
