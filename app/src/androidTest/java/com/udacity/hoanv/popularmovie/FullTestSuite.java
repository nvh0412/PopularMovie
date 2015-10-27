package com.udacity.hoanv.popularmovie;

import android.test.suitebuilder.TestSuiteBuilder;

import junit.framework.Test;
import junit.framework.TestSuite;

/**
 * Created by HoaNV on 10/26/15.
 * This is TestSuite which is run all UnitTest in all package under this class
 */
public class FullTestSuite extends TestSuite {

    public FullTestSuite() {
        super();
    }

    public static Test suite(){
        return new TestSuiteBuilder(FullTestSuite.class).includeAllPackagesUnderHere().build();
    }

}
