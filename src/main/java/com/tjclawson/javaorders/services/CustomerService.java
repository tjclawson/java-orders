package com.tjclawson.javaorders.services;

import com.tjclawson.javaorders.models.Customer;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;

public interface CustomerService {

    List<Customer> getAllCustomers();

    Customer getCustomerById(long id);

    List<Customer> getCustomerByNameLike(String likename);

    Customer save(Customer customer);

    Customer update(Customer customer, long custcode);

    void delete(long id);
}
