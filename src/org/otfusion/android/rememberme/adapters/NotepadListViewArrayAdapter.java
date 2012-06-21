package org.otfusion.android.rememberme.adapters;

import org.otfusion.android.rememberme.R;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

public class NotepadListViewArrayAdapter extends ArrayAdapter<String> {

    private String[] mValues;
    private Context mContext;
    
    public NotepadListViewArrayAdapter(Context context, String[] values) {
        super(context, R.layout.notepad_row, values);
        this.mContext = context;
        this.mValues = values;
        
    }

    @Override
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
        textView.setText(mValues[position]);
        return layout;
    }
    
    
    
    

}
