/*
 * RememberMeActivity.java:
 * Wednesday 20 June 2012
 * Remember Me is a notepad that will remember the notes
 * when you are near a location.
 * 
 * Idea by Alberto Ruiz (aruiz)
 * Coded by Jose Miguel Salcido (jms)
 */
package org.otfusion.android.rememberme;

import org.otfusion.android.rememberme.notepad.RememberMeNotepadActionBar;

import com.markupartist.android.widget.ActionBar;
import com.markupartist.android.widget.ActionBar.IntentAction;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.Window;

/**
 * Parent Activity for all the RememberMe Activities with an ActionBar!
 * @author jms
 */
public abstract class RememberMeActivityActionBar extends Activity {
    
    // UI General
    protected ActionBar mActionBar;
    
    // Activity Fields
    protected Context mContext;
    
    // Control
    //protected DatabaseAdapter mDbNoteAdapter;
    
    // Constants
    
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.mContext = getApplicationContext();
        
        // We will need a database conection always so...
        //mDbNoteAdapter = new NoteDatabaseAdapter(mContext);
        //mDbNoteAdapter.open();
        
        // No label plx0r
        requestWindowFeature(Window.FEATURE_NO_TITLE);
    }
    
    /**
     * This method shall be called always by the RememberMe Activities
     */
    protected abstract void loadUI(int layoutResID);
    
    /**
     * This method shall be called always to prepare the ActionBar used by the activities.
     * @return
     */
    protected ActionBar prepareActionBar(int actionBarId) {
        ActionBar bar = (ActionBar) findViewById(actionBarId);
        bar.setTitle("Remember Me");
        
        // this shall change.
        int icon = R.drawable.app_notes;
        
        bar.setHomeAction(new IntentAction(mContext, setHomeActivity(getApplicationContext()), icon));
        return bar;
    }
    
    /**
     * This method shall be called always by the prepareActionBar method to set the HomeActivity
     * @param context
     * @return
     */
    protected static Intent setHomeActivity(Context context) {
        Intent i = new Intent(context, RememberMeNotepadActionBar.class);
        i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        return i;
    }
}