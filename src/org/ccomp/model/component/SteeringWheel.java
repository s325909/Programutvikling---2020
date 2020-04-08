package org.ccomp.model.component;

public class SteeringWheel extends CarComponent {

    private String type;

    public SteeringWheel(String compName, double compPrice, int compQuantity, String type) {
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
