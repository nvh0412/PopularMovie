package com.udacity.hoanv.popularmovie.data;

import android.test.AndroidTestCase;

/**
 * Created by HoaNV on 10/26/15.
 */
public class TestPractice extends AndroidTestCase{

    public TestPractice(){
        super();
    }

    @Override
    protected void setUp() throws Exception {
        super.setUp();
    }

    public void testThatDemostratesAssertions(){
        int a = 0;
        int b = 0;
        int c = 3;
        int d= 10;

        assertEquals("X should be equal", a, b);
        assertTrue("Y should be true", a < d);
        assertFalse("Z should be false", c > d);

        if(b > d){
            fail("XX should never happen");
        }
    }

    @Override
    protected void tearDown() throws Exception {
        super.tearDown();
    }
}
