package com.example.alawapplication.netWork.retrofit;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitInstance {

    public static Retrofit getInstance(){
        return new Retrofit.Builder()
                .baseUrl("https://alaatv.com/api/v2/home")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }
}
