package com.customer_service;
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

import com.customer_service.entity.Customer;
import com.customer_service.kafka.KafkaProducerService;
import com.customer_service.repository.CustomerRepository;
import com.customer_service.service.CustomerService;

public class CustomerServiceTest {

    @Mock
    private CustomerRepository customerRepository;

    @Mock
    private KafkaProducerService kafkaProducerService;

    @InjectMocks
    private CustomerService customerService;

    private Customer customer;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
        customer = new Customer();
        customer.setId(1L);
        customer.setName("John Doe");
        customer.setEmail("john.doe@example.com");
        customer.setAddress("123 Main St");
    }

    @Test
    public void testSaveCustomer() {
        when(customerRepository.save(customer)).thenReturn(customer);

        Customer savedCustomer = customerService.saveCustomer(customer);
        assertEquals(customer.getId(), savedCustomer.getId());

        verify(kafkaProducerService, times(1)).sendCustomerMessage(anyString());
    }

    @Test
    public void testGetCustomerById() {
        when(customerRepository.findById(1L)).thenReturn(Optional.of(customer));

        Customer foundCustomer = customerService.getCustomerById(1L);
        assertEquals(customer.getId(), foundCustomer.getId());
    }

    @Test
    public void testDeleteCustomer() {
        customerService.deleteCustomer(1L);
        verify(customerRepository, times(1)).deleteById(1L);
        verify(kafkaProducerService, times(1)).sendCustomerMessage(anyString());
    }
}
