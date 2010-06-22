package org.fenwaygrp.fenmarking;

import static org.hamcrest.Matchers.notNullValue;

import org.hamcrest.MatcherAssert;


public class PerformanceConfiguration extends Configuration {

    private Integer numberOfThreads = 1;

    public PerformanceConfiguration() {
    }

    public PerformanceConfiguration(Configuration configuration) {
        super(configuration.getNumberOfWarmUps(), configuration.getNumberOfExecutions());
    }

    public PerformanceConfiguration(Integer numberOfWarmUp, Integer numberOfExecutions,
            Integer numberOfThreads) {
        super(numberOfWarmUp, numberOfExecutions);
        MatcherAssert.assertThat(numberOfThreads, notNullValue());
        this.numberOfThreads = numberOfThreads;
    }

    public PerformanceConfiguration(PerformanceConfiguration config) {
        this(config.getNumberOfWarmUps(), config.getNumberOfExecutions(), config.getNumberOfThreads());
    }
    
    public Integer getNumberOfThreads() {
        return numberOfThreads;
    }

    protected void setNumberOfThreads(Integer numberOfThreads) {
        this.numberOfThreads = numberOfThreads;
    }
    
}
