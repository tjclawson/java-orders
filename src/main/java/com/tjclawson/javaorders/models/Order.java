package com.tjclawson.javaorders.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "orders")
@JsonIgnoreProperties({"hasordamount", "hasadvanceamount"})
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long ordnum;

    @Transient
    public boolean hasordamount = false;
    private double ordamount;

    @Transient
    public boolean hasadvanceamount = false;
    private double advanceamount;

    @ManyToOne
    @JoinColumn(name = "custcode")
    @JsonIgnoreProperties({"agent", "orders"})
    private Customer customer;

    private String orderdescription;

    @ManyToMany(mappedBy = "orders", cascade = CascadeType.REMOVE)
    @JsonIgnoreProperties("orders")
    private List<Payment> payments = new ArrayList<>();

    public Order() {
    }

    public Order(double ordamount, double advanceamount, Customer customer, String orderdescription) {
        this.ordamount = ordamount;
        this.advanceamount = advanceamount;
        this.customer = customer;
        this.orderdescription = orderdescription;
    }

    public long getOrdnum() {
        return ordnum;
    }

    public void setOrdnum(long ordnum) {
        this.ordnum = ordnum;
    }

    public double getOrdamount() {
        return ordamount;
    }

    public void setOrdamount(double ordamount) {
        hasordamount = true;
        this.ordamount = ordamount;
    }

    public double getAdvanceamount() {
        return advanceamount;
    }

    public void setAdvanceamount(double advanceamount) {
        hasadvanceamount = true;
        this.advanceamount = advanceamount;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public String getOrderdescription() {
        return orderdescription;
    }

    public void setOrderdescription(String orderdescription) {
        this.orderdescription = orderdescription;
    }

    public List<Payment> getPayments() {
        return payments;
    }

    public void setPayments(List<Payment> payments) {
        this.payments = payments;
    }

    public void addPayment(Payment payment) {
        payments.add(payment);
        payment.getOrders().add(this);
    }

    public void removePayment(Payment payment) {
        payments.remove(payment);
        payment.getOrders().remove(this);
    }
}
