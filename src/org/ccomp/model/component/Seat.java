package org.ccomp.model.component;

import javafx.beans.property.*;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class Seat extends CarComponent {

    private static final String compType = "Seat";

    private transient StringProperty seatColor, seatMaterial;

    public Seat(String compName, double compPrice, int compQuantity,
                String seatColor, String seatMaterial) {
        super(compType, compName, compPrice, compQuantity);
        this.seatColor = new SimpleStringProperty(seatColor);
        this.seatMaterial = new SimpleStringProperty(seatMaterial);
    }

    // Callback method to be executed automatically by the jvm at the time of serialization
    private void writeObject(ObjectOutputStream oos) throws IOException {
        writeObjectHandler(oos);
    }

    // Callback method to be executed automatically by the jvm at the time of deserialization
    private void readObject(ObjectInputStream ois) throws IOException, ClassNotFoundException {
        readObjectHandler(ois);
    }


    public String getSeatColor() {
        return seatColor.get();
    }

    public StringProperty seatColorProperty() {
        return seatColor;
    }

    public void setSeatColor(String seatColor) {
        this.seatColor.set(seatColor);
    }

    public String getSeatMaterial() {
        return seatMaterial.get();
    }

    public StringProperty seatMaterialProperty() {
        return seatMaterial;
    }

    public void setSeatMaterial(String seatMaterial) {
        this.seatMaterial = new SimpleStringProperty(seatMaterial);
    }

    @Override
    public String toString() {
        return "Seat{" + super.toString() +
                ", color=" + seatColor +
                ", material=" + seatMaterial +
                '}';
    }

    @Override
    public void writeObjectHandler(ObjectOutputStream oos) throws IOException {
        super.writeObjectHandler(oos);
        oos.writeUTF(getSeatColor());
        oos.writeUTF(getSeatMaterial());
    }

    @Override
    public void readObjectHandler(ObjectInputStream ois) throws IOException, ClassNotFoundException {
        super.readObjectHandler(ois);
        seatColor = new SimpleStringProperty(ois.readUTF());
        seatMaterial = new SimpleStringProperty(ois.readUTF());
    }

}
