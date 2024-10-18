package com.abc.agrios;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.abc.agrios.apiInterface.AuthApi;
import com.abc.agrios.instance.ApiClient;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FarmerSignupActivity extends AppCompatActivity {

    private EditText etUsername, etName, etMobile, etEmail, etPassword, etPincode;
    private EditText etStreet, etCity, etState, etCountry;
    private TextView tvLogin;
    private Button btnRegister;
    private AuthApi apiService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_farmer_signup);

        // Initialize UI elements
        tvLogin = findViewById(R.id.tvhave_account);
        etUsername = findViewById(R.id.et_username);
        etName = findViewById(R.id.et_name);
        etMobile = findViewById(R.id.et_mobile);
        etEmail = findViewById(R.id.et_email);
        etPassword = findViewById(R.id.et_password);
        etPincode = findViewById(R.id.et_pincode);
        etStreet = findViewById(R.id.et_street);
        etCity = findViewById(R.id.et_city);
        etState = findViewById(R.id.et_state);
        etCountry = findViewById(R.id.et_country);
        btnRegister = findViewById(R.id.btn_register);

        apiService = ApiClient.getClient().create(AuthApi.class);

        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                registerFarmer();
            }
        });
        tvLogin.setOnClickListener(v -> {
            startActivity(new Intent(FarmerSignupActivity.this, FarmerLoginActivity.class));
        });
    }

    private void registerFarmer() {
        String username = etUsername.getText().toString().trim();
        String name = etName.getText().toString().trim();
        String mobile = etMobile.getText().toString().trim();
        String email = etEmail.getText().toString().trim();
        String password = etPassword.getText().toString().trim();
        String pincode = etPincode.getText().toString().trim();
        String street = etStreet.getText().toString().trim();
        String city = etCity.getText().toString().trim();
        String state = etState.getText().toString().trim();
        String country = etCountry.getText().toString().trim();

        // Validate required fields
        if (TextUtils.isEmpty(username) || TextUtils.isEmpty(name) || TextUtils.isEmpty(mobile) ||
                TextUtils.isEmpty(email) || TextUtils.isEmpty(password) || TextUtils.isEmpty(pincode)) {
            Toast.makeText(this, "All fields are required", Toast.LENGTH_SHORT).show();
            return;
        }

        // Create Address and Farmer objects
        Address address = new Address(street, city, state, country, pincode);
        Farmer farmer = new Farmer(username, password, email, mobile, name, pincode, "farmer", address);

        // Make the API call with Retrofit
        apiService.registerFarmer(farmer).enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                if (response.isSuccessful()) {
                    Toast.makeText(FarmerSignupActivity.this, "Registration Successful", Toast.LENGTH_SHORT).show();
                } else {
                    // Log error details
                    Log.e("RegisterError", "Response Code: " + response.code());
                    try {
                        String errorBody = response.errorBody().string();
                        Log.e("RegisterError", "Error Body: " + errorBody);
                    } catch (Exception e) {
                        Log.e("RegisterError", "Exception while reading error body", e);
                    }
                    Toast.makeText(FarmerSignupActivity.this, "Registration Failed", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                Log.e("RegisterError", "API Call Failed", t);
                Toast.makeText(FarmerSignupActivity.this, "Error: " + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

}
