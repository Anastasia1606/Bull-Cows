package org.example;

import java.util.ArrayList;

public interface Game {
    void start(Integer wordSize, Integer tryCount);
    Answer inputValue(String value);
    GameStatus getGameStatus();
    ArrayList<String> getHistory();
}
