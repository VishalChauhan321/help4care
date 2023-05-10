package com.moutamid.ewastemanagement;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import com.moutamid.ewastemanagement.databinding.ActivityLoginBinding;
import com.moutamid.ewastemanagement.databinding.ActivitySignUpBinding;

public class SignUpActivity extends AppCompatActivity {
    ActivitySignUpBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivitySignUpBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.backBtn.setOnClickListener(v -> {
            startActivity(new Intent(SignUpActivity.this, MainActivity.class));
            finish();
        });

        binding.signUpBtn.setOnClickListener(v -> {
            startActivity(new Intent(SignUpActivity.this, CategoriesActivity.class));
            finish();
        });
    }
}