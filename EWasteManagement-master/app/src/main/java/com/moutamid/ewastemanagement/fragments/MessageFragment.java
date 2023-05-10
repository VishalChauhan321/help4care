package com.moutamid.ewastemanagement.fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.moutamid.ewastemanagement.R;
import com.moutamid.ewastemanagement.adapters.MessageAdapter;
import com.moutamid.ewastemanagement.databinding.FragmentMessageBinding;
import com.moutamid.ewastemanagement.models.MessageModel;

import java.util.ArrayList;

public class MessageFragment extends Fragment {

    FragmentMessageBinding binding;
    MessageModel model;
    MessageAdapter adapter;
    ArrayList<MessageModel> list;
    String name[] = {"Suleman Ijaz", "Moutamid Waseem", "Babrak", "Ali", "Ibrahim"};
    String last[] = {"Lorem ipsum dolor sit amet,", "Lorem ipsum dolor sit amet,", "Lorem ipsum dolor sit amet,", "Lorem ipsum dolor sit amet,", "Lorem ipsum dolor sit amet,"};
    String date[] = {"22/12/2022", "02/04/2022", "12/02/2022", "25/11/2022", "21/12/2022"};

    public MessageFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding =  FragmentMessageBinding.inflate(inflater, container, false);
        View view = binding.getRoot();

        list = new ArrayList<>();

        getData();

        adapter = new MessageAdapter(view.getContext(), list);
        binding.mesagesRC.setLayoutManager(new LinearLayoutManager(view.getContext()));
        binding.mesagesRC.setHasFixedSize(false);
        binding.mesagesRC.setAdapter(adapter);

        return view;
    }

    private void getData() {
        for (int i = 0; i < name.length; i++) {
            model = new MessageModel(date[i], name[i], last[i], R.drawable.dummy_profile);
            list.add(model);
        }
    }
}