package com.cybersoft.osahaneat.service.imp;

import org.springframework.stereotype.Service;

import com.cybersoft.osahaneat.payload.request.OrderRequest;

@Service
public interface OrderServiceImp {
	boolean insertOrder(OrderRequest orderRequest);
}
