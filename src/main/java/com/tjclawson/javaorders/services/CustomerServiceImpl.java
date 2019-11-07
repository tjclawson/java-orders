package com.tjclawson.javaorders.services;

import com.tjclawson.javaorders.models.Customer;
import com.tjclawson.javaorders.repositories.CustomerRepository;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.ArrayList;
import java.util.List;

@Service
@Qualifier("customerService")
public class CustomerServiceImpl implements CustomerService {

    private CustomerRepository customerRepository;

    public CustomerServiceImpl(CustomerRepository customerRepository) { this.customerRepository = customerRepository; }

    @Override
    public List<Customer> getAllCustomers() {
        List<Customer> list = new ArrayList<>();
        customerRepository.findAll().iterator().forEachRemaining(list::add);
        return list;
    }

    @Override
    public Customer getCustomerById(long id) {
        return customerRepository.findById(id).orElseThrow(() -> new EntityNotFoundException(Long.toString(id)));
    }

    @Override
    public List<Customer> getCustomerByNameLike(String likename) {
        return customerRepository.findByCustnameContaining(likename);
    }
}
