package com.playtika.piratedice.util;

import com.playtika.piratedice.context.Dice;

import java.util.ArrayList;
import java.util.List;

public class Dices {

    public static List<Dice> getFewDiceRoll(int count) {
        ArrayList<Dice> dices = new ArrayList<>();

        for (int i = 0; i < count; i++) {
            Dice dice = new Dice(i);
            dice.roll();

            dices.add(dice);
        }

        return dices;
    }

    public static int getSumAllDicesInList(List<Dice> dices) {
        int sum = 0;

        for (Dice dice : dices) {
            sum += dice.getLastCount();
        }

        return sum;
    }

    public static void rollAllDice(List<Dice> dices) {
        for (Dice dice : dices) {
            dice.roll();
        }
    }
}