package com.order_service.cache;

import org.apache.kafka.common.errors.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.order_service.Entity.Order;
import com.order_service.Repository.OrderRepository;

import jakarta.persistence.Cacheable;

@Service
public class OrderService {
	
	@Autowired
	private OrderRepository orderRepository;
    @org.springframework.cache.annotation.Cacheable("orders")
    public Order getOrderById(Long orderId) {
        return orderRepository.findById(orderId)
            .orElseThrow(() -> new ResourceNotFoundException("Order not found"));
    }
}
