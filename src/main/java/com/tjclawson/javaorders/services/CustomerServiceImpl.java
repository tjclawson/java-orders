package com.tjclawson.javaorders.services;

import com.tjclawson.javaorders.models.Agent;
import com.tjclawson.javaorders.models.Customer;
import com.tjclawson.javaorders.models.Order;
import com.tjclawson.javaorders.models.Payment;
import com.tjclawson.javaorders.repositories.CustomerRepository;
import org.aspectj.weaver.ast.Or;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Transactional
@Service
@Qualifier("customerService")
public class CustomerServiceImpl implements CustomerService {

    private CustomerRepository customerRepository;
    private AgentService agentService;
    private PaymentService paymentService;

    public CustomerServiceImpl(CustomerRepository customerRepository, AgentService agentService, PaymentService paymentService) {
        this.customerRepository = customerRepository;
        this.agentService = agentService;
        this.paymentService = paymentService;
    }

    @Override
    public List<Customer> getAllCustomers() {
        List<Customer> list = new ArrayList<>();
        customerRepository.findAll().iterator().forEachRemaining(list::add);
        return list;
    }

    @Override
    public Customer getCustomerById(long id) {
        return customerRepository.findById(id).orElseThrow(() -> new EntityNotFoundException(Long.toString(id)));
    }

    @Override
    public List<Customer> getCustomerByNameLike(String likename) {
        return customerRepository.findByCustnameContaining(likename);
    }

    @Transactional
    @Override
    public Customer save(Customer customer) {

        Customer newCustomer = new Customer();

        newCustomer.setCustname(customer.getCustname());
        newCustomer.setCustcity(customer.getCustcity());
        newCustomer.setWorkingarea(customer.getWorkingarea());
        newCustomer.setCustcountry(customer.getCustcountry());
        newCustomer.setGrade(customer.getGrade());
        newCustomer.setOpeningamt(customer.getOpeningamt());
        newCustomer.setReceiveamt(customer.getReceiveamt());
        newCustomer.setPaymentamt(customer.getPaymentamt());
        newCustomer.setOutstandingamt(customer.getOutstandingamt());
        newCustomer.setPhone(customer.getPhone());

        if (agentService.getAgentById(customer.getAgent().getAgentcode()) != null) {
            Agent agent = agentService.getAgentById(customer.getAgent().getAgentcode());
            newCustomer.setAgent(agent);
        }

        List<Order> newCustomerOrders = customer.getOrders();
        List<Payment> newOrderPayments = new ArrayList<>();
        for (Order o : newCustomerOrders) {
            o.setCustomer(newCustomer);
            for (Payment p : o.getPayments()) {
                Payment payment = paymentService.findPaymentById(p.getPaymentid());
                newOrderPayments.add(payment);
            }

            for (Payment p : newOrderPayments) {
                o.addPayment(p);
            }
        }
        newCustomer.setOrders(newCustomerOrders);

        return customerRepository.save(newCustomer);
    }

    @Transactional
    @Override
    public Customer update(Customer customer, long custcode) {

        Customer currentCustomer = getCustomerById(custcode);

        if (customer.getCustname() != null) {
            currentCustomer.setCustname(customer.getCustname());
        }
        if (customer.getCustcity() != null) {
            currentCustomer.setCustcity(customer.getCustcity());
        }
        if (customer.getWorkingarea() != null) {
            currentCustomer.setWorkingarea(customer.getWorkingarea());
        }
        if (customer.getCustcountry() != null) {
            currentCustomer.setCustcountry(customer.getCustcountry());
        }
        if (customer.getGrade() != null) {
            currentCustomer.setGrade(customer.getGrade());
        }
        if (customer.hasopeningamt) {
            currentCustomer.setOpeningamt(customer.getOpeningamt());
        }
        if (customer.hasreceiveamt) {
            currentCustomer.setReceiveamt(customer.getReceiveamt());
        }
        if (customer.haspaymentamt) {
            currentCustomer.setPaymentamt(customer.getPaymentamt());
        }
        if (customer.hasoutstandingamt) {
            currentCustomer.setOutstandingamt(customer.getOutstandingamt());
        }
        if (customer.getPhone() != null) {
            currentCustomer.setPhone(customer.getPhone());
        }

        if (agentService.getAgentById(customer.getAgent().getAgentcode()) != null) {
            Agent agent = agentService.getAgentById(customer.getAgent().getAgentcode());
            currentCustomer.setAgent(agent);
        }
        return customerRepository.save(currentCustomer);
    }

    @Transactional
    @Override
    public void delete(long id) {
        if (getCustomerById(id) != null) {
            customerRepository.deleteById(id);
        }
    }
}
