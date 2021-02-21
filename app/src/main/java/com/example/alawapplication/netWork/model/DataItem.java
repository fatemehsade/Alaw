package com.example.alawapplication.netWork.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class DataItem{

	@SerializedName("offer")
	private boolean offer;

	@SerializedName("sets")
	//private Object sets;
	private List<SetsItem> sets;

	@SerializedName("updated_at")
	private String updatedAt;

	@SerializedName("contents")
	private Object contents;

	@SerializedName("id")
	private int id;

	@SerializedName("title")
	private Object title;

	@SerializedName("banners")
	private List<BannersItem> banners;

	@SerializedName("url")
	private Object url;

	@SerializedName("order")
	private int order;

	@SerializedName("products")
	private Object products;

	public boolean isOffer(){
		return offer;
	}

	//public Object getSets(){
		//return sets;
	//

	public List<SetsItem> getSets() {
		return sets;
	}
	// }

	public String getUpdatedAt(){
		return updatedAt;
	}

	public Object getContents(){
		return contents;
	}

	public int getId(){
		return id;
	}

	public Object getTitle(){
		return title;
	}

	public List<BannersItem> getBanners(){
		return banners;
	}

	public Object getUrl(){
		return url;
	}

	public int getOrder(){
		return order;
	}

	public Object getProducts(){
		return products;
	}
}