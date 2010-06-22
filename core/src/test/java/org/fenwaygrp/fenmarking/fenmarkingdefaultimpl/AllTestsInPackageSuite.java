package org.fenwaygrp.fenmarking.fenmarkingdefaultimpl;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

@RunWith(Suite.class)
@SuiteClasses({
    WhenSubmittingWithAlgorithmInstance.class,
    WhenSubmittingWithAlgorithmInstanceAndCustomPerfConfig.class,
    WhenSubmittingWithAlgorithmInstAndCustomScalConfig.class,
    WhenSubmittingWithListOfAlgorithmClassesAndCustomPerfConfig.class
})
public class AllTestsInPackageSuite {

}
