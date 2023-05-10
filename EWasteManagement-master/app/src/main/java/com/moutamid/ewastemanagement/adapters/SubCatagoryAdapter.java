package com.moutamid.ewastemanagement.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Filter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.moutamid.ewastemanagement.R;

import java.util.ArrayList;
import java.util.Collection;

public class SubCatagoryAdapter extends RecyclerView.Adapter<SubCatagoryAdapter.CategoryVH> {
    Context context;
    ArrayList<String> categories;

    public SubCatagoryAdapter(Context context, ArrayList<String> list) {
        this.context = context;
        this.categories = list;
    }

    @NonNull
    @Override
    public SubCatagoryAdapter.CategoryVH onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.sub_category_item, parent, false);
        return new SubCatagoryAdapter.CategoryVH(view);
    }

    @Override
    public void onBindViewHolder(@NonNull SubCatagoryAdapter.CategoryVH holder, int position) {
        holder.item.setText(categories.get(position));
    }

    @Override
    public int getItemCount() {
        return categories.size();
    }


    public class CategoryVH extends RecyclerView.ViewHolder{
        TextView item;
        public CategoryVH(@NonNull View itemView) {
            super(itemView);
            item = itemView.findViewById(R.id.itemText);
        }
    }
}
