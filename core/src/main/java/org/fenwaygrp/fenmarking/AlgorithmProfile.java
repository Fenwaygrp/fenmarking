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
