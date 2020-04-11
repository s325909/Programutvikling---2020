package org.ccomp.model.component;

public class Seat extends CarComponent {

    private final String KEY = "Seat";
            //Seat.class.getName();

    private String material, color;
    private int price;

    public Seat(String compName, double compPrice, int compQuantity, String material, String color) {
        super(compName, compPrice, compQuantity);
        this.material = material;
        this.color = color;
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
