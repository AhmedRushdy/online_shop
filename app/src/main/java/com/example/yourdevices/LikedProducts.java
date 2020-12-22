package com.example.yourdevices;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

public class LikedProducts extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_liked_products);
        setTitle("Liked Products");
    }
}