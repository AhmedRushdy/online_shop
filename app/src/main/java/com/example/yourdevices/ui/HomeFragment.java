package com.example.yourdevices.ui;

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

import com.example.yourdevices.ComponentAdapter;
import com.example.yourdevices.R;
import com.example.yourdevices.SliderAdapter;
import com.example.yourdevices.SliderModel;

import java.util.ArrayList;
import java.util.List;

public class HomeFragment extends Fragment {


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
        slider_1.imageUrl = "https://firebasestorage.googleapis.com/v0/b/your-devices.appspot.com/o/jonathan-marchal-xBybRdNp9iw-unsplash.jpg?alt=media&token=33e6dba0-96d3-4f94-b2f6-59839d959b39";
        slider_1.sliderTitle = "Cofee";
        slider_1.location = "Chicago";
        slider_1.starRating = 4.3f;
        sliderModelList.add(slider_1);

        slider_2 = new SliderModel();
        slider_2.imageUrl = "https://firebasestorage.googleapis.com/v0/b/your-devices.appspot.com/o/tamara-gore-nnsEUKefn1U-unsplash.jpg?alt=media&token=d35d8a45-f1aa-43bb-9299-9f96071e6441";
        slider_2.sliderTitle = "Cofee";
        slider_2.location = "Chicago";
        slider_2.starRating = 4.5f;
        sliderModelList.add(slider_2);

        slider_3 = new SliderModel();
        slider_3.imageUrl = "https://firebasestorage.googleapis.com/v0/b/your-devices.appspot.com/o/david-gavi-fGI9a_W6sdI-unsplash.jpg?alt=media&token=02968815-cc8e-4350-a24c-6ca3342da805";
        slider_3.sliderTitle = "Cofee";
        slider_3.location = "Chicago";
        slider_3.starRating = 4.5f;
        sliderModelList.add(slider_3);

        slider_4 = new SliderModel();
        slider_4.imageUrl = "https://firebasestorage.googleapis.com/v0/b/your-devices.appspot.com/o/adrian-dascal-Z4S7NHh26ig-unsplash.jpg?alt=media&token=80a3d2fd-83a8-4833-9646-0d113e3b5af8";
        slider_4.sliderTitle = "Cofee";
        slider_4.location = "Chicago";
        slider_4.starRating = 4.5f;
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