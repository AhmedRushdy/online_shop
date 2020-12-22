package com.example.yourdevices;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.material.ripple.RippleDrawableCompat;
import com.google.android.material.ripple.RippleUtils;

public class Start extends AppCompatActivity implements View.OnClickListener {
    CardView skip;
    Button sign_in,sign_up;
    Intent i;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);
        skip = findViewById(R.id.skip);
        sign_in = findViewById(R.id.log_in_btn);
        sign_up = findViewById(R.id.sign_up_activity);

        skip.setOnClickListener(this);
        sign_in.setOnClickListener(this);
        sign_up.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.skip:
                i = new Intent(Start.this,MainActivity.class);
                startActivity(i);
                break;
            case R.id.sign_up_activity:
                i = new Intent(Start.this,Sign_up.class);
                startActivity(i);
                break;
            case R.id.log_in_btn:
                i = new Intent(Start.this,Log_in.class);
                startActivity(i);
                break;
            default:
                Toast.makeText(this, "error", Toast.LENGTH_SHORT).show();
        }}


}