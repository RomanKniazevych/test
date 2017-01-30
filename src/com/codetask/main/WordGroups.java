package com.codetask.main;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;
import java.util.stream.Stream;

import static java.util.stream.Collectors.toList;

/**
 * Created by romankniazevych on 1/30/17.
 */
public class WordGroups {

    private final CountCharsets countCharsets;
    private final Map<String, AtomicLong> wordGroup;

    public WordGroups(CountCharsets countCharsets) {
        this.wordGroup = new ConcurrentHashMap<>();
        this.countCharsets = countCharsets;
    }

    public void countWordGroups(Stream<String> lines) {
        countCharsets
                .splitLines(lines)
                .forEach(word -> countCharsets.incrementCount(wordGroup, word));
    }

    public List<WordGroup> wordCountGroups(int countLimit) {
        List<WordGroup> collect = wordGroup.entrySet()
                .stream()
                .map(entry -> new WordGroup(entry.getKey(), entry.getValue().longValue()))
                .sorted()
                .filter(wgroup -> wgroup.count() >= countLimit)
                .limit(countLimit)
                .collect(toList());
        return collect;
    }

}
