package org.fenwaygrp.fenmarking.fenmarkingdefaultimpl;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import org.fenwaygrp.fenmarking.Algorithm;

public class AlgorithmTwo implements Algorithm {

    public static int warmups = 0;
    public static Integer executions = 0;
    public static Set<String> threadCount = new HashSet<String>();
    public static Boolean isSetUpCalled = false;
    public static Boolean isTearDownCalled = false;

    public void setUp(Map map) {
        isSetUpCalled = true;
        map.put("AlgorithmTwo", "true");
    }

    public void warmUp() {
        warmups++;
    }

    public void execution() {
        try {
            Thread.sleep((long) (Math.random() * 20));
            synchronized(executions) {
                executions++;  
            } 
            threadCount.add(Thread.currentThread().getName());
        } catch (InterruptedException e) {
        }
    }

    public void tearDown() {
        isTearDownCalled = true;
    }

    public static void reset(){
        warmups = 0;
        executions = 0;
        isSetUpCalled = false;
        isTearDownCalled = false;
        threadCount.clear();
    }
    
}
