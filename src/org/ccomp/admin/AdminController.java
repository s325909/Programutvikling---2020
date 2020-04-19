package org.ccomp.admin;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;
import javafx.beans.value.WritableValue;
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
import javafx.stage.Stage;
import org.ccomp.fileHandling.ComponentOBJHandler;
import org.ccomp.model.component.*;
import org.ccomp.model.component.engine.Engine;

import java.net.URL;
import java.util.HashMap;
import java.util.List;
import java.util.ResourceBundle;

public class AdminController implements Initializable {

    Seat seat;
    int row;


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
    TableView<WheelRim> wheelRimView;
    @FXML
    TableView<Engine> engineView;

    @FXML
    TableColumn<Seat, SimpleStringProperty> nameSeatColum,colorSeatColum,seatPriceColum,quantitySeatColum;
    @FXML
    TableColumn<Seat,String>materiellColum;


    @FXML
    TableColumn<Spoiler, SimpleStringProperty> nameSpoilerColum,colorSpoilerColum,sideSpoilerColum,priceSpoilerColum,quantitySpoilerColum;

    @FXML
    TableColumn<SteeringWheel,SimpleStringProperty> nameSWheelColum,materiellSWeel,colorSWheelColum,priceSWeelColum,quantitySWeelColum;

    @FXML
    TableColumn<WheelRim,SimpleStringProperty> nameWheelRimColum,dimensionWheelRim,colorWheelRim,priceWheelRim,quantityWheelRim;

    @FXML
    TableColumn<Engine, SimpleStringProperty> engintypeColum,nameEngineColum,horsepowerColum, priceEngineColum, quantityEngineColum;

    private ComponentOBJHandler jobjHandler;
    private HashMap<String, List<CarComponent>> compMap, retrievedCompMap;
    private List<CarComponent> carComponents;
    private Object TableColumn;


    @Override
    public void initialize(URL location, ResourceBundle resources) {

        seatView.setItems(seatTable());
        spoilerView.setItems(spoilerTable());
        sWheelView.setItems(sWheelTable());
        wheelRimView.setItems(wheelRTable());

        spoilerView.setEditable(true);
        sWheelView.setEditable(true);
        wheelRimView.setEditable(true);

      editSeaTable();
      seatView.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);



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
           //materiellColum.setCellValueFactory(new PropertyValueFactory<Seat,SimpleStringProperty>("material"));
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

       // steeringWheel = (SteeringWheel) carComponents.get(0);
        //steeringWheel.setSteeringWheelMaterial(materiellColum.getText());

       // carComponents.set(0, steeringWheel);


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

    public ObservableList<WheelRim> wheelRTable(){

        WheelRim wheelRim;
        ObservableList<WheelRim> wheelRims = FXCollections.observableArrayList();
        jobjHandler = new ComponentOBJHandler();
        retrievedCompMap = jobjHandler.readComponent(retrievedCompMap);
        carComponents = retrievedCompMap.get("WheelRim");


        for (CarComponent carComponent : carComponents) {
            wheelRim = (WheelRim) carComponent;
            nameWheelRimColum.setCellValueFactory(new PropertyValueFactory<WheelRim,SimpleStringProperty>("compName"));
            //dimensionWheelRim.setCellValueFactory(new PropertyValueFactory<SteeringWheel,SimpleStringProperty>("compName"));
            wheelRims.add(wheelRim);


        }

        return wheelRims;
    }


    public void editSeaTable(){

        seatView.setEditable(true);
        seat = (Seat) carComponents.get(row);
        

       // seat = (SteeringWheel) carComponents.get(0);
        //steeringWheel.setSteeringWheelMaterial(materiellColum.getText());

        // carComponents.set(,0 steeringWheel);


        materiellColum.setCellFactory(new PropertyValueFactory("Materiale"));
        materiellColum.setCellFactory(TextFieldTableCell.forTableColumn());
        materiellColum.setOnEditCommit((TableColumn.CellEditEvent<Seat, String> t) -> {


            t.getTableView().getItems();
            row = t.getTablePosition().getRow();
            String value =  t.getNewValue();
            System.out.println(value);

            ((Seat) carComponents.get(row)).setMaterial(value);

    //        seat = (Seat) carComponents.get(t.getTablePosition().getRow());
  //          seat.setMaterial(value);
//            carComponents.set(t.getTablePosition().getRow(), seat);

            //carComponents.set(row,value);
            ( t.getTableView().getItems().get(
                    t.getTablePosition().getRow())
            ).setMaterial(t.getNewValue()
            );



        });

        // seat.setMaterial(materiellColum.getText());

    }

    @FXML
    public void deleteSelectedRow(){

       /* ObservableList<Seat> selectedRow,allseats;
        allseats= seatView.getItems();

          selectedRow = seatView.getSelectionModel().getSelectedItem();

        for (Seat seat : selectedRow){

            allseats.remove(seat);
        }
*/


    }


    /*public ObservableList<Engine> engineTable(){

        Engine engine;
        ObservableList<Engine> engines = FXCollections.observableArrayList();
        jobjHandler = new ComponentOBJHandler();
        retrievedCompMap = jobjHandler.readComponent(retrievedCompMap);
        carComponents = retrievedCompMap.get("Engine");
        for (CarComponent carComponent : carComponents) {
            engine = (Engine) carComponent;
            engintypeColum.setCellValueFactory(new PropertyValueFactory<Engine,SimpleStringProperty>("enginetype"));
            engines.add(engine);
        }
        return engines;


    }*/

}
