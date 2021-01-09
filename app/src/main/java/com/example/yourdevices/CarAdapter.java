package com.example.yourdevices;

import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.yourdevices.models.Products;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class CarAdapter extends FirebaseRecyclerAdapter<Products,CarAdapter.MyViewHolder> {


    /**
     * Initialize a {@link RecyclerView.Adapter} that listens to a Firebase query. See
     * {@link FirebaseRecyclerOptions} for configuration options.
     *
     * @param options
     */
    public CarAdapter(@NonNull FirebaseRecyclerOptions options) {
        super(options);
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_car,parent,false);
        return new MyViewHolder(view);
    }


    @Override
    protected void onBindViewHolder(@NonNull CarAdapter.MyViewHolder holder, int position, @NonNull Products model) {
        holder.productName.setText(model.getProName());
        holder.productPrice.setText(String.valueOf(model.getPrice()));
        Picasso.get().load(model.getImage()).into(holder.productImage);
    }


     class MyViewHolder  extends RecyclerView.ViewHolder {

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

//    @Override
//    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
//        holder.productName.setText(productsArrayList.get(position).getProName());
//        holder.productPrice.setText(String.valueOf(productsArrayList.get(position).getPrice()));
//        Picasso.get().load(productsArrayList.get(holder.getAdapterPosition()).getImage()).into(holder.productImage);
//    }

//    @Override
//    public int getItemCount() {
//        return productsArrayList.size();
//    }
//
//    class MyViewHolder extends RecyclerView.ViewHolder {
//
//    ImageView productImage;
//    TextView productName;
//    TextView productPrice;
//
//    public MyViewHolder(@NonNull View itemView ) {
//        super(itemView);
//        productImage = itemView.findViewById(R.id.iv_product);
//        productName = itemView.findViewById(R.id.car_product_name);
//        productPrice = itemView.findViewById(R.id.car_product_price);
//
//
//    }
//    private ArrayList<Products> productsArrayList;
//
//    public CarAdapter(ArrayList<Products> productsArrayList) {
//        this.productsArrayList = productsArrayList;
//    }






