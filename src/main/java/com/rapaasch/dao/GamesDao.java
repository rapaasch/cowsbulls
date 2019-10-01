package com.rapaasch.dao;

import com.rapaasch.dto.Games;

import java.util.List;

public interface GamesDao {

    Games addGame(Games game);
    void updateGame(Games game);
    List<Games> getAll();
    Games getGameById(int id);
}
