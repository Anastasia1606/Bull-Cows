package org.example;

import org.example.service.AbstractGame;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class GameLatin extends AbstractGame {

    @Override
    public List<String> generateCharList() {
        return new ArrayList<>(Arrays.asList(
                "a", "b", "c", "d", "e", "f", "g", "h", "i", "j",
                "k", "l", "m", "n", "o", "p", "q", "r", "s", "t",
                "u", "v", "w", "x", "y", "z"
        ));
    }
}
