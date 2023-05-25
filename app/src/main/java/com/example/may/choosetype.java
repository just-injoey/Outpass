package com.example.may;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.os.Bundle;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.style.StyleSpan;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.may.databinding.ActivityChoosetypeBinding;


public class choosetype extends AppCompatActivity {
    ActivityChoosetypeBinding binding;
    SharedPreferences preferences;
    SharedPreferences.Editor editor;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityChoosetypeBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        preferences = getSharedPreferences("MyPreferences", MODE_PRIVATE);
        editor = preferences.edit();
        System.out.println(preferences.getString("saved_userType","Kuch Nahi Hai"));
        if(preferences.contains("saved_userType")){
            if(preferences.getString("saved_userType","").equals("Student")) {
                System.out.println(preferences.getString("saved_userType",""));
                Intent intent = new Intent(choosetype.this, StudentDashboard.class);
                startActivity(intent);
                finish();
            }
            else if(preferences.getString("saved_userType","").equals("Warden")) {
                System.out.println(preferences.getString("saved_userType",""));
                Intent intent = new Intent(choosetype.this, WardenDashboard.class);
                startActivity(intent);
                finish();
            }
            else if(preferences.getString("saved_userType","").equals("Guard")) {
                System.out.println(preferences.getString("saved_userType",""));
                Intent intent = new Intent(choosetype.this, GuardDashboard.class);
                startActivity(intent);
                finish();
            }
        }
        binding.button5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(choosetype.this, StudentLogin.class);
                startActivity(intent);
                finish();
            }
        });
        binding.button9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(choosetype.this, WardenLogin.class);
                startActivity(intent);
                finish();
            }
        });
        binding.button10.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(choosetype.this, GuardLogin.class);
                startActivity(intent);
                finish();
            }
        });
    }
}