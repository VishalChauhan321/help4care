package com.moutamid.ewastemanagement.adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.moutamid.ewastemanagement.ConversationActivity;
import com.moutamid.ewastemanagement.R;
import com.moutamid.ewastemanagement.models.MessageModel;

import java.util.ArrayList;

import de.hdodenhof.circleimageview.CircleImageView;

public class MessageAdapter extends RecyclerView.Adapter<MessageAdapter.MessageVH> {

    Context context;
    ArrayList<MessageModel> list;

    public MessageAdapter(Context context, ArrayList<MessageModel> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public MessageVH onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.message_card, parent, false);
        return new MessageVH(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MessageVH holder, int position) {
        MessageModel model = list.get(position);
        holder.image.setImageResource(model.getImage());
        holder.date.setText(model.getDate());
        holder.last.setText(model.getLast_message());
        holder.name.setText(model.getName());

        holder.itemView.setOnClickListener(v -> {
            context.startActivity(new Intent(context, ConversationActivity.class));
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class MessageVH extends RecyclerView.ViewHolder{
        CircleImageView image;
        TextView name, last, date;

        public MessageVH(@NonNull View itemView) {
            super(itemView);
            image = itemView.findViewById(R.id.profileImage);
            name = itemView.findViewById(R.id.name);
            last = itemView.findViewById(R.id.lastMessage);
            date = itemView.findViewById(R.id.date);
        }
    }
}
