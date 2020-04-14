package org.ccomp.fileHandling;

import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import org.ccomp.model.CarComp;

import java.io.*;
import java.util.HashMap;
import java.util.List;

public interface ObjectHandler extends Serializable {

    void writeObjectHandler(ObjectOutputStream oos) throws IOException;

    void readObjectHandler(ObjectInputStream ois) throws IOException, ClassNotFoundException;

    // readObjectNoData for stateful extendable serializable classes
//    void readObjectNoData() throws InvalidObjectException; //throw new InvalidObjectException("Stream data required");

}
