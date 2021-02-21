package com.example.alawapplication.netWork.model;

import com.google.gson.annotations.SerializedName;

public class Author{

	@SerializedName("last_name")
	private String lastName;

	@SerializedName("photo")
	private String photo;

	@SerializedName("id")
	private int id;

	@SerializedName("first_name")
	private String firstName;

	public String getLastName(){
		return lastName;
	}

	public String getPhoto(){
		return photo;
	}

	public int getId(){
		return id;
	}

	public String getFirstName(){
		return firstName;
	}
}