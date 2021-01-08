package com.example.yourdevices;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.cardview.widget.CardView;

import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

public class ProductActivity extends AppCompatActivity {

    private ImageView itemIV;
    private TextView itemName, itemPrice, itemDescription, itemType;
    private String itemId;
    private CardView addToCart;
    private ImageView image;
    private Toolbar actionBar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.product_details);
        actionBar =  findViewById(R.id.include);
        setSupportActionBar(actionBar);
        image = actionBar.findViewById(R.id.back);
        image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
                Toast.makeText(ProductActivity.this, "on back pressed", Toast.LENGTH_SHORT).show();
            }
        });
        initiation();
        fetchDataIntent();

        addToCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

    }

    private void fetchDataIntent() {
        itemName.setText(getIntent().getStringExtra("Item_Name"));
        itemPrice.setText(getIntent().getStringExtra("Item_Price"));
        itemDescription.setText(getIntent().getStringExtra("Item_Description"));
        itemDescription.setText(getIntent().getStringExtra("Item_Description"));
       // itemIV.setImageURI(Uri.parse(getIntent().getStringExtra("Item_Image")));
        Picasso.get().load(Uri.parse(getIntent().getStringExtra("Item_Image"))).into(itemIV);
        itemId = getIntent().getStringExtra("Item_Id");
    }

    private void initiation() {
        itemIV = findViewById(R.id.iv_product);
        itemName = findViewById(R.id.tv_product);
        itemPrice = findViewById(R.id.price_product);
        itemDescription = findViewById(R.id.desc_product);
        itemType = findViewById(R.id.productType);
        addToCart = findViewById(R.id.add_to_cart);
    }
}