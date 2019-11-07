package com.tjclawson.javaorders.services;

import com.tjclawson.javaorders.models.Order;
import com.tjclawson.javaorders.repositories.OrderRepository;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

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

    @Override
    public List<Order> findAll() {

        List<Order> list = new ArrayList<>();
        orderRepository.findAll().iterator().forEachRemaining(list::add);
        return list;
    }
}
