package com.abc.agrios.market;

import android.os.Bundle;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.abc.agrios.R;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CropData extends AppCompatActivity {

    private static final String BASE_URL = "https://api.data.gov.in/";
    private static final String API_KEY = "579b464db66ec23bdd000001280ce119245c4f885b149545206ec774";
    private static final String FORMAT = "json";

    private RecyclerView recyclerView;
    private CropAdapter cropAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_crop_data);

        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        fetchCommodityPrices();
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
                        cropAdapter = new CropAdapter(cropList);
                        recyclerView.setAdapter(cropAdapter);
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
