/*
 * NotepadListViewAdapter.java:
 * Wednesday 20 June 2012
 * Remember Me is a notepad that will remember the notes
 * when you are near a location.
 * 
 * Coded by Jose Miguel Salcido (jms)
 */
package org.otfusion.android.rememberme.adapters;

import org.otfusion.android.rememberme.R;
import org.otfusion.android.rememberme.database.DatabaseAdapter;
import org.otfusion.android.rememberme.domain.Note;

import android.content.Context;
import android.database.Cursor;
import android.text.format.Time;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

/**
 * Adapter used to load the notes of the Notepad ListView.
 * @author jms
 */
public class NotepadListViewAdapter extends BaseAdapter {
    
    private Context mContext;
    private DatabaseAdapter mDbAdapter;
    
    private int mCount;
    private Note[] mNotes;
    
    public NotepadListViewAdapter() {
    }
    
    public NotepadListViewAdapter(Context context, DatabaseAdapter adapter) {
        this.mContext = context;
        this.mDbAdapter = adapter;
        
        // fill the mNoteCount and mNotes fields
        fillData(mDbAdapter.fetchAll());
    }
    
    private void fillData(Cursor cursor) {
        
        // cursor shall be on the first position always, DOUBLE DARE YOU TO REMOVE THIS MTF.
        cursor.moveToFirst();
        
        // dinamyc dance
        mCount = cursor.getCount();
        mNotes = new Note[mCount];
        
        //fields of a note
        long row_id;
        String title;
        String body;
        Time time;
        
        // fill the note array
        for(int i = 0; i < mCount; i++) {
            row_id = cursor.getLong(cursor.getColumnIndexOrThrow(Note.DB_KEY_ROWID));
            title = cursor.getString(cursor.getColumnIndexOrThrow(Note.DB_KEY_TITLE));
            body = cursor.getString(cursor.getColumnIndexOrThrow(Note.DB_KEY_BODY));
            time = new Time();
            time.set(cursor.getLong(cursor.getColumnIndexOrThrow(Note.DB_KEY_TIME)));
            mNotes[i] = new Note(row_id, title, body, time);
            cursor.moveToNext();
        }
        
        // i just wanted to double null this
        cursor = null;
    }

    /**
     * return the cursor size.
     */
    public int getCount() {
        return mCount;
    }

    /** 
     * not sure about this, so I will return a note from the notes array 
     */
    public Object getItem(int position) {
        return mNotes[position];
    }

    /**
     * return the id of note in the array of notes
     */
    public long getItemId(int position) {
        return mNotes[position].getId();
    }

    /**
     * return the View object that will be added on the ListView on the notepad
     */
    public View getView(int position, View convertView, ViewGroup parent) {
        View layout;
        TextView textView;
        
        // if the view is not recycled create a new one
        if (convertView == null) {
            LayoutInflater inflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            layout = inflater.inflate(R.layout.notepad_row, null);
        } else {
            layout = convertView;
        }
        
        // create a TextView from the layout provided
        textView = (TextView) layout.findViewById(R.id.notepad_row_note_title);
        
        // set the title as the text in the textView
        textView.setText(mNotes[position].getTitle());
        return layout;
    }

}
