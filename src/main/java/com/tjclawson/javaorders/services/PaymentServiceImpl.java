package com.tjclawson.javaorders.services;

import com.tjclawson.javaorders.models.Payment;
import com.tjclawson.javaorders.repositories.PaymentRepository;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;

@Service
@Qualifier("paymentService")
public class PaymentServiceImpl implements PaymentService {

    private PaymentRepository paymentRepository;

    public PaymentServiceImpl(PaymentRepository paymentRepository) {
        this.paymentRepository = paymentRepository;
    }

    @Override
    public Payment findPaymentById(long id) {
        return paymentRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Payment " + id + " not found"));
    }

    @Override
    public Payment save(Payment payment) {
        return null;
    }

    @Override
    public void delete(long id) {
        paymentRepository.deleteById(id);
    }
}
