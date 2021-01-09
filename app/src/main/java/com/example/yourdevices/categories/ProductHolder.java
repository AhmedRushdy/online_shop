package com.example.yourdevices.categories;

import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.example.yourdevices.R;
import com.example.yourdevices.ui.MainActivity;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class ProductHolder extends RecyclerView.ViewHolder {
    TextView productName, productPrice;
    ImageView productImage;
    ConstraintLayout productsItem;
    Button addToCart;
    public ProductHolder(@NonNull View itemView) {
        super(itemView);
        itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mClickListener.onItemClick(view, getAdapterPosition());
            }
        });
        itemView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                mClickListener.onItemLongClick(view,getAdapterPosition());
                return false;
            }
        });
        productImage = itemView.findViewById(R.id.product_card_img);
        productName = itemView.findViewById(R.id.get_product_name);
        productPrice = itemView.findViewById(R.id.get_product_price);
        productsItem = itemView.findViewById(R.id.view_parent);

      //add to cart
        addToCart = itemView.findViewById(R.id.add_to_cart);
        addToCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mClickListener.onItemClick(view, getAdapterPosition());
            }
        });

    }



    private ProductHolder.ClickListener mClickListener;

    public interface ClickListener {
        void onItemClick(View view, int position);

        void onItemLongClick(View view, int position);
    }

    public void setOnClickListener(ProductHolder.ClickListener clickListener) {
        mClickListener = clickListener;
    }

}

