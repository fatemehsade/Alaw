package com.example.alawapplication.netWork.retrofit;

import android.util.Log;

import com.example.alawapplication.model.InformationItems;
import com.google.gson.JsonArray;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonNull;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class GetAlaaItemDeserializer implements JsonDeserializer<List<InformationItems>> {
    public static final String TAG = "GetAlaaItemDeserializer";


    @Override
    public List<InformationItems> deserialize(
            JsonElement json,
            Type typeOfT,
            JsonDeserializationContext context) throws JsonParseException {


        List<InformationItems> items = new ArrayList<>();

        JsonObject bodyObject = json.getAsJsonObject();

        JsonArray dataArray = bodyObject.getAsJsonArray("data");
        for (int i = 0; i < dataArray.size(); i++) {
            JsonObject dataObject = dataArray.get(i).getAsJsonObject();


            if (dataObject.get("sets") instanceof JsonNull)
                continue;

            JsonArray setsArray = dataObject.getAsJsonArray("sets");

            Log.d(TAG, setsArray.toString());

            for (int j = 0; j < setsArray.size(); j++) {
                JsonObject setsObject = setsArray.get(j).getAsJsonObject();
                if (!setsObject.has("id") || !setsObject.has("title") ||
                        !setsObject.has("url"))
                    continue;
                int id = setsObject.get("id").getAsInt();
                String title = setsObject.get("title").getAsString();
                JsonObject urlObject = setsObject.getAsJsonObject("url");
                String url = urlObject.get("web").getAsString();
                InformationItems item = new InformationItems(id, title, url);
                items.add(item);


            }
        }
        return items;
    }

}
