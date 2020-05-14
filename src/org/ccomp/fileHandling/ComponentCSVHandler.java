package org.ccomp.fileHandling;

import javafx.beans.property.*;
import org.ccomp.model.CompOrder;
import org.ccomp.model.CustomerOrder;
import org.ccomp.model.component.CarComponent;
import org.ccomp.user.Customer;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class ComponentCSVHandler implements CSVFileHandler {

    private final String COMPONENT_ORDERS_PATH = "ComponentOrders.csv";
    private final String CUSTOMER_ORDERS_PATH = "CustomerOrders.csv";

    private boolean firstLine, secondLine;

    private Scanner scanner;

    @Override
    public List<CompOrder> readCompOrder(List<CompOrder> compOrderList) {
        File file = new File(COMPONENT_ORDERS_PATH);

        if (file.length() == 0) {
            System.out.println("COMP ORDER EMPTY");
            return new ArrayList<>();
        }

        try(BufferedReader br = new BufferedReader(new FileReader(file))) {
            String line;


            firstLine = true;
            secondLine = true;

            compOrderList = new ArrayList<>();

            while ((line = br.readLine()) != null) {

                if (firstLine) {
                    System.out.println("FIRST LINE ; CONTINUE == " + firstLine);
                    firstLine = false;
                    continue;
                }

                if (secondLine) {
                    System.out.println("SECOND LINE ; CONTINUE == " + secondLine);
                    secondLine = false;
                    continue;
                }

                String[] values = line.split(",");


               // System.out.println("VALUES: " + Arrays.toString(values));

                //  Customer customer = new Customer(values[1], values[2], values[3], values[4], values[5]);

                int orderNr = Integer.parseInt(values[0]);
                String compType = values[1];
                String compName = values[2];
                double compPrice = Double.parseDouble(values[3]);
                int compQuantity = Integer.parseInt(values[4]);

                CarComponent carComponent = new CarComponent(compType, compName, compPrice, compQuantity);

               // System.out.println("READ CAR COMPONENT: " + carComponent.toCSVFormat());


                CompOrder compOrder = new CompOrder(orderNr, carComponent);

              //  System.out.println("READ COMP ORDER: " + compOrder.toCSVFormat());

                compOrderList.add(compOrder);
            }

        }
        catch (IOException e)
        {
            e.printStackTrace();
        }


        return compOrderList;
    }

    @Override
    public List<CustomerOrder> readCustomerOrder(List<CustomerOrder> customerOrderList) {

        File file = new File(CUSTOMER_ORDERS_PATH);

        if (file.length() == 0) {
            System.out.println("CUSTOMER ORDER EMPTY");
            return new ArrayList<>();
        }

        try(BufferedReader br = new BufferedReader(new FileReader(file))) {
            String line;


            firstLine = true;
            secondLine = true;


            customerOrderList = new ArrayList<>();

            while ((line = br.readLine()) != null) {

                if (firstLine) {
                    System.out.println("FIRST LINE ; CONTINUE == " + firstLine);
                    firstLine = false;
                    continue;
                }

                if (secondLine) {
                    System.out.println("FIRST LINE ; CONTINUE == " + secondLine);
                    secondLine = false;
                    continue;
                }

                String[] values = line.split(",");


               // System.out.println("VALUES: " + Arrays.toString(values));

              //  Customer customer = new Customer(values[1], values[2], values[3], values[4], values[5]);

                int orderNr = Integer.parseInt(values[0]);
                String customerName = values[1];
                String customerMail = values[2];
                String customerNumber = values[3];
                String customerZipCode = values[4];
                String customerCity = values[5];

                CustomerOrder customerOrder = new CustomerOrder(orderNr, customerName, customerMail,customerNumber,
                                                                customerZipCode, customerCity);

                customerOrderList.add(customerOrder);
            }

        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
       // return datalist;


        return customerOrderList;

       // return null;
    }

    @Override
    public void writeCompOrder(List<CompOrder> compOrderList) {
        try {
            File file = new File(COMPONENT_ORDERS_PATH);

            FileWriter fw = new FileWriter(COMPONENT_ORDERS_PATH, true);
            BufferedWriter bw = new BufferedWriter(fw);
            PrintWriter pw = new PrintWriter(bw);

            firstLine = true;
            if (file.length() == 0 && firstLine) {
                System.out.println("FILE EMPTY ; FirstLine == " + firstLine + " ; ADD SEP");
                pw.println("sep=,");
                firstLine = false;
            }


            secondLine = true;
            if (file.length() == 0 && secondLine) {
                System.out.println("FILE EMPTY ; SecondLine == " + secondLine + " ; ADD HEADER");
                pw.println("ORDRE NR,TYPE,NAVN,PRIS,ANTALL");
                secondLine = false;
            }



            for (CompOrder compOrder : compOrderList) {
                pw.println(compOrder.toCSVFormat());
            }


            pw.flush();
            pw.close();

            System.out.println("FILE SAVED");

        } catch (Exception e) {
            System.out.println("FILE NOT SAVED: " + e.toString());
        }
    }

    @Override
    public void writeCustomerOrder(CustomerOrder customerOrder) {
        try {

            File file = new File(CUSTOMER_ORDERS_PATH);

            FileWriter fw = new FileWriter(CUSTOMER_ORDERS_PATH, true);
            BufferedWriter bw = new BufferedWriter(fw);
            PrintWriter pw = new PrintWriter(bw);

            firstLine = true;
            if (file.length() == 0 && firstLine) {
                System.out.println("FILE EMPTY ; FirstLine == " + firstLine + " ; ADD SEP");
                pw.println("sep=,");
                firstLine = false;
            }


            secondLine = true;
            if (file.length() == 0 && secondLine) {
                System.out.println("FILE EMPTY ; SecondLine == " + secondLine + " ; ADD HEADER");
                pw.println("ORDRE NR,NAVN,EPOST,TLF NR,POST NR,POSTSTED");
                secondLine = false;
            }

            pw.println(customerOrder.toCSVFormat());


            pw.flush();
            pw.close();

            System.out.println("FILE SAVED");

        } catch (Exception e) {
            System.out.println("FILE NOT SAVED: " + e.toString());
        }
    }



    public String[] readLastRow() {

        try {

            File compOrderFile = new File(COMPONENT_ORDERS_PATH);
            if (compOrderFile.length() == 0) {
                System.out.println("COMP ORDER EMPTY");
                return null;
            }


            FileReader file = new FileReader(COMPONENT_ORDERS_PATH);
            List<String> lines = new ArrayList<>();
            scanner = new Scanner(file);
            while(scanner.hasNextLine()){
                lines.add(scanner.nextLine());
            }
            scanner.close();

            //If file is empty add 0 to be the last row
            if (lines.size() == 0) lines.add("0");

            String lastLine = lines.get(lines.size()-1);
            System.out.println("LAST LINE: " + lastLine);

            String[] lastRow = lastLine.split(",");
            System.out.println("LAST ROW: " + Arrays.toString(lastRow));

            return lastRow;
        } catch (IOException e) {
            System.out.println(e.toString());
        }

        return null;
    }


    public List<CompOrder> searchOrderRow(String orderId) {
        List<CompOrder> compOrders = new ArrayList<>();
        try {
            String splitBy = ",";
            BufferedReader br = new BufferedReader(new FileReader(COMPONENT_ORDERS_PATH));
            String line;
            while((line = br.readLine()) != null) {
                String[] b = line.split(splitBy);
                System.out.println(b[0]);


                if (orderId.equals(b[0])) {
                    System.out.println("ORDER ID FOUND");
                    System.out.println(b[0] + ";" + b[1] + ";" + b[2] + ";" + b[3] + ";" + b[4]);
                    int orderNr = Integer.parseInt(b[0]);
                    String compType = b[1];
                    StringProperty compName = new SimpleStringProperty(b[2]);
                    DoubleProperty compPrice = new SimpleDoubleProperty(Double.valueOf(b[3]));
                    IntegerProperty compQuantity = new SimpleIntegerProperty(Integer.valueOf(b[4]));

                   // CarComponent carComponent = new CarComponent(compType, compName, compPrice, compQuantity);

                    CarComponent carComponent = new CarComponent(b[1], b[2], Double.valueOf(b[3]), Integer.valueOf(b[4]));
                    compOrders.add(new CompOrder(orderNr, carComponent));

                    System.out.println("FOUND COMP ORDER SIZE: " + compOrders.size());
                }

            }
            br.close();
        } catch (IOException e) {
            System.out.println(e.toString());
        }



        System.out.println("RETURN COMP ORDER SIZE: " + compOrders.size());

        return compOrders;
    }


    private List<CustomerOrder> customerOrders;

    public void removeCustomerOrder(CustomerOrder customerOrder) {
        String tempFile = "temp.csv";
        File oldFile = new File(CUSTOMER_ORDERS_PATH);
        File newFile = new File(tempFile);

        String line;

        try {

            File file = new File(tempFile);

            FileWriter fw = new FileWriter(tempFile, true);
            BufferedWriter bw = new BufferedWriter(fw);
            PrintWriter pw = new PrintWriter(bw);

            firstLine = true;
            if (file.length() == 0 && firstLine) {
                System.out.println("FILE EMPTY ; FirstLine == " + firstLine + " ; ADD SEP");
                pw.println("sep=,");
                firstLine = false;
            }

            secondLine = true;
            if (file.length() == 0 && secondLine) {
                System.out.println("FILE EMPTY ; SecondLine == " + secondLine + " ; ADD HEADER");
                pw.println("ORDRE NR,NAVN,EPOST,TLF NR,POST NR,POSTSTED");
                secondLine = false;
            }



            customerOrders = readCustomerOrder(customerOrders);

            System.out.println("READ CUSTOMER OREDERS SIZE: " + customerOrders.size());


            for (CustomerOrder customerOrder1 : customerOrders) {
                if (customerOrder1.toCSVFormat().equals(customerOrder.toCSVFormat())) {
                    System.out.println("IGNORING CUSTOMER ORDER: " + customerOrder1.toCSVFormat());
                } else pw.println(customerOrder1.toCSVFormat());
            }



            pw.flush();
            pw.close();

            System.out.println(oldFile + " has been deleted!");
            oldFile.delete();
            File customerOrderCSV = new File(CUSTOMER_ORDERS_PATH);
            newFile.renameTo(customerOrderCSV);
            System.out.println("temp file has been renamed to " + newFile);

            System.out.println("FILE SAVED");

        } catch (Exception e) {
            System.out.println("FILE NOT SAVED: " + e.toString());
        }

    }



    private List<CompOrder> compOrders;

    public void removeCompOrder(CompOrder compOrder) {
        String tempFile = "temp.csv";
        File oldFile = new File(COMPONENT_ORDERS_PATH);
        File newFile = new File(tempFile);

        String line;

        try {

            File file = new File(tempFile);

            FileWriter fw = new FileWriter(tempFile, true);
            BufferedWriter bw = new BufferedWriter(fw);
            PrintWriter pw = new PrintWriter(bw);


            firstLine = true;
            if (file.length() == 0 && firstLine) {
                System.out.println("FILE EMPTY ; FirstLine == " + firstLine + " ; ADD SEP");
                pw.println("sep=,");
                firstLine = false;
            }


            secondLine = true;
            if (file.length() == 0 && secondLine) {
                System.out.println("FILE EMPTY ; SecondLine == " + secondLine + " ; ADD HEADER");
                pw.println("ORDRE NR,TYPE,NAVN,PRIS,ANTALL");
                secondLine = false;
            }





            compOrders = readCompOrder(compOrders);

            System.out.println("READ COMP ORDERS SIZE: " + compOrders.size());



            for (CompOrder compOrder1 : compOrders) {
                if (compOrder1.toCSVFormat().equals(compOrder.toCSVFormat())) {
                    System.out.println("IGNORING COMP ORDER: " + compOrder.toCSVFormat());
                } else pw.println(compOrder1.toCSVFormat());
            }


            pw.flush();
            pw.close();

            System.out.println(oldFile + " has been deleted!");
            oldFile.delete();
            File compOrderCSV = new File(COMPONENT_ORDERS_PATH);
            newFile.renameTo(compOrderCSV);
            System.out.println("temp file has been renamed to " + newFile);

            System.out.println("FILE SAVED");

        } catch (Exception e) {
            System.out.println("FILE NOT SAVED: " + e.toString());
        }

    }




}
