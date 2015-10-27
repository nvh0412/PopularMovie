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
        final String SQL_CREATE_SCHEMA_MOVIE =
                "CREATE TABLE " + MovieContract.MovieEntry.TABLE_NAME + " ( " +
                        MovieContract.MovieEntry._ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
                        MovieContract.MovieEntry.COLUMN_MOVIE_ID + " INTEGER," +
                        MovieContract.MovieEntry.COLUMN_TITLE + " TEXT," +
                        MovieContract.MovieEntry.COLUMN_OVERVIEW + " TEXT," +
                        MovieContract.MovieEntry.COLUMN_RUNTIME + " INTEGER," +
                        MovieContract.MovieEntry.COLUMN_POSTER_PATH + " TEXT," +
                        MovieContract.MovieEntry.COLUMN_RELEASE_DATE + " TEXT";
        db.execSQL(SQL_CREATE_SCHEMA_MOVIE);
    }

    // This only call when upgrade version of Database.
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXIST " + MovieContract.MovieEntry.TABLE_NAME);
        onCreate(db);
    }
}
