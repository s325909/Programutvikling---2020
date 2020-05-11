package org.ccomp.model;

import org.ccomp.model.component.CarComponent;
import org.ccomp.user.Customer;

import java.util.List;

public class CustomerOrder {

    private int orderId;
    private Customer customer;

    public CustomerOrder(int orderId, Customer customer) {
        this.orderId = orderId;
        this.customer = customer;
    }

    public String toCSVFormat() {
        return orderId + "," + customer.toCSVFormat();
    }

}
