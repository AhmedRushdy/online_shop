package com.example.yourdevices.admin;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.yourdevices.R;
import com.example.yourdevices.admin.AdminAddProducts;
import com.facebook.login.Login;

public class AdminPanel extends AppCompatActivity implements View.OnClickListener {
    private CardView supermarket, clothes, games, phones, kitchen, health, cleaners, electerical;
    private Intent i;
    private String productCat = "111";
    Button adminSignOut;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_panel);

        adminSignOut = findViewById(R.id.adminSignOut);
        supermarket = findViewById(R.id.add_supermarket);
        supermarket.setOnClickListener(this);
        clothes = findViewById(R.id.add_clothes);
        clothes.setOnClickListener(this);
        cleaners = findViewById(R.id.add_cleaners);
        cleaners.setOnClickListener(this);
        games = findViewById(R.id.add_games);
        games.setOnClickListener(this);
        phones = findViewById(R.id.add_phones);
        phones.setOnClickListener(this);
        kitchen = findViewById(R.id.add_kitchen);
        kitchen.setOnClickListener(this);
        health = findViewById(R.id.add_health);
        health.setOnClickListener(this);
        electerical = findViewById(R.id.add_electrical);
        electerical.setOnClickListener(this);

        i = new Intent(this, AdminAddProducts.class);
        adminSignOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getBaseContext(), Login.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
            }
        });
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.add_supermarket: {
                Toast.makeText(this, "supermarket clicked", Toast.LENGTH_SHORT).show();
                i.putExtra(productCat,"supermarket");
                startActivity(i);
                break;
            }
            case R.id.add_cleaners: {
                Toast.makeText(this, "cleaners clicked", Toast.LENGTH_SHORT).show();
                i.putExtra(productCat, "cleaners");
                startActivity(i);
                break;

            }
            case R.id.add_clothes: {
                Toast.makeText(this, "clothes clicked", Toast.LENGTH_SHORT).show();
                i.putExtra(productCat, "clothes");
                startActivity(i);
                break;

            }
            case R.id.add_electrical: {
                Toast.makeText(this, "electrical clicked", Toast.LENGTH_SHORT).show();
                i.putExtra(productCat, "electrical");
                startActivity(i);
                break;

            }
            case R.id.add_games: {
                Toast.makeText(this, "games clicked", Toast.LENGTH_SHORT).show();
                i.putExtra(productCat, "games");
                startActivity(i);
                break;

            }
            case R.id.add_health: {
                Toast.makeText(this, "health clicked", Toast.LENGTH_SHORT).show();
                i.putExtra(productCat, "health");
                startActivity(i);
                break;

            }
            case R.id.add_kitchen: {
                Toast.makeText(this, "kitchen clicked", Toast.LENGTH_SHORT).show();
                i.putExtra(productCat, "kitchen");
                startActivity(i);
                break;
            }
            case R.id.add_phones: {
                Toast.makeText(this, "phones clicked", Toast.LENGTH_SHORT).show();
                i.putExtra(productCat, "phones");
                startActivity(i);
                break;
            }
            default:
                Toast.makeText(this, "error", Toast.LENGTH_SHORT).show();

        }
    }

    public void signOut(View view) {
        Intent a = new Intent(Intent.ACTION_MAIN);
        a.addCategory(Intent.CATEGORY_HOME);
        a.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(a);

    }
}