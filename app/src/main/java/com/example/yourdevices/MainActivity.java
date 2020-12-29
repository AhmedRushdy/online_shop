package com.example.yourdevices;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.view.Menu;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import androidx.annotation.NonNull;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;
import androidx.viewpager2.widget.CompositePageTransformer;
import androidx.viewpager2.widget.MarginPageTransformer;
import androidx.viewpager2.widget.ViewPager2;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    String cNames[];
    int cImages[] = {R.drawable.supermarket, R.drawable.clothes, R.drawable.phones, R.drawable.kichen, R.drawable.games, R.drawable.health, R.drawable.electrical, R.drawable.cleaners};
    private RecyclerView mainComponent;
    ComponentAdapter componentAdapter;
    List<SliderModel> sliderModelList;
    ViewPager2 sliderPager;
    SliderModel slider_1, slider_2, slider_3, slider_4;
    private AppBarConfiguration mAppBarConfiguration;
    ImageView view;
    FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        cNames = getResources().getStringArray(R.array.components);
        sliderPager = findViewById(R.id.slider);
        sliderModelList = new ArrayList<>();
        setupSliders();

        mAuth = FirebaseAuth.getInstance();
        mainComponent = findViewById(R.id.main_component);
        StaggeredGridLayoutManager staggeredGridLayoutManager = new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL);
        mainComponent.setLayoutManager(staggeredGridLayoutManager);
//        componentList = new ArrayList<>();
        componentAdapter = new ComponentAdapter(getApplicationContext(), cNames, cImages);
        mainComponent.setAdapter(componentAdapter);


        FloatingActionButton fab = findViewById(R.id.fab);
//        fab.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
//                        .setAction("Action", null).show();
//            }
//        });

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.nav_view);

        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        mAppBarConfiguration = new AppBarConfiguration.Builder(
                R.id.nav_home, R.id.nav_waiting, R.id.nav_car, R.id.nav_solded)
                .setDrawerLayout(drawer)
                .build();
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);

        NavigationUI.setupActionBarWithNavController(this, navController, mAppBarConfiguration);
        NavigationUI.setupWithNavController(navigationView, navController);

        View vheader = navigationView.getHeaderView(0);

        view = vheader.findViewById(R.id.user_img);
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity.this, "image clicked", Toast.LENGTH_SHORT).show();
                Intent i = new Intent(MainActivity.this, Profile.class);
                startActivity(i);
            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onSupportNavigateUp() {
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        return NavigationUI.navigateUp(navController, mAppBarConfiguration)
                || super.onSupportNavigateUp();
    }

    private void googleSignOut() {
        GoogleSignInAccount googleSignInAccount = GoogleSignIn.getLastSignedInAccount(this);
        if (googleSignInAccount != null) {

        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        switch (id) {
            case R.id.log_out:
                FirebaseUser user = mAuth.getCurrentUser();
                if (user != null) {
                    FirebaseAuth.getInstance().signOut();
                    Intent i = new Intent(MainActivity.this, Start.class);
                    startActivity(i);
                }
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
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