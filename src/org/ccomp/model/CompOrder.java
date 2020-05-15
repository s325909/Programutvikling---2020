package org.ccomp.model;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import org.ccomp.model.component.CarComponent;

public class CompOrder {

    private transient IntegerProperty compOrderNr;

    private int orderId;
    private CarComponent carComponent;

    public CompOrder(int orderId, CarComponent carComponent) {
        this.orderId = orderId;
        this.carComponent = carComponent;

        this.compOrderNr = new SimpleIntegerProperty(orderId);
    }


    public String toCSVFormat() {
        return orderId + "," + carComponent.toCSVFormat();
    }

    public CarComponent getCarComponent() {
        return carComponent;
    }

    public int getCompOrderNr() {
        return compOrderNr.get();
    }

    public IntegerProperty compOrderNrProperty() {
        return compOrderNr;
    }


    public int getOrderId() {
        return orderId;
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
