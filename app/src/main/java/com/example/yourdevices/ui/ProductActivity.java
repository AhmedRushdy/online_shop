package com.example.yourdevices.ui;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.cardview.widget.CardView;

import com.example.yourdevices.R;
import com.example.yourdevices.models.Products;
import com.example.yourdevices.start.Log_in;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.squareup.picasso.Picasso;

public class ProductActivity extends AppCompatActivity {

    private ImageView itemIV;
    private TextView itemName, itemPrice, itemDescribtion, itemType;
    private String itemId;
    private CardView addToCart;
    private ImageView image, goToCart;
    private Toolbar actionBar;
    private DatabaseReference cartReferance;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.product_details);
        toolBar();
        initiation();
        fetchDataIntent();

        addToCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addProductToCart();
            }
        });

    }

    private void toolBar() {
        actionBar = findViewById(R.id.include);
        setSupportActionBar(actionBar);
        image = actionBar.findViewById(R.id.back);
        image.setOnClickListener(new View.OnClickListener() {
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

    //add product to firebase cart
    private void addProductToCart() {
        Products products = new Products("", getIntent().getStringExtra("Item_Name")
                , getIntent().getStringExtra("Item_Image"),
                0, Float.parseFloat(getIntent().getStringExtra("Item_Price")), 0);

        if (FirebaseAuth.getInstance().getCurrentUser() == null) {
            Intent i = new Intent(this, Log_in.class);
            startActivity(i);
        } else {
            String UID = FirebaseAuth.getInstance().getCurrentUser().getUid();
            String key = FirebaseDatabase.getInstance().getReference().push().getKey();
            DatabaseReference cartReferance = FirebaseDatabase.getInstance().getReference().child("cart").child(UID).child(key);
            cartReferance.setValue(products);
        }
    }

    private void fetchDataIntent() {
        itemName.setText(getIntent().getStringExtra("Item_Name"));
        itemPrice.setText(getIntent().getStringExtra("Item_Price"));
        itemDescribtion.setText(getIntent().getStringExtra("Item_Describtion"));
        Picasso.get().load(Uri.parse(getIntent().getStringExtra("Item_Image"))).into(itemIV);
        itemId = getIntent().getStringExtra("Item_Id");
    }

    private void initiation() {
        itemIV = findViewById(R.id.iv_order_fragment);
        itemName = findViewById(R.id.tv_product);
        itemPrice = findViewById(R.id.price_product);
        itemDescribtion = findViewById(R.id.desc_product);
        itemType = findViewById(R.id.productType);
        addToCart = findViewById(R.id.add_to_cart);
    }
}