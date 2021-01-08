package com.example.yourdevices.categories;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.yourdevices.R;
import com.example.yourdevices.models.Products;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.flaviofaria.kenburnsview.KenBurnsView;
import com.squareup.picasso.Picasso;

import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class ProductAdapter extends FirebaseRecyclerAdapter<Products,ProductAdapter.ProductViewHolder> {


    /**
     * Initialize a {@link RecyclerView.Adapter} that listens to a Firebase query. See
     * {@link FirebaseRecyclerOptions} for configuration options.
     *
     * @param options
     */
    public ProductAdapter(@NonNull FirebaseRecyclerOptions<Products> options) {
        super(options);
    }

    @Override
    protected void onBindViewHolder(@NonNull ProductViewHolder holder, int position, @NonNull Products model) {
        holder.productName.setText(model.getProName());
        holder.productPrice.setText(String.valueOf(model.getPrice()));
        Picasso.get().load(model.getImage()).into(holder.productImage);
    }

    @NonNull
    @Override
    public ProductViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ProductViewHolder(LayoutInflater
                .from(parent.getContext())
                .inflate(R.layout.product_item_layout,parent,false));
    }

    public class ProductViewHolder extends RecyclerView.ViewHolder {
        TextView productName , productPrice;
        CircleImageView productImage;
        public ProductViewHolder(@NonNull View itemView) {
            super(itemView);
            productImage = itemView.findViewById(R.id.product_card_img);
            productName = itemView.findViewById(R.id.get_product_name);
            productPrice = itemView.findViewById(R.id.get_product_price);
        }
    }
}