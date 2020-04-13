package org.ccomp.model.component;

import javafx.beans.property.SimpleStringProperty;

public class Seat extends CarComponent {

    private final String KEY = "Seat";
            //Seat.class.getName();

    private SimpleStringProperty material, color;
    private int price;

    public Seat(String compName, double compPrice, int compQuantity, String material, String color) {
        super(compName, compPrice, compQuantity);
        this.material = new SimpleStringProperty(material) ;
        this.color = new SimpleStringProperty(color) ;
    }

    public SimpleStringProperty materialProperty() {
        return material;
    }

    public SimpleStringProperty colorProperty() {
        return color;
    }

    public String getMaterial() {
        return material.get();
    }

    public void setMaterial(String material) {
        this.material.set(material); ;
    }

    public String getColor() {
        return color.get();
    }

    public void setColor(String color) {
        this.color.set(color);
    }

    public String getKEY() {
        return KEY;
    }

    @Override
    public String toString() {
        return "Seat{" +
                "COMP NAME: " + getCompName() + '\'' +
                "material='" + material + '\'' +
                ", color='" + color + '\'' +
                ", price=" + getCompPrice() +
                ", quantity=" + getCompQuantity() + '\'' +
                '}';
    }



    /*
    public int getCompPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

     */
}
