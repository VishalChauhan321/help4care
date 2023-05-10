package com.moutamid.ewastemanagement.adapters;

import android.content.Context;
import android.text.Editable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.moutamid.ewastemanagement.R;

import java.util.ArrayList;
import java.util.Collection;

public class SearchCategoryAdapter extends RecyclerView.Adapter<SearchCategoryAdapter.CategoryVH> implements Filterable {
    Context context;
    ArrayList<String> categories;
    ArrayList<String> categoriesAll;
    EditText searchCategory;

    public SearchCategoryAdapter(Context context, ArrayList<String> cities, EditText searchCategory) {
        this.context = context;
        this.categories = cities;
        categoriesAll = new ArrayList<>(categories);
        this.searchCategory = searchCategory;
    }

    @NonNull
    @Override
    public CategoryVH onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.search_category_card, parent, false);
        return new CategoryVH(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CategoryVH holder, int position) {
        holder.item.setText(categories.get(position));
        holder.item.setOnClickListener(v -> {
            searchCategory.setText(holder.item.getText().toString());
        });
    }

    @Override
    public int getItemCount() {
        return categories.size();
    }

    @Override
    public Filter getFilter() {
        return filter;
    }

    Filter filter = new Filter() {

        //run on background thread
        @Override
        protected FilterResults performFiltering(CharSequence charSequence) {
            ArrayList<String> filterList = new ArrayList<>();
            if (charSequence.toString().isEmpty()){
                filterList.addAll(categoriesAll);
            } else {
                for (String listModel : categoriesAll){
                    if (listModel.toLowerCase().contains(charSequence.toString().toLowerCase())){
                        filterList.add(listModel);
                    }
                }
            }
            FilterResults filterResults = new FilterResults();
            filterResults.values = filterList;

            return filterResults;
        }

        //run on Ui thread
        @Override
        protected void publishResults(CharSequence charSequence, FilterResults filterResults) {
            categories.clear();
            categories.addAll((Collection<? extends String>) filterResults.values);
            notifyDataSetChanged();
        }
    };

    public class CategoryVH extends RecyclerView.ViewHolder{
        TextView item;
        public CategoryVH(@NonNull View itemView) {
            super(itemView);
            item = itemView.findViewById(R.id.search_item);
        }
    }
}
