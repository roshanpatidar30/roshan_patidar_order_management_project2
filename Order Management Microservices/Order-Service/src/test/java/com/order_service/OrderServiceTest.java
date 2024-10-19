package com.order_service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.order_service.Entity.Order;
import com.order_service.Repository.OrderRepository;
import com.order_service.Service.OrderService;

@ExtendWith(SpringExtension.class)
@SpringBootTest
public class OrderServiceTest {
    @Mock
    private OrderRepository orderRepository;

    @InjectMocks
    private OrderService orderService;

    @Test
    public void testPlaceOrder() {
        Order order = new Order();
        order.setProductId(1L);
        order.setCustomerId(1L);
        order.setTotalAmount(100.0);

        when(orderRepository.save(any(Order.class))).thenReturn(order);
        Order placedOrder = orderService.placeOrder(order);
        assertEquals("PLACED", placedOrder.getStatus());
    }
}
