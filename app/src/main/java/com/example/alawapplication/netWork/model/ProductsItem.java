package com.example.alawapplication.netWork.model;

import com.google.gson.annotations.SerializedName;

public class ProductsItem{

	@SerializedName("price")
	private Price price;

	@SerializedName("photo")
	private String photo;

	@SerializedName("id")
	private int id;

	@SerializedName("title")
	private String title;

	@SerializedName("redirect_url")
	private Object redirectUrl;

	@SerializedName("url")
	private Url url;

	public Price getPrice(){
		return price;
	}

	public String getPhoto(){
		return photo;
	}

	public int getId(){
		return id;
	}

	public String getTitle(){
		return title;
	}

	public Object getRedirectUrl(){
		return redirectUrl;
	}

	public Url getUrl(){
		return url;
	}
}