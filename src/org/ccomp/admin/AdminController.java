package org.ccomp.admin;

import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.control.cell.TextFieldTreeTableCell;
import javafx.stage.Stage;
import org.ccomp.fileHandling.ComponentOBJHandler;
import org.ccomp.model.Car;
import org.ccomp.model.component.CarComponent;
import org.ccomp.model.component.Seat;
import org.ccomp.model.component.Spoiler;
import org.ccomp.model.component.SteeringWheel;

import java.net.URL;
import java.util.HashMap;
import java.util.List;
import java.util.ResourceBundle;

public class AdminController implements Initializable {

    @FXML
    Button backBtn;

    @FXML
    Button addComp;

    @FXML
    TableView<Seat> seatView;
    @FXML
    TableView<Spoiler> spoilerView;
    @FXML
    TableView<SteeringWheel> sWheelView;

    @FXML
    TableColumn<Seat, SimpleStringProperty> nameSeatColum,materiellColum,colorSeatColum,seatPriceColum,quantitySeatColum;
    @FXML
    TableColumn<Spoiler, SimpleStringProperty> nameSpoilerColum,colorSpoilerColum,sideSpoilerColum,priceSpoilerColum,quantitySpoilerColum;

    @FXML
    TableColumn<SteeringWheel,SimpleStringProperty> nameSWheelColum,materiellSWeel,colorSWheelColum,priceSWeelColum,quantitySWeelColum;





    private ComponentOBJHandler jobjHandler;
    private HashMap<String, List<CarComponent>> compMap, retrievedCompMap;
    private List<CarComponent> carComponents;



    @Override
    public void initialize(URL location, ResourceBundle resources) {

        seatView.setItems(seatTable());
        spoilerView.setItems(spoilerTable());
        sWheelView.setItems(sWheelTable());
        seatView.setEditable(true);
        spoilerView.setEditable(true);
        sWheelView.setEditable(true);




    }

    /**
     * Method that opens main.fxml when the Back Button is clicked
     */
    @FXML
    public void backToMain() {
        try {
            Stage stage = (Stage) backBtn.getScene().getWindow();
            URL url = getClass().getResource("/org/ccomp/main/main.fxml");
            FXMLLoader loader = new FXMLLoader(url);
            Parent root = loader.load();
            Scene scene = new Scene(root, 500, 500);
            stage.setScene(scene);
            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    public void toAddComponent() {
        try {
            Stage stage = (Stage) addComp.getScene().getWindow();
            URL url = getClass().getResource("/org/ccomp/admin/addComponent.fxml");
            FXMLLoader loader = new FXMLLoader(url);
            Parent root = loader.load();
            Scene scene = new Scene(root, 600, 500);
            stage.setScene(scene);
            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public ObservableList<Seat> seatTable() {

       Seat seat;
       ObservableList<Seat> seats=  FXCollections.observableArrayList();

       jobjHandler = new ComponentOBJHandler();
       retrievedCompMap = jobjHandler.readComponent(retrievedCompMap);
       carComponents = retrievedCompMap.get("Seat");

        for (CarComponent carComponent : carComponents) {
            seat = (Seat) carComponent;
            nameSeatColum.setCellValueFactory(new PropertyValueFactory<Seat,SimpleStringProperty>("compName"));
            materiellColum.setCellValueFactory(new PropertyValueFactory<Seat,SimpleStringProperty>("material"));
            colorSeatColum.setCellValueFactory(new PropertyValueFactory<Seat,SimpleStringProperty>("color"));
            seatPriceColum.setCellValueFactory(new PropertyValueFactory<Seat,SimpleStringProperty>("compPrice"));
            quantitySeatColum.setCellValueFactory(new PropertyValueFactory<Seat,SimpleStringProperty>("compQuantity"));
            seats.add(seat);
        }

        return seats;
    }

    public ObservableList<Spoiler> spoilerTable(){

        Spoiler spoiler;
        ObservableList<Spoiler> spoilers = FXCollections.observableArrayList();
        jobjHandler = new ComponentOBJHandler();
        retrievedCompMap = jobjHandler.readComponent(retrievedCompMap);
        carComponents = retrievedCompMap.get("Spoiler");

        for (CarComponent carComponent : carComponents) {
            spoiler = (Spoiler) carComponent;
            nameSpoilerColum.setCellValueFactory(new PropertyValueFactory<Spoiler,SimpleStringProperty>("compName"));
           // colorSpoilerColum.setCellValueFactory(new PropertyValueFactory<Spoiler,SimpleStringProperty>("compName"));
            sideSpoilerColum.setCellValueFactory(new PropertyValueFactory<Spoiler,SimpleStringProperty>("spoilerSide"));
            priceSpoilerColum.setCellValueFactory(new PropertyValueFactory<Spoiler,SimpleStringProperty>("compPrice"));
            quantitySpoilerColum.setCellValueFactory(new PropertyValueFactory<Spoiler,SimpleStringProperty>("compQuantity"));
            spoilers.add(spoiler);
        }

        return spoilers;
    }


    public ObservableList<SteeringWheel> sWheelTable(){

        SteeringWheel steeringWheel;
        ObservableList<SteeringWheel> steeringWheels = FXCollections.observableArrayList();
        jobjHandler = new ComponentOBJHandler();
        retrievedCompMap = jobjHandler.readComponent(retrievedCompMap);
        carComponents = retrievedCompMap.get("SteeringWheel");


        for (CarComponent carComponent : carComponents) {
            steeringWheel = (SteeringWheel) carComponent;
            nameSWheelColum.setCellValueFactory(new PropertyValueFactory<SteeringWheel,SimpleStringProperty>("compName"));
            colorSWheelColum.setCellValueFactory(new PropertyValueFactory<SteeringWheel,SimpleStringProperty>("compName"));
            materiellSWeel.setCellValueFactory(new PropertyValueFactory<SteeringWheel,SimpleStringProperty>("spoilerSide"));
            priceSWeelColum.setCellValueFactory(new PropertyValueFactory<SteeringWheel,SimpleStringProperty>("compPrice"));
            quantitySWeelColum.setCellValueFactory(new PropertyValueFactory<SteeringWheel,SimpleStringProperty>("compQuantity"));
            steeringWheels.add(steeringWheel);
        }

        return steeringWheels;
    }






   /* public void changeseatTable(TableColumn.CellEditEvent<Seat, SimpleStringProperty> seatSimpleStringPropertyCellEditEvent) {
        Seat seatselected = seatView.getSelectionModel().getSelectedItem();
        seatselected.setCompName(seatSimpleStringPropertyCellEditEvent.getNewValue().toString());


    }*/
}
