package com.example.alawapplication.netWork.retrofit;

import com.example.alawapplication.model.InformationItems;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.List;

import retrofit2.Converter;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitInstance {
    private static RetrofitInstance sInstance;
    private Retrofit mRetrofit;

    public static RetrofitInstance getInstance(){
        if (sInstance==null)
            sInstance=new RetrofitInstance();
        return sInstance;
    }

    private RetrofitInstance() {
        Object typeAdapter=new GetAlaaItemDeserializer();
        Type type=new TypeToken<List<InformationItems>>(){}.getType();
        mRetrofit=new Retrofit.Builder()
                .baseUrl("https://alaatv.com/api/v2/home/")
                .addConverterFactory(CreateGsonConverter(type,typeAdapter))
                .build();
    }

    private static Converter.Factory CreateGsonConverter(Type type, Object typeAdapter){
        GsonBuilder gsonBuilder=new GsonBuilder();
        gsonBuilder.registerTypeAdapter(type,typeAdapter);
        Gson gson=gsonBuilder.create();

        return GsonConverterFactory.create(gson);
    }

    public Retrofit getmRetrofit() {
        return mRetrofit;
    }
}
