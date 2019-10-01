package com.rapaasch.dao;

import com.rapaasch.dto.Rounds;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class RoundsDaoImpl implements RoundsDao {
    @Autowired
    JdbcTemplate jdbc;

    @Autowired
    GamesDao game;

    @Override
    public Rounds addRound(Rounds round) throws IOException {
        String sql = "INSERT INTO rounds (time, gameId, result, guess) VALUES (?, ?, ?, ?)";
        jdbc.update(sql, round.getTimestamp(), round.getGameId(), round.getResult(), round.getGuess());
        Integer newId = jdbc.queryForObject("SELECT LAST_INSERT_ID()", Integer.class);
        round.setId(newId);
        return round;
    }

    @Override
    public List<Rounds> getAll() {
        String sql = "select * from rounds";
        return jdbc.query(sql, new CategoryMapper());
    }

    @Override
    public List<Rounds> getAllForGame(int gameId) {
        String sql = "SELECT * FROM rounds WHERE gameId = ? ORDER BY time ASC";
        return jdbc.query(sql, new CategoryMapper(), gameId);
    }

    @Override
    public void deleteRounds(int id) {
        String sql = "DELETE rounds WHERE id = ?";
        jdbc.update(sql, new CategoryMapper(), id);
    }

    public class CategoryMapper implements RowMapper<Rounds>{

        @Override
        public Rounds mapRow(ResultSet resultSet, int i) throws SQLException {
            Rounds round = new Rounds();
            round.setId(resultSet.getInt("id"));
            round.setGameId(resultSet.getInt("gameId"));
            round.setGuess(resultSet.getString("guess"));
            round.setTimestamp(resultSet.getTimestamp("time"));
            round.setResult(resultSet.getString("result"));
            return round;
        }
}
}
