package org.ccomp.admin;

import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import org.ccomp.fileHandling.ComponentOBJHandler;
import org.ccomp.model.Car;
import org.ccomp.model.component.CarComponent;
import org.ccomp.model.component.Seat;

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
    TableView<Seat> tableview;

    @FXML
    TableColumn<Seat, SimpleStringProperty> materiellColumn,nameColumn;




    private ComponentOBJHandler jobjHandler;
    private HashMap<String, List<CarComponent>> compMap, retrievedCompMap;
    private List<CarComponent> carComponents;




    @Override
    public void initialize(URL location, ResourceBundle resources) {

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


    public ObservableList<Seat> presentTable() {

       Seat seat;
       ObservableList<Seat> seats=  FXCollections.observableArrayList();

       jobjHandler = new ComponentOBJHandler();
       retrievedCompMap = jobjHandler.readComponent(retrievedCompMap);
       carComponents = retrievedCompMap.get("Seat");

        for (CarComponent carComponent : carComponents) {
            seat = (Seat) carComponent;
            materiellColumn.setCellValueFactory(new PropertyValueFactory<Seat,SimpleStringProperty>("material"));
            nameColumn.setCellValueFactory(new PropertyValueFactory<Seat,SimpleStringProperty>("compName"));




        }
        return seats;
    }









}
