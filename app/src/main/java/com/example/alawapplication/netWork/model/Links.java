package com.example.alawapplication.netWork.model;

import com.google.gson.annotations.SerializedName;

public class Links{

	@SerializedName("next")
	private Object next;

	@SerializedName("last")
	private String last;

	@SerializedName("prev")
	private Object prev;

	@SerializedName("first")
	private String first;

	public Object getNext(){
		return next;
	}

	public String getLast(){
		return last;
	}

	public Object getPrev(){
		return prev;
	}

	public String getFirst(){
		return first;
	}
}