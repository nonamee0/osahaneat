package com.cybersoft.osahaneat.entity;

import java.sql.Date;
import java.util.Set;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;

@Entity(name = "users")
public class Users {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@Column(name = "user_name")
	private String userName;
	
	@Column(name = "password")
	private String password;
	
	@Column(name = "fullname")
	private String fullName;
	
	@Column(name = "create_date")
	private Date createDate;
	
	@ManyToOne
	@JoinColumn(name = "role_id")
	private Roles roles;
	
	@OneToMany(mappedBy = "users")
	private Set<RatingFood> listRatingFood;
	
	@OneToMany(mappedBy = "users")
	private Set<RatingRestaurant> listRatingRestaurant;
	
	@OneToMany(mappedBy = "users")
	private Set<Orders> listOrders;
	
	public Set<RatingRestaurant> getListRatingRestaurant() {
		return listRatingRestaurant;
	}

	public void setListRatingRestaurant(Set<RatingRestaurant> listRatingRestaurant) {
		this.listRatingRestaurant = listRatingRestaurant;
	}

	public Set<RatingFood> getListRatingFood() {
		return listRatingFood;
	}

	public void setListRatingFood(Set<RatingFood> listRatingFood) {
		this.listRatingFood = listRatingFood;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getFullName() {
		return fullName;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public Roles getRoles() {
		return roles;
	}

	public void setRoles(Roles roles) {
		this.roles = roles;
	}
	
	
}
