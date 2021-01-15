package com.example.yourdevices.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.example.yourdevices.R;

public class LikedProducts extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_liked_products);
        setTitle("Liked Products");
    }
}