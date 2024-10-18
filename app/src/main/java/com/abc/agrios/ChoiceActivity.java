package com.abc.agrios;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

public class ChoiceActivity extends AppCompatActivity {
    Button farmer, buyer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_choice);
        farmer = findViewById(R.id.farmer1);
        buyer = findViewById(R.id.buyer);
        farmer.setOnClickListener(v -> {
            startActivity(new Intent(ChoiceActivity.this, FarmerLoginActivity.class));
        });
        buyer.setOnClickListener(v -> {
            startActivity(new Intent(ChoiceActivity.this, LoginActivity.class));
        });
    }
}