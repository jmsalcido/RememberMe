/*
 * RememberMeActivity.java:
 * Thursday 07 June 2012
 * Remember Me is a notepad that will remember the notes
 * when you are near a location.
 * 
 * Idea by Alberto Ruiz (aruiz)
 * Coded by Jose Miguel Salcido (jms)
 */
package org.otfusion.android.rememberme;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.widget.Button;
import android.widget.GridView;

/**
 * RememberMeActivity:
 * Main activity of the NotePad.
 * @author jms
 */
public class RememberMeActivity extends Activity {
    
    private Context mContext;
    private GridView mMainGrid;
    private Button mButtonAddNote;
    private Button mButtonShowMap;
    
    // CONSTANTS
    private final int BUTTON_ADD = 0;
    private final int BUTTON_VIEW = 1;
    
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        mContext = getApplicationContext();
        //mDbAdapter = new DatabaseAdapter(mContext);
    }
}