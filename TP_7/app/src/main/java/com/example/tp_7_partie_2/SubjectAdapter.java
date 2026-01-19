package com.example.tp_7_partie_2;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

public class SubjectAdapter extends ArrayAdapter<String> {

    Context context;
    String[] data;

    public SubjectAdapter(Context context, String[] data) {
        super(context, R.layout.grid_item, data);
        this.context = context;
        this.data = data;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View view = LayoutInflater.from(context)
                .inflate(R.layout.grid_item, parent, false);

        TextView tvItem = view.findViewById(R.id.tvItem);
        tvItem.setText(data[position]);

        return view;
    }




}
