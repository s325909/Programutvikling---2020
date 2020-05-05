package org.ccomp.model.component;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class SteeringWheel extends CarComponent {

    private static final String compType = "Steering_Wheel";

    private transient StringProperty steeringWheelColor, steeringWheelMaterial;

    public SteeringWheel(StringProperty compName, DoubleProperty compPrice, IntegerProperty compQuantity,
                         StringProperty steeringWheelColor, StringProperty steeringWheelMaterial) {
        super(compType, compName, compPrice, compQuantity);
        this.steeringWheelColor = steeringWheelColor;
        this.steeringWheelMaterial = steeringWheelMaterial;
    }

    // Callback method to be executed automatically by the jvm at the time of serialization
    private void writeObject(ObjectOutputStream oos) throws IOException {
        writeObjectHandler(oos);
    }

    // Callback method to be executed automatically by the jvm at the time of deserialization
    private void readObject(ObjectInputStream ois) throws IOException, ClassNotFoundException {
        readObjectHandler(ois);
    }

    public String getSteeringWheelColor() {
        return steeringWheelColor.get();
    }

    public StringProperty steeringWheelColorProperty() {
        return steeringWheelColor;
    }

    public void setSteeringWheelColor(String steeringWheelColor) {
        this.steeringWheelColor.set(steeringWheelColor);
    }

    public String getSteeringWheelMaterial() {
        return steeringWheelMaterial.get();
    }

    public StringProperty steeringWheelMaterialProperty() {
        return steeringWheelMaterial;
    }

    public void setSteeringWheelMaterial(String steeringWheelMaterial) {
        this.steeringWheelMaterial.set(steeringWheelMaterial);
    }

    @Override
    public String toString() {
        return "SteeringWheel{" + super.toString() +
                ", steeringWheelColor=" + steeringWheelColor +
                ", steeringWheelMaterial=" + steeringWheelMaterial +
                '}';
    }

    @Override
    public void writeObjectHandler(ObjectOutputStream oos) throws IOException {
        super.writeObjectHandler(oos);
        oos.writeUTF(getSteeringWheelColor());
        oos.writeUTF(getSteeringWheelMaterial());
    }

    @Override
    public void readObjectHandler(ObjectInputStream ois) throws IOException, ClassNotFoundException {
        super.readObjectHandler(ois);
        steeringWheelColor = new SimpleStringProperty(ois.readUTF());
        steeringWheelMaterial = new SimpleStringProperty(ois.readUTF());
    }
}
