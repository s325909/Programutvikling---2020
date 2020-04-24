package org.ccomp.user.Controllers;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import org.ccomp.fileHandling.ComponentOBJHandler;
import org.ccomp.model.component.Seat;
import org.ccomp.model.component.Spoiler;
import org.ccomp.model.component.SteeringWheel;
import org.ccomp.model.component.WheelRim;
import org.ccomp.model.component.engine.Engine;

import java.net.URL;
import java.util.ResourceBundle;


public class UserCartController implements Initializable {


    @FXML
    Button backBtn, userReg;

    @FXML
    Label viewCart;

    /*@FXML
    TableColumn<Object, String> value, value2, value3, value4, value5;

    @FXML
    TableView<Object> tableView;*/

    @Override
    public void initialize(URL location, ResourceBundle resources) {


        ViewProductController string = new ViewProductController();
       String test = string.toString();
        System.out.println("TEST: " + test);

        // viewCart.setText(string.toString());


        /*ViewProductController view = new ViewProductController();
        viewCart.setText(view.addCompnent());*/
       /*tableView.setEditable(true);
        engineTableCol(value, value2, value3, value4);
        seatTableCol(value, value2, value3, value4, value5);
        spoilerTableCol(value, value2, value3, value4, value5);
        steeringTableCol(value, value2, value3, value4, value5);
        rimTableCol(value, value2, value3, value4, value5);*/

    }

    public void transferMessage(String message) {
        viewCart.setText(message);
    }

   /* public void engineTableCol(TableColumn tCol1, TableColumn tCol2, TableColumn tCol3, TableColumn tCol4){
        tCol1 = new TableColumn<Engine, String>("Navn");
        tCol2 = new TableColumn<Engine, String>("Hestekrefter");
        tCol3 = new TableColumn<Engine, String>("Pris");
        tCol4 = new TableColumn<Engine, String>("Antall");

        tableView.getColumns().setAll(tCol1, tCol2, tCol3, tCol4);
    }

    public void seatTableCol(TableColumn tCol1, TableColumn tCol2, TableColumn tCol3, TableColumn tCol4, TableColumn tCol5) {
        tCol1 = new TableColumn<Seat, String>("Navn");
        tCol2 = new TableColumn<Seat, String>("Materiale");
        tCol3 = new TableColumn<Seat, String>("Farge");
        tCol4 = new TableColumn<Seat, String>("Pris");
        tCol5 = new TableColumn<Seat, String>("Antall");

        tableView.getColumns().setAll(tCol1, tCol2, tCol3, tCol4, tCol5);
    }

    public void spoilerTableCol(TableColumn tCol1, TableColumn tCol2, TableColumn tCol3, TableColumn tCol4, TableColumn tCol5){
        tCol1 = new TableColumn<Spoiler, String>("Navn");
        tCol2 = new TableColumn<Spoiler, String>("Monteringsside");
        tCol3 = new TableColumn<Spoiler, String>("Farge");
        tCol4 = new TableColumn<Spoiler, String>("Pris");
        tCol5 = new TableColumn<Spoiler, String>("Antall");

        tableView.getColumns().setAll(tCol1, tCol2, tCol3, tCol4, tCol5);
    }

    public void steeringTableCol(TableColumn tCol1, TableColumn tCol2, TableColumn tCol3, TableColumn tCol4, TableColumn tCol5){
        tCol1 = new TableColumn<SteeringWheel, String>("Navn");
        tCol2 = new TableColumn<SteeringWheel, String>("Materiale");
        tCol3 = new TableColumn<SteeringWheel, String>("Farge");
        tCol4 = new TableColumn<SteeringWheel, String>("Pris");
        tCol5 = new TableColumn<SteeringWheel, String>("Antall");

        tableView.getColumns().setAll(tCol1, tCol2, tCol3, tCol4, tCol5);
    }

    public void rimTableCol(TableColumn tCol1, TableColumn tCol2, TableColumn tCol3, TableColumn tCol4, TableColumn tCol5){
        tCol1 = new TableColumn<WheelRim, String>("Navn");
        tCol2 = new TableColumn<WheelRim, String>("Farge");
        tCol3 = new TableColumn<WheelRim, String>("Dimensjon");
        tCol4 = new TableColumn<WheelRim, String>("Pris");
        tCol5 = new TableColumn<WheelRim, String>("Antall");

        tableView.getColumns().setAll(tCol1, tCol2, tCol3, tCol4, tCol5);
    }


*/

    @FXML
    public void backToViewProd() {
        try{
            Stage stage = (Stage) backBtn.getScene().getWindow();
            URL url = getClass().getResource("/org/ccomp/user/user.fxml");
            FXMLLoader loader = new FXMLLoader(url);
            Parent root = loader.load();
            Scene scene = new Scene(root, 600, 500);
            stage.setScene(scene);
            stage.show();
        } catch (Exception e){
            e.printStackTrace();
        }
    }

    @FXML
    public void toUserReg() {
        try {
            Stage stage = (Stage) userReg.getScene().getWindow();
            URL url = getClass().getResource("/org/ccomp/user/userCart.fxml");
            FXMLLoader loader = new FXMLLoader(url);
            Parent root = loader.load();
            Scene scene = new Scene(root, 800, 600);
            stage.setScene(scene);
            stage.show();
        }

        catch (Exception e) {
            e.printStackTrace();
        }
    }


    public void setLable() {
        ViewProductController string = new ViewProductController();
        viewCart.setText(string.toString());
    }
}
