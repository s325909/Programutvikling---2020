package org.ccomp.model.component;

import javafx.beans.property.*;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class Seat extends CarComponent {

    private transient StringProperty color, material;

    public Seat(StringProperty compName, DoubleProperty compPrice, IntegerProperty compQuantity,
                StringProperty color, StringProperty material) {
        super(compName, compPrice, compQuantity);
        this.material = material;
        this.color = color;
    }

    // Callback method to be executed automatically by the jvm at the time of serialization
    private void writeObject(ObjectOutputStream oos) throws IOException {
        writeObjectHandler(oos);
    }

    // Callback method to be executed automatically by the jvm at the time of deserialization
    private void readObject(ObjectInputStream ois) throws IOException, ClassNotFoundException {
        readObjectHandler(ois);
    }


    public String getColor() {
        return color.get();
    }

    public StringProperty colorProperty() {
        return color;
    }

    public void setColor(String color) {
        this.color.set(color);
    }

    public String getMaterial() {
        return material.get();
    }

    public StringProperty materialProperty() {
        return material;
    }

    public void setMaterial(String material) {
        this.material = new SimpleStringProperty(material);
    }

    @Override
    public String toString() {
        return "Seat{" + super.toString() +
                ", color=" + color +
                ", material=" + material +
                '}';
    }

    @Override
    public void writeObjectHandler(ObjectOutputStream oos) throws IOException {
        super.writeObjectHandler(oos);
        oos.writeUTF(getColor());
        oos.writeUTF(getMaterial());
    }

    @Override
    public void readObjectHandler(ObjectInputStream ois) throws IOException, ClassNotFoundException {
        super.readObjectHandler(ois);
        color = new SimpleStringProperty(ois.readUTF());
        material = new SimpleStringProperty(ois.readUTF());
    }

}
