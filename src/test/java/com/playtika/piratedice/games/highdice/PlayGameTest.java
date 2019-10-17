package com.playtika.piratedice.games.highdice;

import com.playtika.piratedice.context.Banker;
import com.playtika.piratedice.context.Player;
import com.playtika.piratedice.exception.PlayerNotEnoughMoney;
import com.playtika.piratedice.exception.PlayerNotMakeRatesExceptions;
import com.playtika.piratedice.games.highdice.context.Game;
import org.junit.Before;
import org.junit.Test;

import java.lang.reflect.Field;

import static org.junit.Assert.*;

public class PlayGameTest {
    private Player player;
    private Banker banker;

    private Game game;

    private PlayGame playGame;

    @Before
    public void init() {
        player = new Player(1, "player", 100);
        banker = new Banker(0, "banker", 1000);

        game = new Game(player.getId(), banker.getId(), 2);

        playGame = new PlayGame(player, banker, game);
    }

    @Test
    public void mustCorrectMakeRates() {
        try {
            playGame.makeRates(10);
        } catch (PlayerNotEnoughMoney playerNotEnoughMoney) {
            playerNotEnoughMoney.printStackTrace();
            fail();
        }

        assertEquals(player.getMoney(), 70); // 100 - (10 * 3) 3 - count roll dice player
        assertEquals(banker.getPlayersRates().get(player.getId()).intValue(), 30); // 1000 + (10 * 3)
        assertTrue(game.getBankScore() > 0);
    }

    @Test
    public void mustCorrectPlayRollDice() {
        try {
            playGame.makeRates(10);
        } catch (PlayerNotEnoughMoney playerNotEnoughMoney) {
            playerNotEnoughMoney.printStackTrace();
            fail();
        }

        playGame.playerRollDice();
        assertTrue(game.getLastPlayerScore() > 0);

        if (game.isPlayerWin())
            assertEquals(game.getCountRollDice(), 0);
        else
            assertEquals(game.getCountRollDice(), 2);
    }

    @Test
    public void mustCorrectPlayDoubleRate() {
        try {
            playGame.makeRates(10);
            playGame.playerDoubleRate();
        } catch (PlayerNotEnoughMoney | PlayerNotMakeRatesExceptions e) {
            e.printStackTrace();
            fail();
        }

        assertEquals(banker.getBankerRates() * 2, banker.getPlayersRates().get(player.getId()).intValue());
        assertTrue(game.getLastPlayerScore() > 0);
        assertEquals(game.getCountRollDice(), 0);
    }

    @Test
    public void mustCorrectPlaySave() {
        try {
            playGame.makeRates(10);
            playGame.playerSave();
        } catch (PlayerNotEnoughMoney | PlayerNotMakeRatesExceptions e) {
            e.printStackTrace();
            fail();
        }

        assertEquals(player.getMoney(), 85); // 100 - (30 / 2)
        assertEquals(game.getCountRollDice(), 0);
    }

    @Test
    public void mustCorrectEndGame() {
        try {
            playGame.makeRates(10);
        } catch (PlayerNotEnoughMoney playerNotEnoughMoney) {
            playerNotEnoughMoney.printStackTrace();
            fail();
        }

        try {
            // A bit of magic to win the player.
            Field bankScore = Game.class.getDeclaredField("bankScore");
            bankScore.setAccessible(true);
            bankScore.setInt(game, 1);
        } catch (NoSuchFieldException | IllegalAccessException e) {
            e.printStackTrace();
            fail();
        }

        playGame.playerRollDice();

        try {
            playGame.end();
        } catch (PlayerNotEnoughMoney playerNotEnoughMoney) {
            playerNotEnoughMoney.printStackTrace();
            fail();
        }

        assertEquals(player.getMoney(), 110);
        assertEquals(banker.getMoney(), 990);
    }
}