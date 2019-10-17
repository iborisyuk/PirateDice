package com.playtika.piratedice.games.highdice.context;

import com.playtika.piratedice.exception.GameException;
import org.junit.Before;
import org.junit.Test;

import java.lang.reflect.Field;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

public class GameTest {
    private Game game;

    @Before
    public void init() {
        game = new Game(1, 0, 2);
    }

    @Test
    public void mustCorrectBankRollDice() {
        game.bankRollDice();

        assertTrue(game.getBankScore() > 0);
    }

    @Test
    public void mustCorrectPlayerRollDice() {
        game.bankRollDice();
        game.playerRollDice();

        assertTrue(game.getLastPlayerScore() > 0);
    }

    @Test(expected = GameException.class)
    public void mustThrowExceptionWhenPlayerRoll() {
        game.playerRollDice();
    }

    @Test
    public void mustCorrectCheckPlayerWin() {
        game.bankRollDice();
        game.playerRollDice();

        try {
            // Magic
            Field bankScore = Game.class.getDeclaredField("bankScore");
            bankScore.setAccessible(true);
            bankScore.setInt(game, 1);
        } catch (NoSuchFieldException | IllegalAccessException e) {
            e.printStackTrace();
            fail();
        }

        assertTrue(game.isPlayerWin());
    }

}