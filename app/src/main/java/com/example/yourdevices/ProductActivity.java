package com.example.yourdevices;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.yourdevices.models.Products;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.squareup.picasso.Picasso;

import java.util.HashMap;
import java.util.Map;

public class ProductActivity extends AppCompatActivity {

    private ImageView itemIV;
    private TextView itemName, itemPrice, itemDescription, itemType;
    private String itemId;
    private CardView addToCart;
    private ImageView image;
    private Toolbar actionBar;
    private DatabaseReference cartReferance;
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
                addProductToCart();
            }
        });

    }
    //add product to firebase cart
    private void addProductToCart() {
//        Map<String,Object> map = new HashMap();
//        map.put("name",getIntent().getStringExtra("Item_Name"));
//        map.put("price",getIntent().getStringExtra("Item_Price"));
//        map.put("description",getIntent().getStringExtra("Item_Description"));
//        map.put("img_Url",getIntent().getStringExtra("Item_Image"));
        Products products = new Products(
                getIntent().getStringExtra("Item_Name")
                ,"",getIntent().getStringExtra("Item_Image"), Float.parseFloat(getIntent().getStringExtra("Item_Price")));

        if (FirebaseAuth.getInstance().getCurrentUser() == null) {
            Intent i = new Intent(this,Log_in.class);
            startActivity(i);
        }
        else{
            String UID = FirebaseAuth.getInstance().getCurrentUser().getUid();
            String key =  FirebaseDatabase.getInstance().getReference().push().getKey();
            DatabaseReference cartReferance =FirebaseDatabase.getInstance().getReference().child("cart").child(UID).child(key);
            cartReferance.setValue(products);
        }
    }

    private void fetchDataIntent() {
        itemName.setText(getIntent().getStringExtra("Item_Name"));
        itemPrice.setText(getIntent().getStringExtra("Item_Price"));
        itemDescription.setText(getIntent().getStringExtra("Item_Description"));
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