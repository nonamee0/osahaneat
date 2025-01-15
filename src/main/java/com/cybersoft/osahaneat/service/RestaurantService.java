package com.cybersoft.osahaneat.service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.cybersoft.osahaneat.dto.CategoryDTO;
import com.cybersoft.osahaneat.dto.MenuDTO;
import com.cybersoft.osahaneat.dto.RestaurantDTO;
import com.cybersoft.osahaneat.entity.Food;
import com.cybersoft.osahaneat.entity.MenuRestaurant;
import com.cybersoft.osahaneat.entity.RatingRestaurant;
import com.cybersoft.osahaneat.entity.Restaurant;
import com.cybersoft.osahaneat.repository.RestaurantRepository;
import com.cybersoft.osahaneat.service.imp.FileServiceImp;
import com.cybersoft.osahaneat.service.imp.RestaurantServiceImp;



@Service
public class RestaurantService implements RestaurantServiceImp{

	@Autowired
	FileServiceImp fileServiceImp;
	
	@Autowired
	RestaurantRepository restaurantRepository;
	
	@Override
	public boolean insertRestaurant(MultipartFile file, String title, String subtitle, String description,
			boolean is_freeship, String address, String open_date) {
		
		boolean isInsertSuccess = false;
		
		try {
			boolean isSaveFileSuccess = fileServiceImp.saveFile(file);
			if(isSaveFileSuccess) {
				Restaurant restaurant = new Restaurant();
				restaurant.setTitle(title);
				restaurant.setSubtitle(subtitle);
				restaurant.setDesc(description);
				restaurant.setFreeship(is_freeship);
				restaurant.setAddress(address);
				restaurant.setImage(file.getOriginalFilename());
				
				SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm");
				Date openDate = simpleDateFormat.parse(open_date);
				restaurant.setOpenDate(openDate);
				
				restaurantRepository.save(restaurant);
				isInsertSuccess = true;
			}
		} catch (Exception e) {
			System.out.print("Error insert restaurant " + e.getMessage());
		}
		
		
		
		
		return isInsertSuccess;
	}

	@Override
	public List<RestaurantDTO> getMenuRestaurant() {
		List<RestaurantDTO> listRestaurantDTO = new ArrayList<>();
		PageRequest pageRequest = PageRequest.of(0, 6);
		Page<Restaurant> listData = restaurantRepository.findAll(pageRequest);
		
		for(Restaurant data : listData) {
			RestaurantDTO restaurantDTO = new RestaurantDTO();
			restaurantDTO.setImage(data.getImage());
			restaurantDTO.setTitle(data.getTitle());
			restaurantDTO.setSubtitle(data.getSubtitle());
			restaurantDTO.setIs_freeship(data.isFreeship());
			restaurantDTO.setRating(calculatorRating(data.getListRatingRestaurant()));
			listRestaurantDTO.add(restaurantDTO); 
		}
		return listRestaurantDTO;
	}
	
	private double calculatorRating(Set<RatingRestaurant> listRating) {
		double totalPoint = 0;
		for(RatingRestaurant data : listRating) {
			totalPoint = data.getRatePoint();
		}
		return totalPoint/listRating.size();
	}

	@Override
	public RestaurantDTO getDetailRestaurant(int id) {
		
		Optional<Restaurant> restaurant = restaurantRepository.findById(id);
		RestaurantDTO restaurantDTO = new RestaurantDTO();
		
		if(restaurant.isPresent()) {

			Restaurant data = restaurant.get();
			restaurantDTO.setTitle(data.getTitle());
			restaurantDTO.setSubtitle(data.getSubtitle());
			restaurantDTO.setImage(data.getImage());
			restaurantDTO.setRating(calculatorRating(data.getListRatingRestaurant()));
			restaurantDTO.setIs_freeship(data.isFreeship());
			restaurantDTO.setOpenDate(data.getOpenDate());
			
			List<CategoryDTO> categoryDTOList = new ArrayList<>();
			
			for(MenuRestaurant menuRestaurant : data.getListMenuRestaurant()) {
				List<MenuDTO> menuDTOList = new ArrayList<>();
				
				CategoryDTO categoryDTO = new CategoryDTO();
				categoryDTO.setName(menuRestaurant.getCategory().getNameCate());
				
				for(Food food : menuRestaurant.getCategory().getListFood()) {
					MenuDTO menuDTO = new MenuDTO();
					menuDTO.setImage(food.getImage());
					menuDTO.setFreeShip(food.isFreeShip());
					menuDTO.setTitle(food.getTitle());
					
					menuDTOList.add(menuDTO);
				}
				categoryDTO.setMenus(menuDTOList);
				categoryDTOList.add(categoryDTO);
			}
			restaurantDTO.setCategories(categoryDTOList);
			
		}
		return restaurantDTO;
	}

}
