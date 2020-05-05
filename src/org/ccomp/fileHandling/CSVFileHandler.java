package org.ccomp.fileHandling;

import org.ccomp.model.component.CarComponent;

import java.util.List;

public interface CSVFileHandler {

    List<CarComponent> readComponent(List<CarComponent> loadCompList);

    void writeComponent(List<CarComponent> savedCompList);

}
