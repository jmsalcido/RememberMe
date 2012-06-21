/*
 * RememberMeNotepadBase.java:
 * Sunday 17 June 2012
 * Remember Me is a notepad that will remember the notes
 * when you are near a location.
 * 
 * Coded by Jose Miguel Salcido (jms)
 */
package org.otfusion.android.rememberme.notepad;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;

/**
 * Base class for Notepad actions like View, Edit or Create
 * @author jms
 */
public abstract class RememberMeNotepadBase extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
    }
    
}
