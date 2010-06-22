package org.fenwaygrp.fenmarking.fenmarkingdefaultimpl;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.*;
import static org.junit.Assert.assertThat;

import org.fenwaygrp.fenmarking.Algorithm;
import org.fenwaygrp.fenmarking.Configuration;
import org.fenwaygrp.fenmarking.Fenmarking;
import org.fenwaygrp.fenmarking.FenmarkingDefaultImpl;
import org.fenwaygrp.fenmarking.MetricResult;
import org.fenwaygrp.fenmarking.PerformanceConfiguration;
import org.junit.BeforeClass;
import org.junit.Test;

public class WhenSubmittingWithAlgorithmInstance {

    private static MetricResult result;
    private static Fenmarking fenmarking = new FenmarkingDefaultImpl();  
    private static AlgorithmOne algorithm = new AlgorithmOne(); 
    
    @BeforeClass
    public static void beforeClass(){
        AlgorithmOne.reset();
        AlgorithmTwo.reset();
        result = fenmarking.submit(algorithm);
    }

    @Test
    public void shouldHaveRunDefaultWarms() throws Exception {
        assertThat(algorithm.warmups, is(new PerformanceConfiguration().getNumberOfWarmUps()));
    }
    
    @Test
    public void shouldHaveRunDefaultExecutions() throws Exception {
        assertThat(algorithm.executions, is(new PerformanceConfiguration().getNumberOfExecutions()));
    }

    @Test
    public void shouldHaveRunSetUp() throws Exception {
        assertThat(algorithm.isSetUpCalled, is(true));
    }
    
    @Test
    public void shouldHaveRunTearDown() throws Exception {
        assertThat(algorithm.isTearDownCalled, is(true));
    }
    
    @Test
    public void shouldHaveValidMetricResults() throws Exception {
        new MetricResultAssertions(result);
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
        assertThat(AlgorithmOne.setupCount, is((Configuration.defaultExecutionCount+Configuration.defaultWarmUpCount)));
    }
    
    @Test
    public void shouldHaveRunTearDownWarmUpPlusExectionRuns() throws Exception {
        assertThat(AlgorithmOne.tearDownCount, is((Configuration.defaultExecutionCount+Configuration.defaultWarmUpCount)));
    }
    
}
