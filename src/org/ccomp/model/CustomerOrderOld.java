package org.ccomp.model;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import org.ccomp.user.Customer;

public class CustomerOrderOld {

    private transient IntegerProperty customerOrderNr;

    private int orderId;
    private Customer customer;

    public CustomerOrderOld(int orderId, Customer customer) {
        this.orderId = orderId;
        this.customer = customer;

        this.customerOrderNr = new SimpleIntegerProperty(orderId);
    }

    public String toCSVFormat() {
        return orderId + "," + customer.toCSVFormat();
    }


    public int getCustomerOrderNr() {
        return customerOrderNr.get();
    }

    public IntegerProperty customerOrderNrProperty() {
        return customerOrderNr;
    }

    public int getOrderId() {
        return orderId;
    }

    public Customer getCustomer() {
        return customer;
    }
}
