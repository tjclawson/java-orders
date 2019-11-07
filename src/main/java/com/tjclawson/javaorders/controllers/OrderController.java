package com.tjclawson.javaorders.controllers;

import com.tjclawson.javaorders.models.Order;
import com.tjclawson.javaorders.services.OrderService;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/orders")
public class OrderController {

    private OrderService orderService;

    public OrderController(@Qualifier("orderService") OrderService orderService) {
        this.orderService = orderService;
    }
    // http://localhost:2019/orders/order/{id}
    @GetMapping(value = "/order/{ordnum}", produces = {"application/json"})
    public ResponseEntity<?> getOrderByOrdnum(@PathVariable long ordnum) {
        Order myOrder = orderService.getOrderById(ordnum);
        return new ResponseEntity<>(myOrder, HttpStatus.OK);
    }
}
