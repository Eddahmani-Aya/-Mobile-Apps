package com.example.tp2b;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import java.util.ArrayList;

public class CandidatAdapter extends ArrayAdapter<Candidat> {

    public CandidatAdapter(Context context, ArrayList<Candidat> candidats) {
        super(context, R.layout.item_candidat, candidats);
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, @NonNull ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext())
                    .inflate(R.layout.item_candidat, parent, false);
        }

        Candidat candidat = getItem(position);

        if (candidat != null) {
            ImageView photo = convertView.findViewById(R.id.ivPhoto);
            TextView nom = convertView.findViewById(R.id.tvNom);
            TextView specialite = convertView.findViewById(R.id.tvSpecialite);

            photo.setImageResource(candidat.getPhotoId());
            nom.setText(candidat.getNom());
            specialite.setText(candidat.getSpecialite());
        }

        return convertView;
    }
}