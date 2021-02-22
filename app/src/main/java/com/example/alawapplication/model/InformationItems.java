package com.example.alawapplication.model;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import org.jetbrains.annotations.NotNull;

@Entity(tableName = "information_table")
public class InformationItems {

    @NotNull
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "idg")
    private int mIdGenerate;

    @ColumnInfo(name = "id")
    private int mId;

    @ColumnInfo(name = "title")
    private String mTitle;

    @ColumnInfo(name = "url")
    private String mUrl;



    public InformationItems() {
    }

    public InformationItems(int mId, String mTitle, String mUrl) {
        this.mId = mId;
        this.mTitle = mTitle;
        this.mUrl = mUrl;
    }

    public int getId() {
        return mId;
    }

    public void setId(int mId) {
        this.mId = mId;
    }

    public String getTitle() {
        return mTitle;
    }

    public void setTitle(String mTitle) {
        this.mTitle = mTitle;
    }

    public String getUrl() {
        return mUrl;
    }

    public void setUrl(String mUrl) {
        this.mUrl = mUrl;
    }

    public int getIdGenerate() {
        return mIdGenerate;
    }

    public void setIdGenerate(int mIdGenerate) {
        this.mIdGenerate = mIdGenerate;
    }
}
