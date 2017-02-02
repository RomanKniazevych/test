package com.codetask;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Collection;
import java.util.List;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static java.lang.Integer.parseInt;
import static java.lang.String.format;
import static java.util.regex.Pattern.compile;
import static java.util.stream.Collectors.groupingBy;
import static java.util.stream.Collectors.toList;
import static java.util.stream.Stream.of;

public class WordCount {

    public static void main(String[] args) throws IOException {
        String file = args[0];
        int countLimit = parseInt(args[1]);
        if (countLimit <= 0) {
            throw new IllegalArgumentException("Please enter valid number upper than '1'.");
        }

        Path path = Paths.get("", file);
        try (Stream<String> lines = Files.lines(path)) {
            lines
                    .parallel()
                    .map(WordCount::splitLineIntoWords)
                    .flatMap(Collection::stream)
                    //Convert list to Map<String, Long> (key: word, value: count)
                    .collect(groupingBy(s -> s, Collectors.counting()))
                    .entrySet().stream()
                    // Convert Map<String, Long> to comparable object {@link #WordCount.UniqueWordCount}
                    .map(entry -> new UniqueWordCount(entry.getKey(), entry.getValue()))
                    .sorted()
                    .filter(uniqueWordCount -> uniqueWordCount.count >= countLimit)
                    .limit(countLimit)
                    .forEachOrdered(System.out::println);
        }
    }

    public static List<String> splitLineIntoWords(String line) {
        Pattern splitPattern = compile("([\\., ])");
        return of(splitPattern.split(line))
                .map(WordCount::convertToRawWord)
                .filter(word -> !word.isEmpty())
                .collect(toList());
    }

    public static String convertToRawWord(String word) {
        return word.trim().toLowerCase().replaceAll("[^a-zA-Z]", "");
    }

    private static class UniqueWordCount implements Comparable<UniqueWordCount> {

        private final String word;
        private final long count;

        private UniqueWordCount(String word, long count) {
            this.word = word;
            this.count = count;
        }

        @Override
        public String toString() {
            return format("%s=%d", word, count);
        }

        @Override
        public int compareTo(UniqueWordCount o) {
            int compare = Long.valueOf(o.count).compareTo(count);
            if (compare != 0) {
                return compare;
            }
            return word.compareTo(o.word);
        }

    }

}
