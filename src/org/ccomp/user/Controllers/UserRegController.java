package org.ccomp.user.Controllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import org.ccomp.fileHandling.ComponentCSVHandler;
import org.ccomp.fileHandling.ComponentOBJHandler;
import org.ccomp.model.CompOrder;
import org.ccomp.model.CustomerOrder;
import org.ccomp.model.Validation;
import org.ccomp.model.component.CarComponent;
import org.ccomp.user.Customer;

import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;


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
    Label customerTxt1, customerTxt2;
    List<CarComponent> carComponents;

    @FXML
    TextField name, mail, phone, zip, city;


    @FXML
    Button register, cancel;

    @FXML
    AnchorPane contentcustomer, contentRecipt;

    @FXML
    Label countOrder;

    private Scene scene;
    private ComponentOBJHandler jobjHandler;

    private int orderNr;

    private boolean cancelOrder;

    UserViewCartController userViewCartController = new UserViewCartController();


    public void toRecipt() {

        System.out.println("SAVING COMP ORDER...");


        emptyFields();

        if (!validationUser().isEmpty()) {
            if (!name.getText().isEmpty() || !mail.getText().isEmpty() || !phone.getText().isEmpty() || !zip.getText().isEmpty() || !city.getText().isEmpty()) {
                alert(validationUser());
            }

        } else {
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
                countOrder.setText((carComponents.size()) + " produkter ");

                customerTxt1 = (Label) loader.getNamespace().get("customerTxt1");
                customerTxt2 = (Label) loader.getNamespace().get("customerTxt2");

                String txt1 = "Navn: \n" +
                        "E-post: \n" +
                        "Mobilnr: \n" +
                        "Postnr: \n" +
                        "By: ";
                customerTxt1.setText(txt1);

                String txt2 = getRegister().getFullName() + "\n" +
                        getRegister().getEmailadress() + "\n" +
                        getRegister().getNumber() + "\n" +
                        getRegister().getZipcode() + "\n" +
                        getRegister().getCity() + "\n";
                customerTxt2.setText(txt2);

                //legger inn i table
                ordedView.setItems(orderTable());

                orderNr = getNextOrderNr();

                System.out.println("CUSTOMER ORDER NR: " + orderNr);

               // CustomerOrder customerOrder = new CustomerOrder(orderNr, name.getText(), mail.getText(), phone.getText(), zip.getText(), city.getText());
                saveCompOrder(carComponents);

                CustomerOrder customerOrder = new CustomerOrder(orderNr, getRegister());
                saveCustomerOrder(customerOrder);

            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    @FXML
    public void cancelOrder() {

       // cancelOrder;


        if (cancelOrder) {

            try {
                Stage stage = (Stage) cancel.getScene().getWindow();
                URL url = getClass().getResource("/org/ccomp/user/user.fxml");
                FXMLLoader loader = new FXMLLoader(url);
                Parent root = loader.load();
                Scene scene = new Scene(root, 430, 430);
                stage.setScene(scene);
                stage.show();
            } catch (Exception e) {
                e.printStackTrace();
            }

        }


        if (!cancelOrder) cancel();
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
        // int orderNr = getNextOrderNr();
        System.out.println("COMP ORDER NR: " + orderNr);
        for (CarComponent carComponent : componentsCart) {
            CompOrder compOrder = new CompOrder(orderNr, carComponent);
            System.out.println(compOrder.toCSVFormat());
            compOrderList.add(compOrder);
        }

        ComponentCSVHandler csvHandler = new ComponentCSVHandler();

        csvHandler.writeCompOrder(compOrderList);
    }


    private void saveCustomerOrder(CustomerOrder customerOrder) {
        ComponentCSVHandler csvHandler = new ComponentCSVHandler();

        csvHandler.writeCustomerOrder(customerOrder);
    }


    private int getNextOrderNr() {
        ComponentCSVHandler csvHandler = new ComponentCSVHandler();

        //  String[] lastRow = csvHandler.readLast();

        String[] lastRow = csvHandler.readLastRow();

        if (lastRow == null) {
            System.out.println("LAST ROW IS NULL!");
            lastRow = new String[]{"0"};
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

    public Customer getRegister() {
        Customer customer = new Customer(name.getText(), mail.getText(), phone.getText(), zip.getText(), city.getText());
        /*String txt = "Navn:   " + customer.getFullName() + "\n" +
                "E-post:   " + customer.getEmailadress() + "\n" +
                "Mobilnr:   " + customer.getNumber() + "\n" +
                "Postnr:   " + customer.getZipcode() + "\n" +
                "By:   " + customer.getCity();*/

        return customer;
    }

    //validation
    public String validationUser() {
        return Validation.valUser(name.getText(), mail.getText(), phone.getText(), zip.getText(),
                city.getText());
    }

    public static void alert(String msg) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("CCOMP");
        alert.setHeaderText("Feil!");
        alert.setContentText(msg);
        alert.showAndWait();

    }

    public void emptyFields() {
        if (name.getText().isEmpty() && mail.getText().isEmpty() && phone.getText().isEmpty() && zip.getText().isEmpty() && city.getText().isEmpty()) {
            alert("Fyll inn alle felt!");
        }
    }

    public void cancel() {

        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setContentText("Du er i ferd med å avbryte bestillingen din.");
        alert.setHeaderText("Er du sikker på at du vil kansellere?");

        ButtonType cancelButton = new ButtonType("Avbryt", ButtonBar.ButtonData.CANCEL_CLOSE);
        alert.getDialogPane().getButtonTypes().add(cancelButton);

        Optional<ButtonType> result = alert.showAndWait();

        if (result.isPresent() && result.get() == ButtonType.OK) {
            cancelOrder = true;
            cancelOrder();
        }
    }
}

