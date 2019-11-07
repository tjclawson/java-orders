package com.tjclawson.javaorders.services;

import com.tjclawson.javaorders.models.Order;

public interface OrderService {

    Order getOrderById(long ordnum);
}
