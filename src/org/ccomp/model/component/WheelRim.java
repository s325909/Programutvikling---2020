package org.ccomp.model.component;

import javafx.beans.property.*;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class WheelRim extends CarComponent{

    private static final String compType = "Wheel_Rim";

    private transient StringProperty wheelRimColor, wheelRimDimension;
    // private transient IntegerProperty wheelRimSize;
    // private String summerSeason, winterSeason;

    public WheelRim(String compName, double compPrice, int compQuantity,
                    String wheelRimColor, String wheelRimDimension) {
        super(compType, compName, compPrice, compQuantity);
        this.wheelRimColor = new SimpleStringProperty(wheelRimColor);
        this.wheelRimDimension = new SimpleStringProperty(wheelRimDimension);
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

    public String getWheelRimDimension() {
        return wheelRimDimension.get();
    }

    public StringProperty wheelRimDimensionProperty() {
        return wheelRimDimension;
    }

    public void setWheelRimDimension(String wheelRimDimension) {
        this.wheelRimDimension.set(wheelRimDimension);
    }

    @Override
    public String toString() {
        return "WheelRim{" + super.toString() +
                ", wheelRimColor=" + wheelRimColor +
                ", wheelRimDimension=" + wheelRimDimension +
              //  ", wheelRimSize=" + wheelRimSize +
                '}';
    }

    @Override
    public void writeObjectHandler(ObjectOutputStream oos) throws IOException {
        super.writeObjectHandler(oos);
        oos.writeUTF(getWheelRimColor());
        oos.writeUTF(getWheelRimDimension());
      //  oos.writeInt(getWheelRimSize());
    }

    @Override
    public void readObjectHandler(ObjectInputStream ois) throws IOException, ClassNotFoundException {
        super.readObjectHandler(ois);
        wheelRimColor = new SimpleStringProperty(ois.readUTF());
        wheelRimDimension = new SimpleStringProperty(ois.readUTF());
       // wheelRimSize = new SimpleIntegerProperty(ois.readInt());
    }
}
