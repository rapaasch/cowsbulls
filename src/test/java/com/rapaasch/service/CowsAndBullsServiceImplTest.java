package com.rapaasch.service;

import com.rapaasch.TestApplicationConfiguration;
import com.rapaasch.dao.GamesDao;
import com.rapaasch.dao.RoundsDao;
import com.rapaasch.dto.Games;
import com.rapaasch.dto.Rounds;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = TestApplicationConfiguration.class)
public class CowsAndBullsServiceImplTest {

    @Autowired
    CowsAndBullsServiceImpl service;
    @Autowired
    RoundsDao rounds;
    @Autowired
    GamesDao games;

    @Before
    public void setUp() throws Exception {
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void calcResultsWrong() {
        Games game = new Games();
        game.setSolved(false);
        game.setAnswer("1234");
        game.setId(1);
        Rounds round = new Rounds();
        round.setGuess("4567");
        round.setResult("E:3:P:1");
        round.setGameId(1);
        round.setId(1);
        String results = service.calcResults(game, round);
        Assert.assertNotEquals(results, round.getResult());
    }

    @Test
    public void calcResultsSolved() {
        Games game = new Games();
        game.setAnswer("1234");
        game.setId(1);
        Rounds round = new Rounds();
        round.setGuess("1234");
        round.setResult("E:4:P:0");
        round.setGameId(1);
        round.setId(1);
        String results = service.calcResults(game, round);
        Assert.assertEquals(results, round.getResult());
        Assert.assertTrue(game.isSolved());
    }

    @Test
    public void calcAnswer() {
        String answer = service.calcAnswer();
        Assert.assertTrue(answer.length()==4);
    }

    @Test
    public void calcResults() {
        Games game = new Games();
        game.setSolved(false);
        game.setAnswer("1234");
        game.setId(1);
        Rounds round = new Rounds();
        round.setGuess("4567");
        round.setResult("E:0:P:1");
        round.setGameId(1);
        round.setId(1);
        String results = service.calcResults(game, round);
        Assert.assertEquals(results, round.getResult());
    }
}