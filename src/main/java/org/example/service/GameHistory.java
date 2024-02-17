package org.example;

import java.util.ArrayList;

public interface GameHistory {

    ArrayList<String> getHistory();

    void addHistory(String mes);
}
