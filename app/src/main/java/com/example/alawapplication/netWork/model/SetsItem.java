package com.example.alawapplication.netWork.model;

import com.google.gson.annotations.SerializedName;

public class SetsItem{

	@SerializedName("short_title")
	private String shortTitle;

	@SerializedName("updated_at")
	private String updatedAt;

	@SerializedName("contents")
	private Object contents;

	@SerializedName("author")
	private Author author;

	@SerializedName("photo")
	private String photo;

	@SerializedName("created_at")
	private String createdAt;

	@SerializedName("id")
	private int id;

	@SerializedName("title")
	private String title;

	@SerializedName("redirect_url")
	private Object redirectUrl;

	@SerializedName("url")
	private Url url;

	@SerializedName("contents_count")
	private int contentsCount;

	public String getShortTitle(){
		return shortTitle;
	}

	public String getUpdatedAt(){
		return updatedAt;
	}

	public Object getContents(){
		return contents;
	}

	public Author getAuthor(){
		return author;
	}

	public String getPhoto(){
		return photo;
	}

	public String getCreatedAt(){
		return createdAt;
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

	public int getContentsCount(){
		return contentsCount;
	}
}