package com.playtika.piratedice.core.database.model;

import com.playtika.piratedice.context.Player;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
@Getter
@Setter
public class Players {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    private String name;
    private int money;

    public Players() {
    }

    public Players(String name, int money) {
        this.name = name;
        this.money = money;
    }

    public Player getPlayer() {
        return new Player(id, name, money);
    }
}
