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

import com.google.android.material.card.MaterialCardView;
import com.moutamid.ewastemanagement.R;
import com.moutamid.ewastemanagement.SubCategoryActivity;
import com.moutamid.ewastemanagement.models.AllCategoryModel;

import java.util.ArrayList;

public class AllCategoryAdapter extends RecyclerView.Adapter<AllCategoryAdapter.CategoryVH> {

    Context context;
    ArrayList<AllCategoryModel> list;

    public AllCategoryAdapter(Context context, ArrayList<AllCategoryModel> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public CategoryVH onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.all_category_item, parent, false);
        return new CategoryVH(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CategoryVH holder, int position) {
        AllCategoryModel model = list.get(position);
        holder.icon.setImageResource(model.getIcon());
        holder.name.setText(model.getName());
        holder.item.setOnClickListener(v -> {
            Intent i = new Intent(context, SubCategoryActivity.class);
            i.putExtra("name", model.getName());
            context.startActivity(i);
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class CategoryVH extends RecyclerView.ViewHolder{
        ImageView icon;
        TextView name;
        MaterialCardView item;
        public CategoryVH(@NonNull View itemView) {
            super(itemView);
            icon = itemView.findViewById(R.id.icon);
            name = itemView.findViewById(R.id.itemText);
            item = itemView.findViewById(R.id.item);
        }
    }
}
