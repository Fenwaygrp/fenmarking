package org.fenwaygrp.fenmarking;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class PerformanceProfile {

    private String name;
    private List<AlgorithmProfile> algorithmProfiles = new ArrayList<AlgorithmProfile>();
    
    /*Business Methods*/
    
    public void addAlgorithmProfile(AlgorithmProfile algorithmProfile){
        algorithmProfiles.add(algorithmProfile);
    }
    
    public List<AlgorithmProfile> getAlgorithmProfiles(){
        return Collections.unmodifiableList(algorithmProfiles);
    }
    
    /*Setter/Getters*/
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
