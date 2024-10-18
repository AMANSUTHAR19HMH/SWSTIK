package com.abc.agrios.weather;

import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.abc.agrios.R;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class WeatherConditionsActivity extends AppCompatActivity {
    private TextView temperatureField;
    private TextView humidityField;
    private TextView windSpeedField;
    private TextView dateTimeField;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_weather_conditions);

        temperatureField = findViewById(R.id.temperature);
        humidityField = findViewById(R.id.humidity);
        windSpeedField = findViewById(R.id.windspeed);
        dateTimeField = findViewById(R.id.date_time);

        // Set the current date and time
        setCurrentDateTime();

        // Fetch weather data
        fetchWeatherData("India");
    }

    private void setCurrentDateTime() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault());
        String currentDateTime = sdf.format(new Date());
        dateTimeField.setText(currentDateTime);
    }

    private void fetchWeatherData(String location) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://api.openweathermap.org/data/2.5/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        ApiInterface apiInterface = retrofit.create(ApiInterface.class);
        Call<WeatherResponse> call = apiInterface.getWeatherData(location, "2256af0cbd5e9207e761d4139c2fd180", "metric");

        call.enqueue(new Callback<WeatherResponse>() {
            @Override
            public void onResponse(Call<WeatherResponse> call, Response<WeatherResponse> response) {
                if (response.isSuccessful() && response.body() != null) {
                    WeatherResponse weatherData = response.body();

                    Log.d("WeatherData", "Response: " + weatherData.toString());

                    double temperatureCelsius = weatherData.getMain().getTemp() - 273.15;
                    int humidity = weatherData.getMain().getHumidity();
                    double windSpeed = weatherData.getWind().getSpeed();

                    temperatureField.setText(String.format("%.2f °C", temperatureCelsius));
                    humidityField.setText(String.format("%d %%", humidity));
                    windSpeedField.setText(String.format("%.2f m/s", windSpeed));
                } else {
                    Log.e("WeatherData", "Response not successful: " + response.errorBody());
                    // Handle UI error update
                }
            }

            @Override
            public void onFailure(Call<WeatherResponse> call, Throwable t) {
                Log.e("WeatherData", "Request failed: " + t.getMessage());
                // Handle UI error update
            }
        });
    }
}
