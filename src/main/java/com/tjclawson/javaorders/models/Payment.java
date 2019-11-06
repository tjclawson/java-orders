package com.tjclawson.javaorders.models;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "payments")
public class Payment {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long paymentid;

    @Column(nullable = false)
    private String type;

    @ManyToMany
    @JoinTable(name = "orderspayments", joinColumns = @JoinColumn(name = "paymentid"), inverseJoinColumns = @JoinColumn(name = "ordnum"))
    List<Order> orders = new ArrayList<>();
}
