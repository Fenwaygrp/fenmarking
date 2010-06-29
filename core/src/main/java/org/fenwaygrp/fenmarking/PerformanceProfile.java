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
