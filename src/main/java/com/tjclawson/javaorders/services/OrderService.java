package com.tjclawson.javaorders.services;

import com.tjclawson.javaorders.models.Order;

import java.util.List;

public interface OrderService {

    Order getOrderById(long ordnum);

    List<Order> findAll();

    Order save(Order order);

    Order update(Order order, long ordnum);

    void delete(long ordnum);
}
