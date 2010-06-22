package org.fenwaygrp.fenmarking;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ScalabilityProfile {
    
    private Long id;
    private String name;
//    private Integer maxThreads;
//    private Integer incrementBy;
    private ScalabilityConfiguration configuration;
    private List<PerformanceProfile> perfProfiles = new ArrayList<PerformanceProfile>();
    
    
    public List<PerformanceProfile> getPerformanceProfiles(){
        return Collections.unmodifiableList(perfProfiles);
    }
    
    /*Getter/Setter*/
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public void addPerformanceProfile(PerformanceProfile perfProfile) {
        perfProfiles.add(perfProfile);
    }
    
}
