package org.fenwaygrp.fenmarking;

import static org.junit.Assert.*;

import org.junit.Test;

public class ScalabilityConfigurationUnitTest {

    @Test
    public void testThrowsAssertionErrorWithNullMaxThreads() throws Exception {
        new ScalabilityConfiguration(null, 1);
    }
    
    @Test
    public void testThrowsAssertionErrorWithNullIncrementBy() throws Exception {
        new ScalabilityConfiguration(null, 1);
    }
    
    @Test
    public void testThrowsAssertionErrorWithLessThanOneMaxThreads() throws Exception {
        new ScalabilityConfiguration(-1, 1);
    }
    
    @Test
    public void testThrowsAssertionErrorWithLessThanOneIncrementBy() throws Exception {
        new ScalabilityConfiguration(1, -1);
    }
    
}
