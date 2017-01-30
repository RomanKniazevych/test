package com.codetask.main;

import java.util.List;
import java.util.function.Consumer;

/**
 * Created by romankniazevych on 1/30/17.
 */
public class Words {

    private final List<WordGroup> wordGroups;

    public Words(List<WordGroup> wordGroups) {
        this.wordGroups = wordGroups;
    }

    public void print(Consumer<String> consumer) {
        wordGroups.forEach(wordGroup -> {
            consumer.accept(wordGroup.info());
        });
    }


}
