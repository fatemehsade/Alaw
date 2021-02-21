package com.example.alawapplication.repository;

import android.util.Log;

import com.example.alawapplication.model.InformationItems;
import com.example.alawapplication.netWork.model.AlaaResponse;
import com.example.alawapplication.netWork.model.DataItem;
import com.example.alawapplication.netWork.model.SetsItem;
import com.example.alawapplication.netWork.retrofit.AlaaService;
import com.example.alawapplication.netWork.retrofit.RetrofitInstance;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Response;
import retrofit2.Retrofit;

public class InformationRepository {
    public static final String TAG = "InformationRepository";
    private AlaaService mService;
    //private InformationAlaw mInformation;


    public InformationRepository() {
        //mInformation = new InformationAlaw();
        Retrofit retrofit= RetrofitInstance.getInstance();
        mService=retrofit.create(AlaaService.class);
    }

    public List<InformationItems> fetchItems() {
        Call<AlaaResponse> call=mService.listItem();
        List<InformationItems> itemsList=new ArrayList<>();
        try {
            Response<AlaaResponse> response=call.execute();
            AlaaResponse alaaResponse= response.body();



            for (DataItem dataItem: alaaResponse.getData()) {
                if (dataItem.getSets().equals(null))
                    continue;
                for (SetsItem setItem:dataItem.getSets()) {
                    InformationItems item=new InformationItems(
                            setItem.getId(),setItem.getTitle(),setItem.getUrl().getWeb());
                    itemsList.add(item);


                }

            }
        } catch (IOException e) {
            Log.e(TAG, e.getMessage(),e );
        }finally {
            return itemsList;
        }
        }
        /*String url = mInformation.getUri();
        try {

            String response = mInformation.getUrlString(url);
            JSONObject bodyObject = new JSONObject(response);
            Log.d(TAG, "response" + response);
            List<InformationItems> items = ParseJson(bodyObject);
            return items;

        } catch (IOException | JSONException e) {
            Log.e(TAG, e.getMessage(), e);
            return null;
        }
        }

         */


    /*private List<InformationItems> ParseJson(JSONObject bodyObject) throws JSONException {
        List<InformationItems> items = new ArrayList<>();
        JSONArray dataArray = bodyObject.getJSONArray("data");
        for (int i = 0; i < dataArray.length(); i++) {
            JSONObject dataObject = dataArray.getJSONObject(i);


            if (dataObject.isNull("sets"))
                continue;

            JSONArray setsArray = dataObject.getJSONArray("sets");
            Log.d(TAG, setsArray.toString());

            for (int j = 0; j < setsArray.length(); j++) {
                JSONObject setsObject = setsArray.getJSONObject(j);
                if (!setsObject.has("id") || !setsObject.has("title") ||
                        !setsObject.has("url"))
                    continue;
                int id = setsObject.getInt("id");
                String title = setsObject.getString("title");
                JSONObject urlObject = setsObject.getJSONObject("url");
                String url = urlObject.getString("web");
                InformationItems item = new InformationItems(id, title, url);
                items.add(item);


            }

        }
        return items;
    }

     */
}
