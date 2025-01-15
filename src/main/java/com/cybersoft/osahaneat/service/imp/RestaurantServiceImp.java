package com.cybersoft.osahaneat.service.imp;

import java.util.List;

import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import com.cybersoft.osahaneat.dto.*;
public interface RestaurantServiceImp {
	boolean insertRestaurant( MultipartFile file,
							  String title,
							  String subtitle,
							  String description,
							  boolean is_freeship,
							  String address,
							  String open_date);
	
	List<RestaurantDTO> getMenuRestaurant();
	RestaurantDTO getDetailRestaurant(int id);
}
