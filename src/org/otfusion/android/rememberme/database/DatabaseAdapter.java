/*
 * DatabaseAdapter.java:
 * Saturday 16 June 2012
 * Remember Me is a notepad that will remember the notes
 * when you are near a location.
 * 
 * Coded by Jose Miguel Salcido (jms)
 */
package org.otfusion.android.rememberme.database;

public abstract class DatabaseAdapter {
    protected static final String DATABASE_NAME = "remember_me";
    protected static final int    DATABASE_VERSION = 1;
    
    protected abstract DatabaseAdapter open();
    protected abstract void close();
}
