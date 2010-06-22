package org.fenwaygrp.fenmarking;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

@RunWith(Suite.class)
@SuiteClasses({
   AllTestsInPackageSuite.class,
   org.fenwaygrp.fenmarking.fenmarkingdefaultimpl.AllTestsInPackageSuite.class
})
public class AllTestsSuite {

}
