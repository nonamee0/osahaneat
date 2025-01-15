package com.cybersoft.osahaneat.service;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.cybersoft.osahaneat.entity.Category;
import com.cybersoft.osahaneat.entity.Food;
import com.cybersoft.osahaneat.entity.Restaurant;
import com.cybersoft.osahaneat.repository.FoodRepository;
import com.cybersoft.osahaneat.service.imp.FileServiceImp;
import com.cybersoft.osahaneat.service.imp.MenuServiceImp;

@Service
public class MenuService implements MenuServiceImp{

	@Autowired
	FileServiceImp fileServiceImp;
	
	@Autowired
	FoodRepository foodRepository;
	
	@Override
	public boolean createMenu(MultipartFile file, String title, boolean is_freeship, String time_ship, double price,
			int cate_id) {
		boolean isInsertSuccess = false;
		
		try {
			boolean isSaveFileSuccess = fileServiceImp.saveFile(file);
			if(isSaveFileSuccess) {
				Food food = new Food();
				food.setTitle(title);
				food.setFreeShip(is_freeship);
				food.setPrice(price);
				food.setTimeShip(time_ship);
				food.setImage(file.getOriginalFilename());
				
				Category category = new Category();
				category.setId(cate_id);
				food.setCategory(category);
				
				foodRepository.save(food);
				isInsertSuccess = true;
			}
		} catch (Exception e) {
			System.out.print("Error insert restaurant " + e.getMessage());
		}
		
		
		
		
		return isInsertSuccess;
	}

}
