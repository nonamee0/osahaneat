package com.cybersoft.osahaneat.entity;



import java.io.Serializable;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;

@Embeddable
public class KeyMenuRestaurant implements Serializable {
	
	@Column(name = "cate_id")
	private int cateId;
	
	@Column(name = "res_id")
	private int resId;
	
	public KeyMenuRestaurant() {
		
	}
	
	public KeyMenuRestaurant(int cateId, int resId) {
		this.cateId = cateId;
		this.resId = resId;
	}

	public int getCateId() {
		return cateId;
	}

	public void setCateId(int cateId) {
		this.cateId = cateId;
	}

	public int getResId() {
		return resId;
	}

	public void setResId(int resId) {
		this.resId = resId;
	}
	
	
	
	
}
