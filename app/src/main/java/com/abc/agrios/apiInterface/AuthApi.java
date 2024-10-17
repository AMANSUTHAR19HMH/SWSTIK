package com.abc.agrios.apiInterface;

import com.abc.agrios.apirequest.LoginRequest;
import com.abc.agrios.apirequest.SignupRequest;
import com.abc.agrios.response.ApiResponse;
import com.abc.agrios.response.LoginResponse;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Headers;
import retrofit2.http.POST;

public interface AuthApi {
    @Headers("Content-Type: application/json")
    @POST("auth/register")
    Call<ApiResponse> registerUser(@Body SignupRequest signupRequest);

    @Headers("Content-Type: application/json")
    @POST("auth/login")
    Call<LoginResponse> loginUser(@Body LoginRequest loginRequest);
}
