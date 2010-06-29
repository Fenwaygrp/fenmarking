package org.fenwaygrp.fenmarking.fenmarkingdefaultimpl;

import java.util.Collections;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.atomic.AtomicInteger;

import org.fenwaygrp.fenmarking.Algorithm;

public class AlgorithmOne implements Algorithm {

    public static AtomicInteger warmups = new AtomicInteger(0);
    public static AtomicInteger executions = new AtomicInteger(0);
    public static AtomicInteger setupCount = new AtomicInteger(0);
    public static AtomicInteger tearDownCount = new AtomicInteger(0);
    public static Set<String> threadCount = Collections.synchronizedSet(new HashSet<String>());
    public static Boolean isSetUpCalled = false;
    public static Boolean isTearDownCalled = false;

    public void setUp(Map map) {
        isSetUpCalled = true;
        setupCount.incrementAndGet();
        map.put("AlgorithmOne", "true");
    }

    public void warmUp() {
        warmups.incrementAndGet();
    }

    public void execution() {
        try {
            Thread.sleep((long) (Math.random() * 10));
            executions.incrementAndGet();
            threadCount.add(Thread.currentThread().getName());
        } catch (InterruptedException e) {
        }
    }

    public void tearDown() {
        isTearDownCalled = true;
        tearDownCount.incrementAndGet();
    }

    public static void reset(){
        warmups.set(0);
        executions.set(0);
        isSetUpCalled = false;
        isTearDownCalled = false;
        setupCount.set(0);
        tearDownCount.set(0);
        threadCount.clear();
    }
    
}
