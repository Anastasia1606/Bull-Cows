package org.example;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        Scanner value = new Scanner(System.in);
        System.out.print("Выбери словарь, возможные значения: ");
        List<String> types = new ArrayList<>();
        for(TypeDictionary typeDictionary: TypeDictionary.values()) {
            System.out.print(typeDictionary + " ");
            types.add(typeDictionary.toString());
        }
        String interType = value.nextLine().toUpperCase();
        if (!types.contains(interType)) {
            System.out.println("Словарь не найден");
            return;
        }

        AbstractGame ag = switch (TypeDictionary.valueOf(interType)) {
            case NUM -> new GameNumber();
            case RUS -> new GameCyrillic();
            case ENG -> new GameLatin();
        };
        int wordSize = 3;
        ag.start(wordSize,3);
        System.out.println("Введи значение ("+wordSize+" символов)");

        while (ag.getGameStatus().equals(GameStatus.START)){
            Answer answer = ag.inputValue(value.nextLine());
            System.out.println(answer);
        }
        if (ag.getGameStatus().equals(GameStatus.WIN)){
            System.out.println("Вы победили");
        } else if (ag.getGameStatus().equals(GameStatus.LOOSE)){
            System.out.println("Вы проиграли");
            System.out.println("Было загадано: " + ag.getWord());
        }
        else {
            System.out.println("Неопознанный статус");
        }
        System.out.print("Вывести историю игры (y/n): ");
        if (value.nextLine().toLowerCase().trim().charAt(0) == 'y') {
            List<String> history = ag.getHistory();
            for (String line : history) {
                System.out.println(line);
            }
        }
    }
}