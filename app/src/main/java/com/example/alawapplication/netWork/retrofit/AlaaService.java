package com.example.alawapplication.netWork.retrofit;

import com.example.alawapplication.netWork.model.AlaaResponse;

import retrofit2.Call;
import retrofit2.http.GET;


public interface AlaaService {

    @GET(".")
    Call<AlaaResponse> listItem();
}
