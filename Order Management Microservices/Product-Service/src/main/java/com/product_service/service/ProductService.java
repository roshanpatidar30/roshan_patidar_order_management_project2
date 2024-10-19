package com.product_service.service;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.product_service.Entity.Product;
import com.product_service.kafka.KafkaProducerService;
import com.product_service.repository.ProductRepository;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private KafkaProducerService kafkaProducerService;

    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    public Product getProductById(Long id) {
        return productRepository.findById(id).orElseThrow(() -> new RuntimeException("Product not found"));
    }

    public Product saveProduct(Product product) {
        Product savedProduct = productRepository.save(product);
        kafkaProducerService.sendProductMessage("Product created: " + product.toString());
        return savedProduct;
    }

    public void deleteProduct(Long id) {
        productRepository.deleteById(id);
        kafkaProducerService.sendProductMessage("Product deleted with ID: " + id);
    }
}
