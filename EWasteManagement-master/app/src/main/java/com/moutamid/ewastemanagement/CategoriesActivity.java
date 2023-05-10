package com.moutamid.ewastemanagement;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import com.moutamid.ewastemanagement.databinding.ActivityCategoriesBinding;

public class CategoriesActivity extends AppCompatActivity {
    ActivityCategoriesBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityCategoriesBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.goBtn.setOnClickListener(v -> {
            startActivity(new Intent(this, SelectOptionsActivity.class));
            finish();
        });
    }
}