/*
 * DatabaseAdapter.java:
 * Saturday 16 June 2012
 * Remember Me is a notepad that will remember the notes
 * when you are near a location.
 * 
 * Coded by Jose Miguel Salcido (jms)
 */
package org.otfusion.android.rememberme.database;

import android.database.Cursor;

/**
 * Adapter for the database conection, used as an abstract class not as an interface because of the fields used here.
 * @author jms
 */
public abstract class DatabaseAdapter {
    protected static final String DATABASE_NAME = "remember_me";
    protected static final int    DATABASE_VERSION = 1;
    
    public abstract DatabaseAdapter open();
    public abstract void close();
    
    /**
     * insert an object to the database, cast needed.
     * @param object
     * @return
     */
    public abstract long insert(Object object);
    
    /**
     * delete an object from the database, cast needed.
     * @param object
     * @return
     */
    public abstract boolean delete(Object object);
    
    /**
     * update an object from the database, cast needed.
     * @param object
     * @return
     */
    public abstract long update(Object object);
    
    /**
     * fetch all objects from the database, used as an: select * from table
     * @return
     */
    public abstract Cursor fetchAll();
}
