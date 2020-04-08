package org.ccomp.model.component;

public class WheelRim extends CarComponent{

    private String material, color;
    private int price;
    private int size;
    private String winterSeason;
    private String summerSeason;


    public WheelRim(String compName, double compPrice, int compQuantity, String material, String color) {
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

   // public int getCompPrice() {
    //    return price;
  //  }

    public void setPrice(int price) {
        this.price = price;
    }
}
