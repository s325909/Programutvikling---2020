package org.ccomp.model.component;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class Spoiler extends CarComponent {

    private static final String compType = "Spoiler";

   // private transient StringProperty spoilerSide, spoilerType; //sport, normal, etc.
    private transient StringProperty spoilerColor, spoilerSide;

    public Spoiler(String compName, double compPrice, int compQuantity,
                   String spoilerColor, String spoilerSide) {
        super(compType, compName, compPrice, compQuantity);
        this.spoilerColor = new SimpleStringProperty(spoilerColor);
        this.spoilerSide = new SimpleStringProperty(spoilerSide);
    }

    // Callback method to be executed automatically by the jvm at the time of serialization
    private void writeObject(ObjectOutputStream oos) throws IOException {
        writeObjectHandler(oos);
    }

    // Callback method to be executed automatically by the jvm at the time of deserialization
    private void readObject(ObjectInputStream ois) throws IOException, ClassNotFoundException {
        readObjectHandler(ois);
    }


    public String getSpoilerColor() {
        return spoilerColor.get();
    }

    public StringProperty spoilerColorProperty() {
        return spoilerColor;
    }

    public void setSpoilerColor(String spoilerColor) {
        this.spoilerColor.set(spoilerColor);
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
                ", spoilerColor=" + spoilerColor +
                ", spoilerSide=" + spoilerSide +
                '}';
    }

    @Override
    public void writeObjectHandler(ObjectOutputStream oos) throws IOException {
        super.writeObjectHandler(oos);
        oos.writeUTF(getSpoilerColor());
        oos.writeUTF(getSpoilerSide());
    }

    @Override
    public void readObjectHandler(ObjectInputStream ois) throws IOException, ClassNotFoundException {
        super.readObjectHandler(ois);
        spoilerColor = new SimpleStringProperty(ois.readUTF());
        spoilerSide = new SimpleStringProperty(ois.readUTF());
    }
}
