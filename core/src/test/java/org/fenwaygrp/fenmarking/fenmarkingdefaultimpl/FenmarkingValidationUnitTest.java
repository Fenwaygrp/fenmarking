package org.fenwaygrp.fenmarking.fenmarkingdefaultimpl;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.*;

import org.fenwaygrp.fenmarking.Algorithm;
import org.fenwaygrp.fenmarking.Fenmarking;
import org.fenwaygrp.fenmarking.FenmarkingDefaultImpl;
import org.fenwaygrp.fenmarking.PerformanceConfiguration;
import org.junit.Test;

public class FenmarkingValidationUnitTest {

    
    private Fenmarking fenmarking = new FenmarkingDefaultImpl();
    
    @Test(expected=AssertionError.class)
    public void testAlogrithmIsRequiredWhenSubmittingSingleAlgorithm() throws Exception {
        Class<? extends Algorithm> a = null;
        try {
            fenmarking.submit(a);
        } catch (AssertionError e) {
            assertThat(e.getMessage(),is("\nExpected: not null\n     got: null\n"));
            throw e;
        }
    }
    
}
