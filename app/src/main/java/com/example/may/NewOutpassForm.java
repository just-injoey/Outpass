package com.example.may;

import static java.lang.Thread.sleep;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ActionBar;
import android.app.DatePickerDialog;
import android.app.ProgressDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.DatePicker;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.example.may.Models.OutPass;
//import com.example.may.Models.UserStudent;
import com.example.may.databinding.ActivityNewOutpassFormBinding;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.sql.Time;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

public class NewOutpassForm extends AppCompatActivity {
    ProgressDialog progressDialog;
    FirebaseDatabase firebaseDatabase;
    DatabaseReference reference;
    ActivityNewOutpassFormBinding binding;
    SharedPreferences preferences;
    SharedPreferences.Editor editor;
    public Long outPassCount;
    public String[] months;
    public int year1;
    public int month1;
    public int dayOfMonth1;
    public int hour1;
    public int minute1;
    public String chosenDate="";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityNewOutpassFormBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        preferences =  getSharedPreferences("MyPreferences",MODE_PRIVATE);
        editor = preferences.edit();

        Thread thread2 = new Thread(new Runnable() {
            @Override
            public void run() {
                firebaseDatabase = FirebaseDatabase.getInstance();
                firebaseDatabase.getReference().child("OutPasses").child("count")
                        .addValueEventListener(new ValueEventListener() {
                            @Override
                            public void onDataChange(@NonNull DataSnapshot snapshot) {

                                outPassCount = snapshot.getValue(Long.class);
                                System.out.println(outPassCount);
                                System.out.println("-----------------------------------------------");

                            }
                            @Override
                            public void onCancelled(@NonNull DatabaseError error) {

                            }
                        });
            }
        });
        thread2.start();
//        firebaseDatabase = FirebaseDatabase.getInstance();
//        firebaseDatabase.getReference().child("OutPasses").child("count")
//                .addValueEventListener(new ValueEventListener() {
//                    @Override
//                    public void onDataChange(@NonNull DataSnapshot snapshot) {
//
//                        outPassCount = snapshot.getValue(Long.class);
//                        System.out.println(outPassCount);
//                        System.out.println("-----------------------------------------------");
//
//                    }
//                    @Override
//                    public void onCancelled(@NonNull DatabaseError error) {
//
//                    }
//                });

        months=new String[]{"Jan","Feb","Mar","Apr","May","Jun","Jul","Aug","Sept","Oct","Nov","Dec"};
        Calendar calendar = Calendar.getInstance();
        this.year1 = calendar.get(Calendar.YEAR);
        this.month1 = calendar.get(Calendar.MONTH);
        this.dayOfMonth1 = calendar.get(Calendar.DAY_OF_MONTH);
        this.hour1=calendar.get(Calendar.HOUR_OF_DAY);
        this.minute1=calendar.get(Calendar.MINUTE);
        System.out.println("Reached here");
        binding.submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                System.out.println("Submit button clicked");
                if(outPassCount!=null){

                    OutPass outPass = new OutPass(outPassCount+1, dayOfMonth1 + "-" + months[month1] + "-" + String.valueOf(year1),
                            binding.textView27.getText().toString(), binding.textView24.getText().toString(), binding.textView23.getText().toString(),
                            binding.textView29.getText().toString(), preferences.getString("stName", ""), preferences.getString("towName", ""),
                            binding.textView21.getText().toString(), preferences.getString("roomNum", ""),
                            "Not yet Set", preferences.getLong("contactNum", 0));
                    firebaseDatabase.getReference().child("OutPasses").child("count").setValue(outPassCount+1);
//                System.out.println(fcount);
//                Set<String> op_data = new HashSet<String>();
//                editor.putString("")
                    String opData = outPass.getDateGenerated()+ "," + outPass.getOutDate() + "," + outPass.getOutTime() + "," + outPass.getInDate() + "," + outPass.getInTime() + "," + outPass.getStudentName() + "," +
                            outPass.getTowerName() + "," + outPass.getToPlace() + "," + outPass.getRoomNum() + "," + "Not yet Set" + "," + String.valueOf(outPass.getContactNum()) + "," + outPass.getOid();
//                op_data.addAll(Arrays.asList(opData));
//                System.out.println("Itna hua");

                    Thread thread = new Thread(new Runnable() {
                        @Override
                        public void run() {
                            firebaseDatabase.getReference().child("OutPasses").child(String.valueOf(outPass.oid)).setValue(outPass);
                            firebaseDatabase.getReference().child("Users").child("Student").child(preferences.getString("saved_userid", "")).child("OutPasses").child(String.valueOf(outPass.oid)).setValue(outPass);
                            runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    Toast.makeText(NewOutpassForm.this, "OutPass Created Successfully", Toast.LENGTH_SHORT).show();
                                }
                            });

                            editor.putString(outPass.oid, opData);
                            editor.commit();

                            System.out.println("NewFormmmmmmmmmm");
                            System.out.println(outPass.oid);
                            System.out.println(preferences.getString(outPass.oid, "gayaHeNahi"));

                            Intent intent = new Intent(NewOutpassForm.this, OutpassGenerated.class);
                            intent.putExtra("oid", outPass.oid);
                            startActivity(intent);
                            finish();
                            System.out.println("Idhar hu ab mai");
                        }
                    });
                    thread.start();
