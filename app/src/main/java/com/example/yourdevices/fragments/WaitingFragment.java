package com.example.yourdevices.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.yourdevices.R;
import com.example.yourdevices.adapters.CarAdapter;
import com.example.yourdevices.adapters.WaitingAdapter;
import com.example.yourdevices.models.Products;
import com.example.yourdevices.models.Users;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class WaitingFragment extends Fragment {
    FirebaseAuth auth;
    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference , addressRef;

    FirebaseUser currentUser;
    private RecyclerView orderProducts;
    private WaitingAdapter waitingAdapter;
    private ArrayList<Products> productsList;
    private float total =0 ;
    private TextView address, totalPrice;
    private View view;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_wait,container,false);

        initFirebase();
        productsList = new ArrayList<>();
        orderProducts = view.findViewById(R.id.waiting_rv);
        totalPrice = view.findViewById(R.id.total_price_waiting);
        address = view.findViewById(R.id.tv_wait_address);

        orderProducts.setLayoutManager(new LinearLayoutManager(view.getContext()));

        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if(snapshot.getValue() !=null){
                    for(DataSnapshot dataSnapshot:snapshot.getChildren()){
                        productsList.add(dataSnapshot.getValue(Products.class));
                        total+=dataSnapshot.getValue(Products.class).getPrice();
                    }
                    totalPrice.setText(String.valueOf(total));
                }
                waitingAdapter = new WaitingAdapter(productsList,view.getContext());
                orderProducts.setAdapter(waitingAdapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });


        return view;
    }

    private void initFirebase() {
        auth = FirebaseAuth.getInstance();
        currentUser = auth.getCurrentUser();
        firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference = firebaseDatabase.getReference("orders").child(currentUser.getUid());
        addressRef = firebaseDatabase.getReference("users").child(currentUser.getUid());
    }
    private void getAddress(){
        addressRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.getValue() !=null){
                    address.setText(snapshot.getValue(Users.class).getAddress());
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }




}
