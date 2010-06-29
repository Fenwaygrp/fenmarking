/*
   Copyright 2010 fenwaygrp.com

   Licensed under the Apache License, Version 2.0 (the "License");
   you may not use this file except in compliance with the License.
   You may obtain a copy of the License at

     http://www.apache.org/licenses/LICENSE-2.0

   Unless required by applicable law or agreed to in writing, software
   distributed under the License is distributed on an "AS IS" BASIS,
   WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
   See the License for the specific language governing permissions and
   limitations under the License.
 */
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
