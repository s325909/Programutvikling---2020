package org.ccomp.model.component;

import org.ccomp.model.component.engine.Engine;

public class CarComponent {

    private String compName;
    private double compPrice;
    private int compQuantity;

    public CarComponent(String compName, double compPrice, int compQuantity) {
        this.compName = compName;
        this.compPrice = compPrice;
        this.compQuantity = compQuantity;
    }

    private String determineEngineType(Engine engine) {
        if (engine.isCombustion()) return "bensin"; //compType = "bensin"
        else if (engine.isElectric()) return "elbil";
        else if (engine.isCombustion() && engine.isElectric()) return "hybrid";
        else return "ukjent";
     }

    public String getCompName() {
        return compName;
    }

    public void setCompName(String compName) {
        this.compName = compName;
    }

    public double getCompPrice() {
        return compPrice;
    }

    public void setCompPrice(double compPrice) {
        this.compPrice = compPrice;
    }

    public int getCompQuantity() {
        return compQuantity;
    }

    public void setCompQuantity(int compQuantity) {
        this.compQuantity = compQuantity;
    }

    @Override
    public String toString() {
        return "CarComponent{" +
                "compName='" + compName + '\'' +
                ", compPrice=" + compPrice +
                ", compQuantity=" + compQuantity +
                '}';
    }
}
