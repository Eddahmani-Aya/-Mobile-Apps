package com.example.tp_12;

import android.content.Context;
import android.view.*;
import android.widget.*;
import java.util.List;

public class NoteAdapter extends BaseAdapter {
    private Context context;
    private List<DataClass> notes;

    public NoteAdapter(Context context, List<DataClass> notes) {
        this.context = context;
        this.notes = notes;
    }

    @Override
    public int getCount() { return notes.size(); }

    @Override
    public Object getItem(int position) { return notes.get(position); }

    @Override
    public long getItemId(int position) { return position; }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(android.R.layout.simple_list_item_2, parent, false);
        }
        TextView title = convertView.findViewById(android.R.id.text1);
        TextView desc = convertView.findViewById(android.R.id.text2);

        DataClass note = notes.get(position);
        title.setText(note.getDataTitle());
        desc.setText(note.getDataDesc());
        return convertView;
    }
}
