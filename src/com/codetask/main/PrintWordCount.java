package com.codetask.main;

/**
 * Created by romankniazevych on 1/30/17.
 */
public class PrintWordCount {

    private final Words words;

    public PrintWordCount(Words words) {
        this.words = words;
    }

    public void printInConsole() {
        words.print(System.out::println);
    }

}
