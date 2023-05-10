package com.moutamid.ewastemanagement;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;

import com.moutamid.ewastemanagement.adapters.SearchCategoryAdapter;
import com.moutamid.ewastemanagement.adapters.SearchCityAdapter;
import com.moutamid.ewastemanagement.databinding.ActivitySearchBinding;

import java.util.ArrayList;

public class SearchActivity extends AppCompatActivity {
    ActivitySearchBinding binding;
    SearchCategoryAdapter categoryAdapter;
    SearchCityAdapter cityAdapter;
    ArrayList<String> cities, categories;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivitySearchBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.searchRC.setHasFixedSize(false);
        binding.searchRC.setLayoutManager(new LinearLayoutManager(this));

        binding.backbtn.setOnClickListener(v -> {
            onBackPressed();
            finish();
        });

        data();

        cityAdapter = new SearchCityAdapter(SearchActivity.this, cities, binding.searchCities);
        categoryAdapter = new SearchCategoryAdapter(SearchActivity.this, categories, binding.searchCategories);

        binding.searchCategories.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (s.toString().length() > 0){
                    binding.searchRC.setVisibility(View.VISIBLE);
                    binding.vectorLayout.setVisibility(View.GONE);
                } else {
                    binding.searchRC.setVisibility(View.GONE);
                    binding.vectorLayout.setVisibility(View.VISIBLE);
                }
                categoryAdapter.getFilter().filter(s);
                binding.searchRC.setAdapter(categoryAdapter);
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        binding.searchCities.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (s.toString().length() > 0) {
                    binding.searchRC.setVisibility(View.VISIBLE);
                    binding.vectorLayout.setVisibility(View.GONE);
                } else {
                    binding.searchRC.setVisibility(View.GONE);
                    binding.vectorLayout.setVisibility(View.VISIBLE);
                }
                cityAdapter.getFilter().filter(s);
                binding.searchRC.setAdapter(cityAdapter);
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

    }

    private void data() {
        cities = new ArrayList<>();
        cities.add("Sialkot");
        cities.add("Lahore");
        cities.add("Karachi");
        cities.add("Islamabad");
        cities.add("Peshawar");
        cities.add("Patoki");

        categories = new ArrayList<>();
        categories.add("Electronics");
        categories.add("Food");
        categories.add("Printers");
        categories.add("Mobile Phones");
        categories.add("Hard Drives");
        categories.add("Computers/Laptops");
    }
}