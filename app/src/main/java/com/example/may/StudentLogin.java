package com.example.may;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.provider.Settings;
import android.view.View;
import android.widget.Toast;

import com.google.firebase.database.GenericTypeIndicator;
import com.google.gson.Gson;
import com.example.may.Models.UserStudent;
import com.example.may.databinding.ActivityStudentLoginBinding;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.HashMap;
import java.util.Objects;


public class StudentLogin extends AppCompatActivity {
    ProgressDialog progressDialog;
    ActivityStudentLoginBinding binding;
    FirebaseAuth auth;
    FirebaseDatabase firebaseDatabase;
    DatabaseReference reference;
    SharedPreferences preferences;
    SharedPreferences.Editor editor;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityStudentLoginBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        auth = FirebaseAuth.getInstance();
        firebaseDatabase = FirebaseDatabase.getInstance();
//        one time login using Shared Preferences
        preferences =  getSharedPreferences("MyPreferences",MODE_PRIVATE);
        editor = preferences.edit();

        if(preferences.contains("saved_userType")){
//            String uid = preferences.getString("saved_userid","");
//            String userType = String.valueOf(firebaseDatabase.getReference().child("Users").child("Student").child(uid).child("userType"));
//            System.out.println(userType);
            Intent intent = new Intent(StudentLogin.this, StudentDashboard.class);
//            intent.putExtra("userid", uid);
            startActivity(intent);
            finish();
        }
        else {
            progressDialog = new ProgressDialog(StudentLogin.this);
            progressDialog.setTitle("Login");
            progressDialog.setMessage("Login in to your account");

            binding.btnLogin.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    progressDialog.show();
                    String mail = binding.etUsername.getText().toString();
                    String pwd = binding.etPwd.getText().toString();
                    auth.signInWithEmailAndPassword(mail, pwd)
                            .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                                @Override
                                public void onComplete(@NonNull Task<AuthResult> task) {
                                    progressDialog.dismiss();
                                    if (task.isSuccessful()) {
                                        String uid = auth.getUid();
//                                        binding.textView12.setText(uid);

                                        reference = firebaseDatabase.getReference("Users").child("Student").child(uid);
                                        reference.addValueEventListener(new ValueEventListener() {
                                            @Override
                                            public void onDataChange(@NonNull DataSnapshot snapshot) {
                                                try {
                                                    HashMap<String, Object> userData = snapshot.getValue(new GenericTypeIndicator<HashMap<String, Object>>() {});

                                                    assert userData != null;
                                                    UserStudent user = new UserStudent(
                                                            userData.get("rollNum").toString(),
                                                            userData.get("userName").toString(),
                                                            userData.get("mail").toString(),
                                                            userData.get("password").toString(),
                                                            Long.parseLong(userData.get("contactNum").toString()),
                                                            userData.get("hostelName").toString(),
                                                            userData.get("roomNum").toString()
                                                    );
                                                    Toast.makeText(StudentLogin.this, user.getUserName(), Toast.LENGTH_SHORT).show();
//                                                    UserStudent user = snapshot.getValue(UserStudent.class);
////                                                    assert user != null;
////                                                    if(user==null){
////                                                        throw new Exception("Nai mila");
////                                                    }
                                                    String userType = user.userType;
                                                    if (Objects.equals(userType, "Student")) {
                                                        editor.clear();
                                                        editor.putString("mail", mail);
//                                                        editor.putString("saved_pwd", pwd);
                                                        editor.putString("saved_userid", auth.getUid());
                                                        editor.putString("stName",user.userName);
                                                        editor.putString("rollNum",user.rollNum);
                                                        editor.putString("towName",user.hostelName);
                                                        editor.putString("saved_userType", userType);
                                                        editor.putString("roomNum",user.roomNum);
//                                                        editor.putLong("contactNum",user.contactNum);
                                                        editor.putLong("contactNum",user.contactNum);
                                                        editor.commit();
                                                        Toast.makeText(StudentLogin.this, userType, Toast.LENGTH_SHORT).show();


                                                        Intent intent = new Intent(StudentLogin.this, StudentDashboard.class);
//                                                        Intent intent = new Intent(StudentLogin.this, SplashActivity.class);
//                                                        intent.putExtra("userid", auth.getUid());
                                                        startActivity(intent);
                                                        reference.removeEventListener(this);
                                                        finish();

                                                    }
                                                    else{
                                                        throw new Exception("Nai mila");
                                                    }
                                                }
                                                catch(Exception e){
                                                    Toast.makeText(StudentLogin.this, "hai error ", Toast.LENGTH_SHORT).show();
                                                    System.out.println("$$$$$$$$$$$$$################");
                                                    System.out.println(e.getMessage());
//                                                    binding.textView7.setText(userType);
//                                                    System.out.println(userType);
                                                }
                                            }

                                            @Override
                                            public void onCancelled(@NonNull DatabaseError error) {

                                            }
                                        });
                                        String userType = String.valueOf(firebaseDatabase.getReference().child("Users").child("Student").child(uid).child("userType"));

                                    } else {
                                        Toast.makeText(StudentLogin.this, task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                                    }
                                }
                            });

                }
            });

        }
    }
}