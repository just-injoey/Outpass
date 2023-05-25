package com.example.may;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.may.databinding.ActivityGuardDashboardBinding;


public class GuardDashboard extends AppCompatActivity {


    ActivityGuardDashboardBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityGuardDashboardBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.btnScan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(GuardDashboard.this, QrScan.class);
                startActivity(intent);
            }
        });

    }
}