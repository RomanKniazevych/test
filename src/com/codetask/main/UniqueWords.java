package com.codetask.main;

import java.util.Map;
import java.util.concurrent.atomic.AtomicLong;
import java.util.regex.Pattern;
import java.util.stream.Stream;

/**
 * Created by romankniazevych on 1/30/17.
 */
public class UniqueWords implements CountCharsets {

    private final Pattern splitPattern;

    public UniqueWords(String wordSplitRegex) {
        this.splitPattern = Pattern.compile(wordSplitRegex);
    }

    @Override
    public Stream<String> spiltLineByPattern(String line) {
        return Stream.of(splitPattern.split(line));
    }

    @Override
    public synchronized void incrementCount(Map<String, AtomicLong> wordGroup, String word) {
        wordGroup.compute(word, (s, atomicLong) -> {
            if (atomicLong == null){
                return new AtomicLong(1);
            }
            atomicLong.incrementAndGet();
            return atomicLong;
        });
    }

}
