package org.ccomp.fileHandling;

import org.ccomp.model.MapKey;
import org.ccomp.model.component.CarComponent;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;

public interface OLDFileHandler extends Serializable {

    HashMap<String, List<CarComponent>> readComponent(HashMap<String, List<CarComponent>> loadCompMap);

    void writeComponent(HashMap<String, List<CarComponent>> savedCompMap);

}
