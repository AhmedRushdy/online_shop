package com.example.yourdevices.ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.yourdevices.R;
import com.example.yourdevices.adapters.ProductAdapter;
import com.example.yourdevices.models.Products;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.FirebaseDatabase;

public class ViewProductsActivity extends AppCompatActivity {
    private RecyclerView productRV;
    private ProductAdapter productAdapter;
    private String categoryName;
    ImageView imageView, goToCart;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_products);

        toolBar();
        categoryName = getIntent().getStringExtra("categoryName").toLowerCase();
        if (categoryName != null) {
            Toast.makeText(getApplicationContext(), categoryName, Toast.LENGTH_SHORT);
        }
        productRV = findViewById(R.id.products_rv);
        productRV.setLayoutManager(new LinearLayoutManager(this));

        FirebaseRecyclerOptions<Products> options =
                new FirebaseRecyclerOptions.Builder<Products>()
                        .setQuery(FirebaseDatabase.getInstance().getReference().child("category")
                                .child(categoryName), Products.class)
                        .build();
        productAdapter = new ProductAdapter(options);
        productRV.setAdapter(productAdapter);

    }

    private void toolBar() {
        Toolbar actionBar = findViewById(R.id.include2);
        setSupportActionBar(actionBar);
        imageView = actionBar.findViewById(R.id.back);
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });
        goToCart = actionBar.findViewById(R.id.go_to_car);
        goToCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), CartActivity.class));
            }

        });
    }

    @Override
    protected void onStart() {
        super.onStart();
        productAdapter.startListening();
    }

    @Override
    protected void onPause() {
        super.onPause();
        productAdapter.startListening();
    }

    @Override
    protected void onResume() {
        super.onResume();
        productAdapter.startListening();
    }

    @Override
    protected void onStop() {
        super.onStop();
        productAdapter.stopListening();
    }

}