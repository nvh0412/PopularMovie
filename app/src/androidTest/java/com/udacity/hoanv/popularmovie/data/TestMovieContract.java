package com.udacity.hoanv.popularmovie.data;

import android.net.Uri;
import android.test.AndroidTestCase;

/**
 * Created by HoaNV on 10/28/15.
 */
public class TestMovieContract extends AndroidTestCase {

    private static final int TEST_MOVIE_ID = 1;
    private static final String TEST_MOVIE_ORDER = "popularity.desc";

    @Override
    protected void setUp() throws Exception {
        super.setUp();
    }

    @Override
    protected void tearDown() throws Exception {
        super.tearDown();
    }

    public void testBuildMovieUri() {
        Uri uri = MovieContract.MovieEntry.buildMovieUri(TEST_MOVIE_ID);

        assertNotNull("Error: Null uri returned!!", uri);

        assertEquals("Error: Uri have to match with expected string",uri.toString(),
                "content://com.udacity.hoanv.popularmovie/movie/1");
    }

    public void testBuildMovieWithOrder() {
        Uri uri = MovieContract.MovieEntry.buildMovieWithOrder(TEST_MOVIE_ORDER);

        assertNotNull("Error: Null uri returned!", uri);

        assertEquals("Error: Path param have to equal with expected params", TEST_MOVIE_ORDER, uri.getLastPathSegment());

        assertEquals("Error: Uri have to match with expected string", uri.toString(),
                "content://com.udacity.hoanv.popularmovie/movie/popularity.desc");
    }
}
