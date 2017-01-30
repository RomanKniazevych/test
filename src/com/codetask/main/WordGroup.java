package com.codetask.main;

import static java.lang.String.format;

/**
 * Created by romankniazevych on 1/30/17.
 */
public class WordGroup implements Comparable<WordGroup> {

    private final String word;
    private final long count;

    public WordGroup(String word, long count) {
        this.word = word;
        this.count = count;
    }

    public long count() {
        return count;
    }

    public String info() {
        return format("%s=%d", word, count);
    }

    @Override
    public String toString(){
        return info();
    }

    @Override
    public int compareTo(WordGroup wordCount) {
        int compare = Long.valueOf(wordCount.count).compareTo(count);
        if (compare != 0) {
            return compare;
        }
        return word.compareTo(wordCount.word);
    }

}
