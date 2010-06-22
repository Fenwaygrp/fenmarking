package org.fenwaygrp.fenmarking;

import java.util.List;
import java.util.Map;


class FenmarkCallable implements Runnable {

	private List<Long> times;
	private Algorithm algorithm;
	private Map params;
	public FenmarkCallable(List<Long> times, Algorithm algorithm, Map params) {
	    this.times = times;
	    this.algorithm = algorithm;
	    this.params = params;
	}

	public void run() {
	    algorithm.setUp(params);
		long start = System.currentTimeMillis();
		algorithm.execution();
		long stop = System.currentTimeMillis();
		times.add(stop-start);
		algorithm.tearDown();
	}
	
}
