package com.rapaasch.dao;

import com.rapaasch.TestApplicationConfiguration;
import com.rapaasch.dto.Games;
import com.rapaasch.dto.Rounds;
import org.hamcrest.CoreMatchers;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.IOException;
import java.util.List;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = TestApplicationConfiguration.class)
public class GamesDaoTest {

    @Autowired
    GamesDao games;

    @Autowired
    JdbcTemplate jdbc;

    @Before
    public void setUp() throws Exception {
        jdbc.execute("SET FOREIGN_KEY_CHECKS = 0;");
        jdbc.execute("TRUNCATE TABLE `cowsbullstest`.`rounds`;");
        jdbc.execute("TRUNCATE TABLE `cowsbullstest`.`games`;");
        jdbc.execute("SET FOREIGN_KEY_CHECKS = 1;");
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void addGame() throws IOException {
        Games game = new Games();
        game.setSolved(false);
        game.setAnswer("1234");
        game = games.addGame(game);
        List<Games> allGames = games.getAll();
        Games gotGame = games.getGameById(1);
        assertThat(allGames, CoreMatchers.hasItems(game));
        assertEquals(game, gotGame);
    }

    @Test
    public void updateGame() {
        Games game = new Games();
        game.setSolved(true);
        games.updateGame(game);
        assertTrue(game.isSolved()==true);
    }

}