package org.ccomp.user;

public class Customer {

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
}
