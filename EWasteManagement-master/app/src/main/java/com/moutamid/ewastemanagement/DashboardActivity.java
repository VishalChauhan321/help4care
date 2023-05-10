package com.moutamid.ewastemanagement;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.os.Bundle;
import android.view.MenuItem;

import com.google.android.material.navigation.NavigationBarView;
import com.moutamid.ewastemanagement.databinding.ActivityDashboardBinding;
import com.moutamid.ewastemanagement.fragments.HomeFragment;
import com.moutamid.ewastemanagement.fragments.MenuFragment;
import com.moutamid.ewastemanagement.fragments.MessageFragment;
import com.moutamid.ewastemanagement.fragments.SellFragment;

public class DashboardActivity extends AppCompatActivity {
    ActivityDashboardBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityDashboardBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        //binding.bottomNav.setItemIconTintList(null);
        getSupportFragmentManager().beginTransaction().replace(R.id.frame, new HomeFragment()).commit();

        binding.bottomNav.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.home_nav:
                        getSupportFragmentManager().beginTransaction().replace(R.id.frame, new HomeFragment()).commit();
                        break;
                    case R.id.sell_nav:
                        getSupportFragmentManager().beginTransaction().replace(R.id.frame, new SellFragment()).commit();
                        break;
                    case R.id.msg_nav:
                        getSupportFragmentManager().beginTransaction().replace(R.id.frame, new MessageFragment()).commit();
                        break;
                    case R.id.more_nav:
                        getSupportFragmentManager().beginTransaction().replace(R.id.frame, new MenuFragment()).commit();
                        break;
                    default:
                        break;
                }
                return true;
            }
        });

    }
}