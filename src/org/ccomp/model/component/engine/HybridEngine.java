package org.ccomp.model.component.engine;

public class HybridEngine implements Engine {

    String engineName;
    int horsePower, enginePrice;

    @Override
    public boolean isCombustion() {
        return true;
    }

    @Override
    public boolean isElectric() {
        return true;
    }

    @Override
    public void engineName(String name) {
        this.engineName = name;
    }

    @Override
    public void enginePower(int power) {
        this.horsePower = power;
    }

    @Override
    public void enginePrice(int price) {
        this.enginePrice = price;
    }

   // @Override
    public void printEngine() {
        System.out.println("name: " + engineName
                + "\nhorsepower: " + horsePower
                + "\nprice: " + enginePrice );
    }
}
