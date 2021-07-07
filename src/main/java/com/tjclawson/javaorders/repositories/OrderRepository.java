package com.tjclawson.javaorders.repositories;

import com.tjclawson.javaorders.models.Order;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface OrderRepository extends CrudRepository<Order, Long> {

    Order findByOrdnum(long ordnum);
}
