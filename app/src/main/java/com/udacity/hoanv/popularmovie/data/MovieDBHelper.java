package com.udacity.hoanv.popularmovie.data;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by HoaNV on 10/26/15.
 */
public class MovieDBHelper extends SQLiteOpenHelper {

    public final static String DATABASE_NAME = "movie.db";
    public final static int DATABASE_VERSION = 1;

    public MovieDBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    public MovieDBHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        //Create Table Schema.
//        final String SQL_CREATE_SCHEMA_POSTER =
//                "CREATE TABLE " + MovieContract.PosterEntry.TABLE_NAME + " ( " +
//                        MovieContract.PosterEntry._ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"+
//                        MovieContract.PosterEntry.COLUMN_MOVIE_ID + " INTEGER NOT NULL," +
//                        MovieContract.PosterEntry.COLUMN_POSTER_PATH + " TEXT ," +
//                        MovieContract.PosterEntry.COLUMN_POPULARITY + " REAL NOT NULL," +
//                        MovieContract.PosterEntry.COLUMN_VOTE_AVERAGE + " REAL NOT NULL )";

        final String SQL_CREATE_SCHEMA_MOVIE =
                "CREATE TABLE " + MovieContract.MovieEntry.TABLE_NAME + " ( " +
                        MovieContract.MovieEntry._ID + " INTEGER PRIMARY KEY ," +
                        MovieContract.MovieEntry.COLUMN_TITLE + " TEXT NOT NULL," +
                        MovieContract.MovieEntry.COLUMN_OVERVIEW + " TEXT NOT NULL," +
                        MovieContract.MovieEntry.COLUMN_RUNTIME + " INTEGER NOT NULL," +
                        MovieContract.MovieEntry.COLUMN_POSTER_PATH + " TEXT NOT NULL," +
                        MovieContract.MovieEntry.COLUMN_RELEASE_DATE + " TEXT NOT NULL, " +
                        MovieContract.MovieEntry.COLUMN_BACKDROP_PATH + " TEXT NOT NULL," +
                        MovieContract.MovieEntry.COLUMN_VOTE_AVERAGE + " REAL NOT NULL," +
                        MovieContract.MovieEntry.COLUMN_VOTE_COUNT + " INTEGER," +
                        MovieContract.MovieEntry.COLUMN_POPULARITY + " REAL NOT NULL," +
                        MovieContract.MovieEntry.COLUMN_VIDEO + " INTEGER NOT NULL)";

        //db.execSQL(SQL_CREATE_SCHEMA_POSTER);
        db.execSQL(SQL_CREATE_SCHEMA_MOVIE);

    }

    // This only call when upgrade version of Database.
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXIST " + MovieContract.MovieEntry.TABLE_NAME);
        onCreate(db);
    }
}
