package com.playtika.piratedice.context;

import com.playtika.piratedice.exception.PlayerNotEnoughMoney;
import com.playtika.piratedice.exception.PlayerNotMakeRatesExceptions;
import lombok.Getter;
import lombok.Setter;

import java.util.HashMap;

@Getter
public class Banker extends Player {

    @Setter
    private HashMap<Integer, Integer> playersRates = new HashMap<>();

    @Setter
    private int bankerRates = 0; // player rate excluding re-roll

    public Banker(int id, String name, int money) {
        super(id, name, money);
    }

    public void addPlayerRates(Player player, int rates) throws PlayerNotEnoughMoney {
        player.writeOffMoney(rates);

        playersRates.put(player.getId(), rates);
    }

    public void changePlayerRates(Player player, int newRates) throws PlayerNotMakeRatesExceptions, PlayerNotEnoughMoney {
        if (!playersRates.containsKey(player.getId()))
            throw new PlayerNotMakeRatesExceptions("Failed changes rates. Player " + player.getId() + " not make rates.");

        Integer oldRates = playersRates.get(player.getId());

        if (oldRates > newRates) {
            player.addMoney(oldRates - newRates);
        } else {
            player.writeOffMoney(newRates - oldRates);
        }

        playersRates.replace(player.getId(), newRates);
    }

    public void getAllRatesPlayer(Player player) throws PlayerNotEnoughMoney {
        player.addMoney(playersRates.get(player.getId()));
        player.addMoney(bankerRates);

        writeOffMoney(bankerRates);
        bankerRates = 0;
    }

    public void getAllRatesBanker() {
        for (Integer playerId : playersRates.keySet()) {
            addMoney(playersRates.get(playerId));
        }

        bankerRates = 0;
    }
}
