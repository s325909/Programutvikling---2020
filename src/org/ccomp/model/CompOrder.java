package org.ccomp.model;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.StringProperty;
import org.ccomp.model.component.CarComponent;

import java.util.List;

public class CompOrder {

    private transient StringProperty compType;
    private transient StringProperty compName;
    private transient DoubleProperty compPrice;
    private transient IntegerProperty compQuantity;

    private int orderId;
    private CarComponent carComponent;

    public CompOrder(int orderId, CarComponent carComponent) {
        this.orderId = orderId;
        this.carComponent = carComponent;
    }

    public String toCSVFormat() {
        return orderId + "," + carComponent.toCSVFormat();
    }

    public String getOrderNr() {
        return String.valueOf(orderId);
    }

    public String getCompType() {
        return carComponent.getCompType();
    }

    public String getCompName() {
        return carComponent.getCompName();
    }

    public double getCompPrice() {
        return carComponent.getCompPrice();
    }

    public int getCompQuantity() {
        return carComponent.getCompQuantity();
    }

}
