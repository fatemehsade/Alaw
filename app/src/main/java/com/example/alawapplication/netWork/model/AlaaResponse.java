package com.example.alawapplication.netWork.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class AlaaResponse{

	@SerializedName("data")
	private List<DataItem> data;

	@SerializedName("meta")
	private Meta meta;

	@SerializedName("links")
	private Links links;

	public List<DataItem> getData(){
		return data;
	}

	public Meta getMeta(){
		return meta;
	}

	public Links getLinks(){
		return links;
	}
}