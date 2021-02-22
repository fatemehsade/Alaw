package com.example.alawapplication.repository;

import android.content.Context;
import android.util.Log;

import androidx.lifecycle.MutableLiveData;

import com.example.alawapplication.dataBase.InformationDataBase;
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
    private InformationDataBase mDataBase;
    private AlaaService mService;
    private Context mContext;
    private MutableLiveData<List<InformationItems>> mItemsLiveData=new MutableLiveData<>();

    public MutableLiveData<List<InformationItems>> getItemsLiveData() {
        return mItemsLiveData;
    }

    public InformationRepository(Context context) {
        mContext=context.getApplicationContext();
        Retrofit retrofit = RetrofitInstance.getInstance().getmRetrofit();
        mService = retrofit.create(AlaaService.class);
        mDataBase = InformationDataBase.getInstance(mContext.getApplicationContext());
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
                //List<InformationItems> item=new ArrayList<>();
                for (int i = 0; i <items.size() ; i++) {
                    InformationItems item=new InformationItems(
                            items.get(i).getId(),
                            items.get(i).getTitle(),items.get(i).getUrl());
                    insert(item);

                }
                mItemsLiveData.setValue(items);
                getItems();
            }

            @Override
            public void onFailure(Call<List<InformationItems>> call, Throwable t) {

            }
        });

    }

    public void insert(InformationItems items) {
        mDataBase.getInformationDao().insert(items);
    }

    public List<InformationItems> getItems() {
        return mDataBase.getInformationDao().getItems();
    }



    public void updateItem(List<InformationItems> items) {
        mDataBase.getInformationDao().updateItem(items);
    }



}
