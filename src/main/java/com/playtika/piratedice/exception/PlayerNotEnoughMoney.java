package com.playtika.piratedice.exception;

public class PlayerNotEnoughMoney extends Throwable {
    public PlayerNotEnoughMoney() {
    }

    public PlayerNotEnoughMoney(String message) {
        super(message);
    }
}
