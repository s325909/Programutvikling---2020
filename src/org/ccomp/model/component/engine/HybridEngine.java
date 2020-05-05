package org.ccomp.model.component.engine;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.StringProperty;
import org.ccomp.model.component.CarComponent;

public class HybridEngine extends CarComponent implements Engine {

    private static final String compType = "Gasoline_Engine";

   // String engineName;
    int horsePower; //enginePrice;

    public HybridEngine(StringProperty compName, DoubleProperty compPrice, IntegerProperty compQuantity, int horsePower) {
        super(compType, compName, compPrice, compQuantity);
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
