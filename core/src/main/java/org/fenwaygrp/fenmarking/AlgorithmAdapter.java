package org.fenwaygrp.fenmarking;

import java.util.Map;

/**
 * This is a convenience adapter class provides an empty setUp/tearDown implementation and 
 * has the warmUp delegate to the execution operation.  Use this adapter class for the simple 
 * case where you only need to provide an implementation in the execute method.
 * @author Saad Khawaja
 *
 */
public abstract class AlgorithmAdapter implements Algorithm {

    /**
     * A do nothing implementation
     */
    @SuppressWarnings("unchecked")
    public void setUp(Map params) {
    }

    /**
     * A do nothing implementation
     */
    public void tearDown() {
    }

    /**
     * The warmup delegates to the execution method.
     */
    public void warmUp() {
        execution();
    }

}
