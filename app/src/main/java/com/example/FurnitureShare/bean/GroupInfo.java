package com.example.FurnitureShare.bean;

import java.io.Serializable;

public class GroupInfo extends BaseInfo implements Serializable
{
	String shopid;

	public GroupInfo(String id, String name, String shopid, String shopname) {
		super(id, name);
		this.shopid = shopid;
		this.shopname = shopname;
	}

	public String getShopid() {
		return shopid;
	}

	public void setShopid(String shopid) {
		this.shopid = shopid;
	}

	public String getShopname() {
		return shopname;
	}

	public void setShopname(String shopname) {
		this.shopname = shopname;
	}

	String shopname;

}
