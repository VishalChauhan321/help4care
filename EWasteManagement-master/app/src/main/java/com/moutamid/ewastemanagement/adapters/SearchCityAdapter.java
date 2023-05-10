package com.moutamid.ewastemanagement.adapters;

import android.content.Context;
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

public class SearchCityAdapter extends RecyclerView.Adapter<SearchCityAdapter.CityVH> implements Filterable {
    Context context;
    ArrayList<String> cities;
    ArrayList<String> citiesAll;
    EditText searchCities;

    public SearchCityAdapter(Context context, ArrayList<String> cities, EditText searchCities) {
        this.context = context;
        this.cities = cities;
        citiesAll = new ArrayList<>(cities);
        this.searchCities = searchCities;
    }

    @NonNull
    @Override
    public CityVH onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.search_city_card, parent, false);
        return new CityVH(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CityVH holder, int position) {
        holder.city.setText(cities.get(position));
        holder.city.setOnClickListener(v -> {
            searchCities.setText(holder.city.getText().toString());
        });
    }

    @Override
    public int getItemCount() {
        return cities.size();
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
                filterList.addAll(citiesAll);
            } else {
                for (String listModel : cities){
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
            cities.clear();
            cities.addAll((Collection<? extends String>) filterResults.values);
            notifyDataSetChanged();
        }
    };

    public class CityVH extends RecyclerView.ViewHolder{
        TextView city;
        public CityVH(@NonNull View itemView) {
            super(itemView);
            city = itemView.findViewById(R.id.search_item);
        }
    }
}
