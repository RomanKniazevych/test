package com.codetask.main;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.stream.Stream;

/**
 * Created by romankniazevych on 1/30/17.
 */
public class FileWordCount {

    private final String file;

    public FileWordCount(String file) {
        this.file = file;
    }

    public Words countWords(CountCharsets countCharsets, int countLimit) throws IOException {
        WordGroups wordGroups = new WordGroups(countCharsets);
        Path path = Paths.get("", file);
        try (Stream<String> lines = Files.lines(path).parallel()) {
            wordGroups.countWordGroups(lines);
        }
        return new Words(wordGroups.wordCountGroups(countLimit));
    }

}
