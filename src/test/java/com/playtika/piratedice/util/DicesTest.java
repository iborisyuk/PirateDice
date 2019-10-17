package com.playtika.piratedice.util;

import com.playtika.piratedice.context.Dice;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

public class DicesTest {

    @Test
    public void mustGetCorrectFewDiceRoll() {
        int count = 2;

        List<Dice> fewDiceRoll = Dices.getFewDiceRoll(count);

        assertEquals(fewDiceRoll.size(), count);
    }

    @Test
    public void mustGetCorrectSumAllDice() {
        List<Dice> dices = Dices.getFewDiceRoll(2);

        int sum = 0;
        for (Dice dice : dices) {
            sum += dice.getLastCount();
        }

        int sumAllDicesInList = Dices.getSumAllDicesInList(dices);

        assertEquals(sum, sumAllDicesInList);
    }

    @Test
    public void mustRollAllDice() {
        List<Dice> dices = Dices.getFewDiceRoll(2);

        int dice0Count = dices.get(0).getLastCount();
        int dice1Count = dices.get(1).getLastCount();

        Dices.rollAllDice(dices);

        assertNotEquals(dices.get(0).getLastCount(), dice0Count);
        assertNotEquals(dices.get(1).getLastCount(), dice1Count);
    }

}