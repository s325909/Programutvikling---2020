package org.ccomp.fileHandling;

import org.ccomp.model.CarComp;
import org.ccomp.model.component.CarComponent;

import java.io.*;
import java.util.HashMap;
import java.util.List;

public class ReadWriteObjectsHelper implements ObjectHandler {
    @Override
    public HashMap<String, List<CarComp>> readComponent(HashMap<String, List<CarComp>> loadCompMap) {
        System.out.println("DE-SERIALIZING COMPONENT");

        try {
            //todo: handle if file not found / exists
            FileInputStream fis = new FileInputStream("comps.obj");
            ObjectInputStream ois = new ObjectInputStream(fis);

            loadCompMap = (HashMap<String, List<CarComp>>) ois.readObject();

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
    public void writeComponent(HashMap<String, List<CarComp>> savedCompMap) {
        try {
            FileOutputStream fos = new FileOutputStream("comps.obj");
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(savedCompMap);
            oos.flush();
            oos.close();
            fos.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
