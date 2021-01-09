package com.example.yourdevices.categories;

import android.app.Activity;
import android.app.ActivityOptions;
import android.content.Context;
import android.content.Intent;
import android.util.Pair;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.example.yourdevices.Log_in;
import com.example.yourdevices.ProductActivity;
import com.example.yourdevices.R;
import com.example.yourdevices.ViewProductsActivity;
import com.example.yourdevices.models.Products;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.flaviofaria.kenburnsview.KenBurnsView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.squareup.picasso.Picasso;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import de.hdodenhof.circleimageview.CircleImageView;

public class ProductAdapter extends FirebaseRecyclerAdapter<Products, ProductHolder> {


    View view;
    String itemName, itemImage, itemDescription, itemId;

    float itemPrice;

    public ProductAdapter(@NonNull FirebaseRecyclerOptions<Products> options) {
        super(options);
    }

    @Override
    protected void onBindViewHolder(@NonNull final ProductHolder holder, int position, @NonNull final Products model) {
        holder.productName.setText(model.getProName());
        holder.productPrice.setText(model.getPrice() + " L.E");
        Picasso.get().load(model.getImage()).into(holder.productImage);
        holder.setOnClickListener(new ProductHolder.ClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                itemName = getItem(position).getProName();
                itemImage = "" + getItem(position).getImage();
                itemDescription = getItem(position).getProDescribtion();
                itemPrice = getItem(position).getPrice();
                itemId = getItem(position).getId();
                Intent sharedIntent = new Intent(view.getContext(), ProductActivity.class);
                sharedIntent.putExtra("Item_Name", itemName);
                sharedIntent.putExtra("Item_Image", itemImage);
                sharedIntent.putExtra("Item_Description", itemDescription);
                sharedIntent.putExtra("Item_Price", "" + itemPrice);
                sharedIntent.putExtra("Item_Id", itemId);
                Pair[] pairs = new Pair[3];
                pairs[0] = new Pair<View, String>(holder.productImage, "Image_Transition");
                pairs[1] = new Pair<View, String>(holder.productName, "Name_Transition");
                pairs[2] = new Pair<View, String>(holder.productPrice, "Price_Transition");
                ActivityOptions options = null;
                if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.LOLLIPOP) {
                    options = ActivityOptions.makeSceneTransitionAnimation((Activity) view.getContext(), pairs);
                }
                view.getContext().startActivity(sharedIntent, options.toBundle());
                Toast.makeText(view.getContext(), itemName, Toast.LENGTH_SHORT).show();
                //view.getContext().startActivity(sharedIntent);
                holder.addToCart.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        addProductToCart();
                    }
                });
            }

            @Override
            public void onItemLongClick(View view, int position) {

            }
        });
    }

    @NonNull
    @Override
    public ProductHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        view = LayoutInflater.from(parent.getContext()).inflate(R.layout.product_item_layout, parent, false);

        return new ProductHolder(view);
    }
    //add product to firebase cart
    private void addProductToCart() {
        Products products = new Products(itemName,"",itemImage,itemPrice);
        if (FirebaseAuth.getInstance().getCurrentUser() == null) {
           return;
        }
        else{
            String UID = FirebaseAuth.getInstance().getCurrentUser().getUid();
            String key =  FirebaseDatabase.getInstance().getReference().push().getKey();
            DatabaseReference cartReferance =FirebaseDatabase.getInstance().getReference().child("cart").child(UID).child(key);
            cartReferance.setValue(products);
        }
    }



}