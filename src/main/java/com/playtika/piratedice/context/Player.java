package com.playtika.piratedice.context;

import com.playtika.piratedice.exception.PlayerNotEnoughMoney;
import lombok.Getter;


@Getter
public class Player {

    private final int id;
    private final String name;

    private int money;

    public Player(int id, String name, int money) {
        this.id = id;
        this.name = name;
        this.money = money;
    }

    public void addMoney(int amount) {
        money += amount;
    }

    public void writeOffMoney(int amount) throws PlayerNotEnoughMoney {
        if (money < amount)
            throw new PlayerNotEnoughMoney("id: " + id + " money: " + money + " amount: " + amount);

        money -= amount;
    }
}
