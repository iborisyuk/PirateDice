package com.playtika.piratedice.core.database.model;

import com.google.gson.Gson;
import com.playtika.piratedice.games.highdice.context.Game;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
@Getter
@Setter
public class Games {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    private static final Gson gson = new Gson();

    private String game;

    public Games() {
    }

    public void updateGame(int id, Game game) {
        this.id = id;
        setGame(game);
    }

    public void setGame(Game game) {
        this.game = gson.toJson(game);
    }

    public Game getGame() {
        return gson.fromJson(game, Game.class);
    }
}