//                    firebaseDatabase.getReference().child("OutPasses").child(String.valueOf(outPass.oid)).setValue(outPass);
//                    firebaseDatabase.getReference().child("Users").child("Student").child(preferences.getString("saved_userid", "")).child("OutPasses").child(String.valueOf(outPass.oid)).setValue(outPass);
//                    Toast.makeText(NewOutpassForm.this, "OutPass Created Successfully", Toast.LENGTH_SHORT).show();
//
////                    editor.putString(outPass.oid, opData);
////                    editor.commit();
//                    System.out.println("NewFormmmmmmmmmm");
//                    System.out.println(preferences.getString(outPass.oid, "gayaHeNahi"));
//
//
////                    try {
//                    Intent intent = new Intent(NewOutpassForm.this, OutpassGenerated.class);
//                    intent.putExtra("oid", outPass.oid);
//                    startActivity(intent);
//                    finish();
////                    } catch (Exception e) {
////                        System.out.println("Error aaya");
////                        System.out.println(e.getMessage());
////                        e.printStackTrace();
////                    }
                }
                else{
                    Toast.makeText(getApplicationContext(), "Please wait for the count to be retrieved from Firebase", Toast.LENGTH_SHORT).show();
                }
            }
        });
        binding.tvName2.setText(preferences.getString("stName",",,,"));
        binding.tvRollnum2.setText(preferences.getString("rollNum",""));
        binding.tvHostelName2.setText(preferences.getString("towName",""));
        binding.tvRoomNum2.setText(preferences.getString("roomNum",""));

        binding.btnDateOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openDialogDateOut();
            }
        });
        binding.btnDateIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openDialogDateIn();
            }
        });
        binding.btnTimeOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openDialogTimeOut();
            }
        });
        binding.btnTimeIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openDialogTimeIn();
            }
        });

    }
    private void openDialogDateOut(){
        DatePickerDialog datePickerDialog=new DatePickerDialog(this,R.style.DialogTheme, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                if(Integer.valueOf(dayOfMonth)<10){
                    binding.textView27.setText("0"+String.valueOf(dayOfMonth)+"-"+months[Integer.valueOf(month)]+"-"+String.valueOf(year));
                }else {
                    binding.textView27.setText(String.valueOf(dayOfMonth) + "-" + months[Integer.valueOf(month)] + "-" + String.valueOf(year));
                }
            }
        }, year1, month1, dayOfMonth1);
        if(binding.textView23.getText().toString().equals("DD/MM/YYYY")){
            // Get the DatePicker from the dialog
            DatePicker datePicker = datePickerDialog.getDatePicker();
            // Set the minimum date
            Calendar minDate = Calendar.getInstance();
            minDate.set(this.year1, this.month1, this.dayOfMonth1); // Set to January 1st of the minimum year

            datePicker.setMinDate(minDate.getTimeInMillis());

            // Set the maximum date
            Calendar maxDate = Calendar.getInstance();
//        maxDate.set(maxYear, this.month1, this.dayOfMonth1+2); // Set to December 31st of the maximum year
            maxDate.add(Calendar.DATE,2);
            datePicker.setMaxDate(maxDate.getTimeInMillis());
//        datePicker.setMaxDate(minDate.);
        }else{
            String inputDateIn = binding.textView23.getText().toString();
            String[] split= inputDateIn.split("-");
            int inputDate = Integer.parseInt(split[0]);
            int inputMonth = Integer.parseInt(split[1]);
            int inputYear = Integer.parseInt(split[2]);
            // Get the DatePicker from the dialog
            DatePicker datePicker = datePickerDialog.getDatePicker();
            // Set the minimum date
            Calendar minDate = Calendar.getInstance();
            minDate.set(this.year1, this.month1, this.dayOfMonth1); // Set to January 1st of the minimum year

            datePicker.setMinDate(minDate.getTimeInMillis());

            // Set the maximum date
            Calendar maxDate = Calendar.getInstance();

            maxDate.set(inputYear, inputMonth, inputDate); // Set to December 31st of the maximum year
            datePicker.setMaxDate(maxDate.getTimeInMillis());
        }
        datePickerDialog.show();
        datePickerDialog=null;
    }
    private void openDialogDateIn(){
        DatePickerDialog datePickerDialog=new DatePickerDialog(this,R.style.DialogTheme, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                if(Integer.valueOf(dayOfMonth)<10){
                    binding.textView23.setText("0"+String.valueOf(dayOfMonth)+"-"+months[Integer.valueOf(month)]+"-"+String.valueOf(year));
                }else {
                    binding.textView23.setText(String.valueOf(dayOfMonth) + "-" + months[Integer.valueOf(month)] + "-" + String.valueOf(year));
                }
            }
        }, year1, month1, dayOfMonth1);

