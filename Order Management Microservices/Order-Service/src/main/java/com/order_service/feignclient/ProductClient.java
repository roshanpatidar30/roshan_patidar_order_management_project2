package com.order_service.feignclient;

package com.product_service.Entity.Entity.Product;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient("product-service")
public interface ProductClient {
    @GetMapping("/products/{productId}")
    Product getProductById(@PathVariable Long productId);
}
