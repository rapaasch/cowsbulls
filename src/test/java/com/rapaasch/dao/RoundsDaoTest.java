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
public class RoundsDaoTest {

    @Autowired
    GamesDao games;

    @Autowired
    RoundsDao rounds;

    @Autowired
    JdbcTemplate jdbc;

   // Date date= new Date();
    //Timestamp ts = new Timestamp(date.getTime());
   //java.sql.Date currentTimestamp = new java.sql.Timestamp(Calendar.getInstance().getTime().getTime());


    @Before
    public void setUp() throws Exception {
        jdbc.execute("SET FOREIGN_KEY_CHECKS = 0;");
        jdbc.execute("TRUNCATE TABLE `cowsbullstest`.`rounds`;");
        jdbc.execute("TRUNCATE TABLE `cowsbullstest`.`games`;");
        jdbc.execute("SET FOREIGN_KEY_CHECKS = 1;");
        Games game = new Games();
        game.setAnswer("1234");
        game.setSolved(false);
        game = games.addGame(game);
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void addRoundGetALL() throws IOException {
        Rounds round = new Rounds();
        round.setId(1);
        round.setGameId(1);
        round.setGuess("3434");
        round.setResult("E:2:P:0");
        round = rounds.addRound(round);
        List<Rounds> allRoundsGame = rounds.getAllForGame(round.getGameId());
        List<Rounds> allRounds = rounds.getAll();
        assertThat(allRounds, CoreMatchers.hasItems(round));
        assertThat(allRoundsGame, CoreMatchers.hasItems(round));

    }

}