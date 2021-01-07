package com.example.yourdevices;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.core.view.KeyEventDispatcher;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class ComponentAdapter extends RecyclerView.Adapter<ComponentAdapter.MyViewHolder> {
    Context context;
    String cNames[];
    int cImages[];

    public ComponentAdapter() {
    }

    public ComponentAdapter(Context context, String cNames[], int cImages[] ) {
        this.context = context;
        this.cNames = cNames;
        this.cImages = cImages;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.component_item_layout, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {


        holder.componentName.setText(cNames[position]);
        holder.componentImage.setImageResource(cImages[position]);
//        try {
//            Picasso.get().load(componentImage).placeholder(R.drawable.supermarket).into(holder.componentImage);
//        } catch (Exception e) {
//
//        }

        if(position == 0){
            RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT, RelativeLayout.LayoutParams.WRAP_CONTENT);
            layoutParams.setMargins(5,80,5,5);
            holder.layout.setLayoutParams(layoutParams);
        }
    }

    @Override
    public int getItemCount() {
        return cImages.length;
    }

    class MyViewHolder extends RecyclerView.ViewHolder {

        ImageView componentImage;
        TextView componentName;
        RelativeLayout layout;

        public MyViewHolder(@NonNull View itemView ) {
            super(itemView);
            componentImage = itemView.findViewById(R.id.component_image);
            componentName = itemView.findViewById(R.id.component_name);
            layout = itemView.findViewById(R.id.relative_layout);


        }



    }

}
