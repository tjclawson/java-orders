package com.tjclawson.javaorders.repositories;

import com.tjclawson.javaorders.models.Payment;
import org.springframework.data.repository.CrudRepository;

public interface PaymentRepository extends CrudRepository<Payment, Long> {
}
