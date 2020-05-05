package org.ccomp.fileHandling;

import org.ccomp.model.component.CarComponent;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.List;

public class ComponentCSVHandler implements CSVFileHandler {


    @Override
    public List<CarComponent> readComponent(List<CarComponent> compList, String filePath) {
        return null;
    }

    @Override
    public void writeComponent(List<CarComponent> compList, String filePath) {
        try {
            FileWriter fw = new FileWriter(filePath, true);
            BufferedWriter bw = new BufferedWriter(fw);
            PrintWriter pw = new PrintWriter(bw);

            pw.println("TYPE, NAME, PRICE, QUANTITY");




            for (CarComponent carComponent : compList) {
                pw.println(carComponent.toCSVFormat());
            }

          //  pw.println();

            pw.flush();
            pw.close();

            System.out.println("FILE SAVED");

        } catch (Exception e) {
            System.out.println("FILE NOT SAVED: " + e.toString());
        }
    }


}
