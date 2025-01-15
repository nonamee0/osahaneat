package com.cybersoft.osahaneat.entity;

import java.sql.Date;
import java.util.Set;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;

@Entity(name = "category")
public class Category {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@Column(name = "name_cate")
	private String nameCate;
	
	@Column(name = "create_date")
	private Date createDate;
	
	@OneToMany(mappedBy = "category")
	private Set<Food> listFood;

	@OneToMany(mappedBy = "category")
	private Set<MenuRestaurant> listMenuRestaurant;
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNameCate() {
		return nameCate;
	}
	
	public void setNameCate(String nameCate) {
		this.nameCate = nameCate;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public Set<Food> getListFood() {
		return listFood;
	}

	public void setListFood(Set<Food> listFood) {
		this.listFood = listFood;
	}

	public Set<MenuRestaurant> getListMenuRestaurant() {
		return listMenuRestaurant;
	}

	public void setListMenuRestaurant(Set<MenuRestaurant> listMenuRestaurant) {
		this.listMenuRestaurant = listMenuRestaurant;
	}
	
	
}
