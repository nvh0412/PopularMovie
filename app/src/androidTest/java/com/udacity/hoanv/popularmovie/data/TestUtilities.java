package com.udacity.hoanv.popularmovie.data;

import android.content.ContentValues;
import android.database.Cursor;
import android.test.AndroidTestCase;

import java.util.Map;
import java.util.Set;

/**
 * Created by HoaNV on 10/26/15.
 */
public class TestUtilities extends AndroidTestCase{

    public static ContentValues createMovieValues() {
        ContentValues value = new ContentValues();
        value.put(MovieContract.MovieEntry._ID, 1);
        value.put(MovieContract.MovieEntry.COLUMN_TITLE, "abc");
        value.put(MovieContract.MovieEntry.COLUMN_OVERVIEW, "abcxyz");
        value.put(MovieContract.MovieEntry.COLUMN_POSTER_PATH, "http://xyz.com");
        value.put(MovieContract.MovieEntry.COLUMN_POPULARITY, 2.567);
        value.put(MovieContract.MovieEntry.COLUMN_VOTE_AVERAGE, 7.7);
        value.put(MovieContract.MovieEntry.COLUMN_VOTE_COUNT, 330);
        value.put(MovieContract.MovieEntry.COLUMN_RUNTIME, 180);
        value.put(MovieContract.MovieEntry.COLUMN_RELEASE_DATE, "2010");
        value.put(MovieContract.MovieEntry.COLUMN_BACKDROP_PATH, "http://abc.xyz");
        value.put(MovieContract.MovieEntry.COLUMN_VIDEO, 0);
        return value;
    }


    public static void validateRecord(String error, Cursor cursor, ContentValues testValues) {
        assertTrue("Cursor shouldn't empty!!", cursor.moveToFirst());
        validateCurrentRecord(error, cursor, testValues);
    }

    private static void validateCurrentRecord(String error, Cursor cursor, ContentValues expectedValues) {
        Set<Map.Entry<String, Object>> valueSet = expectedValues.valueSet();
        for(Map.Entry<String, Object> value : valueSet){
            String columnName = value.getKey();
            int idx = cursor.getColumnIndex(columnName);
            assertTrue("Database doesn't contain all of the required column", idx != -1);
            String expectedValue = value.getValue().toString();
            assertEquals("Value " + cursor.getString(idx) + " doesn't match with " + expectedValue
                            + ". Error:" + error,
                    value.getValue().toString(), cursor.getString(idx));
        }
    }
}
