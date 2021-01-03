package com.example.yourdevices;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class AdminPanel extends AppCompatActivity implements View.OnClickListener {
    private CardView supermarket, clothes, games, phones, kitchen, health, cleaners, electerical;
    private Intent i;
    private String productCat = "111";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_panel);

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
                i.putExtra(productCat, "cloths");
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
}