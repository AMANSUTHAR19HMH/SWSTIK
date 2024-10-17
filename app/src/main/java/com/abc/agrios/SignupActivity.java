package com.abc.agrios;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.abc.agrios.apiInterface.AuthApi;
import com.abc.agrios.apirequest.SignupRequest;
import com.abc.agrios.instance.ApiClient;
import com.abc.agrios.response.ApiResponse;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SignupActivity extends AppCompatActivity {

    EditText usernameEditText, passwordEditText, emailEditText, mobileNoEditText, nameEditText, pincodeEditText;
    EditText streetEditText, cityEditText, stateEditText, countryEditText, zipCodeEditText;
    Button signupButton;
    TextView loginTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_signuppage);
        usernameEditText = findViewById(R.id.username);
        passwordEditText = findViewById(R.id.password);
        loginTextView = findViewById(R.id.logintxtbtn);
        emailEditText = findViewById(R.id.email);
        mobileNoEditText = findViewById(R.id.mobileNo);
        nameEditText = findViewById(R.id.name);
        pincodeEditText = findViewById(R.id.pincode);
        streetEditText = findViewById(R.id.street);
        cityEditText = findViewById(R.id.city);
        stateEditText = findViewById(R.id.state);
        countryEditText = findViewById(R.id.country);
        zipCodeEditText = findViewById(R.id.zipCode);
        signupButton = findViewById(R.id.btnSignup);
        loginTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(SignupActivity.this, LoginActivity.class));
            }
        });
        signupButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Collect data from EditTexts
                String username = usernameEditText.getText().toString();
                String password = passwordEditText.getText().toString();
                String email = emailEditText.getText().toString();
                String mobileNo = mobileNoEditText.getText().toString();
                String name = nameEditText.getText().toString();
                String pincode = pincodeEditText.getText().toString();
                String street = streetEditText.getText().toString();
                String city = cityEditText.getText().toString();
                String state = stateEditText.getText().toString();
                String country = countryEditText.getText().toString();
                String zipCode = zipCodeEditText.getText().toString();

                SignupRequest.Address address = new SignupRequest.Address(street, city, state, country, zipCode);
                SignupRequest signupRequest = new SignupRequest(username, password, email, mobileNo, name, pincode, "customer", address);

                signupUser(signupRequest);
            }
        });
    }

    private void signupUser(SignupRequest signupRequest) {
        AuthApi apiService = ApiClient.getClient().create(AuthApi.class);
        Call<ApiResponse> call = apiService.registerUser(signupRequest);

        call.enqueue(new Callback<ApiResponse>() {
            @Override
            public void onResponse(Call<ApiResponse> call, Response<ApiResponse> response) {
                Log.d("SignupActivity", "Response code: " + response.code());

                if (response.isSuccessful()) {
                    ApiResponse apiResponse = response.body();

                    if (apiResponse != null) {
                        Log.d("SignupActivity", "API Response: " + apiResponse.getMessage());

                        if (apiResponse.isSuccess()) {
                            Toast.makeText(SignupActivity.this, "Signup successful!", Toast.LENGTH_SHORT).show();

                            // Intent to navigate to homeActivity
                            Intent intent = new Intent(SignupActivity.this, homeActivity.class);
                            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK); // Clear stack
                            startActivity(intent);
                            finish(); // Finish current activity
                        } else {
                            Toast.makeText(SignupActivity.this, apiResponse.getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    } else {
                        Log.e("SignupActivity", "API Response is null");
                        Toast.makeText(SignupActivity.this, "Error occurred: Response is null", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Log.e("SignupActivity", "Response not successful: " + response.message());
                    Toast.makeText(SignupActivity.this, "Signup failed! Please try again.", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<ApiResponse> call, Throwable t) {
                Log.e("SignupActivity", "Error: " + t.getMessage());
                Toast.makeText(SignupActivity.this, "Error: " + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

}
