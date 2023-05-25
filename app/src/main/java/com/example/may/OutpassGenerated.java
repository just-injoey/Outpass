package com.example.may;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.os.Bundle;
import android.view.inputmethod.InputMethodManager;
import android.widget.ImageView;

import com.example.may.databinding.ActivityOutpassGeneratedBinding;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import com.journeyapps.barcodescanner.BarcodeEncoder;

public class OutpassGenerated extends AppCompatActivity {
    ActivityOutpassGeneratedBinding binding;
    SharedPreferences preferences;
    SharedPreferences.Editor editor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_outpass_generated);
        binding=ActivityOutpassGeneratedBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        System.out.println("Kya bolti public");
        preferences =  getSharedPreferences("MyPreferences",MODE_PRIVATE);
        editor = preferences.edit();
        Intent intent=getIntent();
        String oid=intent.getStringExtra("oid");
        String opDataString = preferences.getString(oid,"");
        String[] opData=(opDataString).split(",");
//        String[] opData = {"13May2023,14May2023,9:00,15May2023,9:00,Joey,Godavari,Benz,615,Yes,65355363637"};
//        String[] opData = {"13May2023","14May2023","9:00","15May2023","9:00","Joey","Godavari","Benz","615","Yes","65355363637","5842"}; // this was used as sample data while debugging
//        System.out.println(preferences.getString(oid,"ledura"));
        binding.passId.setText("#"+opData[11]);
        binding.passDateCreated.setText(opData[0]);
        binding.stName.setText(opData[5]);
        binding.dateOut.setText(opData[1]);
        binding.outTime.setText(opData[2]);
        binding.dateIn.setText(opData[3]);
        binding.inTime.setText(opData[4]);
        binding.toPlace.setText(opData[7]);
        binding.towName.setText(opData[6]);
        binding.roomNum.setText(opData[8]);
        binding.approval.setText(opData[9]);
        binding.contactNum.setText(opData[10]);
        // getting text from input text field.
//        String myText = "Id=#"+opData[11]+" || Date="+opData[0]+"\n"+
//                "Out Time: "+opData[1]+" "+opData[2]+"\nIn Time: "+opData[3]+" "+opData[4]+"\n"+
//                "Name: "+opData[5]+" || Tower: "+opData[6]+"\n"+
//                "To: "+opData[7]+" || Room: "+opData[8]+"\n"+
//                "Approved By: "+opData[9]+" || Contact: "+opData[10];
//        String myText = opData[11]+"$$"+opData[0]+"$$"+opData[1]+"$$"+opData[2]+opData[3]+"$$"+opData[4]+"$$"+opData[5]+"$$"+opData[6]+"$$"+opData[7]+"$$"+opData[8]+"$$"+opData[9]+"$$"+opData[10];
        String myText = opDataString;
        // initializing MultiFormatWriter for QR code
        ImageView imageCode = findViewById(R.id.qr_code);
        MultiFormatWriter mWriter = new MultiFormatWriter();
        try {
            // BitMatrix class to encode entered text and set Width & Height
            BitMatrix mMatrix = mWriter.encode(myText, BarcodeFormat.QR_CODE, 250, 250);

            // Changing QR code color to yellow
            BarcodeEncoder mEncoder = new BarcodeEncoder();
            Bitmap mBitmap = mEncoder.createBitmap(mMatrix);
            int[] mColors = new int[mBitmap.getHeight() * mBitmap.getWidth()];
            for (int y = 0; y < mBitmap.getHeight(); y++) {
                for (int x = 0; x < mBitmap.getWidth(); x++) {
                    int pixel = mBitmap.getPixel(x, y);
                    if (Color.red(pixel) == 0 && Color.green(pixel) == 0 && Color.blue(pixel) == 0) {
                        mColors[y * mBitmap.getWidth() + x] = Color.parseColor("#000000");
                    } else {
                        mColors[y * mBitmap.getWidth() + x] = Color.parseColor("#F4F4F4");
                    }
                }
            }
            mBitmap.setPixels(mColors, 0, mBitmap.getWidth(), 0, 0, mBitmap.getWidth(), mBitmap.getHeight());

            imageCode.setImageBitmap(mBitmap); // Setting generated QR code to imageView
            // to hide the keyboard
//            InputMethodManager manager = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
//                manager.hideSoftInputFromWindow(etText.getApplicationWindowToken(), 0);
        } catch (WriterException e) {
            e.printStackTrace();
        }
//        binding.stName.setText("Kyaaaaaaaa");
        System.out.println("Done");
//        System.out.println(opData[5]);
    }
}