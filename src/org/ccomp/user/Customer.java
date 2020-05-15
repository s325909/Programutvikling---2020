package org.ccomp.user;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Customer {

    private transient StringProperty customerName, customerMail, cusomerNumber, customerZipCode, customerCity;

    private String fullName;
    private String emailadress;
    private String number;
    private String zipcode;
    private String city;


    public Customer (String fullName, String emailadress, String number, String zipcode, String city) {
        this.fullName = fullName;
        this.emailadress = emailadress;
        this.number = number;
        this.zipcode = zipcode;
        this.city = city;

        customerName = new SimpleStringProperty(fullName);
        customerMail = new SimpleStringProperty(emailadress);
        cusomerNumber = new SimpleStringProperty(number);
        customerZipCode = new SimpleStringProperty(zipcode);
        customerCity = new SimpleStringProperty(city);
    }


    public String getFullName() {
        return fullName;
    }

    public String getEmailadress() {
        return emailadress;
    }

    public String getNumber() {
        return number;
    }

    public String getZipcode() {
        return zipcode;
    }

    public String getCity() {
        return city;
    }


    public String toCSVFormat() {
        return getFullName() + "," + getEmailadress() + "," + getNumber() + "," + getZipcode() + "," + getCity();
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

    public String getCusomerNumber() {
        return cusomerNumber.get();
    }

    public StringProperty cusomerNumberProperty() {
        return cusomerNumber;
    }

    public void setCusomerNumber(String cusomerNumber) {
        this.cusomerNumber.set(cusomerNumber);
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
