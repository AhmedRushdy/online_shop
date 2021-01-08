package com.example.yourdevices;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.ViewGroup;

import com.example.yourdevices.categories.ProductAdapter;
import com.example.yourdevices.models.Products;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.FirebaseDatabase;

public class ViewProductsActivity extends AppCompatActivity {
    private RecyclerView productRV;
    private ProductAdapter productAdapter;
    private String categoryName;

    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_products);

        categoryName = getIntent().getStringExtra("NameOfCategory").toLowerCase();

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

    @Override
    protected void onStart() {
        super.onStart();
        productAdapter.startListening();
    }

    @Override
    protected void onStop() {
        super.onStop();
        productAdapter.stopListening();
    }

}