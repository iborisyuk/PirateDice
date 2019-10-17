package com.playtika.piratedice.core.database.model;

import com.playtika.piratedice.context.Banker;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.HashMap;

@Entity
@Getter
@Setter
public class Bankers {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    private String name;
    private int money;
    private int bankerRates;
    private HashMap<Integer, Integer> playersRates;

    public Bankers() {
    }

    public Bankers(String name, int money) {
        this.name = name;
        this.money = money;

        bankerRates = 0;
        playersRates = new HashMap<>();
    }

    public void setBanker(Banker banker) {
        name = banker.getName();
        money = banker.getMoney();
        bankerRates = banker.getBankerRates();
        playersRates = getPlayersRates();
    }

    public Banker getBanker() {
        Banker banker = new Banker(id, name, money);
        banker.setBankerRates(bankerRates);
        banker.setPlayersRates(playersRates);

        return banker;
    }
}
