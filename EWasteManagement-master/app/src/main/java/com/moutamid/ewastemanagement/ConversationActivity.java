package com.moutamid.ewastemanagement;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.os.Bundle;

import com.moutamid.ewastemanagement.adapters.ConversationAdapter;
import com.moutamid.ewastemanagement.databinding.ActivityConversationBinding;
import com.moutamid.ewastemanagement.models.ConversationModel;

import java.util.ArrayList;

public class ConversationActivity extends AppCompatActivity {
    ActivityConversationBinding binding;

    private String[] message_chat = {"Hi1", "Hi2", "Hi3", "Hi4", "Hi5", "Hi6",};
    private String[] time_chat = {"Just Now ", "9 hours ago " , "9 hours ago " , "Just Now ", "9 hours ago ", "Just Now",};
    ArrayList<ConversationModel> messageArrayList;
    ConversationAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityConversationBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        messageArrayList = new ArrayList();

        binding.backbtn.setOnClickListener(v -> {
            onBackPressed();
            finish();
        });

        binding.chatRC.setLayoutManager(new LinearLayoutManager(this));
        binding.chatRC.setHasFixedSize(false);

        loadchat();

    }

    private void loadchat() {
        messageArrayList = new ArrayList<>();

        for (int i = 0; i < message_chat.length; i++) {
            ConversationModel modelAndroid = new ConversationModel(
                    message_chat[i],
                    time_chat[i],
                    R.drawable.dummy_profile
            );
            messageArrayList.add(modelAndroid);
        }
        adapter = new ConversationAdapter(this, messageArrayList);
        binding.chatRC.setAdapter(adapter);
    }

}