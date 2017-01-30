package com.codetask.test;

import com.codetask.main.UniqueWords;
import org.junit.Test;

import java.util.*;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicLong;

import static java.util.Arrays.asList;
import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;

/**
 * Created by romankniazevych on 1/30/17.
 */
public class UTest {


    @Test
    public void testIncrementCount() throws Exception {
        UniqueWords uniqueWords = new UniqueWords("");
        Map<String, AtomicLong> wordGroup = new HashMap<>();
        uniqueWords.incrementCount(wordGroup, "step1");

        Map<String, AtomicLong> expectedWordGroup1 = new HashMap<>();
        expectedWordGroup1.put("step1", new AtomicLong(1));
        checkMaps(expectedWordGroup1, wordGroup);

        uniqueWords.incrementCount(wordGroup, "step1");
        uniqueWords.incrementCount(wordGroup, "step2");
        Map<String, AtomicLong> expectedWordGroup2 = new HashMap<>();
        expectedWordGroup2.put("step1", new AtomicLong(2));
        expectedWordGroup2.put("step2", new AtomicLong(1));
        checkMaps(expectedWordGroup2, wordGroup);

        uniqueWords.incrementCount(wordGroup, "step1");
        uniqueWords.incrementCount(wordGroup, "step2");
        uniqueWords.incrementCount(wordGroup, "step3");
        Map<String, AtomicLong> expectedWordGroup3 = new HashMap<>();
        expectedWordGroup3.put("step1", new AtomicLong(3));
        expectedWordGroup3.put("step2", new AtomicLong(2));
        expectedWordGroup3.put("step3", new AtomicLong(1));
        checkMaps(expectedWordGroup3, wordGroup);
    }

    @Test
    public void testIncrementCountParallel() throws Exception {

        UniqueWords uniqueWords = new UniqueWords("");
        Map<String, AtomicLong> wordGroup = new HashMap<>();

        ExecutorService executorService = Executors.newFixedThreadPool(3);

        Callable<Object> submit = Executors.callable(() -> {
            try {
                uniqueWords.incrementCount(wordGroup, "step1");
                Thread.sleep(500);
                uniqueWords.incrementCount(wordGroup, "step2");
                Thread.sleep(500);
                uniqueWords.incrementCount(wordGroup, "step3");
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        Callable<Object> submit1 = Executors.callable(() -> {
            try {
                uniqueWords.incrementCount(wordGroup, "step1");
                Thread.sleep(500);
                uniqueWords.incrementCount(wordGroup, "step2");
                Thread.sleep(500);
                uniqueWords.incrementCount(wordGroup, "step3");
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        Callable<Object> submit2 = Executors.callable(() -> {
            try {
                uniqueWords.incrementCount(wordGroup, "step1");
                Thread.sleep(500);
                uniqueWords.incrementCount(wordGroup, "step2");
                Thread.sleep(500);
                uniqueWords.incrementCount(wordGroup, "step3");
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        Collection<? extends Callable<Object>> callables = asList(submit, submit1, submit2);
        List<Future<Object>> futures = executorService.invokeAll(callables);
        futures.forEach(f->{
            try {
                f.get();
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (ExecutionException e) {
                e.printStackTrace();
            }
        });
        Map<String, AtomicLong> expectedWordGroup = new HashMap<>();
        expectedWordGroup.put("step1", new AtomicLong(3));
        expectedWordGroup.put("step2", new AtomicLong(3));
        expectedWordGroup.put("step3", new AtomicLong(3));
        checkMaps(expectedWordGroup, wordGroup);
    }

    private void checkMaps(Map<String, AtomicLong> expected, Map<String, AtomicLong> actual) {
        assertEquals(expected.size(), actual.size());
        Set<String> expectedKeys = expected.keySet();
        assertArrayEquals(expected.keySet().toArray(), actual.keySet().toArray());
        assertArrayEquals(expected.values().stream().map(AtomicLong::longValue).toArray(), actual.values().stream().map(AtomicLong::longValue).toArray());
        expectedKeys.stream().forEach(key -> {
            assertEquals(expected.get(key).longValue(), actual.get(key).longValue());
        });
    }

}