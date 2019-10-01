package com.rapaasch.dao;

import com.rapaasch.dto.Games;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class GamesDaoImpl implements GamesDao {
    @Autowired
    JdbcTemplate jdbc;


    @Override
    public Games addGame(Games game) {
        String sql = "INSERT INTO games(solved, answer) VALUES (?, ?)";
        jdbc.update(sql, game.isSolved(), game.getAnswer());
        Integer newId = jdbc.queryForObject("SELECT LAST_INSERT_ID()", Integer.class);
        game.setId(newId);
        return game;
    }

    @Override
    public void updateGame(Games game) {
        String sql = "UPDATE games SET solved = ? WHERE id = ?";
        jdbc.update(sql, game.isSolved(), game.getId());
    }

    @Override
    public List<Games> getAll() {
        String sql = "SELECT * FROM games";
        return jdbc.query(sql, new GamesMapper());
    }

    @Override
    public Games getGameById(int id) {
        String sql = "SELECT * FROM games WHERE id = ?";
        return jdbc.queryForObject(sql, new GamesMapper(), id);
    }

    private class GamesMapper implements RowMapper<Games> {
        @Override
        public Games mapRow(ResultSet resultSet, int i) throws SQLException {
            Games game = new Games();
            game.setId(resultSet.getInt("id"));
            game.setAnswer(resultSet.getString("answer"));
            game.setSolved(resultSet.getBoolean("solved"));
            return game;
        }
    }
}
