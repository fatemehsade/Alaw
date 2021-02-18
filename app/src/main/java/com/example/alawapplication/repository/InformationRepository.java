package com.example.alawapplication.repository;

import com.example.alawapplication.model.InformationItems;

import java.util.ArrayList;
import java.util.List;

public class InformationRepository {

    private List<InformationItems> mItems=new ArrayList<>();

    public List<InformationItems> getItems() {
        return mItems;
    }

    public void setItems(List<InformationItems> mItems) {
        this.mItems = mItems;
    }
}
