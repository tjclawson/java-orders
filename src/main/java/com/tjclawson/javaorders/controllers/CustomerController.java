package com.tjclawson.javaorders.controllers;

import com.tjclawson.javaorders.models.Customer;
import com.tjclawson.javaorders.services.CustomerService;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/customers")
public class CustomerController {

    private CustomerService customerService;

    public CustomerController(@Qualifier("customerService") CustomerService customerService) {
        this.customerService = customerService;
    }

    // http://localhost:2019/customers/orders
    @GetMapping(value = "/orders", produces = {"application/json"})
    public ResponseEntity<?> getAllCustomers() {
        List<Customer> myCustomers = customerService.getAllCustomers();
        return new ResponseEntity<>(myCustomers, HttpStatus.OK);
    }

    // http://localhost:2019/customers/customer/{id}
    @GetMapping(value = "/customer/{id}", produces = {"application/json"})
    public ResponseEntity<?> getCustomerById(@PathVariable long id) {
        Customer myCustomer = customerService.getCustomerById(id);
        return new ResponseEntity<>(myCustomer, HttpStatus.OK);
    }

    // http://localhost:2019/customers/namelike/{likename}
    @GetMapping(value = "/namelike/{namelike}", produces = {"application/json"})
    public ResponseEntity<?> getCustomersByNameLike(@PathVariable String namelike) {
        List<Customer> myCustomers = customerService.getCustomerByNameLike(namelike);
        return new ResponseEntity<>(myCustomers, HttpStatus.OK);
    }
}
