package com.moutamid.ewastemanagement;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.os.Bundle;

import com.moutamid.ewastemanagement.adapters.AllCategoryAdapter;
import com.moutamid.ewastemanagement.databinding.ActivityAllCategoryBinding;
import com.moutamid.ewastemanagement.models.AllCategoryModel;

import java.util.ArrayList;

public class AllCategoryActivity extends AppCompatActivity {

    ActivityAllCategoryBinding binding;
    ArrayList<AllCategoryModel> list;
    AllCategoryModel model;
    AllCategoryAdapter adapter;
    String name[] = {"All Businesses", "Active Life", "Art & Entertainment", "Automotive Service", "Bars", "Education", "Food", "Home Services", "Event Management Services"};
    int icons[] = {R.drawable.ic_baseline_business, R.drawable.ic_baseline_active_life, R.drawable.ic_baseline_music, R.drawable.ic_round_directions_car, R.drawable.ic_round_wine_bar, R.drawable.ic_baseline_menu_book, R.drawable.ic_baseline_fastfood, R.drawable.ic_baseline_home, R.drawable.ic_baseline_event_note };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityAllCategoryBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        list = new ArrayList<>();

        getData();

        binding.backbtn.setOnClickListener(v -> {
            onBackPressed();
            finish();
        });

        binding.itemsRC.setLayoutManager(new LinearLayoutManager(this));
        binding.itemsRC.setHasFixedSize(false);
        adapter = new AllCategoryAdapter(this, list);
        binding.itemsRC.setAdapter(adapter);
    }

    private void getData() {
        for (int i = 0; i < name.length; i++) {
            model = new AllCategoryModel(name[i], icons[i]);
            list.add(model);
        }
    }
}