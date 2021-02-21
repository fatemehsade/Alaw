package com.example.alawapplication.netWork.model;

import com.google.gson.annotations.SerializedName;

public class Price{

	@SerializedName("final")
	private int jsonMemberFinal;

	@SerializedName("discount")
	private int discount;

	@SerializedName("base")
	private int base;

	public int getJsonMemberFinal(){
		return jsonMemberFinal;
	}

	public int getDiscount(){
		return discount;
	}

	public int getBase(){
		return base;
	}
}