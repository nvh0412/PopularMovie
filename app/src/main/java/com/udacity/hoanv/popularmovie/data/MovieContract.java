package com.udacity.hoanv.popularmovie.data;

import android.content.ContentResolver;
import android.content.ContentUris;
import android.net.Uri;
import android.provider.BaseColumns;

/**
 * Created by HoaNV on 10/26/15.
 * This class include Entry of tables which are saved in SQLite
 * Defines table and column names for the Movie Database.
 */
public class MovieContract {

    /** Authority string for this provider */
    public static final String AUTHORITY = "com.udacity.hoanv.popularmovie";

    public static final Uri BASE_CONTENT_URI = Uri.parse("content://" + AUTHORITY);

    public static final String PATH_MOVIE = "movie";

    public static final class MovieEntry implements BaseColumns {

        public static final Uri CONTENT_URI = BASE_CONTENT_URI.buildUpon().appendPath(PATH_MOVIE).build();

        public static final String CONTENT_TYPE = ContentResolver.CURSOR_DIR_BASE_TYPE + "/" + AUTHORITY + "/" + PATH_MOVIE;

        public static final String CONTENT_TYPE_ITEM = ContentResolver.CURSOR_ITEM_BASE_TYPE + "/" + AUTHORITY + "/" + PATH_MOVIE;

        public static final String TABLE_NAME = "movie";

        //Id is stored as a integer representing id of movie
        public static final String COLUMN_MOVIE_ID = "movie_id";

        //Title is stored as a String representing title of movie
        public static final String COLUMN_TITLE = "title";

        //Overview is stored as a String representing overview of movie
        public static final String COLUMN_OVERVIEW = "overview";

        //Runtime is stored as a Integer representing runtime of movie
        public static final String COLUMN_RUNTIME = "runtime";

        //Release date is stored as a String representing release date of movie
        public static final String COLUMN_RELEASE_DATE = "release_date";

        //Poster path is stored as a String representing URL of movie's poster.
        public static final String COLUMN_POSTER_PATH = "poster_path";

        //function for create URI for movie
        public static Uri buildMovieUri(int id) {
            return ContentUris.withAppendedId(CONTENT_URI, id);
        }

        public static Uri buildMovieWithOrder(String order) {
            return CONTENT_URI.buildUpon().appendPath(order).build();
        }

        public static String getOrderFromUri(Uri uri){
            return uri.getPathSegments().get(0);
        }
    }



}
