package com.playtika.piratedice.games.highdice.context;

import com.playtika.piratedice.context.Dice;
import com.playtika.piratedice.exception.GameException;
import com.playtika.piratedice.util.Dices;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
public class Game {

    private static final List<Dice> dices = Dices.getFewDiceRoll(2);

    private final int playerId;
    private final int bankId;

    private int lastPlayerScore = 0;
    private int bankScore = 0;

    @Setter
    private int countRollDice = 1;

    public Game(int playerId, int bankId, int countRollDice) {
        this.playerId = playerId;
        this.bankId = bankId;
        this.countRollDice += countRollDice;
    }

    public void playerRollDice() throws GameException {
        if (countRollDice == 0)
            throw new GameException("Player not has out roll dice!");

        if (bankScore == 0)
            throw new GameException("A player cannot go first before the bank.");

        Dices.rollAllDice(dices);
        lastPlayerScore = Dices.getSumAllDicesInList(dices);

        countRollDice--;
    }

    public void bankRollDice() {
        Dices.rollAllDice(dices);
        bankScore = Dices.getSumAllDicesInList(dices);
    }

    public boolean isPlayerWin() {
        return bankId < lastPlayerScore;
    }

}
