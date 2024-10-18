package com.abc.agrios;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

public class farmerhomepage extends AppCompatActivity {
    Button assistant, product, bid, weather, community, market;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_farmerhomepage);
        assistant = findViewById(R.id.buttonassistance);
        product = findViewById(R.id.buttonproduct);
        market = findViewById(R.id.buttonmarket);
        bid = findViewById(R.id.buttonbid);
        weather = findViewById(R.id.buttonweather);
        community = findViewById(R.id.buttoncommunity);
        product.setOnClickListener(v -> {
            startActivity(new Intent(farmerhomepage.this, productlist.class));
        });
        bid.setOnClickListener(v -> {
            startActivity(new Intent(farmerhomepage.this, bidlist.class));
        });

        market.setOnClickListener(v -> {
            startActivity(new Intent(farmerhomepage.this, productlist.class));
        });
    }
}