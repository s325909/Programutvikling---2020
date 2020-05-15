package org.ccomp.fileHandling;

import org.ccomp.model.component.CarComponent;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.HashMap;
import java.util.List;

public interface ObjectFileHandler extends Serializable {

    HashMap<String, List<CarComponent>> readComponent(HashMap<String, List<CarComponent>> compMap);

    void writeComponent(HashMap<String, List<CarComponent>> compMap);


    void writeObjectHandler(ObjectOutputStream oos) throws IOException;

    void readObjectHandler(ObjectInputStream ois) throws IOException, ClassNotFoundException;
    
}
