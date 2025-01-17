package com.cybersoft.osahaneat.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cybersoft.osahaneat.entity.KeyOrderItem;
import com.cybersoft.osahaneat.entity.OrderItem;

@Repository
public interface OrderItemRepository extends JpaRepository<OrderItem, KeyOrderItem>{

}
