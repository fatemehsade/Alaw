package com.example.alawapplication.repository;

import android.util.Log;

import com.example.alawapplication.model.InformationItems;
import com.example.alawapplication.netWork.retrofit.AlaaService;
import com.example.alawapplication.netWork.retrofit.RetrofitInstance;

import java.io.IOException;
import java.util.List;

import retrofit2.Call;
import retrofit2.Response;
import retrofit2.Retrofit;

public class InformationRepository {
    public static final String TAG = "InformationRepository";
    private AlaaService mService;


    public InformationRepository() {
        Retrofit retrofit = RetrofitInstance.getInstance().getmRetrofit();
        mService = retrofit.create(AlaaService.class);
    }

    public List<InformationItems> fetchItems() {
        Call<List<InformationItems>> call = mService.listItem();
        try {

            Response<List<InformationItems>> response = call.execute();
            return response.body();

        } catch (IOException e) {
            Log.e(TAG, e.getMessage(), e);
            return null;
        }
    }

}
