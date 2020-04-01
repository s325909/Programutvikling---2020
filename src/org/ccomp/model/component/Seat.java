package org.ccomp.model.component;

public class Seat {

    private String material, color;
    private int price;

    public Seat(String material, String color, int price) {
        this.material = material;
        this.color = color;
        this.price = price;
    }

    public String getMaterial() {
        return material;
    }

    public void setMaterial(String material) {
        this.material = material;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }
}
