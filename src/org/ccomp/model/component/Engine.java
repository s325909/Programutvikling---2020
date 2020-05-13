package org.ccomp.model.component;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class Engine extends CarComponent {

    private transient IntegerProperty engineHorsePower;

    public Engine(String compType, String compName, double compPrice, int compQuantity, int horsePower) {
        super(compType, compName, compPrice, compQuantity);
        this.engineHorsePower = new SimpleIntegerProperty(horsePower);
    }

    // Callback method to be executed automatically by the jvm at the time of serialization
    private void writeObject(ObjectOutputStream oos) throws IOException {
        writeObjectHandler(oos);
    }

    // Callback method to be executed automatically by the jvm at the time of deserialization
    private void readObject(ObjectInputStream ois) throws IOException, ClassNotFoundException {
        readObjectHandler(ois);
    }


    public int getEngineHorsePower() {
        return engineHorsePower.get();
    }

    public IntegerProperty engineHorsePowerProperty() {
        return engineHorsePower;
    }

    public void setEngineHorsePower(int engineHorsePower) {
        this.engineHorsePower.set(engineHorsePower);
    }


    @Override
    public void writeObjectHandler(ObjectOutputStream oos) throws IOException {
        super.writeObjectHandler(oos);
        oos.writeInt(getEngineHorsePower());
    }

    @Override
    public void readObjectHandler(ObjectInputStream ois) throws IOException, ClassNotFoundException {
        super.readObjectHandler(ois);
        engineHorsePower = new SimpleIntegerProperty(ois.readInt());
    }
}
