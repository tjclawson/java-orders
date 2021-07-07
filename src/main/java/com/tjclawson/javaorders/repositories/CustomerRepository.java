package com.tjclawson.javaorders.repositories;

import com.tjclawson.javaorders.models.Customer;
import org.springframework.data.repository.CrudRepository;

import java.util.ArrayList;

public interface CustomerRepository extends CrudRepository<Customer, Long> {

    ArrayList<Customer> findByCustnameContaining(String namelike);
}
