package com.codetask;

import com.codetask.main.FileWordCount;
import com.codetask.main.PrintWordCount;
import com.codetask.main.UniqueWords;
import com.codetask.main.Words;

import java.io.IOException;

import static java.lang.Integer.parseInt;

public class WordCount {

    public static void main(String[] args) throws IOException {
        String file = args[0];
        int countLimit = parseInt(args[1]);
        if (countLimit <= 0) {
            throw new IllegalArgumentException("Please enter valid number upper than '1'.");
        }
        FileWordCount fileWordCount = new FileWordCount(file);
        Words words = fileWordCount.countWords(new UniqueWords("([\\., ])"), countLimit);
        new PrintWordCount(words).printInConsole();
    }

}
