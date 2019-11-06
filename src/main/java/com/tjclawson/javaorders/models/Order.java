package com.tjclawson.javaorders.models;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "orders")
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long ordnum;
    private double ordamount;
    private double advanceamount;

    @ManyToOne
    @JoinColumn(name = "custcode")
    private long custcode;

    private String orderdescription;

    @ManyToMany(mappedBy = "orders")
    private List<Payment> payments = new ArrayList<>();
}
