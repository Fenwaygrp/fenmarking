package org.fenwaygrp.fenmarking;

import java.util.Map;

public class WarmUpRunnable extends AbstractRunnable {

	public WarmUpRunnable(Class<? extends Algorithm> clazz, Map params) {
		super(clazz, params);
	}
	
	public void runInternal(Algorithm instance) {
	    instance.warmUp();
	}
	
}
