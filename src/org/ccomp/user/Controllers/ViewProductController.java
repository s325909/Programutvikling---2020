package org.ccomp.user.Controllers;

import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import org.ccomp.fileHandling.ComponentOBJHandler;
import org.ccomp.model.component.*;
import org.ccomp.model.component.engine.Engine;

import java.awt.print.Book;
import java.net.URL;
import java.util.HashMap;
import java.util.List;
import java.util.ResourceBundle;



public class ViewProductController implements Initializable {

    private ComponentOBJHandler jobjHandler;
    private HashMap<String, List<CarComponent>> compMap, retrievedCompMap;
    private List<CarComponent> carComponents;

    Seat seat;
    Spoiler spoiler;


    @FXML
    Button backBtn, addComp;

    @FXML
    TitledPane seatPane, spoilerPane;

    @FXML
    TableView<Seat> customerSeatView;
    @FXML
    TableView<Spoiler> customerSpoilerView;
    @FXML
    TableView<SteeringWheel> CustomersWheelView;
    @FXML
    TableView<WheelRim> customerWheelRimView;
    @FXML
    TableView<Engine> customorEngineView;
    @FXML
    TableColumn<Seat, String> nameSeatColum, materiellColum, colorSeatColum;
    @FXML
    TableColumn<Seat, Double> seatPriceColum;
    @FXML
    TableColumn<Seat, Integer> quantitySeatColum;
    @FXML
    TableColumn<Spoiler, String> nameSpoilerColum, colorSpoilerColum, sideSpoilerColum;
    @FXML
    TableColumn<Spoiler, Double> priceSpoilerColum;
    @FXML
    TableColumn<Spoiler, Integer> quantitySpoilerColum;
    /*@FXML
    TableColumn<SteeringWheel, String> nameSWheelColum, materiellSWeel, colorSWheelColum, priceSWeelColum, quantitySWeelColum;
    @FXML
    TableColumn<WheelRim, String> nameWheelRimColum, dimensionWheelRim, colorWheelRim, priceWheelRim, quantityWheelRim;
    @FXML
    TableColumn<Engine, String> engintypeColum, nameEngineColum, horsepowerColum, priceEngineColum, quantityEngineColum;*/

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        jobjHandler = new ComponentOBJHandler();
        retrievedCompMap = jobjHandler.readComponent(retrievedCompMap);

        customerSeatView.setItems(seatTable());
        customerSpoilerView.setItems(spoilerTable());

    }


    public ObservableList<Seat> seatTable() {

        Seat seat;
        ObservableList<Seat> seats = FXCollections.observableArrayList();

        //Henter dem først ut her

        carComponents = retrievedCompMap.get("Seat");

        //Presenter objektene i tableview ved sette inn riktige verdier til riktig tablecolonne

        for (CarComponent carComponent : carComponents) {
            seat = (Seat) carComponent;
            nameSeatColum.setCellValueFactory(new PropertyValueFactory<Seat, String>("compName"));
            materiellColum.setCellValueFactory(new PropertyValueFactory<Seat, String>("material"));
            colorSeatColum.setCellValueFactory(new PropertyValueFactory<Seat, String>("color"));
            seatPriceColum.setCellValueFactory(new PropertyValueFactory<Seat, Double>("compPrice"));
            quantitySeatColum.setCellValueFactory(new PropertyValueFactory<Seat, Integer>("compQuantity"));
            seats.add(seat);
        }

        return seats;
    }


    public ObservableList<Spoiler> spoilerTable() {

        Spoiler spoiler;
        ObservableList<Spoiler> spoilers = FXCollections.observableArrayList();

        carComponents = retrievedCompMap.get("Spoiler");

        for (CarComponent carComponent : carComponents) {
            spoiler = (Spoiler) carComponent;
            nameSpoilerColum.setCellValueFactory(new PropertyValueFactory<Spoiler, String>("compName"));
            colorSpoilerColum.setCellValueFactory(new PropertyValueFactory<Spoiler, String>("compName"));
            sideSpoilerColum.setCellValueFactory(new PropertyValueFactory<Spoiler, String>("spoilerSide"));
            priceSpoilerColum.setCellValueFactory(new PropertyValueFactory<Spoiler, Double>("compPrice"));
            quantitySpoilerColum.setCellValueFactory(new PropertyValueFactory<Spoiler, Integer>("compQuantity"));
            spoilers.add(spoiler);
        }

        return spoilers;
    }


    @FXML
    public void backToMain() {
        try {
            Stage stage = (Stage) backBtn.getScene().getWindow();
            URL url = getClass().getResource("/org/ccomp/user/user.fxml");
            FXMLLoader loader = new FXMLLoader(url);
            Parent root = loader.load();
            Scene scene = new Scene(root, 600, 500);
            stage.setScene(scene);
            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    public void toUserCart() {
        try {
            Stage stage = (Stage) addComp.getScene().getWindow();
            URL url = getClass().getResource("/org/ccomp/user/userCart.fxml");
            FXMLLoader loader = new FXMLLoader(url);
            Parent root = loader.load();
            Scene scene = new Scene(root, 800, 600);
            stage.setScene(scene);
            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    public String addCompnent() {
        String txt = "";


        if (seatPane.isExpanded()) {
            int index = customerSeatView.getSelectionModel().getSelectedIndex();
            Seat item = customerSeatView.getItems().get(index);
            txt =  "Navn: " + item.getCompName() + "\n " +
                    "Farge: " + item.getColor();
        }

        if (spoilerPane.isExpanded()) {
            int index = customerSpoilerView.getSelectionModel().getSelectedIndex();
            Spoiler item = customerSpoilerView.getItems().get(index);
            txt = "Navn: " + item.getCompName() + "\n" +
                    "Side: " + item.getSpoilerSide();
        }
        toUserCart();
        return txt ;

    }


}










        //TableColumn col = pos.getTableColumn();

// this gives the value in the selected cell:

       // String data = (String) col.getColumns(item).getValue();






