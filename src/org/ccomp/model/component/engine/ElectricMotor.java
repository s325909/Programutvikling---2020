package org.ccomp.model.component.engine;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.StringProperty;
import org.ccomp.model.component.CarComponent;

public class ElectricMotor extends CarComponent implements Engine {

    private static final String compType = "Electric_Motor";

    String engineName;
    int horsePower, enginePrice;

    public ElectricMotor(StringProperty compName, DoubleProperty compPrice, IntegerProperty compQuantity,
                         String engineName, int horsePower, int enginePrice) {
        super(compType, compName, compPrice, compQuantity);
        this.engineName = engineName;
        this.horsePower = horsePower;
        this.enginePrice = enginePrice;
    }

    @Override
    public boolean isCombustion() {
        return false;
    }

    @Override
    public boolean isElectric() {
        return true;
    }

   // @Override
    public void engineName(String name) {
        this.engineName = name;
    }

    @Override
    public void enginePower(int power) {
        this.horsePower = power;
    }

   // @Override
    public void enginePrice(int price) {
        this.enginePrice = price;
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
