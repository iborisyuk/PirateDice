package com.playtika.piratedice.context;

import com.playtika.piratedice.exception.PlayerNotEnoughMoney;
import com.playtika.piratedice.exception.PlayerNotMakeRatesExceptions;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

public class BankerTest {

    private Banker banker;
    private Player player;

    @Before
    public void init() {
        banker = new Banker(0, "Banker", 1000);
        player = new Player(1, "Player", 1000);
    }

    @Test
    public void mustCorrectAddPlayerRates() {
        try {
            banker.addPlayerRates(player, 10);
        } catch (PlayerNotEnoughMoney playerNotEnoughMoney) {
            playerNotEnoughMoney.printStackTrace();
            fail();
        }

        assertEquals(banker.getPlayersRates().get(player.getId()).intValue(), 10);
        assertEquals(player.getMoney(), 990);
    }

    @Test(expected = PlayerNotEnoughMoney.class)
    public void mustExceptionsFromAddPlayerRates() throws PlayerNotEnoughMoney {
        banker.addPlayerRates(player, 100000);
    }

    @Test
    public void mustCorrectUpChangePlayerRates() {
        try {
            banker.addPlayerRates(player, 10);

            banker.changePlayerRates(player, 15);
        } catch (PlayerNotEnoughMoney | PlayerNotMakeRatesExceptions playerNotEnoughMoney) {
            playerNotEnoughMoney.printStackTrace();
            fail();
        }

        assertEquals(banker.getPlayersRates().get(player.getId()).intValue(), 15);
        assertEquals(player.getMoney(), 985);
    }

    @Test
    public void mustCorrectDownChangePlayerRates() {
        try {
            banker.addPlayerRates(player, 10);

            banker.changePlayerRates(player, 5);
        } catch (PlayerNotEnoughMoney | PlayerNotMakeRatesExceptions playerNotEnoughMoney) {
            playerNotEnoughMoney.printStackTrace();
            fail();
        }

        assertEquals(banker.getPlayersRates().get(player.getId()).intValue(), 5);
        assertEquals(player.getMoney(), 995);
    }

    @Test(expected = PlayerNotMakeRatesExceptions.class)
    public void mustExceptFromChangePlayerRates() throws PlayerNotMakeRatesExceptions {
        try {
            banker.addPlayerRates(player, 10);

            Player fake = new Player(2, "Fake", 1234);
            banker.changePlayerRates(fake, 123);
        } catch (PlayerNotEnoughMoney playerNotEnoughMoney) {
            playerNotEnoughMoney.printStackTrace();
            fail();
        }
    }

    @Test
    public void mustCorrectGetAllRatesPlayer() {
        try {
            banker.addPlayerRates(player, 10);
            banker.setBankerRates(10);
            banker.getAllRatesPlayer(player);
        } catch (PlayerNotEnoughMoney playerNotEnoughMoney) {
            playerNotEnoughMoney.printStackTrace();
            fail();
        }

        assertEquals(player.getMoney(), 1010);
    }

    @Test
    public void mustCorrectGetAllRatesBanker() {
        try {
            banker.addPlayerRates(player, 10);
            banker.setBankerRates(10);
            banker.getAllRatesBanker();
        } catch (PlayerNotEnoughMoney playerNotEnoughMoney) {
            playerNotEnoughMoney.printStackTrace();
            fail();
        }

        assertEquals(banker.getMoney(), 1010);
    }
}