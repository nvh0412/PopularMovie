package com.udacity.hoanv.popularmovie.data;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.net.Uri;
import android.support.annotation.Nullable;

/**
 * Created by HoaNV on 10/28/15.
 */
public class MovieProvider extends ContentProvider {

    private MovieDBHelper movieDB;

    private static final UriMatcher sUriMatcher = buildUriMatcher();

    // order by rate DESC or ASC
    private static final String sMovieOrderRateSelection = "";

    // order by newest DESC or ASC
    private static final String sMovieOrderReleaseDateSelection = "";

    @Override
    public boolean onCreate() {
        movieDB = new MovieDBHelper(getContext());
        return true;
    }

    @Nullable
    @Override
    public String getType(Uri uri) {
        if (uri == null) {
            return null;
        }

        int match = sUriMatcher.match(uri);
        switch (match) {
            case MovieContract.MovieEntry.MOVIE:
                return MovieContract.MovieEntry.CONTENT_TYPE;
            case MovieContract.MovieEntry.MOVIE_ORDER:
                return MovieContract.MovieEntry.CONTENT_TYPE;
            case MovieContract.MovieEntry.MOVIE_DETAIL:
                return MovieContract.MovieEntry.CONTENT_TYPE_ITEM;
        }
        return null;
    }

    @Nullable
    @Override
    public Cursor query(Uri uri, String[] projection, String selection, String[] selectionArgs, String sortOrder) {
        return null;
    }

    @Nullable
    @Override
    public Uri insert(Uri uri, ContentValues values) {
        return null;
    }

    @Override
    public int delete(Uri uri, String selection, String[] selectionArgs) {
        return 0;
    }

    @Override
    public int update(Uri uri, ContentValues values, String selection, String[] selectionArgs) {
        return 0;
    }

    public static UriMatcher buildUriMatcher() {
        final UriMatcher uriMatcher = new UriMatcher(UriMatcher.NO_MATCH);
        String authority = MovieContract.AUTHORITY;
        uriMatcher.addURI(authority, MovieContract.PATH_MOVIE, MovieContract.MovieEntry.MOVIE);
        uriMatcher.addURI(authority, MovieContract.PATH_MOVIE + "/#", MovieContract.MovieEntry.MOVIE_DETAIL);
        uriMatcher.addURI(authority, MovieContract.PATH_MOVIE + "/*", MovieContract.MovieEntry.MOVIE_ORDER);
        return uriMatcher;
    }
}
