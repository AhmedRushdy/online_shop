package com.example.yourdevices.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.yourdevices.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.squareup.picasso.Picasso;

public class Profile extends AppCompatActivity {
    Button log_out;
    ImageView iv;
    TextView name , email;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        log_out = findViewById(R.id.log_out_btn);
        iv = findViewById(R.id.change_image);
        name = findViewById(R.id.profile_name);
        email = findViewById(R.id.profile_email);

        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        Picasso.get().load(user.getPhotoUrl()).into(iv);
        name.setText(user.getDisplayName());
        email.setText(user.getEmail());

        if (user != null) {
            log_out.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    signOut();
                }
            });}
        iv.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {

                return false;
            }
        });    }
    public void signOut() {
        // [START auth_sign_out]
        FirebaseAuth.getInstance().signOut();
        // [END auth_sign_out]
    }}