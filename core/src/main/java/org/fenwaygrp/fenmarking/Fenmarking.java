package org.fenwaygrp.fenmarking;

import java.util.List;

/**
 * This is the main interface that the client will use to invoke the micro benchmark 
 * framework. 
 * @author Saad Khawaja
 * 
 */
public interface Fenmarking {

    /**
     * Use this method to pass a instance of the algorithm that you want to micro benchmark.
     * The instance will be reused and the Default PerformanceConfiguration will be applied.
     * @param algorithm the algorithm to benchmark
     * @return the results of the benchmarking.
     */
    MetricResult submit(Algorithm algorithm);

    /**
     * Use this method to pass an instance of the algorithm you want to micro benchmark and
     * a custom configuration to apply.
     * @param configuration
     * @param algorithm
     * @return
     */
    MetricResult submit(PerformanceConfiguration configuration, Algorithm algorithm);

    /**
     * This method allows you to pass a algorithm instance and a custom scalability
     * configuration.
     * @param configuration
     * @param algorithm
     * @return
     */
    List<MetricResult> submit(ScalabilityConfiguration configuration, Algorithm algorithm);

    /**
     * 
     * @param configuration
     * @param clazzez
     * @return
     */
    List<MetricResult> submit(PerformanceConfiguration configuration,
            List<Class<? extends Algorithm>> clazzez);

    ScalabilityProfile submit(ScalabilityTrial trial);

}
