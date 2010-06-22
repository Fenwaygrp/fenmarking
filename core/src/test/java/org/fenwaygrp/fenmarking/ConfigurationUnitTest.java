package org.fenwaygrp.fenmarking;

import static org.junit.Assert.*;

import org.junit.Test;

public class ConfigurationUnitTest {
    
    @Test(expected=AssertionError.class)
    public void shouldThrowNullAssertionForNumberOfWarmUps() throws Exception {
        new DummyConfiguration(null, 1);
    }
    
    @Test(expected=AssertionError.class)
    public void shouldThrowNullAssertionForNumberOfExecutions() throws Exception {
        new DummyConfiguration(null, 1);
    }
    
    @Test(expected=AssertionError.class)
    public void shouldThrowInvalidValudForNumberOfWarmUps() throws Exception {
        new DummyConfiguration(-1, 1);
    }
    
    @Test(expected=AssertionError.class)
    public void shouldThrowInvalidValudForNumberOfExecutions() throws Exception {
        new DummyConfiguration(1, -1);
    }
    
}


class DummyConfiguration extends Configuration {

    public DummyConfiguration(Integer numberOfWarmUps, Integer numberOfExecutions) {
        super(numberOfWarmUps, numberOfExecutions);
    }

    
    
}
