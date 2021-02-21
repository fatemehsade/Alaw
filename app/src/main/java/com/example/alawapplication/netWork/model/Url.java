package com.example.alawapplication.netWork.model;

import com.google.gson.annotations.SerializedName;

public class Url{

	@SerializedName("web")
	private String web;

	@SerializedName("api")
	private String api;

	public String getWeb(){
		return web;
	}

	public String getApi(){
		return api;
	}
}