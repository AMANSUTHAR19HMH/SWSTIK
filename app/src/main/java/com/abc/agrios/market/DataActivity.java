package com.abc.agrios.market;

import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.abc.agrios.R;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DataActivity extends AppCompatActivity {
    private static final String BASE_URL = "https://api.data.gov.in/";
    private static final String API_KEY = "579b464db66ec23bdd000001280ce119245c4f885b149545206ec774";
    private static final String FORMAT = "json";
    private TextView tvCropName;
    private TextView tvPrice;
    private Button btnRefreshData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_data);


        tvCropName = findViewById(R.id.tvCropName);
        tvPrice = findViewById(R.id.tvPrice);
        btnRefreshData = findViewById(R.id.btnRefreshData);

        // Fetch and display data
        fetchCommodityPrices();

        // Set up refresh button
        btnRefreshData.setOnClickListener(v -> fetchCommodityPrices());
    }

    private void fetchCommodityPrices() {
        ApiService apiService = RetrofitClient.getClient(BASE_URL).create(ApiService.class);
        Call<ApiResponse> call = apiService.getCommodityPrices(API_KEY, FORMAT);
        call.enqueue(new Callback<ApiResponse>() {
            @Override
            public void onResponse(Call<ApiResponse> call, Response<ApiResponse> response) {
                if (response.isSuccessful()) {
                    ApiResponse apiResponse = response.body();
                    if (apiResponse != null) {
                        List<CommodityPrice> cropList = apiResponse.getRecords();

                        // Process the first crop in the list
                        if (!cropList.isEmpty()) {
                            CommodityPrice firstCrop = cropList.get(0);
                            String cropName = firstCrop.getCommodity();
                            double currentPrice = Double.parseDouble(firstCrop.getModalPrice());

                            // Set crop name and price
                            tvCropName.setText("Crop Name: " + cropName);
                            tvPrice.setText("Price: ₹" + currentPrice + " per 100 Kg");

                           
                        }
                    }
                } else {
                    Log.e("API_ERROR", "Response Error: " + response.message());
                }
            }

            @Override
            public void onFailure(Call<ApiResponse> call, Throwable t) {
                Log.e("API_ERROR", "Request Failed: " + t.getMessage());
            }
        });
    }


}
