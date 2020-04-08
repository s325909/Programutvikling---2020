package org.ccomp.model.component.engine;

import org.ccomp.model.component.CarComponent;

public class HybridEngine extends CarComponent implements Engine {

   // String engineName;
    int horsePower; //enginePrice;

    public HybridEngine(String compName, double compPrice, int compQuantity, int horsePower) {
        super(compName, compPrice, compQuantity);
        this.horsePower = horsePower;
    }

    @Override
    public boolean isCombustion() {
        return true;
    }

    @Override
    public boolean isElectric() {
        return true;
    }

    @Override
    public void enginePower(int power) {
        this.horsePower = power;
    }


    public int getHorsePower() {
        return horsePower;
    }


   // @Override
    public void printEngine() {
        System.out.println("name: " + getCompName() //engineName
                + "\nhorsepower: " + horsePower
                + "\nprice: " + getCompPrice() );
    }

}
