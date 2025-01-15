package com.cybersoft.osahaneat.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.cybersoft.osahaneat.dto.CategoryDTO;
import com.cybersoft.osahaneat.dto.MenuDTO;
import com.cybersoft.osahaneat.entity.Category;
import com.cybersoft.osahaneat.entity.Food;
import com.cybersoft.osahaneat.repository.CategoryRepository;
import com.cybersoft.osahaneat.service.imp.CategoryServiceImp;

@Service
public class CategoryService implements CategoryServiceImp{

	@Autowired
	CategoryRepository categoryRepository;
	
	@Override
	public List<CategoryDTO> getCategoryHomePage() {
		
		PageRequest pageRequest = PageRequest.of(0, 2, Sort.by("id"));
		Page<Category> listCategory = categoryRepository.findAll(pageRequest);
		
		List<CategoryDTO> listCategoryDTO = new ArrayList<>();
		
		for(Category data : listCategory) {
			CategoryDTO categoryDTO = new CategoryDTO();
			categoryDTO.setName(data.getNameCate());
			
			List<MenuDTO> menuDTOS = new ArrayList<>();
			
			for(Food dataFood : data.getListFood()) {
				MenuDTO menuDTO = new MenuDTO();
				menuDTO.setTitle(dataFood.getTitle());
				menuDTO.setImage(dataFood.getImage());
				menuDTO.setFreeShip(dataFood.isFreeShip());
				
				menuDTOS.add(menuDTO);
			}
			
			categoryDTO.setMenus(menuDTOS);
			
			listCategoryDTO.add(categoryDTO);
		}
		
		
		return listCategoryDTO;
	}

}
