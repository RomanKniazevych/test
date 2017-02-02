package com.codetask.test;

import org.junit.Test;

import static com.codetask.WordCount.convertToRawWord;
import static com.codetask.WordCount.splitLineIntoWords;
import static java.util.Arrays.asList;
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
       assertArrayEquals(asList("rrr", "bb", "b", "bd","dd","aa","xx").toArray(), splitLineIntoWords("rrr,. !bb b5 bd,dd2.aa xx").toArray());
    }

}