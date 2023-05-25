package com.example.may;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.may.Adapters.OutpassAdapter;
import com.example.may.Domain.DomainOutpass;
import com.example.may.Models.UserStudent;
import com.example.may.databinding.ActivityStudentDashboardBinding;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class StudentDashboard extends AppCompatActivity {

    private RecyclerView.Adapter adapter;
    private RecyclerView recyclerView;
    //    public String[] stData;
    FirebaseDatabase firebaseDatabase;
    DatabaseReference reference;

    ActivityStudentDashboardBinding binding;
    SharedPreferences preferences;
    SharedPreferences.Editor editor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityStudentDashboardBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        preferences = getSharedPreferences("MyPreferences", MODE_PRIVATE);
        editor = preferences.edit();

        recyclerViewOutpass();
//        String[] stData;
        // below line is used to get the instance
        // of our Firebase database.
        firebaseDatabase = FirebaseDatabase.getInstance();
        // create the get Intent object
//        Intent intent = getIntent();
        // receive the value by getStringExtra() method and
        // key must be same which is send by first activity
        System.out.println("Hellloooo"+preferences.getString("saved_userType",""));
        binding.tvName.setText(preferences.getString("stName",""));
        binding.tvRollnum.setText(preferences.getString("rollNum",""));
        binding.tvHostelName.setText(preferences.getString("towName",""));
        binding.tvRoomNum.setText(preferences.getString("roomNum",""));
//        String id = intent.getStringExtra("userid");

//        reference = firebaseDatabase.getReference().child("Users").child("Student").child(id);
//        reference.addValueEventListener(new ValueEventListener() {
//            @Override
//            public void onDataChange(@NonNull DataSnapshot snapshot) {
//                // this method is call to get the realtime
//                // updates in the data.
//                // this method is called when the data is
//                // changed in our Firebase console.
//                // below line is for getting the data from
//                // snapshot of our database.
//                UserStudent user = snapshot.getValue(UserStudent.class);
//                assert user != null;
//                binding.tvName.setText(user.userName);
//                binding.tvRollnum.setText(user.rollNum);
//                binding.tvHostelName.setText(user.hostelName);
//                binding.tvRoomNum.setText(user.roomNum);
//            }
//
//            @Override
//            public void onCancelled(@NonNull DatabaseError error) {
//                Toast.makeText(StudentDashboard.this, "Fail to get data", Toast.LENGTH_SHORT).show();
//            }
//        });
//        temp logout on clicking hamburger button
        binding.btnHamburger.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                editor.clear();
                editor.commit();
                finishAffinity();
//                ProcessPhoenix.triggerRebirth(getApplicationContext());
                Intent intent = new Intent(StudentDashboard.this, choosetype.class);
//                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
//                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
//                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
                finish();
            }
        });
        binding.imageButton3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(StudentDashboard.this,NewOutpassForm.class);
//                intent.putExtra("uid",id);
                startActivity(intent);
            }
        });
    }

    private void recyclerViewOutpass() {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false);
        recyclerView = findViewById(R.id.view);
        recyclerView.setLayoutManager(linearLayoutManager);

        ArrayList<DomainOutpass> domainOutpassesArrayList = new ArrayList<>();

        domainOutpassesArrayList.add(new DomainOutpass("10000", "6 May, 2023", "6 May 2023","5:30pm","6 May, 2023","9:00pm","OYO","In Progress" ));
        domainOutpassesArrayList.add(new DomainOutpass("10001", "7 May, 2023", "6 May 2023","5:30pm","17 May, 2023","9:00pm","Bangalore","Approved" ));
        domainOutpassesArrayList.add(new DomainOutpass("10002", "8 May, 2023", "6 May 2003","5:30pm","6 May, 2023","9:00pm","GF's Home","Denied" ));
        domainOutpassesArrayList.add(new DomainOutpass("10003", "8 May, 2023", "6 May 2003","5:30pm","6 May, 2023","9:00pm","Barkhaas","Denied" ));
        domainOutpassesArrayList.add(new DomainOutpass("10004", "8 May, 2023", "6 May 2003","5:30pm","6 May, 2023","9:00pm","Alpha","Denied" ));
        domainOutpassesArrayList.add(new DomainOutpass("10005", "8 May, 2023", "6 May 2003","5:30pm","6 May, 2023","9:00pm","Benz Circle","Denied" ));
        domainOutpassesArrayList.add(new DomainOutpass("10006", "8 May, 2023", "6 May 2003","5:30pm","6 May, 2023","9:00pm","Mangalagiri","Denied" ));

        adapter = new OutpassAdapter(domainOutpassesArrayList);
        recyclerView.setAdapter(adapter);
    }
}