package com.codetask.test;

import com.codetask.WordCount.*;
import org.junit.Test;

import java.util.List;
import java.util.stream.Stream;

import static com.codetask.WordCount.*;
import static java.util.Arrays.asList;
import static java.util.stream.Collectors.toList;
import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;

/**
 * Created by romankniazevych on 1/30/17.
 */
public class UniqueWordsTest {

    @Test
    public void testConvertToRawWord() throws Exception {
        assertEquals("aaaa", convertToRawWord("aaaa"));
        assertEquals("rrr", convertToRawWord("rrr,"));
        assertEquals("rrr", convertToRawWord(",rrr,"));
        assertEquals("bb", convertToRawWord("!bb,"));
        assertEquals("b", convertToRawWord("b5,"));
        assertEquals("ddd", convertToRawWord("ddd+!"));
    }

    @Test
    public void testSplitLineIntoWords() throws Exception {
        assertArrayEquals(asList("rrr", "bb", "b", "bd", "dd", "aa", "xx").toArray(), splitLineIntoWords("rrr,. !bb b5 bd,dd2.aa xx").toArray());
    }

    @Test
    public void textWordCount() {
        List<String> strings = asList("rom", "char", "rom", "char", "char", "basic", "basic", "rxr");
        Stream<UniqueWordCount> uniqueWordCount = findUniqueWordCount(strings.stream(), 2);
        Object[] actualArray = uniqueWordCount.collect(toList()).toArray();
        Object[] expectedArray = asList(
                new UniqueWordCount("char", 3),
                new UniqueWordCount("basic", 2)
                ).toArray();
        assertArrayEquals(expectedArray, actualArray);
    }



}