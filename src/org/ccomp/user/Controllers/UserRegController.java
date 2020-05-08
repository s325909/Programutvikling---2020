package org.ccomp.user.Controllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import org.ccomp.fileHandling.ComponentOBJHandler;
import org.ccomp.model.Car;
import org.ccomp.model.CarComp;
import org.ccomp.model.component.CarComponent;
import org.ccomp.user.Customer;

import java.net.URL;
import java.util.HashMap;
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
    TextArea orderTxt,customerTxt;

    List<CarComponent> carComponents;

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



            //legger inn i table
            ordedView.setItems(orderTable());






        } catch (Exception e) {
            e.printStackTrace();
        }





    }


    public ObservableList<CarComponent> orderTable() {
        ObservableList<CarComponent> carComps = FXCollections.observableArrayList();
        //  carComponents = retrievedCompMap.get("CarComp");


        for (CarComponent carComponent :  getCarComponents()) {

            orderType.setCellValueFactory(new PropertyValueFactory<CarComponent, String>("compType"));
            orderName.setCellValueFactory(new PropertyValueFactory<CarComponent, String>("compType"));
            orderPrice.setCellValueFactory(new PropertyValueFactory<CarComponent, Double>("compPrice"));
            orderQuantity.setCellValueFactory(new PropertyValueFactory<CarComponent, Integer>("compQuantity"));
            carComps.add(carComponent);



        }

        return carComps;
    }

    



    public List<CarComponent> getCarComponents() {
        return carComponents;
    }

    public void setCarComponents(List<CarComponent> carComponents) {
        this.carComponents = carComponents;
    }



}

