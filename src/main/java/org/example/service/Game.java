package org.example.model;

import org.example.enums.GameStatus;

public interface Game {
    void start(Integer wordSize, Integer tryCount);
    Answer inputValue(String value);
    GameStatus getGameStatus();
}
