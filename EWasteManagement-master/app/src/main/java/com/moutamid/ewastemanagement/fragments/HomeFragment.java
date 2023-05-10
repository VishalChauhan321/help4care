package com.moutamid.ewastemanagement.fragments;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.material.card.MaterialCardView;
import com.moutamid.ewastemanagement.AllCategoryActivity;
import com.moutamid.ewastemanagement.R;
import com.moutamid.ewastemanagement.SearchActivity;
import com.moutamid.ewastemanagement.adapters.RecommendedAdapter;
import com.moutamid.ewastemanagement.databinding.FragmentHomeBinding;
import com.moutamid.ewastemanagement.models.RecommendedModel;

import java.util.ArrayList;

public class HomeFragment extends Fragment {

    FragmentHomeBinding binding;
    String names[] = {"Suleman", "babrak", "Moutamid", "jhon"};
    ArrayList<RecommendedModel> list;
    RecommendedAdapter adapter;
    RecommendedModel model;
    Context context;

    public HomeFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentHomeBinding.inflate(inflater, container, false);
        View view = binding.getRoot();

        list = new ArrayList<>();

        binding.searchBtn.setOnClickListener(v -> {
            startActivity(new Intent(view.getContext(), SearchActivity.class));
        });

        binding.morebtn.setOnClickListener(v -> {
            startActivity(new Intent(view.getContext(), AllCategoryActivity.class));
        });

        binding.recommendedRC.setLayoutManager(new LinearLayoutManager(view.getContext()));
        binding.recommendedRC.setHasFixedSize(false);

        addData();

        adapter = new RecommendedAdapter(view.getContext(), list);
        binding.recommendedRC.setAdapter(adapter);

        return view;
    }

    private void addData() {
        for (int i = 0; i < names.length; i++) {
            model = new RecommendedModel(names[i], R.drawable.dummy_profile, R.drawable.dummy_profile);
            list.add(model);
        }
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}