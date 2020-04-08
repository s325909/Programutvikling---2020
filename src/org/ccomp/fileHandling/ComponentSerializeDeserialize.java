package org.ccomp.fileHandling;

import java.io.Serializable;

public class ComponentSerializeDeserialize implements Serializable, FileHandler {


    @Override
    public void saveComponent() {
        System.out.println("SAVING SERIALIZED COMPONENT");
    }

    @Override
    public void loadComponent() {
        System.out.println("LOADING DE-SERIALIZED COMPONENT");
    }
}