//        if(binding.textView27.getText().toString().equals("DD/MM/YYYY")){
//            // Get the DatePicker from the dialog
//            DatePicker datePicker = datePickerDialog.getDatePicker();
//            // Set the minimum date
//            Calendar minDate = Calendar.getInstance();
//            minDate.set(this.year1, this.month1, this.dayOfMonth1); // Set to January 1st of the minimum year
//
//            datePicker.setMinDate(minDate.getTimeInMillis());
//
//            // Set the maximum date
//            Calendar maxDate = Calendar.getInstance();
////            maxDate.set(this.year1, this.month1, this.dayOfMonth1+2); // Set to December 31st of the maximum year
//            maxDate.add(Calendar.DATE,60);
//            datePicker.setMaxDate(maxDate.getTimeInMillis());
////        datePicker.setMaxDate(minDate.);
//        }else{
//            String inputDateOut = binding.textView27.getText().toString();
//            String[] split= inputDateOut.split("-");
//            int inputDate = Integer.parseInt(split[0]);
//            int inputMonth = Integer.parseInt(split[1]);
//            int inputYear = Integer.parseInt(split[2]);
//            // Get the DatePicker from the dialog
//            DatePicker datePicker = datePickerDialog.getDatePicker();
//            // Set the minimum date
//            Calendar minDate = Calendar.getInstance();
//            minDate.set(inputDate, inputMonth, inputYear); // Set to January 1st of the minimum year
//
//            datePicker.setMinDate(minDate.getTimeInMillis());
//
//            // Set the maximum date
////            Calendar maxDate = Calendar.getInstance();//error hai//yeh chal raha tha
//            //mindate+60
//            Calendar maxDate = minDate;
//
//            maxDate.add(Calendar.DATE,60);
////            maxDate.set(inputYear, inputMonth, inputDate); // Set to December 31st of the maximum year
//            datePicker.setMaxDate(maxDate.getTimeInMillis());
//        }
        datePickerDialog.show();
        datePickerDialog=null;
    }
    private void openDialogTimeOut(){
        TimePickerDialog timePickerDialog = new TimePickerDialog(this, R.style.DialogTheme, new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                Date date= new Date(year1,month1,dayOfMonth1,Integer.valueOf(hourOfDay),Integer.valueOf(minute));
                String strDateFormat = "HH : mm";
                SimpleDateFormat sdf = new SimpleDateFormat(strDateFormat);
                binding.textView24.setText(sdf.format(date));
            }
        }, hour1, minute1, false);

//        TimePicker timePicker = timePickerDialog.
        timePickerDialog.show();
        timePickerDialog=null;
    }
    private void openDialogTimeIn(){
        TimePickerDialog timePickerDialog = new TimePickerDialog(this, R.style.DialogTheme, new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                Date date= new Date(year1,month1,dayOfMonth1,Integer.valueOf(hourOfDay),Integer.valueOf(minute));
                String strDateFormat = "HH : mm";
                SimpleDateFormat sdf = new SimpleDateFormat(strDateFormat);
                binding.textView29.setText(sdf.format(date));
            }
        }, hour1, minute1, true);
        timePickerDialog.show();
        timePickerDialog=null;
    }


}