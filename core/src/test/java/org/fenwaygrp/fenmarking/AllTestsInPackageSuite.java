package org.fenwaygrp.fenmarking;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;


@RunWith(Suite.class)
@SuiteClasses({
    FenmarkBigDecimalUnitTest.class,
    MetricResultUnitTest.class,
    StatisticCalculatorWithDiffDataPointsUnitTest.class,
    StatisticCalculatorWithSameDataPointsUnitTest.class
})
public class AllTestsInPackageSuite {

}
