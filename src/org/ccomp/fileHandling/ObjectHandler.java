package org.ccomp.fileHandling;

import org.ccomp.model.CarComp;

import java.util.HashMap;
import java.util.List;

public interface ObjectHandler {

    HashMap<String, List<CarComp>> readComponent(HashMap<String, List<CarComp>> loadCompMap);

    void writeComponent(HashMap<String, List<CarComp>> savedCompMap);

}
