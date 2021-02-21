package com.example.alawapplication.netWork.retrofit;

import com.example.alawapplication.model.InformationItems;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;


public interface AlaaService {

    @GET(".")
    Call<List<InformationItems>> listItem();
}
