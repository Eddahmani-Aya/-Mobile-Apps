package ma.projet.lifebalance.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import ma.projet.lifebalance.R;
import ma.projet.lifebalance.models.Tache;

public class TacheAdapter extends RecyclerView.Adapter<TacheAdapter.TacheViewHolder> {

    private List<Tache> liste;
    private final OnTacheListener listener;

    public interface OnTacheListener {
        void onTacheClick(Tache tache);
        void onTacheLongClick(Tache tache);
    }

    public TacheAdapter(ArrayList<Tache> liste, OnTacheListener listener) {
        this.liste = liste;
        this.listener = listener;
    }

    @NonNull
    @Override
    public TacheViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_tache, parent, false);
        return new TacheViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull TacheViewHolder holder, int position) {
        Tache t = liste.get(position);
        holder.bind(t, listener);
    }

    @Override
    public int getItemCount() {
        return liste.size();
    }

    public void setListe(List<Tache> nouvelleListe) {
        this.liste = nouvelleListe;
        notifyDataSetChanged();
    }

    public static class TacheViewHolder extends RecyclerView.ViewHolder {
        TextView tvTitre, tvDate;

        public TacheViewHolder(View itemView) {
            super(itemView);
            tvTitre = itemView.findViewById(R.id.tvTitre);
            tvDate = itemView.findViewById(R.id.tvDate);
        }

        public void bind(final Tache tache, final OnTacheListener listener) {
            tvTitre.setText(tache.getTitre());
            tvDate.setText(tache.getDate());
            itemView.setOnClickListener(v -> listener.onTacheClick(tache));
            itemView.setOnLongClickListener(v -> {
                listener.onTacheLongClick(tache);
                return true;
            });
        }
    }
}
