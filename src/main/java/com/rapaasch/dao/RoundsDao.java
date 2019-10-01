package com.rapaasch.dao;

import com.rapaasch.dto.Rounds;

import java.io.IOException;
import java.util.List;

public interface RoundsDao {
    Rounds addRound(Rounds round) throws IOException;
    List<Rounds> getAll();
    List<Rounds> getAllForGame(int gameId);
    void deleteRounds(int id);
}
