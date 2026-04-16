package com.example.tp_firebase;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.List;

public class MsgAdapter extends RecyclerView.Adapter<MsgViewHolder> {

    private List<Message> messageList;

    public MsgAdapter(List<Message> messageList) {
        this.messageList = messageList;
    }

    @Override
    public MsgViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.msgitem, parent, false);

        return new MsgViewHolder(view);
    }

    @Override
    public void onBindViewHolder(MsgViewHolder holder, int position) {
        Message msg = messageList.get(position);
        holder.msgTv.setText(msg.getMsg());
        holder.authorTv.setText("- " + msg.getAuthor());

        holder.itemView.setOnLongClickListener(v -> {

            FirebaseDatabase.getInstance()
                    .getReference("msg")
                    .child(msg.getKey())
                    .removeValue();

            return true;
        });
    }

    @Override
    public int getItemCount() {
        return messageList.size();
    }
}