package com.udacity.hoanv.popularmovie.data;

import android.content.ContentResolver;
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

    public static final String PATH_POSTER_MOVIE = "poster";

    public static final class MovieEntry implements BaseColumns {


        public static final Uri CONTENT_URI = BASE_CONTENT_URI.buildUpon().appendPath(PATH_MOVIE).build();

        public static final String CONTENT_TYPE = ContentResolver.CURSOR_DIR_BASE_TYPE + "/" + AUTHORITY + "/" + PATH_MOVIE;

        public static final String CONTENT_TYPE_ITEM = ContentResolver.CURSOR_ITEM_BASE_TYPE + "/" + AUTHORITY + "/" + PATH_MOVIE;

        public static final int MOVIE = 100;

        public static final int MOVIE_ORDER = 101;

        public static final int MOVIE_DETAIL = 102;

        public static final String TABLE_NAME = "movie";

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

        public static final String COLUMN_BACKDROP_PATH = "backdrop_path";

        public static final String COLUMN_POPULARITY = "popularity";

        public static final String COLUMN_VOTE_AVERAGE = "vote_average";

        public static final String COLUMN_VOTE_COUNT = "vote_count";

        public static final String COLUMN_VIDEO = "video";

        //function for create URI for movie
        public static Uri buildMovieUri(int id) {
            return CONTENT_URI.buildUpon().appendPath(String.valueOf(id)).build();
        }

        public static Uri buildMovieWithOrder(String order) {
            return CONTENT_URI.buildUpon().appendPath(order).build();
        }

        public static Uri buildMovieWithOrderAndId(String order, int movieId){
            return CONTENT_URI.buildUpon().appendPath(order).appendPath(String.valueOf(movieId)).build();
        }

        public static String getOrderFromUri(Uri uri){
            return uri.getPathSegments().get(0);
        }
    }

    public static final class PosterEntry implements BaseColumns {

        public static final Uri CONTENT_URI = BASE_CONTENT_URI.buildUpon().appendPath(PATH_POSTER_MOVIE).build();

        public static final String TABLE_NAME = "poster";

        public static final String COLUMN_MOVIE_ID = "id";

        public static final String COLUMN_POSTER_PATH = "path";

        public static final String COLUMN_POPULARITY = "popularity";

        public static final String COLUMN_VOTE_AVERAGE = "vote_average";

    }

}
