package com.cybersoft.osahaneat.dto;

import java.util.Date;
import java.util.List;

public class RestaurantDTO {
	private String image;
	private String title;
	private double rating;
	private String subtitle;
	private boolean is_freeship;
	private Date openDate;
	List<CategoryDTO> categories;
	
	
	public String getImage() {
		return image;
	}
	public void setImage(String image) {
		this.image = image;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public double getRating() {
		return rating;
	}
	public void setRating(double rating) {
		this.rating = rating;
	}
	public String getSubtitle() {
		return subtitle;
	}
	public void setSubtitle(String subtitle) {
		this.subtitle = subtitle;
	}
	public boolean isIs_freeship() {
		return is_freeship;
	}
	public void setIs_freeship(boolean is_freeship) {
		this.is_freeship = is_freeship;
	}
	
	public List<CategoryDTO> getCategories() {
		return categories;
	}
	public void setCategories(List<CategoryDTO> categories) {
		this.categories = categories;
	}
	public Date getOpenDate() {
		return openDate;
	}
	public void setOpenDate(Date openDate) {
		this.openDate = openDate;
	}
	
	
	
	
}
