package com.example.yourdevices.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.yourdevices.R;
import com.example.yourdevices.models.SliderModel;
import com.flaviofaria.kenburnsview.KenBurnsView;
import com.squareup.picasso.Picasso;

import java.util.List;


public class SliderAdapter extends RecyclerView.Adapter<SliderAdapter.SliderViewHolder> {

    private List<SliderModel> sliderModelList;

    public SliderAdapter(List<SliderModel> sliderModelList) {
        this.sliderModelList = sliderModelList;
    }

    @NonNull
    @Override
    public SliderViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new SliderViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_slider_layout,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull SliderViewHolder holder, int position) {
        holder.setSliderLocation(sliderModelList.get(position));

    }

    @Override
    public int getItemCount() {
        return sliderModelList.size();
    }

    static class SliderViewHolder extends RecyclerView.ViewHolder {
        private KenBurnsView kenBurnsView;
        private TextView sliderTitle,sliderLocation,textStar;
        public SliderViewHolder(@NonNull View itemView) {
            super(itemView);
            kenBurnsView = itemView.findViewById(R.id.kbv_slider);
            sliderTitle = itemView.findViewById(R.id.slider_title);
            sliderLocation = itemView.findViewById(R.id.text_slider);
            textStar = itemView.findViewById(R.id.text_star_rating);
        }
        void setSliderLocation(SliderModel sliderModel){
            Picasso.get().load(sliderModel.imageUrl).into(kenBurnsView);
            sliderTitle.setText(sliderModel.sliderTitle);
            textStar.setText(String.valueOf(sliderModel.starRating));
            sliderLocation.setText(sliderModel.location);
        }
    }
}
