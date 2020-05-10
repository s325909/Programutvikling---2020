package org.ccomp.user.Controllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import org.ccomp.fileHandling.ComponentCSVHandler;
import org.ccomp.fileHandling.ComponentOBJHandler;
import org.ccomp.model.CompOrder;
import org.ccomp.model.component.CarComponent;
import org.ccomp.user.Customer;

import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;



public class UserRegController {

    @FXML
    TableView<CarComponent> ordedView;

    @FXML
    TableColumn<CarComponent, String> orderType, orderName;
    @FXML
    TableColumn<CarComponent, Double> orderPrice;
    @FXML
    TableColumn<CarComponent, Integer> orderQuantity;

    @FXML
    TextArea customerTxt;
    List<CarComponent> carComponents;

    @FXML
    TextField name, mail, phone, zip, city;


    @FXML
    Button register;

    @FXML
    AnchorPane contentcustomer, contentRecipt;

    @FXML
    Label countOrder;

    private Scene scene;
    private ComponentOBJHandler jobjHandler;


    UserViewCartController userViewCartController = new UserViewCartController();


    public void toRecipt() {

        System.out.println("SAVING COMP ORDER...");
        saveCompOrder(carComponents);

        try {
            URL url = getClass().getResource("/org/ccomp/user/order.fxml");


            FXMLLoader loader = new FXMLLoader(url);
            scene = contentcustomer.getScene();
            scene.setRoot(loader.load());

            //sende inn kolonnene og viewet
            ordedView = (TableView<CarComponent>) loader.getNamespace().get("ordedView");
            orderName = (TableColumn<CarComponent, String>) loader.getNamespace().get("orderName");
            orderType = (TableColumn<CarComponent, String>) loader.getNamespace().get("orderType");
            orderPrice = (TableColumn<CarComponent, Double>) loader.getNamespace().get("orderPrice");
            orderQuantity = (TableColumn<CarComponent, Integer>) loader.getNamespace().get("orderQuantity");

            countOrder = (Label) loader.getNamespace().get("countOrder");
            countOrder.setText(String.valueOf(carComponents.size()) + " produkter ");

            customerTxt = (TextArea) loader.getNamespace().get("customerTxt");
            String txt = getRegister();
            customerTxt.setText(txt);

            //legger inn i table
            ordedView.setItems(orderTable());

        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public ObservableList<CarComponent> orderTable() {
        ObservableList<CarComponent> carComps = FXCollections.observableArrayList();
        //  carComponents = retrievedCompMap.get("CarComp");

        for (CarComponent carComponent : getCarComponents()) {

            orderType.setCellValueFactory(new PropertyValueFactory<CarComponent, String>("compType"));
            orderName.setCellValueFactory(new PropertyValueFactory<CarComponent, String>("compType"));
            orderPrice.setCellValueFactory(new PropertyValueFactory<CarComponent, Double>("compPrice"));
            orderQuantity.setCellValueFactory(new PropertyValueFactory<CarComponent, Integer>("compQuantity"));
            carComps.add(carComponent);

        }

        return carComps;
    }


    private void saveCompOrder(List<CarComponent> componentsCart) {
        List<CompOrder> compOrderList = new ArrayList<>();
       // int orderNr = 2;
        int orderNr = getNextOrderNr();
        System.out.println("ORDER NR: " + orderNr);
        for (CarComponent carComponent : componentsCart) {
            CompOrder compOrder = new CompOrder(orderNr, carComponent);
            System.out.println(compOrder.toCSVFormat());
            compOrderList.add(compOrder);
        }

        ComponentCSVHandler csvHandler = new ComponentCSVHandler();

        csvHandler.writeCompOrder(compOrderList, "testCompOrders.csv");
    }

    private int getNextOrderNr() {
        ComponentCSVHandler csvHandler = new ComponentCSVHandler();

      //  String[] lastRow = csvHandler.readLast();

        String[] lastRow = csvHandler.readLastRow();

        if (lastRow[0] == null) {
            System.out.println("LAST ROW IS NULL!");
            lastRow[0] = "0";
        }

        System.out.println("RETREIVED LAST ROW: " + Arrays.toString(lastRow));

        int orderNr = Integer.parseInt(lastRow[0]);
        orderNr++;

        return orderNr;
    }

    public List<CarComponent> getCarComponents() {
        return carComponents;
    }

    public void setCarComponents(List<CarComponent> carComponents) {
        this.carComponents = carComponents;
    }

    public String getRegister() {
        Customer customer = new Customer(name.getText(), mail.getText(), phone.getText(), zip.getText(), city.getText());
        String txt = "Navn:   " + customer.getFullName() + "\n" +
                "E-post:   " + customer.getEmailadress() + "\n" +
                "Mobilnr:   " + customer.getNumber() + "\n" +
                "Postnr:   " + customer.getZipcode() + "\n" +
                "By:   " + customer.getCity();

        return txt;
    }
}

