package org.ccomp.model.component;

import javafx.beans.property.*;
import org.ccomp.fileHandling.ObjectHandler;
import org.ccomp.model.component.engine.Engine;

import java.io.*;

public class CarComponent implements ObjectHandler {

    private static final long serialVersionUID = 1L;

    private transient StringProperty compName;
    private transient DoubleProperty compPrice;
    private transient IntegerProperty compQuantity;

    private transient StringProperty compType;

   // private String compType;

    public CarComponent(StringProperty compName, DoubleProperty compPrice, IntegerProperty compQuantity, String compType) {
        this.compName = compName;
        this.compPrice = compPrice;
        this.compQuantity = compQuantity;
        this.compType = new SimpleStringProperty(compType);
    }

    // Callback method to be executed automatically by the jvm at the time of serialization
    private void writeObject(ObjectOutputStream oos) throws IOException {
        writeObjectHandler(oos);
    }

    // Callback method to be executed automatically by the jvm at the time of deserialization
    private void readObject(ObjectInputStream ois) throws IOException, ClassNotFoundException {
        readObjectHandler(ois);
    }


    private String determineEngineType(Engine engine) {
        if (engine.isCombustion()) return "bensin"; //compType = "bensin"
        else if (engine.isElectric()) return "elbil";
        else if (engine.isCombustion() && engine.isElectric()) return "hybrid";
        else return "ukjent";
    }


    public String getCompName() {
        return compName.get();
    }

    public StringProperty compNameProperty() {
        return compName;
    }

    public void setCompName(String compName) {
        this.compName.set(compName);
    }

    public double getCompPrice() {
        return compPrice.get();
    }

    public DoubleProperty compPriceProperty() {
        return compPrice;
    }

    public void setCompPrice(double compPrice) {
        this.compPrice.set(compPrice);
    }

    public int getCompQuantity() {
        return compQuantity.get();
    }

    public IntegerProperty compQuantityProperty() {
        return compQuantity;
    }

    public void setCompQuantity(int compQuantity) {
        this.compQuantity.set(compQuantity);
    }

    public String getCompType() {
        return compType.get();
    }

    public StringProperty compTypeProperty() {
        return compType;
    }

    public void setCompType(String compType) {
        this.compType.set(compType);
    }

    /*
    public String getCompType() {
        return compType;
    }

    public void setCompType(String compType) {
        this.compType = compType;
    }
    */

    @Override
    public String toString() {
        return "CarComponent{" +
                "compName=" + compName +
                ", compPrice=" + compPrice +
                ", compQuantity=" + compQuantity +
                ", compType='" + compType + '\'' +
                '}';
    }

    @Override
    public void writeObjectHandler(ObjectOutputStream oos) throws IOException {
        oos.defaultWriteObject();
        oos.writeUTF(getCompName());
        oos.writeDouble(getCompPrice());
        oos.writeInt(getCompQuantity());
        oos.writeUTF(getCompType());
       // oos.writeUTF(getCompType());
    }

    @Override
    public void readObjectHandler(ObjectInputStream ois) throws IOException, ClassNotFoundException {
        compName = new SimpleStringProperty(ois.readUTF());
        compPrice = new SimpleDoubleProperty(ois.readDouble());
        compQuantity = new SimpleIntegerProperty(ois.readInt());
        compType = new SimpleStringProperty(ois.readUTF());
       // compType = ois.readUTF();
    }

}
