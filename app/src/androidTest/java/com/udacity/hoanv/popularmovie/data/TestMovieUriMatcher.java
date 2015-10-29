package com.udacity.hoanv.popularmovie.data;

import android.content.UriMatcher;
import android.net.Uri;
import android.test.AndroidTestCase;
import android.util.Log;

/**
 * Created by HoaNV on 10/28/15.
 */
public class TestMovieUriMatcher extends AndroidTestCase {

    private static final String LOG_TAG = TestMovieUriMatcher.class.getSimpleName();
    private static final Uri TEST_URI_MOVIE = MovieContract.MovieEntry.CONTENT_URI;
    private static final Uri TEST_URI_MOVIE_ORDER = MovieContract.MovieEntry.buildMovieWithOrder("abc");
    private static final Uri TEST_URI_MOVIE_DETAIL = MovieContract.MovieEntry.buildMovieUri(1223456);

    @Override
    protected void setUp() throws Exception {
        super.setUp();
    }

    @Override
    protected void tearDown() throws Exception {
        super.tearDown();
    }

    public void testUriMatcher(){
        UriMatcher uriMatcher = MovieProvider.buildUriMatcher();

        int match = uriMatcher.match(TEST_URI_MOVIE);
        assertEquals("Error: match value has to equal MOVIE constant!", MovieContract.MovieEntry.MOVIE, match);

        match = uriMatcher.match(TEST_URI_MOVIE_ORDER);
        assertEquals("Error: match value has to equal to MOVIE_ORDER Constant", MovieContract.MovieEntry.MOVIE_ORDER, match);

        match = uriMatcher.match(TEST_URI_MOVIE_DETAIL);
        Log.w(LOG_TAG, TEST_URI_MOVIE_DETAIL.toString() + "| match : " + match);
        assertEquals("Error: match = " + match +" has to equals to MOVIE_DETAIL Constant", MovieContract.MovieEntry.MOVIE_DETAIL, match);
    }

}
