package com.codetask.main;

import java.util.Map;
import java.util.concurrent.atomic.AtomicLong;
import java.util.stream.Stream;

/**
 * Created by romankniazevych on 1/30/17.
 */
interface CountCharsets {


    Stream<String> spiltLineByPattern(String line);

    default Stream<String> splitLines(Stream<String> lines) {
        return lines
                .flatMap(this::spiltLineByPattern)
                .map(String::trim)
                .map(word -> word.replaceAll("[^a-zA-Z]", ""))
                .filter(word -> !word.isEmpty())
                .map(String::toLowerCase);
    }

    void incrementCount(Map<String, AtomicLong> wordGroup, String word);

}
