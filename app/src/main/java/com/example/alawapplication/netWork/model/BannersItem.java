package com.example.alawapplication.netWork.model;

import com.google.gson.annotations.SerializedName;

public class BannersItem{

	@SerializedName("link")
	private Object link;

	@SerializedName("photo")
	private String photo;

	@SerializedName("id")
	private int id;

	@SerializedName("title")
	private String title;

	@SerializedName("body")
	private Object body;

	@SerializedName("order")
	private int order;

	@SerializedName("ratio")
	private double ratio;

	public Object getLink(){
		return link;
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

	public Object getBody(){
		return body;
	}

	public int getOrder(){
		return order;
	}

	public double getRatio(){
		return ratio;
	}
}