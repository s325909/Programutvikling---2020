package org.ccomp.model.component.engine;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.StringProperty;
import org.ccomp.model.component.CarComponent;

public class GasolineEngine extends CarComponent implements Engine {

    String engineName;
    int horsePower, enginePrice;

    public GasolineEngine(StringProperty compName, DoubleProperty compPrice, IntegerProperty compQuantity,
                          String engineName, int horsePower, int enginePrice) {
        super(compName, compPrice, compQuantity);
        this.engineName = engineName;
        this.horsePower = horsePower;
        this.enginePrice = enginePrice;
    }

    @Override
    public boolean isCombustion() {
        return true;
    }

    @Override
    public boolean isElectric() {
        return false;
    }

    @Override
    public void enginePower(int power) {
        this.horsePower = power;
    }

   // @Override
    public void printEngine() {
        System.out.println("name: " + engineName
                + "\nhorsepower: " + horsePower
                + "\nprice: " + enginePrice );
    }

    public String getEngineName() {
        return engineName;
    }

    public int getHorsePower() {
        return horsePower;
    }

    public int getEnginePrice() {
        return enginePrice;
    }
}
