package org.ccomp.model.component;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class Spoiler extends CarComponent {

    private transient StringProperty spoilerSide, spoilerType; //sport, normal, etc.

    public Spoiler(StringProperty compName, DoubleProperty compPrice, IntegerProperty compQuantity,
                   StringProperty spoilerSide) {
        super(compName, compPrice, compQuantity);
        this.spoilerSide = spoilerSide;
    }

    // Callback method to be executed automatically by the jvm at the time of serialization
    private void writeObject(ObjectOutputStream oos) throws IOException {
        writeObjectHandler(oos);
    }

    // Callback method to be executed automatically by the jvm at the time of deserialization
    private void readObject(ObjectInputStream ois) throws IOException, ClassNotFoundException {
        readObjectHandler(ois);
    }


    public String getSpoilerSide() {
        return spoilerSide.get();
    }

    public StringProperty spoilerSideProperty() {
        return spoilerSide;
    }

    public void setSpoilerSide(String spoilerSide) {
        this.spoilerSide.set(spoilerSide);
    }

    @Override
    public String toString() {
        return "Spoiler{" + super.toString() +
                ", type=" + spoilerSide +
                '}';
    }

    @Override
    public void writeObjectHandler(ObjectOutputStream oos) throws IOException {
        super.writeObjectHandler(oos);
        oos.writeUTF(getSpoilerSide());
    }

    @Override
    public void readObjectHandler(ObjectInputStream ois) throws IOException, ClassNotFoundException {
        super.readObjectHandler(ois);
        spoilerSide = new SimpleStringProperty(ois.readUTF());
    }
}
