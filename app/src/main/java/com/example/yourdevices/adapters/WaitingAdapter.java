package com.example.yourdevices.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.yourdevices.R;
import com.example.yourdevices.models.Order;
import com.example.yourdevices.models.Products;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class WaitingAdapter extends RecyclerView.Adapter<WaitingAdapter.WaitingViewHolder> {
    ArrayList<Products> productsList;
    Context context;

    public WaitingAdapter(ArrayList<Products> productsList, Context context) {
        this.productsList = productsList;
        this.context = context;
    }

    @NonNull
    @Override
    public WaitingViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new WaitingViewHolder(LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_waiting,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull WaitingViewHolder holder, int position) {
        holder.name.setText(productsList.get(position).getProName());
        holder.price.setText(String.valueOf(productsList.get(position).getPrice()));
        Picasso.get().load(productsList.get(position).getImage()).into(holder.productImage);


    }

    @Override
    public int getItemCount() {
        return productsList.size();
    }

    public class WaitingViewHolder extends RecyclerView.ViewHolder {
        TextView name , price ;
        ImageView productImage;
        public WaitingViewHolder(@NonNull View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.tv_product_order);
            price = itemView.findViewById(R.id.tv_price_order);
            productImage= itemView.findViewById(R.id.iv_order_fragment);
        }
    }
}
