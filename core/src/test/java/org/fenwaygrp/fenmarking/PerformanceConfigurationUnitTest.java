package org.fenwaygrp.fenmarking;

import org.junit.Test;

public class PerformanceConfigurationUnitTest {

    @Test(expected=AssertionError.class)
    public void shouldThrowNullAssertionForNumberOfThreads() throws Exception {
        new PerformanceConfiguration(null, 1,1);        
    }
    
}
