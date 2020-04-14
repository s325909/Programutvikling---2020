package org.ccomp.model;

import javafx.beans.property.*;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

public class CarComp implements Serializable {

    private static final long serialVersionUID = 1L;

   // private transient StringProperty compName = new SimpleStringProperty();
   // private transient DoubleProperty compPrice = new SimpleDoubleProperty();
   // private transient IntegerProperty compQuantity = new SimpleIntegerProperty();

    private transient StringProperty compName;
    private transient DoubleProperty compPrice;
    private transient IntegerProperty compQuantity;


    /*
    public CarComp(String compName, double compPrice, int compQuantity) {
        setCompName(compName);
        setCompPrice(compPrice);
        setCompQuantity(compQuantity);
    }
    */

    public CarComp(StringProperty compName, DoubleProperty compPrice, IntegerProperty compQuantity) {
        this.compName = compName;
        this.compPrice = compPrice;
        this.compQuantity = compQuantity;
    }


    /*
    public CarComp() {
        this.compName = new SimpleStringProperty();
        this.compPrice = new SimpleDoubleProperty();
        this.compQuantity = new SimpleIntegerProperty();
    }
    */

    public StringProperty compNameProperty() {
        return this.compName;
    }

    public String getCompName() {
        return compName.get();
    }

    public void setCompName(String compName) {
        this.compName.set(compName);
    }


    public DoubleProperty compPriceProperty() {
        return this.compPrice;
    }

    public double getCompPrice() {
        return compPrice.get();
    }

    public void setCompPrice(double compPrice) {
        this.compPrice.set(compPrice);
    }


    public IntegerProperty compQuantityProperty() {
        return compQuantity;
    }

    public int getCompQuantity() {
        return compQuantity.get();
    }

    public void setCompQuantity(int compQuantity) {
        this.compQuantity.set(compQuantity);
    }


    private void writeObject(ObjectOutputStream s) throws IOException {
        s.defaultWriteObject();
        s.writeUTF(getCompName());
        s.writeDouble(getCompPrice());
        s.writeInt(getCompQuantity());
    }

    private void readObject(ObjectInputStream s) throws IOException, ClassNotFoundException {
        compName = new SimpleStringProperty(s.readUTF());
        compPrice = new SimpleDoubleProperty(s.readDouble());
        compQuantity = new SimpleIntegerProperty(s.readInt());
    }


    @Override
    public String toString() {
        return "CarComp{" +
                "compName=" + compName +
                ", compPrice=" + compPrice +
                ", compQuantity=" + compQuantity +
                '}';
    }

}
