package com.abc.agrios;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class homeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        loadFragment(new homemain());
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_home);
        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_navigation);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @SuppressLint("NonConstantResourceId")
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                Fragment selectedFragment = null;
//                View layout = findViewById(R.id.profile);
                if (item.getItemId() == R.id.nav_home) {
                    selectedFragment = new homemain();
                }
                if (item.getItemId() == R.id.nav_menu) {
                    selectedFragment = new menuFragment();
                }
                if (item.getItemId() == R.id.nav_cart) {
                    selectedFragment = new cartFragment();
                } else if (item.getItemId() == R.id.nav_profile) {
                    Intent menuIntent = new Intent(homeActivity.this, profile.class);
                    startActivity(menuIntent);
                    return true;
                } else if (item.getItemId() == R.id.nav_settings) {
                    Intent menuIntent = new Intent(homeActivity.this, settingsActivity.class);
                    startActivity(menuIntent);
                    return true;
                }
                if (selectedFragment != null) {
                    loadFragment(selectedFragment);
                }
                return true;
            }
        });
    }

    private void loadFragment(Fragment fragment) {
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.fragment_container, fragment)  // Use your actual container ID
                .commit();
    }


}