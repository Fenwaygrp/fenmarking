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


public class AlgorithmProfile {

	private Long id;
	private String name;
	private List<TestCaseProfile> testCaseProfiles = new ArrayList<TestCaseProfile>();
	
	public AlgorithmProfile(String name) {
	    this.name = name;
	}

    public List<TestCaseProfile> getTestCaseProfiles(){
	    return Collections.unmodifiableList(testCaseProfiles);
	}

    void addTestCaseProfile(TestCaseProfile testDataProfile) {
        testCaseProfiles.add(testDataProfile);
    }

    /*Getter/Setters*/
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

	
}
