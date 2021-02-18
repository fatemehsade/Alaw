package com.example.alawapplication.repository;

import android.util.Log;

import com.example.alawapplication.model.InformationItems;
import com.example.alawapplication.netWork.InformationAlaw;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class InformationRepository {
    public static final String TAG = "InformationRepository";
    private InformationAlaw mInformation;


    public InformationRepository() {
        mInformation = new InformationAlaw();
    }

    public List<InformationItems> fetchItems() {
        String url = mInformation.getUri();
        try {

            String response = mInformation.getUrlString(url);
            JSONObject bodyObject = new JSONObject(response);
            Log.d(TAG, "response" + response);
            List<InformationItems> items = ParseJson(bodyObject);
            return items;

        } catch (IOException | JSONException e) {
            //Log.e(TAG, e.getMessage(), e);
            return null;
        }
    }

    private List<InformationItems> ParseJson(JSONObject bodyObject) throws JSONException {
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
}
