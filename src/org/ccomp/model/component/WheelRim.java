package org.ccomp.model.component;

import javafx.beans.property.*;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class WheelRim extends CarComponent{

    private static final String compType = "Wheel_Rim";

    private transient StringProperty wheelRimColor, wheelRimMaterial;
    private transient IntegerProperty wheelRimSize;
    private String winterSeason;
    private String summerSeason;

    public WheelRim(StringProperty compName, DoubleProperty compPrice, IntegerProperty compQuantity,
                    StringProperty wheelRimColor, StringProperty wheelRimMaterial) {
                     //, IntegerProperty wheelRimSize) {
        super(compName, compPrice, compQuantity, compType);
        this.wheelRimColor = wheelRimColor;
        this.wheelRimMaterial = wheelRimMaterial;
      //  this.wheelRimSize = wheelRimSize;
    }

    // Callback method to be executed automatically by the jvm at the time of serialization
    private void writeObject(ObjectOutputStream oos) throws IOException {
        writeObjectHandler(oos);
    }

    // Callback method to be executed automatically by the jvm at the time of deserialization
    private void readObject(ObjectInputStream ois) throws IOException, ClassNotFoundException {
        readObjectHandler(ois);
    }

    public String getWheelRimColor() {
        return wheelRimColor.get();
    }

    public StringProperty wheelRimColorProperty() {
        return wheelRimColor;
    }

    public void setWheelRimColor(String wheelRimColor) {
        this.wheelRimColor.set(wheelRimColor);
    }

    public String getWheelRimMaterial() {
        return wheelRimMaterial.get();
    }

    public StringProperty wheelRimMaterialProperty() {
        return wheelRimMaterial;
    }

    public void setWheelRimMaterial(String wheelRimMaterial) {
        this.wheelRimMaterial.set(wheelRimMaterial);
    }

    public int getWheelRimSize() {
        return wheelRimSize.get();
    }

    public IntegerProperty wheelRimSizeProperty() {
        return wheelRimSize;
    }

    public void setWheelRimSize(int wheelRimSize) {
        this.wheelRimSize.set(wheelRimSize);
    }

    @Override
    public String toString() {
        return "WheelRim{" + super.toString() +
                ", wheelRimColor=" + wheelRimColor +
                ", wheelRimMaterial=" + wheelRimMaterial +
              //  ", wheelRimSize=" + wheelRimSize +
                '}';
    }

    @Override
    public void writeObjectHandler(ObjectOutputStream oos) throws IOException {
        super.writeObjectHandler(oos);
        oos.writeUTF(getWheelRimColor());
        oos.writeUTF(getWheelRimMaterial());
      //  oos.writeInt(getWheelRimSize());
    }

    @Override
    public void readObjectHandler(ObjectInputStream ois) throws IOException, ClassNotFoundException {
        super.readObjectHandler(ois);
        wheelRimColor = new SimpleStringProperty(ois.readUTF());
        wheelRimMaterial = new SimpleStringProperty(ois.readUTF());
       // wheelRimSize = new SimpleIntegerProperty(ois.readInt());
    }
}
