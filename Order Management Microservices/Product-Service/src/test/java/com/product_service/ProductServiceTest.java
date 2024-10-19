package com.product_service;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.product_service.Entity.Product;
import com.product_service.kafka.KafkaProducerService;
import com.product_service.repository.ProductRepository;
import com.product_service.service.ProductService;

public class ProductServiceTest {

    @Mock
    private ProductRepository productRepository;

    @Mock
    private KafkaProducerService kafkaProducerService;

    @InjectMocks
    private ProductService productService;

    private Product product;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
        product = new Product();
        product.setId(1L);
        product.setName("Test Product");
        product.setDescription("This is a test product");
        product.setPrice(100.0);
        product.setQuantity(10);
    }

    @Test
    public void testSaveProduct() {
        when(productRepository.save(product)).thenReturn(product);

        Product savedProduct = productService.saveProduct(product);
        assertEquals(product.getId(), savedProduct.getId());

        verify(kafkaProducerService, times(1)).sendProductMessage(anyString());
    }

    @Test
    public void testGetProductById() {
        when(productRepository.findById(1L)).thenReturn(Optional.of(product));

        Product foundProduct = productService.getProductById(1L);
        assertEquals(product.getId(), foundProduct.getId());
    }

    @Test
    public void testDeleteProduct() {
        productService.deleteProduct(1L);
        verify(productRepository, times(1)).deleteById(1L);
        verify(kafkaProducerService, times(1)).sendProductMessage(anyString());
    }
}
