package org.fenwaygrp.fenmarking;

public class ScalabilityConfiguration extends Configuration {

    private Integer maxThreads=1;
    private Integer incrementBy=1;
    
    public ScalabilityConfiguration() {
    }
    
    public ScalabilityConfiguration(Integer maxThreads, Integer incrementBy, Integer numberOfWarmUps, Integer numberOfExecutions) {
        super(numberOfWarmUps, numberOfExecutions);
        this.maxThreads = maxThreads;
        this.incrementBy = incrementBy;
    }
    
    public ScalabilityConfiguration(Integer maxThreads, Integer incrementBy) {
        this.maxThreads = maxThreads;
        this.incrementBy = incrementBy;
    }
    
    public Integer getMaxThreads() {
        return maxThreads;
    }
    public void setMaxThreads(Integer maxThreads) {
        this.maxThreads = maxThreads;
    }
    public Integer getIncrementBy() {
        return incrementBy;
    }
    public void setIncrementBy(Integer incrementBy) {
        this.incrementBy = incrementBy;
    }
    

    
    
}
