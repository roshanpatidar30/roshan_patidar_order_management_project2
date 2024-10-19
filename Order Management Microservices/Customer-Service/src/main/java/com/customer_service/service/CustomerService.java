package com.customer_service.service;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.customer_service.entity.Customer;
import com.customer_service.kafka.KafkaProducerService;
import com.customer_service.repository.CustomerRepository;

@Service
public class CustomerService {

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private KafkaProducerService kafkaProducerService;

    public List<Customer> getAllCustomers() {
        return customerRepository.findAll();
    }

    public Customer getCustomerById(Long id) {
        return customerRepository.findById(id).orElseThrow(() -> new RuntimeException("Customer not found"));
    }

    public Customer saveCustomer(Customer customer) {
        Customer savedCustomer = customerRepository.save(customer);
        kafkaProducerService.sendCustomerMessage("Customer created: " + customer.toString());
        return savedCustomer;
    }

    public void deleteCustomer(Long id) {
        customerRepository.deleteById(id);
        kafkaProducerService.sendCustomerMessage("Customer deleted with ID: " + id);
    }
}