package com.example.yourdevices.categories;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.example.yourdevices.R;

public class ProductHolder extends RecyclerView.ViewHolder {
    TextView productName, productPrice;
    ImageView productImage;
    ConstraintLayout productsItem;

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

