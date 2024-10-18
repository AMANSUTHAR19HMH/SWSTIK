package com.abc.agrios.weather;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ApiInterface {
    @GET("weather")
    Call<WeatherResponse> getWeatherData(
            @Query("q") String city,
            @Query("appid") String appid,
            @Query("unit") String unit
    );
}
