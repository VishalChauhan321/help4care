package com.moutamid.ewastemanagement;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.app.Activity;
import android.os.Bundle;

import com.moutamid.ewastemanagement.adapters.SubCatagoryAdapter;
import com.moutamid.ewastemanagement.databinding.ActivitySubCategoryBinding;

import java.util.ArrayList;

public class SubCategoryActivity extends AppCompatActivity {
    ActivitySubCategoryBinding binding;
    String name[] = {"Computer & Laptops", "TVs, Video-Audio", "Camera & Lenses", "Games & Entertainment", "Fridges", "ACs", "Washing Machine", "Computer Accessories", "Hard Disks", "Printers & Monitors"};
    ArrayList<String> list;
    SubCatagoryAdapter adapter;
    String n;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivitySubCategoryBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        n = getIntent().getStringExtra("name");

        list = new ArrayList<>();

        binding.catName.setText(n);

        binding.backbtn.setOnClickListener(v -> {
            onBackPressed();
            finish();
        });

        for (int i = 0; i < name.length; i++) {
            list.add(name[i]);
        }

        adapter = new SubCatagoryAdapter(this, list);

        binding.subRC.setLayoutManager(new LinearLayoutManager(this));
        binding.subRC.setHasFixedSize(false);
        binding.subRC.setAdapter(adapter);

    }
}