package com.udacity.hoanv.popularmovie.data;

import android.provider.BaseColumns;

/**
 * Created by HoaNV on 10/26/15.
 * This class include Entry of tables which are saved in SQLite
 * Defines table and column names for the Movie Database.
 */
public class MovieContract {

    public static final class MovieEntry implements BaseColumns {

        public static final String TABLE_NAME = "movie";

        //Title is stored  as a String representing title of movie
        public static final String COLUMN_TITLE = "title";

        //Overview is stored as a String representing overview of movie
        public static final String COLUMN_OVERVIEW = "overview";

        //Runtime is stored as a Integer representing runtime of movie
        public static final String COLUMN_RUNTIME = "runtime";

        //Release date is stored as a String representing release date of movie
        public static final String COLUMN_RELEASE_DATE = "release_date";

        //Poster path is stored as a String representing URL of movie's poster.
        public static final String COLUMN_POSTER_PATH = "poster_path";

    }

}
