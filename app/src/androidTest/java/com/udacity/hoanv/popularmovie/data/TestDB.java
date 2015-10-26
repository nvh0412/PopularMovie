package com.udacity.hoanv.popularmovie.data;

import android.test.AndroidTestCase;

/**
 * Created by HoaNV on 10/26/15.
 */
public class TestDB extends AndroidTestCase{

    private static final String LOG_TAG = TestDB.class.getSimpleName();

    // Clear database before you do anything.
    public void deleteDatabase(){
        mContext.deleteDatabase("");
    }

    /**
     * This function get called before each test is executed to delete the database.
     * This make sure that we always have a clean test.
     * @throws Exception
     */
    @Override
    protected void setUp() throws Exception {
        deleteDatabase();
    }

    /**
     *
     */
    public void testCreateDatabase(){

    }

    /**
     *
     */
    public void testMovideTable(){

    }

    @Override
    protected void tearDown() throws Exception {
        super.tearDown();
    }
}
