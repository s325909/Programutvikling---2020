package org.ccomp.model.component.engine;

import org.ccomp.model.component.CarComponent;

public class ElectricMotor extends CarComponent implements Engine {

    String engineName;
    int horsePower, enginePrice;

    public ElectricMotor(String compName, double compPrice, int compQuantity, String engineName, int horsePower, int enginePrice) {
        super(compName, compPrice, compQuantity);
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
