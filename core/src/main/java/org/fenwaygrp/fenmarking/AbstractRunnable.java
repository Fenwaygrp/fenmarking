package org.fenwaygrp.fenmarking;

import java.util.List;
import java.util.Map;

public abstract class AbstractRunnable implements Runnable {

    private static FenmarkUtility fenmarkUtility = new FenmarkUtility();
    
    private Map params;
    private Algorithm instance;
    
    public AbstractRunnable(Class<? extends Algorithm> clazz, Map params){
        instance = fenmarkUtility.newInstance(clazz);
        this.params = params;
    }
    
    public void run() {
        instance.setUp(params);
        runInternal(instance);
        instance.tearDown();
    }
    
    protected abstract void runInternal(Algorithm instance);
    
}
