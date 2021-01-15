package com.example.yourdevices.adapters;

import android.content.Context;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.yourdevices.R;
import com.example.yourdevices.models.Products;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class CarAdapter extends RecyclerView.Adapter<CarAdapter.MyViewHolder> {

    Context context;
    ArrayList<Products> productsList;
    View view;

    public CarAdapter(Context context, ArrayList<Products> productsList) {
        this.context = context;
        this.productsList = productsList;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_car, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.productName.setText(productsList.get(position).getProName());
        holder.productPrice.setText("" + productsList.get(position).getPrice());
//        Picasso.get().load(model.getImage()).into(holder.productImage);
        Picasso.get().load(Uri.parse(productsList.get(position).getImage())).into(holder.productImage);

    }

    @Override
    public int getItemCount() {
        return productsList.size();
    }


    class MyViewHolder extends RecyclerView.ViewHolder {

        ImageView productImage;
        TextView productName;
        TextView productPrice;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            productImage = itemView.findViewById(R.id.iv_product_cart);
            productName = itemView.findViewById(R.id.cart_product_name);
            productPrice = itemView.findViewById(R.id.cart_product_price);

        }
    }
}







