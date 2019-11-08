package com.tjclawson.javaorders.services;

import com.tjclawson.javaorders.models.Payment;

public interface PaymentService {

    Payment findPaymentById(long id);

    Payment save(Payment payment);
}
