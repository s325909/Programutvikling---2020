package org.ccomp.fileHandling;

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
    private Scanner scanner;

    @Override
    public List<CompOrder> readCompOrder(List<CompOrder> compOrderList, String filePath) {



        return null;
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

                Customer customer = new Customer(values[1], values[2], values[3], values[4], values[5]);

                /*

                int orderNr = Integer.parseInt(values[0]);
                String customerName = values[1];
                String customerMail = values[2];
                String customerNumber = values[3];
                String customerZipCode = values[4];
                String customerCity = values[5];

                CustomerOrder customerOrder = new CustomerOrder(orderNr, customerName, customerMail,customerNumber,
                                                                customerZipCode, customerCity);


                 */


                int orderNr = Integer.parseInt(values[0]);
                CustomerOrder customerOrder = new CustomerOrder(orderNr, customer);

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


    //todo:  refactor name to "writeCustomerOrder"

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



    public void removeCustomerOrderRow(String filepath, CustomerOrder customerOrder){


        String[] customerOrderValues = getCustomerOrderValues(customerOrder);

        System.out.println("CUSTOMER ORDER VALUES: " + Arrays.toString(customerOrderValues));

        /*
        //todo: make method that returns String[];
        String ordreId = String.valueOf(customerOrder.getOrderId());
        String customerName = customerOrder.getCustomerName();
        String customerMail = customerOrder.getCustomerMail();
        String customerPhoneNr = customerOrder.getCustomerNumber();
        String customerZipCode = customerOrder.getCustomerZipCode();
        String customerCity = customerOrder.getCustomerCity();
        */

        String tempFile = "temp.csv";
        File oldFile = new File(filepath);
        File newFile = new File(tempFile);
        String ordreNr = ""; String fullName = ""; String emailadress = ""; String phoneNr = "";
        String zipcode = ""; String city = "";


        try {
            FileWriter fw = new FileWriter(tempFile, true);
            BufferedWriter bw = new BufferedWriter(fw);
            PrintWriter pw = new PrintWriter(bw);
            scanner = new Scanner(new File(filepath));
            scanner.useDelimiter("[,\n]");




            System.out.println("TEMP FILE ; ADD SEP");
            pw.println("sep=,");

            System.out.println("TEMP FILE ; ADD HEADER");
            pw.println("ORDRE NR,NAVN,EPOST,TLF NR,POST NR,POSTSTED");


            while (scanner.hasNext()){
                ordreNr = scanner.next();
                fullName = scanner.next();
                emailadress = scanner.next();
                phoneNr = scanner.next();
                zipcode = scanner.next();
                city = scanner.next();


                if (!ordreNr.equals(customerOrderValues[0]) && !fullName.equals(customerOrderValues[1])
                        && !emailadress.equals(customerOrderValues[2]) && !zipcode.equals(customerOrderValues[3])
                        && !zipcode.equals(customerOrderValues[4]) && !city.equals(customerOrderValues[5])) {

                    pw.println(customerOrder.toCSVFormat());
                    System.out.println("WRITING ROW TO TMP FILE");
                } else System.out.println("IGNORING ROW");
            }
            scanner.close();
            pw.flush();
            pw.close();
            System.out.println(oldFile + " has been deleted!");
            oldFile.delete();
            File contactperson = new File(filepath);
            newFile.renameTo(contactperson);
            System.out.println("temp file has been renamed to " + newFile);
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println(e);
        }
    }

    private String[] getCustomerOrderValues(CustomerOrder customerOrder) {

        String ordreId = String.valueOf(customerOrder.getOrderId());
        String customerName = customerOrder.getCustomerName();
        String customerMail = customerOrder.getCustomerMail();
        String customerPhoneNr = customerOrder.getCustomerNumber();
        String customerZipCode = customerOrder.getCustomerZipCode();
        String customerCity = customerOrder.getCustomerCity();


        String customerOrderValues[] = new String[] {ordreId, customerName, customerMail, customerPhoneNr, customerZipCode, customerCity};

        return customerOrderValues;
    }


}
