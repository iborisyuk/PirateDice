package com.playtika.piratedice.context;

import lombok.Getter;

import java.util.Random;

public class Dice {
    @Getter
    private final int id;
    private final Random rand = new Random();

    @Getter
    private int lastCount = 0;

    public Dice(int id) {
        this.id = id;
    }

    public void roll() {
        lastCount = rand.nextInt(6) + 1;
    }
}
