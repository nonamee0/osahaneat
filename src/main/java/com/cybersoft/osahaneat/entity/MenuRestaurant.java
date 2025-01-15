package com.cybersoft.osahaneat.entity;

import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity(name = "menu_restaurant")
public class MenuRestaurant {
	
	@EmbeddedId
	KeyMenuRestaurant keys;
	
	@ManyToOne
	@JoinColumn(name = "cate_id", insertable = false, updatable = false)
	private Category category;
	
	@ManyToOne
	@JoinColumn(name = "res_id", insertable = false, updatable = false)
	private Restaurant restaurant;

	public KeyMenuRestaurant getKeys() {
		return keys;
	}

	public void setKeys(KeyMenuRestaurant keys) {
		this.keys = keys;
	}

	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}

	public Restaurant getRestaurant() {
		return restaurant;
	}

	public void setRestaurant(Restaurant restaurant) {
		this.restaurant = restaurant;
	}
	
	
}
