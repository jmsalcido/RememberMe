/*
 * DatabaseAdapter.java:
 * Thursday 07 June 2012
 * Remember Me is a notepad that will remember the notes
 * when you are near a location.
 * 
 * Coded by Jose Miguel Salcido (jms)
 */
package org.otfusion.android.rememberme.database;

import java.util.ArrayList;
import java.util.List;

import org.otfusion.android.rememberme.domain.Note;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Helper class for database connections in the Notes table.
 * @author jms
 */
public class NoteDatabaseAdapter extends DatabaseAdapter {
    
    // BEGIN DATABASE CONSTANTS
    // -----------------------------------------------------------------
    // BEGIN DATABASE CONSTANTS
    
    private static final String DATABASE_TABLE = "notes";
    // TODO Move to PlaceDatabaseAdapter.java
    //private static final String DATABASE_PLACES_TABLE = "places";
    
    private static final String CREATE_TABLE_NOTES = "" +
            "CREATE TABLE " + DATABASE_TABLE + "(" +
            Note.DB_KEY_ROWID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
            Note.DB_KEY_TITLE + " TEXT NOT NULL," +
            Note.DB_KEY_BODY + " TEXT NOT NULL," +
            Note.DB_KEY_TIME + " TIMESTAMP DEFAULT CURRENT_TIMESTAMP);"; // I WILL NOT ADD PLACES YET.
            //"place INTEGER NOT NULL);";  // I WILL NOT ADD PLACES YET.
    
    // TODO Move to PlaceDatabaseAdapter.java
    //private static final String CREATE_TABLE_PLACES = "" +
    //        "CREATE TABLE " + DATABASE_PLACES_TABLE + "(" +
    //        "_id INTEGER PRIMARY KEY AUTOINCREMENT," +
    //        "latitude TEXT NOT NULL," +
    //        "longitude TEXT NOT NULL);";
    
    // END DATABASE CONSTANTS
    // -----------------------------------------------------------------
    // END DATABASE CONSTANTS
    
    private Context mContext;
    private DatabaseHelper mDbHelper;
    private SQLiteDatabase mDb;
    
    /**
     * Create a NoteDatabaseAdapter with Context
     * @param context
     */
    public NoteDatabaseAdapter(Context context) {
        this.mContext = context;
    }
    
    /**
     * Open a new connection to the database
     * @return
     */
    public NoteDatabaseAdapter open() {
        this.mDbHelper = new DatabaseHelper(mContext);
        this.mDb = mDbHelper.getWritableDatabase();
        return this;
    }
    
    /**
     * Close connection
     */
    public void close() {
        mDbHelper.close();
    }
    
    /**
     * Insert a Note in the database
     * @param note
     * @return
     */
    public long insert(Note note) {
        ContentValues noteValues = new ContentValues();
        //noteValues.put(Note.DB_KEY_ROWID_NOTE, note.getId());
        noteValues.put(Note.DB_KEY_TITLE, note.getTitle());
        noteValues.put(Note.DB_KEY_BODY, note.getBody());
        noteValues.put(Note.DB_KEY_TIME, note.getTime().toString());
        return mDb.insert(DATABASE_TABLE, null, noteValues);
    }
    
    /**
     * Delete a Note from the database
     * @param note
     * @return
     */
    public boolean delete(Note note) {
        String whereClause = Note.DB_KEY_ROWID + "=" + note.getId();
        return mDb.delete(DATABASE_TABLE, whereClause, null) > 0;
    }
    
    /**
     * Fetch all notes from the database.
     * @return
     */
    public Cursor fetchAllNotes() {
        // Columns to project in a Note.
        String projection[] = new String[] {
            Note.DB_KEY_ROWID,
            Note.DB_KEY_TITLE,
            Note.DB_KEY_BODY,
            Note.DB_KEY_TIME
        };
        // table, columns, selection, args, groupby, having, orderby
        return mDb.query(DATABASE_TABLE, projection, null, null, null, null, null);
    }
    
    /**
     * DatabaseHelper local class for the conecction
     * @author jms
     */
    private static class DatabaseHelper extends SQLiteOpenHelper {
        
        /**
         * Create a DatabaseHelper with a Context
         * @param context
         */
        public DatabaseHelper(Context context) {
            super(context, DATABASE_NAME, null, DATABASE_VERSION);
        }
        
        /**
         * Create the Default note in the application 
         * @return
         */
        public ContentValues createFirstNote() {
            String first_title = "First Note in Remember Me";
            String first_body =
                    "This is your first note on " +
                            "<strong>Remember Me</strong>";
            
            Note note = new Note();
            note.setId(1);
            note.setTitle(first_title);
            note.setBody(first_body);
            
            ContentValues values = new ContentValues();
            values.put(Note.DB_KEY_ROWID, note.getId());
            values.put(Note.DB_KEY_TITLE, note.getTitle());
            values.put(Note.DB_KEY_BODY, note.getBody());
            
            return values;
        }

        /**
         * onCreate overrided to create the table and insert
         * the first default note in the app.
         */
        @Override
        public void onCreate(SQLiteDatabase db) {
            db.execSQL(CREATE_TABLE_NOTES);
            ContentValues firstNote = createFirstNote();
            db.insert(DATABASE_TABLE, null, firstNote);
        }

        /**
         * crazy shit for me
         */
        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            // TODO helpalots ??
        }
        
    }
    
}
