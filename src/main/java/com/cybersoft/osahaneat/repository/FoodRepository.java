package com.cybersoft.osahaneat.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cybersoft.osahaneat.entity.Food;

@Repository
public interface FoodRepository extends JpaRepository<Food, Integer>{

}
