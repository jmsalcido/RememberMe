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
import org.otfusion.android.rememberme.RememberMeActivityActionBar;
import org.otfusion.android.rememberme.adapters.NotepadListViewAdapter;
import org.otfusion.android.rememberme.database.DatabaseAdapter;
import org.otfusion.android.rememberme.database.NoteDatabaseAdapter;
import org.otfusion.android.rememberme.domain.Note;

import com.markupartist.android.widget.ActionBar;

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
public class RememberMeNotepadActionBar extends RememberMeActivityActionBar {

    // CONSTANTS
    private final static int LAYOUT = R.layout.notepad_actionbar;
    
    // Adapters
    private NotepadListViewAdapter mListViewAdapter;
    
    // UI
    private ListView mNoteList;
    
    /**
     * Called when the activity is first created.
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mListViewAdapter = new NotepadListViewAdapter(mContext);
        loadUI(LAYOUT);
    }
    
    
    /**
     * load UI from the layout ID given by the param
     * @param layoutResId layout ID
     */
    @Override
    protected void loadUI(int layoutResID) {
        // set the content view of the UI
        setContentView(layoutResID);
        
        // prepare the action bar
        mActionBar = prepareActionBar(R.id.actionbar_notepad);
        
        // load the notes on the ListView mNotelist
        this.mNoteList = (ListView) findViewById(R.id.notepad_note_list);
        this.mNoteList.setAdapter(mListViewAdapter);
        this.mNoteList.setOnItemClickListener(getItemClickListener());
    }
    
    /**
     * get the behaviour of a click on the listview
     * @return
     */
    private OnItemClickListener getItemClickListener() {
        // behaviour of the list items (notes) click
        OnItemClickListener selected = new OnItemClickListener() {

            public void onItemClick(AdapterView<?> parentView, View childView, int position,
                    long id) {
                Note note = (Note) mNoteList.getAdapter().getItem(position);
                Toast.makeText(mContext, note.getTitle() + " ID: " + note.getId(), Toast.LENGTH_SHORT).show();
            }
        };
        return selected;
    }
    
    /**
     * refresh the UI (dah)
     */
    private void refreshUI() {
        // fill data from the adapter again
        mListViewAdapter.fillData();
        
        // this will notify the adapter to reload itself.
        mListViewAdapter.notifyDataSetChanged();
    }

    /**
     * adsd
     */
    @Override
    protected ActionBar prepareActionBar(int resId) {
        
        // some constants required for this method
        final int POSITION_ADD = 0;
        
        // set the HomeActivity and Icon from the parent class
        ActionBar bar = super.prepareActionBar(resId);
        
        // here we will add the actions available for the activity like adding a note :*
        bar.addAction(new ActionAddNote(), POSITION_ADD);
        return bar;
    }
    
    /**
     * Action used to AddNotes, private because it will be used only on this activity
     * @author jms
     */
    private class ActionAddNote implements ActionBar.Action {

        public int getDrawable() {
            // TODO new icon?, used the ICS add default button.
            return R.drawable.add;
        }

        public void performAction(View view) {
            // TODO this is a stub.
            Note note = new Note("Note");
            note.setBody("isabella");
            DatabaseAdapter adapter = new NoteDatabaseAdapter(mContext);
            adapter.open();
            adapter.insert(note);
            adapter.close();
            refreshUI();
        }
        
    }
}
