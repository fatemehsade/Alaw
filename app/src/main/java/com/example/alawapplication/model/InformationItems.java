package com.example.alawapplication.model;

public class InformationItems {
    private int mId;
    private String mTitle;
    private String mUrl;
    private Object sets;

    public InformationItems(Object obj) {
        sets = obj;
    }

    public InformationItems() {
    }

    public InformationItems(int mId, String mTitle, String mUrl) {
        this.mId = mId;
        this.mTitle = mTitle;
        this.mUrl = mUrl;
    }

    public int getmId() {
        return mId;
    }

    public void setmId(int mId) {
        this.mId = mId;
    }

    public String getmTitle() {
        return mTitle;
    }

    public void setmTitle(String mTitle) {
        this.mTitle = mTitle;
    }

    public String getmUrl() {
        return mUrl;
    }

    public void setmUrl(String mUrl) {
        this.mUrl = mUrl;
    }
}
