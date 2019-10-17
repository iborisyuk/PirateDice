package com.playtika.piratedice.context;

import com.playtika.piratedice.exception.PlayerNotEnoughMoney;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

public class PlayerTest {
    private Player player;

    @Before
    public void init() {
        player = new Player(0, "player", 100);
    }

    @Test
    public void mustCorrectAddMoney() {
        player.addMoney(100);

        assertEquals(player.getMoney(), 200);
    }

    @Test
    public void mustCorrectWriteOffMoney() {
        try {
            player.writeOffMoney(50);
        } catch (PlayerNotEnoughMoney playerNotEnoughMoney) {
            playerNotEnoughMoney.printStackTrace();
            fail();
        }

        assertEquals(player.getMoney(), 100 - 50);
    }

    @Test(expected = PlayerNotEnoughMoney.class)
    public void musThrowExceptionsWriteOffMoney() throws PlayerNotEnoughMoney {
        player.writeOffMoney(200);
    }

}