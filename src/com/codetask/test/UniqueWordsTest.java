package com.codetask.test;

import com.codetask.main.UniqueWords;
import org.junit.Test;

import java.util.stream.Stream;

import static java.util.Arrays.asList;
import static org.junit.Assert.assertArrayEquals;

/**
 * Created by romankniazevych on 1/30/17.
 */
public class UniqueWordsTest {

    @Test
    public void testSpiltLineByPattern() throws Exception {
        UniqueWords uniqueWords = new UniqueWords("([\\., ])");
        Stream<String> stringStream = uniqueWords.splitLines(Stream.of("rrr,. !bb b5 bd,dd2.aa xx"));

        assertArrayEquals(asList("rrr", "bb", "b", "bd","dd","aa","xx").toArray(),  stringStream.toArray());

    }

}