package com.tjclawson.javaorders.services;

import com.tjclawson.javaorders.models.Order;
import com.tjclawson.javaorders.repositories.OrderRepository;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service
@Qualifier("orderService")
public class OrderServiceImpl implements OrderService {

    private OrderRepository orderRepository;

    public OrderServiceImpl(OrderRepository orderRepository){
        this.orderRepository = orderRepository;
    }

    @Override
    public Order getOrderById(long ordnum) {
        return orderRepository.findByOrdnum(ordnum);
    }
}
