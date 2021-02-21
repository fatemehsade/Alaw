package com.example.alawapplication.repository;

import android.util.Log;

import androidx.lifecycle.MutableLiveData;

import com.example.alawapplication.model.InformationItems;
import com.example.alawapplication.netWork.retrofit.AlaaService;
import com.example.alawapplication.netWork.retrofit.RetrofitInstance;

import java.io.IOException;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class InformationRepository {
    public static final String TAG = "InformationRepository";
    private AlaaService mService;
    private MutableLiveData<List<InformationItems>> mItemsLiveData=new MutableLiveData<>();

    public MutableLiveData<List<InformationItems>> getItemsLiveData() {
        return mItemsLiveData;
    }

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

        public void fetchItemsAsync(){
        Call<List<InformationItems>> call=mService.listItem();
        call.enqueue(new Callback<List<InformationItems>>() {
            @Override
            public void onResponse(Call<List<InformationItems>> call, Response<List<InformationItems>> response) {
                List<InformationItems> items=response.body();
                mItemsLiveData.setValue(items);
            }

            @Override
            public void onFailure(Call<List<InformationItems>> call, Throwable t) {

            }
        });

    }



}
