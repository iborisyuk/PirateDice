package com.playtika.piratedice.context;

import org.junit.Test;

import static org.junit.Assert.assertTrue;

public class DiceTest {

    @Test
    public void mustCorrectRollsDice() {
        Dice dice = new Dice(1);
        for (int i = 0; i < 20; i++) {
            dice.roll();

            assertTrue(dice.getLastCount() > 0);
            assertTrue(dice.getLastCount() < 7);
        }
    }
}