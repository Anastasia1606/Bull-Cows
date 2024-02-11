package org.example;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;

public abstract class AbstractGame implements Game {
    abstract List<String> generateCharList();
    private String word;
    private ArrayList<String> history;
    Integer tryCount;
    GameStatus gameStatus = GameStatus.INIT;


    @Override
    public void start(Integer wordSize, Integer tryCount) {
        word = generateWord(wordSize);
        this.tryCount = tryCount;
        gameStatus = GameStatus.START;
        history = new ArrayList<>();
        addHistory("Старт игры, загадано слово: "+word+", количество попыток: "+tryCount);
    }

    private String generateWord(Integer wordSize) {
        List<String> alphabet = generateCharList();
        Random rnd = new Random();
        String result = "";
        for (int i = 0; i < wordSize; i++) {
            int randomIndex = rnd.nextInt(alphabet.size());
            result += alphabet.get(randomIndex);
            alphabet.remove(randomIndex);
        }
        return result;
    }

    @Override
    public Answer inputValue(String value) {
        if (!getGameStatus().equals(GameStatus.START)) {
            addHistory("Игра не запущена");
            throw new RuntimeException("Игра не запущена");
        }
        int cowCounter = 0;
        int bullCounter = 0;
        for (int i = 0; i < word.length(); i++) {
            if (value.charAt(i) == word.charAt(i)) {
                cowCounter++;
                bullCounter++;
            } else if (word.contains(String.valueOf(value.charAt(i)))) {
                cowCounter++;
            }
        }
        tryCount--;
        if (tryCount == 0){
            gameStatus = GameStatus.LOOSE;
        }
        if (bullCounter == word.length()){
            gameStatus = GameStatus.WIN;
        }
        addHistory(String.format("Введено: %s, совпадений по позиции: %d (быков), совпадений по наличию: %d (коров)," +
                " осталось попыток: %d, статус игры: %s",
                value,
                bullCounter,
                cowCounter,
                tryCount,
                gameStatus));
        return new Answer(cowCounter, bullCounter, tryCount);
    }

    @Override
    public GameStatus getGameStatus() {
        return gameStatus;
    }

    public void addHistory(String mes) {
        history.add((new Date()).toString() + ": " + mes);
    }

    @Override
    public ArrayList<String> getHistory() {
        return history;
    }

    public String getWord() {
        if (!getGameStatus().equals(GameStatus.START)) {
            return word;
        }
        return null;
    }
}