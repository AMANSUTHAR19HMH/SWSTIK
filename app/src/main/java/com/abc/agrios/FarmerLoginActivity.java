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

public class FarmerLoginActivity extends AppCompatActivity {

    private EditText etUsername, etPassword;
    private Button btnLogin;
    private TextView tvSignUp;
    private AuthApi apiService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_farmer_login);
        tvSignUp = findViewById(R.id.tv_dont_have_account);
        etUsername = findViewById(R.id.et_username);
        etPassword = findViewById(R.id.et_password);
        btnLogin = findViewById(R.id.btn_login);
tvSignUp.setOnClickListener(new View.OnClickListener(){
    @Override
    public  void onClick(View v){
        Intent intent = new Intent(FarmerLoginActivity.this,FarmerSignupActivity.class);
        startActivity(intent);
    }
});

        apiService = ApiClient.getClient().create(AuthApi.class);


        btnLogin.setOnClickListener(v -> loginFarmer());
        tvSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                startActivity(new Intent(FarmerLoginActivity.this, FarmerSignupActivity.class));

            }
        });
    }

    private void loginFarmer() {
        String username = etUsername.getText().toString().trim();
        String password = etPassword.getText().toString().trim();

        // Validate input
        if (TextUtils.isEmpty(username) || TextUtils.isEmpty(password)) {
            Toast.makeText(this, "Both fields are required", Toast.LENGTH_SHORT).show();
            return;
        }

        // Make login request
        LoginRequestF loginRequest = new LoginRequestF(username, password);

        apiService.loginFarmer(loginRequest).enqueue(new Callback<LoginResponseF>() {
            @Override
            public void onResponse(Call<LoginResponseF> call, Response<LoginResponseF> response) {
                if (response.isSuccessful() && response.body() != null) {
                    String token = response.body().getToken();
                    Toast.makeText(FarmerLoginActivity.this, "Login Successful", Toast.LENGTH_SHORT).show();

                    // Save token or proceed to next screen
                    Intent intent = new Intent(FarmerLoginActivity.this, farmerhomepage.class);
                    intent.putExtra("TOKEN", token);
                    startActivity(intent);
                    finish();
                } else {
                    Toast.makeText(FarmerLoginActivity.this, "Invalid Credentials", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<LoginResponseF> call, Throwable t) {
                Log.e("LoginError", "API Call Failed", t);
                Toast.makeText(FarmerLoginActivity.this, "Login Failed: " + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}
