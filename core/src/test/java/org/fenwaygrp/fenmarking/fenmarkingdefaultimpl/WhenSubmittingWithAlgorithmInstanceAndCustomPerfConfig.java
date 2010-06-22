package org.fenwaygrp.fenmarking.fenmarkingdefaultimpl;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.*;
import static org.junit.Assert.assertThat;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import org.fenwaygrp.fenmarking.Algorithm;
import org.fenwaygrp.fenmarking.Configuration;
import org.fenwaygrp.fenmarking.Fenmarking;
import org.fenwaygrp.fenmarking.FenmarkingDefaultImpl;
import org.fenwaygrp.fenmarking.MetricResult;
import org.fenwaygrp.fenmarking.PerformanceConfiguration;
import org.junit.BeforeClass;
import org.junit.Test;

public class WhenSubmittingWithAlgorithmInstanceAndCustomPerfConfig {
    
    private static Fenmarking fenmarking = new FenmarkingDefaultImpl();  
    private static MetricResult result; 
    private static final Map<String,Integer> map = new HashMap<String,Integer>();
    private static final Set<String> threadCount = new HashSet<String>();
    
    @BeforeClass
    public static void beforeClass() throws Exception {
        map.put("execs", 0);
        map.put("warmups", 0);
        map.put("setUpCount", 0);
        map.put("tearDownCount", 0);
        result = fenmarking.submit(new PerformanceConfiguration(10,10,1), new Algorithm() {
            public void warmUp() {
                int warmups = map.get("warmups");
                warmups++;
                map.put("warmups", warmups);
            }
            public void tearDown() {
                map.put("tearDown", 1);
                int tearDownCount = map.get("tearDownCount");
                tearDownCount++;
                map.put("tearDownCount", tearDownCount);
            }
            public void setUp(Map params) {
                map.put("setUp", 1);
                int tearDownCount = map.get("setUpCount");
                tearDownCount++;
                map.put("setUpCount", tearDownCount);
                
            }
            public void execution() {
                try {
                    Thread.sleep((long)(Math.random()*10));
                    int execs = map.get("execs");
                    execs++;
                    map.put("execs", execs);
                    threadCount.add(Thread.currentThread().getName());
                } catch (InterruptedException e) {
                }
            }
        });
    }
    
    @Test
    public void shouldHaveRunDefaultNumberWarmUps() throws Exception {
        assertThat(map.get("warmups"), is(10));
    }
    
    @Test
    public void shouldHaveRunDefaultNumberExecutions() throws Exception {
        assertThat(map.get("execs"), is(10));
    }

    @Test
    public void shouldHaveCalledSetUp() throws Exception {
        assertThat(map.get("setUp"), is(1));
    }
    
    @Test
    public void shouldHaveCalledTearDown() throws Exception {
        assertThat(map.get("tearDown"), is(1));
    }
    
    @Test
    public void shouldHaveValidMetricResults() throws Exception {
        new MetricResultAssertions(result);
    }
    
    @Test
    public void shouldHaveOnlySingleThread() throws Exception {
        assertThat(threadCount.size(), is(1));
    }

    @Test(expected=AssertionError.class)
    public void shouldThrowExceptionOnNullAlgorithm() throws Exception {
        Algorithm a = null;
        try {
            fenmarking.submit(a);
        } catch (AssertionError e) {
            assertThat(e.getMessage(),is("\nExpected: not null\n     got: null\n"));
            throw e;
        }
    }
    
    @Test
    public void shouldHaveRunSetUpWarmUpPlusExecutionRuns() throws Exception {
        assertThat(map.get("setUpCount"), is(20));
    }
    
    @Test
    public void shouldHaveRunTearDownWarmUpPlusExectionRuns() throws Exception {
        assertThat(map.get("tearDownCount"), is(20));
    }
    
}

