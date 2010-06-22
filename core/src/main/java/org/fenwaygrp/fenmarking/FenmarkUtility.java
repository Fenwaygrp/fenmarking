package org.fenwaygrp.fenmarking;

class FenmarkUtility {

    public Algorithm newInstance(Class<? extends Algorithm> clazz) { 
        try {
            Algorithm algorithm = clazz.newInstance();
            return algorithm;
        } catch (InstantiationException e) {
            throw new RuntimeException(e.getMessage(), e);
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e.getMessage(), e);
        }
    }
    
}
