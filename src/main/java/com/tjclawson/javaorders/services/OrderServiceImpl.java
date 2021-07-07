package com.tjclawson.javaorders.services;

import com.tjclawson.javaorders.models.Order;
import com.tjclawson.javaorders.models.Payment;
import com.tjclawson.javaorders.repositories.OrderRepository;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Service
@Qualifier("orderService")
public class OrderServiceImpl implements OrderService {

    private OrderRepository orderRepository;
    private PaymentService paymentService;

    public OrderServiceImpl(OrderRepository orderRepository, PaymentService paymentService){
        this.orderRepository = orderRepository;
        this.paymentService = paymentService;
    }

    @Override
    public Order getOrderById(long ordnum) {
        return orderRepository.findById(ordnum).orElseThrow(() -> new EntityNotFoundException(Long.toString(ordnum)));
    }

    @Transactional
    @Override
    public List<Order> findAll() {

        List<Order> list = new ArrayList<>();
        orderRepository.findAll().iterator().forEachRemaining(list::add);
        return list;
    }

    @Transactional
    @Override
    public Order save(Order order) {

        Order newOrder = new Order();

        newOrder.setOrdamount(order.getOrdamount());
        newOrder.setAdvanceamount(order.getAdvanceamount());
        newOrder.setCustomer(order.getCustomer());
        newOrder.setOrderdescription(order.getOrderdescription());
        for (Payment p : order.getPayments()) {
            Payment newPayment = paymentService.findPaymentById(p.getPaymentid());
            newOrder.addPayment(newPayment);
        }
        System.out.println(newOrder.getPayments().get(0).getType());
        return orderRepository.save(newOrder);
    }

    @Transactional
    @Override
    public Order update(Order order, long ordnum) {

        Order currentOrder = getOrderById(ordnum);

        if (order.hasordamount) {
            currentOrder.setOrdamount(order.getOrdamount());
        }
        if (order.hasadvanceamount) {
            currentOrder.setAdvanceamount(order.getAdvanceamount());
        }
        if (order.getCustomer() != null) {
            currentOrder.setCustomer(order.getCustomer());
        }
        if (order.getOrderdescription() != null) {
            currentOrder.setOrderdescription(order.getOrderdescription());
        }
        return orderRepository.save(currentOrder);
    }

    @Override
    public void delete(long ordnum) {
        if (getOrderById(ordnum) != null) {
            orderRepository.deleteById(ordnum);
        }
    }
}
