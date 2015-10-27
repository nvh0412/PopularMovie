package com.udacity.hoanv.popularmovie.data;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.test.AndroidTestCase;

import java.util.HashSet;

/**
 * Created by HoaNV on 10/26/15.
 */
public class TestDB extends AndroidTestCase{

    private static final String LOG_TAG = TestDB.class.getSimpleName();

    // Clear database before you do anything.
    public void deleteDatabase(){
        mContext.deleteDatabase(MovieDBHelper.DATABASE_NAME);
    }

    /**
     * This function get called before each test is executed to delete the database.
     * This make sure that we always have a clean test.
     * @throws Exception
     */
    @Override
    protected void setUp() throws Exception {
        deleteDatabase();
    }

    /**
     *
     */
    public void testCreateDatabase() throws Throwable{
        MovieDBHelper dbHelper = new MovieDBHelper(mContext);

        final HashSet<String> tableNameSet = new HashSet<>();
        tableNameSet.add(MovieContract.MovieEntry.TABLE_NAME);

        //Check db open
        SQLiteDatabase sqLiteDatabase = dbHelper.getReadableDatabase();
        assertTrue("DB should be open", sqLiteDatabase.isOpen());

        //Check table name.
        Cursor cursor = sqLiteDatabase.rawQuery("SELECT name FROM sqlite_master WHERE type = 'table'", null);
        assertTrue("Cursor should be can move to first row", cursor.moveToFirst());

        do{
            tableNameSet.remove(cursor.getString(0));
        }while(cursor.moveToNext());

        assertTrue("TableNameSet should be empty", tableNameSet.isEmpty());

        //Check column name.
        cursor = sqLiteDatabase.rawQuery("PRAGMA table_info(" + MovieContract.MovieEntry.TABLE_NAME + ")", null);

        final HashSet<String> columnNameSet = new HashSet<String>();
        columnNameSet.add(MovieContract.MovieEntry._ID);
        columnNameSet.add(MovieContract.MovieEntry.COLUMN_MOVIE_ID);
        columnNameSet.add(MovieContract.MovieEntry.COLUMN_TITLE);
        columnNameSet.add(MovieContract.MovieEntry.COLUMN_OVERVIEW);
        columnNameSet.add(MovieContract.MovieEntry.COLUMN_RELEASE_DATE);
        columnNameSet.add(MovieContract.MovieEntry.COLUMN_POSTER_PATH);

        int columnNameIndex = cursor.getColumnIndex("name");
        do{
            columnNameSet.remove(cursor.getString(columnNameIndex));
        }while(cursor.moveToNext());

        assertTrue("Error: The datase doesn't contain all of the required movie entry columns",
                columnNameSet.isEmpty());

        cursor.close();
        dbHelper.close();
    }

    /**
     *
     */
    public void testMovideTable() {
        MovieDBHelper dbHelper = new MovieDBHelper(mContext);

        //First step: Get reference to writable database
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        assertTrue("DB should be opened!", db.isOpen());

        //Second Step: Create contentValues for save into database
        ContentValues testValues = TestUtilities.createMovieValues();


        //Third Step: Insert into database
        long id = db.insert(MovieContract.MovieEntry.TABLE_NAME, null, testValues);
        assertTrue("Id of row should be different with -1", id != -1);

        //Four Step: Query and check ID
        Cursor cursor = db.query(MovieContract.MovieEntry.TABLE_NAME,
                null,
                null,
                null,
                null,
                null,
                null);

        assertTrue("Cursor should be not empty", cursor.moveToFirst());

        //Fifth step: Validate data from db with contentValues
        TestUtilities.validateCurrentRecord(cursor, testValues);

        //Check only this record in database.
        assertFalse("More than one records returned from location query", cursor.moveToNext());

        //Close connection
        cursor.close();
        db.close();
    }

    @Override
    protected void tearDown() throws Exception {
        super.tearDown();
    }
}
