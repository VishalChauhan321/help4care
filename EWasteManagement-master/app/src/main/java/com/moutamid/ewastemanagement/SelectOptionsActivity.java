package com.moutamid.ewastemanagement;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import com.moutamid.ewastemanagement.databinding.ActivitySelectOptionsBinding;

public class SelectOptionsActivity extends AppCompatActivity {
    ActivitySelectOptionsBinding binding;
    String option = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivitySelectOptionsBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.submitBtn.setOnClickListener(v -> {
            startActivity(new Intent(this, DashboardActivity.class));
            finish();
        });

        binding.recycleBtn.setOnClickListener(v -> {
            option = "Recycle";
            binding.recycleBtn.setCardBackgroundColor(getResources().getColor(R.color.green));
            binding.recycleTV.setTextColor(getResources().getColor(R.color.white));

            binding.repairBtn.setCardBackgroundColor(getResources().getColor(R.color.white));
            binding.repairTv.setTextColor(getResources().getColor(R.color.textblack));

            binding.reuseBtn.setCardBackgroundColor(getResources().getColor(R.color.white));
            binding.reuseTv.setTextColor(getResources().getColor(R.color.textblack));
        });

        binding.repairBtn.setOnClickListener(v -> {
            option = "Repair";
            binding.recycleBtn.setCardBackgroundColor(getResources().getColor(R.color.white));
            binding.recycleTV.setTextColor(getResources().getColor(R.color.textblack));

            binding.repairBtn.setCardBackgroundColor(getResources().getColor(R.color.green));
            binding.repairTv.setTextColor(getResources().getColor(R.color.white));

            binding.reuseBtn.setCardBackgroundColor(getResources().getColor(R.color.white));
            binding.reuseTv.setTextColor(getResources().getColor(R.color.textblack));
        });

        binding.reuseBtn.setOnClickListener(v -> {
            option = "Reuse";
            binding.recycleBtn.setCardBackgroundColor(getResources().getColor(R.color.white));
            binding.recycleTV.setTextColor(getResources().getColor(R.color.textblack));

            binding.repairBtn.setCardBackgroundColor(getResources().getColor(R.color.white));
            binding.repairTv.setTextColor(getResources().getColor(R.color.textblack));

            binding.reuseBtn.setCardBackgroundColor(getResources().getColor(R.color.green));
            binding.reuseTv.setTextColor(getResources().getColor(R.color.white));
        });
    }
}