package org.ccomp.model;

import org.ccomp.model.component.CarComponent;

import java.util.List;

public class CompOrder {

    private int orderId;
    private CarComponent carComponent;
    private List<CarComponent> carComponents;

    public CompOrder(int orderId, CarComponent carComponent) {
        this.orderId = orderId;
        this.carComponent = carComponent;
    }

    public String toCSVFormat() {
        return orderId + "," + carComponent.toCSVFormat();
    }

}
