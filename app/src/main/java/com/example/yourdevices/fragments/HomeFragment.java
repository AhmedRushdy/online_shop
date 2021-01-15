package com.example.yourdevices.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;
import androidx.viewpager2.widget.CompositePageTransformer;
import androidx.viewpager2.widget.MarginPageTransformer;
import androidx.viewpager2.widget.ViewPager2;

import com.example.yourdevices.adapters.ComponentAdapter;
import com.example.yourdevices.R;
import com.example.yourdevices.adapters.SliderAdapter;
import com.example.yourdevices.models.SliderModel;

import java.util.ArrayList;
import java.util.List;

public class HomeFragment extends Fragment  {


    String cNames[];
    int cImages[] = {R.drawable.supermarket, R.drawable.clothes, R.drawable.phones, R.drawable.kichen, R.drawable.games, R.drawable.health, R.drawable.electrical, R.drawable.cleaners};
    private RecyclerView mainComponent;
    ComponentAdapter componentAdapter;
    List<SliderModel> sliderModelList;
    ViewPager2 sliderPager;
    SliderModel slider_1, slider_2, slider_3, slider_4;
    View view;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_home, container, false);
        initiation();
        setupSliders();
        return view;
    }


    private void initiation() {

        mainComponent = view.findViewById(R.id.main_component);
        cNames = getResources().getStringArray(R.array.components);
        sliderPager = view.findViewById(R.id.slider);
        sliderModelList = new ArrayList<>();



        // recyclerview initiation
        StaggeredGridLayoutManager staggeredGridLayoutManager = new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL);
        mainComponent.setLayoutManager(staggeredGridLayoutManager);
        componentAdapter = new ComponentAdapter(view.getContext(), cNames, cImages);
        mainComponent.setAdapter(componentAdapter);
    }

    private void setupSliders() {
        slider_1 = new SliderModel();
        slider_1.imageUrl = "https://firebasestorage.googleapis.com/v0/b/your-devices.appspot.com/o/%D8%B9%D8%B1%D9%88%D8%B6-%D9%88%D8%AE%D8%B5%D9%88%D9%85%D8%A7%D8%AA-%D8%A7%D9%84%D8%AC%D9%85%D8%B9%D8%A9-%D8%A7%D9%84%D8%B5%D9%81%D8%B1%D8%A7%D8%A1-2019.jpg?alt=media&token=4fcc0bb2-dc78-4e06-a8cf-c7a03614685a";
        slider_1.sliderTitle = "Best offers and ocassion";
        slider_1.location = "Carforr";
        slider_1.starRating = 5.0f;
        sliderModelList.add(slider_1);

        slider_2 = new SliderModel();
        slider_2.imageUrl = "https://firebasestorage.googleapis.com/v0/b/your-devices.appspot.com/o/e33b67a7c381649c9592feed97b0c9cf.png?alt=media&token=900f35af-c073-4e84-a6d4-c645fb221642";
        slider_2.sliderTitle = "Flash deal brands";
        slider_2.location = "Copons";
        slider_2.starRating = 4.9f;
        sliderModelList.add(slider_2);

        slider_3 = new SliderModel();
        slider_3.imageUrl = "https://firebasestorage.googleapis.com/v0/b/your-devices.appspot.com/o/Nisnass-13.jpg?alt=media&token=426306bf-3d24-490b-8f99-f46c64c267a6";
        slider_3.sliderTitle = "T-shirts and big offers from cotoneel";
        slider_3.location = "Cairo, Alex";
        slider_3.starRating = 3.8f;
        sliderModelList.add(slider_3);

        slider_4 = new SliderModel();
        slider_4.imageUrl = "https://firebasestorage.googleapis.com/v0/b/your-devices.appspot.com/o/tamara-gore-nnsEUKefn1U-unsplash.jpg?alt=media&token=d35d8a45-f1aa-43bb-9299-9f96071e6441";
        slider_4.sliderTitle = "Fresh equipement models and winter offers";
        slider_4.location = "Mall-Masr";
        slider_4.starRating = 4.0f;
        sliderModelList.add(slider_4);

        sliderPager.setAdapter(new SliderAdapter(sliderModelList));

        CompositePageTransformer compositePageTransformer = new CompositePageTransformer();
        compositePageTransformer.addTransformer(new MarginPageTransformer(40));
        compositePageTransformer.addTransformer(new ViewPager2.PageTransformer() {
            @Override
            public void transformPage(@NonNull View page, float position) {
                float r = 1 - Math.abs(position);
                page.setScaleY(0.95f + r * 0.05f);

            }
        });

        sliderPager.setPageTransformer(compositePageTransformer);
    }



}