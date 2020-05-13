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

    private boolean firstLine, secondLine;

    @Override
    public List<CompOrder> readCompOrder(List<CompOrder> compOrderList, String filePath) {
        File file = new File(filePath);

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


                System.out.println("VALUES: " + Arrays.toString(values));

                //  Customer customer = new Customer(values[1], values[2], values[3], values[4], values[5]);

                int orderNr = Integer.parseInt(values[0]);
                String compType = values[1];
               // StringProperty compName = new SimpleStringProperty(values[2]);
               // DoubleProperty compPrice = new SimpleDoubleProperty(Double.valueOf(values[3]));
               // IntegerProperty compQuantity = new SimpleIntegerProperty(Integer.valueOf(values[4]));


                String compName = values[2];
                double compPrice = Double.parseDouble(values[3]);
                int compQuantity = Integer.parseInt(values[4]);

                CarComponent carComponent = new CarComponent(compType, compName, compPrice, compQuantity);

                System.out.println("READ CAR COMPONENT: " + carComponent.toCSVFormat());


                CompOrder compOrder = new CompOrder(orderNr, carComponent);

                System.out.println("READ COMP ORDER: " + compOrder.toCSVFormat());

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
    public List<CarComponent> readComponent(List<CarComponent> compList, String filePath) {
        return null;
    }

    @Override
    public List<CustomerOrder> readCustomerOrder(List<CustomerOrder> customerOrderList, String filePath) {

        File file = new File(filePath);

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

                System.out.println("#0 ; FirstLine == " + firstLine + " ; " + "SecondLine == " + secondLine);


                if (firstLine) {
                    System.out.println("FIRST LINE ; CONTINUE == " + firstLine);
                    firstLine = false;
                    continue;
                }

                System.out.println("#1 ; FirstLine == " + firstLine + " ; " + "SecondLine == " + secondLine);

                if (secondLine) {
                    System.out.println("FIRST LINE ; CONTINUE == " + secondLine);
                    secondLine = false;
                    continue;
                }

                System.out.println("#2 ; FirstLine == " + firstLine + " ; " + "SecondLine == " + secondLine);


                String[] values = line.split(",");


                System.out.println("VALUES: " + Arrays.toString(values));

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
    public void writeCompOrder(List<CompOrder> compOrderList, String filePath) {
        try {



            File file = new File(filePath);

            FileWriter fw = new FileWriter(filePath, true);
            BufferedWriter bw = new BufferedWriter(fw);
            PrintWriter pw = new PrintWriter(bw);

            firstLine = true;
            if (file.length() == 0 && firstLine) {
                System.out.println("FILE EMPTY ; FirstLine == " + firstLine + " ; ADD SEP");
                pw.println("sep=,");
                firstLine = false;
            } else System.out.println("FILE NOT EMPTY ; SEP ADDED == " + firstLine);


            secondLine = true;
            if (file.length() == 0 && secondLine) {
                System.out.println("FILE EMPTY ; SecondLine == " + secondLine + " ; ADD HEADER");
                pw.println("ORDRE NR,TYPE,NAVN,PRIS,ANTALL");
                secondLine = false;
            } else System.out.println("FILE NOT EMPTY ; HEADER ADDED == " + secondLine);



           // if (fw.)

           // pw.println("TYPE, NAME, PRICE, QUANTITY");




            for (CompOrder compOrder : compOrderList) {
                pw.println(compOrder.toCSVFormat());
            }


            //  pw.println();

            pw.flush();
            pw.close();

            System.out.println("FILE SAVED");

        } catch (Exception e) {
            System.out.println("FILE NOT SAVED: " + e.toString());
        }
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

    @Override
    public void writeCustomerOrder(CustomerOrder customerOrder, String filePath) {
        try {

            File file = new File(filePath);

            FileWriter fw = new FileWriter(filePath, true);
            BufferedWriter bw = new BufferedWriter(fw);
            PrintWriter pw = new PrintWriter(bw);

            firstLine = true;
            if (file.length() == 0 && firstLine) {
                System.out.println("FILE EMPTY ; FirstLine == " + firstLine + " ; ADD SEP");
                pw.println("sep=,");
                firstLine = false;
            } else System.out.println("FILE NOT EMPTY ; SEP ADDED == " + firstLine);



            /*
            this.emailadress = emailadress;
        this.number = number;
        this.zipcode = zipcode;
        this.city = city;
             */

            secondLine = true;
            if (file.length() == 0 && secondLine) {
                System.out.println("FILE EMPTY ; SecondLine == " + secondLine + " ; ADD HEADER");
                pw.println("ORDRE NR,NAVN,EPOST,TLF NR,POST NR,POSTSTED");
                secondLine = false;
            } else System.out.println("FILE NOT EMPTY ; HEADER ADDED == " + secondLine);



            // if (fw.)

            // pw.println("TYPE, NAME, PRICE, QUANTITY");



            /*
            for (CompOrder compOrder : compOrderList) {
                pw.println(compOrder.toCSVFormat());
            }
            */

            pw.println(customerOrder.toCSVFormat());

            //  pw.println();

            pw.flush();
            pw.close();

            System.out.println("FILE SAVED");

        } catch (Exception e) {
            System.out.println("FILE NOT SAVED: " + e.toString());
        }
    }



    public String[] readLastRow() {
        try {
            FileReader file = new FileReader("testCompOrders.csv");  //address of the file
            List<String> lines = new ArrayList<>();  //to store all lines
            Scanner scanner = new Scanner(file);
            while(scanner.hasNextLine()){  //checking for the presence of next Line
                lines.add(scanner.nextLine());  //reading and storing all lines
            }
            scanner.close();  //close the scanner


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


    public List<CompOrder> searchOrderRow(String filepath, String orderId) {
        List<CompOrder> compOrders = new ArrayList<>();
        try {
            String splitBy = ",";
            BufferedReader br = new BufferedReader(new FileReader(filepath));
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

}
