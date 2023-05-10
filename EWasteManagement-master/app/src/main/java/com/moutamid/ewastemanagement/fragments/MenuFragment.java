package com.moutamid.ewastemanagement.fragments;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.moutamid.ewastemanagement.AddBusinessActivity;
import com.moutamid.ewastemanagement.ProfileActivity;
import com.moutamid.ewastemanagement.R;
import com.moutamid.ewastemanagement.databinding.FragmentMenuBinding;

public class MenuFragment extends Fragment {

    FragmentMenuBinding binding;

    public MenuFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentMenuBinding.inflate(inflater, container, false);
        View view = binding.getRoot();

        binding.Profile.setOnClickListener(v -> {
            startActivity(new Intent(view.getContext(), ProfileActivity.class));
        });

        binding.addBusiness.setOnClickListener(v -> {
            startActivity(new Intent(view.getContext(), AddBusinessActivity.class));
        });

        return view;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}