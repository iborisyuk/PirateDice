package com.playtika.piratedice.exception;

public class GameException extends RuntimeException {
    public GameException() {
    }

    public GameException(String message) {
        super(message);
    }
}
