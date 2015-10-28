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

    @Override
    public boolean onCreate() {
        movieDB = new MovieDBHelper(getContext());
        return false;
    }

    @Nullable
    @Override
    public String getType(Uri uri) {
        UriMatcher uriMatcher = buildUriMatcher(uri);
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

    public UriMatcher buildUriMatcher(Uri uri){
        return null;
    }
}
