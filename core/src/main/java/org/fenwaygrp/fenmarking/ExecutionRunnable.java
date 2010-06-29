package org.fenwaygrp.fenmarking;

import java.util.List;
import java.util.Map;


class ExecutionRunnable extends AbstractRunnable {

    private List<Long> times;
	
	public ExecutionRunnable(List<Long> times, Class<? extends Algorithm> clazz, Map params) {
	    super(clazz, params);
	    this.times = times;
	}
  
	public void runInternal(Algorithm instance) {
		long start = System.currentTimeMillis();
		instance.execution();
		long stop = System.currentTimeMillis();
		times.add(stop-start);
	}
	
}
