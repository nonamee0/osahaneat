package com.cybersoft.osahaneat.service.imp;

import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import org.springframework.core.io.Resource;

@Configuration
public interface FileServiceImp {
	boolean saveFile(MultipartFile file);
	Resource loadFile(String fileName);
	
}
