package org.ccomp.model.component;

public class Spoiler extends CarComponent {

    private String type; //sport, normal, etc.

    public Spoiler(String compName, double compPrice, int compQuantity, String type) {
        super(compName, compPrice, compQuantity);
        this.type = type;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
