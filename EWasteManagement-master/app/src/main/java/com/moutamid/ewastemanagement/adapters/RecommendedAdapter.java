package com.moutamid.ewastemanagement.adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.moutamid.ewastemanagement.ListDetailActivity;
import com.moutamid.ewastemanagement.R;
import com.moutamid.ewastemanagement.models.RecommendedModel;

import java.util.ArrayList;

import de.hdodenhof.circleimageview.CircleImageView;

public class RecommendedAdapter extends RecyclerView.Adapter<RecommendedAdapter.RecommendVH> {
    Context context;
    ArrayList<RecommendedModel> list;

    public RecommendedAdapter(Context context, ArrayList<RecommendedModel> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public RecommendVH onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.recommended_card, parent, false);
        return new RecommendVH(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecommendVH holder, int position) {
        RecommendedModel model = list.get(position);
        holder.name.setText(model.getName());
        holder.imageView.setImageResource(model.getImage());
        holder.logo.setImageResource(model.getLogo());

        holder.itemView.setOnClickListener(v -> {
            context.startActivity(new Intent(context, ListDetailActivity.class));
        });

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class RecommendVH extends RecyclerView.ViewHolder{
        ImageView imageView;
        TextView name;
        CircleImageView logo;

        public RecommendVH(@NonNull View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.name);
            logo = itemView.findViewById(R.id.profileImage);
            imageView = itemView.findViewById(R.id.imageView);
        }
    }
}
