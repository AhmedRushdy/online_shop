package com.example.yourdevices.ui;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.yourdevices.CarAdapter;
import com.example.yourdevices.R;
import com.example.yourdevices.models.Products;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class CarFragment extends Fragment {
    private RecyclerView carProducts;
    private CarAdapter carAdapter;
    private View view;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_car, container, false);
        carProducts = view.findViewById(R.id.car_rv);
        carProducts.setLayoutManager(new LinearLayoutManager(view.getContext()));

        String key = FirebaseAuth.getInstance().getCurrentUser().getUid();

        FirebaseRecyclerOptions<Products> options =
                new FirebaseRecyclerOptions.Builder<Products>()
                        .setQuery(FirebaseDatabase.getInstance().getReference()
                                .child("cart").child(key), Products.class)
                        .build();


        carAdapter = new CarAdapter(options);
        carProducts.setAdapter(carAdapter);
        return view;
    }

//    private void initRecyclerView() {
//
//        cartReferance.addValueEventListener(new ValueEventListener() {
//            @Override
//            public void onDataChange(@NonNull DataSnapshot snapshot) {
//                if (snapshot.exists()) {
//                    for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
//                        if (snapshot.getChildren() != null) {
//                            Products products = dataSnapshot.getValue(Products.class);
//                            list.add(products);
//                        } else
//                            Toast.makeText(view.getContext(), "No data", Toast.LENGTH_SHORT).show();
//                    }
//                } else
//                    Toast.makeText(view.getContext(), "error", Toast.LENGTH_SHORT).show();
//            }
//
//            @Override
//            public void onCancelled(@NonNull DatabaseError error) {
//
//            }
//        });
//        list = new ArrayList<>();
//
//    }

}
