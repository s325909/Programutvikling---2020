package org.ccomp.model;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import org.ccomp.model.component.CarComponent;
import org.ccomp.user.Customer;

import java.util.List;

public class CustomerOrder {

    private transient IntegerProperty customerOrderNr;
    private transient StringProperty customerName, customerMail, customerNumber, customerZipCode, customerCity;

    private int orderId;
    private Customer customer;

    public CustomerOrder(int orderId, String fullName, String emailadress, String number, String zipcode, String city) {
        this.orderId = orderId;


        this.customerOrderNr = new SimpleIntegerProperty(orderId);
        this.customerName = new SimpleStringProperty(fullName);
        this.customerMail = new SimpleStringProperty(emailadress);
        this.customerNumber = new SimpleStringProperty(number);
        this.customerZipCode = new SimpleStringProperty(zipcode);
        this.customerCity = new SimpleStringProperty(city);
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




    public void setCustomerOrderNr(int customerOrderNr) {
        this.customerOrderNr.set(customerOrderNr);
    }

    public String getCustomerName() {
        return customerName.get();
    }

    public StringProperty customerNameProperty() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName.set(customerName);
    }

    public String getCustomerMail() {
        return customerMail.get();
    }

    public StringProperty customerMailProperty() {
        return customerMail;
    }

    public void setCustomerMail(String customerMail) {
        this.customerMail.set(customerMail);
    }

    public String getCustomerNumber() {
        return customerNumber.get();
    }

    public StringProperty customerNumberProperty() {
        return customerNumber;
    }

    public void setCustomerNumber(String customerNumber) {
        this.customerNumber.set(customerNumber);
    }

    public String getCustomerZipCode() {
        return customerZipCode.get();
    }

    public StringProperty customerZipCodeProperty() {
        return customerZipCode;
    }

    public void setCustomerZipCode(String customerZipCode) {
        this.customerZipCode.set(customerZipCode);
    }

    public String getCustomerCity() {
        return customerCity.get();
    }

    public StringProperty customerCityProperty() {
        return customerCity;
    }

    public void setCustomerCity(String customerCity) {
        this.customerCity.set(customerCity);
    }
}
