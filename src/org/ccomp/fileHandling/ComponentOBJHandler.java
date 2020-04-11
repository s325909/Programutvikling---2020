package org.ccomp.fileHandling;

import org.ccomp.model.MapKey;
import org.ccomp.model.component.CarComponent;

import java.io.*;
import java.util.HashMap;
import java.util.List;

public class ComponentOBJHandler implements FileHandler {
    @Override
    public HashMap<String, List<CarComponent>> readComponent(HashMap<String, List<CarComponent>> loadCompMap) {
        System.out.println("DE-SERIALIZING COMPONENT");

        try {
            //todo: handle if file not found / exists
            FileInputStream fis = new FileInputStream("components.obj");
            ObjectInputStream ois = new ObjectInputStream(fis);

            loadCompMap = (HashMap<String, List<CarComponent>>) ois.readObject();

            fis.close();

            System.out.println("COMP MAP: " + loadCompMap);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        return loadCompMap;
    }

    @Override
    public void writeComponent(HashMap<String, List<CarComponent>> compMap) {
        try {
            FileOutputStream fos = new FileOutputStream("components.obj");
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(compMap);
            oos.flush();
            oos.close();
            fos.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
