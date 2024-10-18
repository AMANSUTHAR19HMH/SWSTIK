package com.abc.agrios.market;// ApiService.java
import com.abc.agrios.market.ApiResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ApiService {
    @GET("/resource/9ef84268-d588-465a-a308-a864a43d0070")
    Call<ApiResponse> getCommodityPrices(
            @Query("api-key") String apiKey,
            @Query("format") String format
    );
}
