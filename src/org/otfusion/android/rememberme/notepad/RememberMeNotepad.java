/*
 * RememberMeNotepad.java:
 * Wednesday 20 June 2012
 * Remember Me is a notepad that will remember the notes
 * when you are near a location.
 * 
 * Coded by Jose Miguel Salcido (jms)
 */
package org.otfusion.android.rememberme.notepad;

import org.otfusion.android.rememberme.R;
import org.otfusion.android.rememberme.RememberMeActivity;
import org.otfusion.android.rememberme.adapters.NotepadListViewAdapter;
import org.otfusion.android.rememberme.database.NoteDatabaseAdapter;
import org.otfusion.android.rememberme.domain.Note;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Toast;
import android.widget.ListView;

/**
 * Activity where the user manages the notes.
 * @author jms
 */
public class RememberMeNotepad extends RememberMeActivity {

    // Adapters
    private NotepadListViewAdapter mListViewAdapter;
    
    // UI
    private ListView mNoteList;
    
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.mDbAdapter = new NoteDatabaseAdapter(mContext);
        mDbAdapter.open();
        mListViewAdapter = new NotepadListViewAdapter(mContext, mDbAdapter);
        loadUI();
    }
    
    OnItemClickListener mItemSelected = new OnItemClickListener() {

        public void onItemClick(AdapterView<?> parentView, View childView, int position,
                long id) {
            Note note = (Note)mNoteList.getAdapter().getItem(position);
            Toast.makeText(mContext, note.getTitle(), Toast.LENGTH_SHORT).show();
        }
    };
    
    @Override
    protected void loadUI() {
        // set the content view of the UI
        setContentView(R.layout.notepad);
        
        // load the notes on the ListView mNotelist
        this.mNoteList = (ListView) findViewById(R.id.notepad_note_list);
        this.mNoteList.setAdapter(mListViewAdapter);
        this.mNoteList.setOnItemClickListener(mItemSelected);
    }

}
