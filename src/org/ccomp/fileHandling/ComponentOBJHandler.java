package org.ccomp.fileHandling;

import org.ccomp.model.component.CarComponent;

import java.io.*;
import java.util.HashMap;
import java.util.List;

public class ComponentOBJHandler implements ObjectFileHandler {
    @Override
    public HashMap<String, List<CarComponent>> readComponent(HashMap<String, List<CarComponent>> compMap) {
        System.out.println("DE-SERIALIZING COMPONENT");

        try {
            //todo: handle if file not found / exists
           // FileInputStream fis = new FileInputStream("components.obj");
            FileInputStream fis = new FileInputStream("testComponents.obj");
            ObjectInputStream ois = new ObjectInputStream(fis);

            compMap = (HashMap<String, List<CarComponent>>) ois.readObject();

            fis.close();

            System.out.println("COMP MAP: " + compMap);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }


        return compMap;
    }

    @Override
    public void writeComponent(HashMap<String, List<CarComponent>> compMap) {
        System.out.println("SERIALIZING COMPONENTS...");
        try {
            // FileOutputStream fos = new FileOutputStream("components.obj");
            FileOutputStream fos = new FileOutputStream("testComponents.obj");
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(compMap);
            oos.flush();
            oos.close();
            fos.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("COMPONENTS SAVED TO FILE");
    }

    @Override
    public void writeObjectHandler(ObjectOutputStream oos) throws IOException {

    }

    @Override
    public void readObjectHandler(ObjectInputStream ois) throws IOException, ClassNotFoundException {

    }
}
