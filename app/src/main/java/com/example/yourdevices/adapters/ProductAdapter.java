package com.example.yourdevices.adapters;

import android.app.Activity;
import android.app.ActivityOptions;
import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.util.Pair;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.example.yourdevices.ui.ProductActivity;
import com.example.yourdevices.R;
import com.example.yourdevices.models.Products;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.squareup.picasso.Picasso;

import java.util.Objects;

public class ProductAdapter extends FirebaseRecyclerAdapter<Products, ProductAdapter.ProductHolder> {


    View view;
    String itemName, itemImage, itemDescribtion, itemId;
    int quantity;
    float itemPrice, discount;

    Dialog loaderDialog;


    public ProductAdapter(@NonNull FirebaseRecyclerOptions<Products> options) {
        super(options);
    }


    @NonNull
    @Override
    public ProductHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        view = LayoutInflater.from(parent.getContext()).inflate(R.layout.product_item_layout, parent, false);
        loaderDialog = new Dialog(view.getContext());
        loaderDialog.setContentView(R.layout.loader_dialog);
        loaderDialog.setCanceledOnTouchOutside(false);
        Objects.requireNonNull(loaderDialog.getWindow()).setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

        return new ProductHolder(view);
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
                itemPrice = getItem(position).getPrice();
                itemId = getItem(position).getId();
                itemDescribtion = getItem(position).getProDescribtion();
                quantity = getItem(position).getQuantity();
                discount = getItem(position).getDiscount();
                Intent sharedIntent = new Intent(view.getContext(), ProductActivity.class);
                sharedIntent.putExtra("Item_Name", itemName);
                sharedIntent.putExtra("Item_Image", itemImage);
                sharedIntent.putExtra("Item_Describtion", itemDescribtion);
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

                //add to cart button
                holder.addToCart.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        loaderDialog.show();
                        addProductToCart();
                    }
                });
            }

            @Override
            public void onItemLongClick(View view, int position) {

            }
        });
    }

    //add product to firebase cart
    private void addProductToCart() {
        if (FirebaseAuth.getInstance().getCurrentUser() == null) {
            return;
        } else {
            String UID = FirebaseAuth.getInstance().getCurrentUser().getUid();
            String key = FirebaseDatabase.getInstance().getReference().push().getKey();
            Products products = new Products(key, itemName, itemImage, quantity, itemPrice, discount);

            DatabaseReference cartReferance = FirebaseDatabase.getInstance().getReference().child("cart").child(UID).child(key);
            cartReferance.setValue(products).addOnCompleteListener(new OnCompleteListener<Void>() {
                @Override
                public void onComplete(@NonNull Task<Void> task) {
                    if (task.isSuccessful()) {
                        loaderDialog.dismiss();
                        Toast.makeText(view.getContext(), "Added to cart", Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }
    }



static class ProductHolder extends RecyclerView.ViewHolder {
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

}}