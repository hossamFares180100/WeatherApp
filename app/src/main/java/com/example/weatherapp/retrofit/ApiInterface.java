package com.example.weatherapp.retrofit;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ApiInterface {

    @GET("weather?appid=bb999d2863ecaacfcad1ab4690f38416&units=metric")
    Call<Example> getWeatherData(@Query("q")String name);
}
