package com.example.yourdevices;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.example.yourdevices.models.Products;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class ConfirmOrderActivity extends AppCompatActivity {
    private Button confirm,cancel;
    private ListView details;
    ArrayList<Products> productsList;
    ArrayList<String> productName;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_confirm_order);
        confirm = findViewById(R.id.confirm_btn);
        cancel = findViewById(R.id.cancel_btn);
        details = findViewById(R.id.tv_details);

        fetchData();
        fillText();
    }

    private void fillText() {
        for(int i=0; i <productsList.size();i++){
            Products products =new Products();
            products = productsList.get(i);
            productName.add(products.getProName());
        }
        ArrayAdapter adp = new ArrayAdapter(this, android.R.layout.simple_list_item_1, productName);

        details.setAdapter(adp);
    }

    private void fetchData() {

        productsList = new ArrayList<>();
        FirebaseAuth auth = FirebaseAuth.getInstance();
        FirebaseUser currentUser = auth.getCurrentUser();
        FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
        DatabaseReference databaseReference = firebaseDatabase.getReference("cart");
        databaseReference.child(currentUser.getUid()).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                int i = 0;
                if (snapshot != null) {
                    for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
                        Products products = dataSnapshot.getValue(Products.class);
                        productsList.add(products);
                    }


                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });}
}