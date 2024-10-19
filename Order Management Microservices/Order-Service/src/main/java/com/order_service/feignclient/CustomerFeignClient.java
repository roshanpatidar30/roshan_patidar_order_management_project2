package com.order_service.feignclient;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

//FeignClient for Customer Service
@FeignClient(name = "customer-service", url = "http://localhost:8082/api/customers")
public interface CustomerFeignClient {

 @GetMapping("/{id}")
 Customer getCustomerById(@PathVariable("id") Long id);
}