package org.ccomp.user.Controllers;

import javafx.beans.property.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
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
import org.ccomp.model.component.*;
import org.ccomp.model.component.engine.Engine;

import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


public class UserViewCartController {

    private ComponentOBJHandler jobjHandler;

    //  private OLDComponentOBJHandlerOLD jobjHandler;
    private HashMap<String, List<CarComponent>> compMap, retrievedCompMap;
    private List<CarComponent> carComponents;


    @FXML
    AnchorPane contentProducts, contentCart;

    @FXML
    Label viewCart,numberofProduct;

    /*@FXML
    TableColumn<Object, String> value, value2, value3, value4, value5;

    @FXML
    TableView<Object> tableView;*/

    @FXML
    Button backBtnView, backBtnCart, addToCart, userReg,toTheChart;

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

    @FXML
    TableView<CarComponent> cartTable;

    @FXML
    TableColumn<CarComponent, String> compNameColumn, compTypeColumn;

    @FXML
    TableColumn<CarComponent, Double> compPriceColumn;

    @FXML
    TableColumn<CarComponent, Integer> compQuantityColumn;


    /*@FXML
    TableColumn<SteeringWheel, String> nameSWheelColum, materiellSWeel, colorSWheelColum, priceSWeelColum, quantitySWeelColum;
    @FXML
    TableColumn<WheelRim, String> nameWheelRimColum, dimensionWheelRim, colorWheelRim, priceWheelRim, quantityWheelRim;
    @FXML
    TableColumn<Engine, String> engintypeColum, nameEngineColum, horsepowerColum, priceEngineColum, quantityEngineColum;*/

    private Scene scene;
    private static List<CarComponent> componentsCart = new ArrayList<>();


    @FXML
    public void initialize() {
        jobjHandler = new ComponentOBJHandler();
        // jobjHandler = new OLDComponentOBJHandlerOLD();
        retrievedCompMap = jobjHandler.readComponent(retrievedCompMap);

        //  customerSeatView.setItems(seatTable());
        //  customerSpoilerView.setItems(spoilerTable());

       /*tableView.setEditable(true);
        engineTableCol(value, value2, value3, value4);
        seatTableCol(value, value2, value3, value4, value5);
        spoilerTableCol(value, value2, value3, value4, value5);
        steeringTableCol(value, value2, value3, value4, value5);
        rimTableCol(value, value2, value3, value4, value5);*/


        componentsCart = getComponentsCart();
        System.out.println("(INIT) COMP CART: " + componentsCart.size());

        System.out.println("TESTING");
    }

    @FXML
    public void backToMain() {
        try {
            Stage stage = (Stage) backBtnView.getScene().getWindow();
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

        System.out.println("TO USER CART");


        if (componentsCart == null) componentsCart = new ArrayList<>();

        //todo: fjernes etter testing
       /* StringProperty compNameProperty = new SimpleStringProperty("COMP NAME");
        DoubleProperty compPriceProperty = new SimpleDoubleProperty(230);
        IntegerProperty compQuantityProperty = new SimpleIntegerProperty(15);


        componentsCart.add(new CarComponent("COMP TYPE", compNameProperty, compPriceProperty, compQuantityProperty));
        */
        try {
            URL url = getClass().getResource("/org/ccomp/user/userCart.fxml");


            FXMLLoader loader = new FXMLLoader(url);
            scene = contentProducts.getScene();
            scene.setRoot(loader.load());


            cartTable = (TableView<CarComponent>) loader.getNamespace().get("cartTable");

            compNameColumn = (TableColumn<CarComponent, String>) loader.getNamespace().get("compNameColumn");
            compTypeColumn = (TableColumn<CarComponent, String>) loader.getNamespace().get("compTypeColumn");
            compPriceColumn = (TableColumn<CarComponent, Double>) loader.getNamespace().get("compPriceColumn");
            compQuantityColumn = (TableColumn<CarComponent, Integer>) loader.getNamespace().get("compQuantityColumn");

            cartTable.setItems(cartTable());

           // cartProduct.setText(componentsCart.get(0).getCompName());

            //orderdPrductCar(); kall på metode for å se carten

            //  FXMLLoader loader = new FXMLLoader(url);
            //  AnchorPane newCartScene = (AnchorPane) loader.load();
            //  contentProducts.getChildren().setAll(newCartScene.getChildren());
            // scene.setRoot(screenMap.get("userCart"));
        } catch (Exception e) {
            e.printStackTrace();
        }


    }



    @FXML
    public void backToViewProd() {

        System.out.println("TO VIEW PRODUCTS");


        componentsCart = getComponentsCart();

        try {
            URL url = getClass().getResource("/org/ccomp/user/viewProduct.fxml");
            scene = contentCart.getScene();
            scene.setRoot(FXMLLoader.load(url));
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @FXML
    public void toUserReg() {
        try {
            Stage stage = (Stage) userReg.getScene().getWindow();
            URL url = getClass().getResource("/org/ccomp/user/userReg.fxml");
            FXMLLoader loader = new FXMLLoader(url);
            Parent root = loader.load();
            Scene scene = new Scene(root, 800, 600);
            stage.setScene(scene);
            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
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

        if (carComponents == null) {
            carComponents = retrievedCompMap.get("Spoiler");
        }

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


    public void viewTheComponents() {

        if (seatPane.isExpanded()) {

            customerSeatView.setItems(seatTable());
            customerSeatView.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);

        }

        if (spoilerPane.isExpanded()) {
            customerSpoilerView.setItems(spoilerTable());

        }


    }

    public void countProducts() {

        numberofProduct.setText("Antall produkter valgt:" + String.valueOf(componentsCart.size()));

    }


    /*public void orderdPrductCar() {

       // Seat seat = (Seat) componentsCart.get(0);

        String s = "";
        for (CarComponent carComponent : componentsCart) {
            s += carComponent.toCSVFormat() + "\n";
        }

        cartProduct.setText(s);
    }
*/
    @FXML
    public void chooseProduct() {

        int index = customerSeatView.getSelectionModel().getSelectedIndex();
        int spoilerindex = customerSpoilerView.getSelectionModel().getSelectedIndex();


        if (customerSeatView.getSelectionModel().isSelected(index)) {
            Seat item = customerSeatView.getItems().get(index);
            StringProperty compNameProperty = new SimpleStringProperty(item.getCompName());
            StringProperty compColorProperty = new SimpleStringProperty(item.getColor());
            StringProperty compMaterialProperty = new SimpleStringProperty(item.getColor());
            DoubleProperty compPriceProperty = new SimpleDoubleProperty(item.getCompPrice());
            IntegerProperty compQuantityProperty = new SimpleIntegerProperty(item.getCompQuantity());
            carComponents = getComponentsCart();
            componentsCart.add(new Seat(compNameProperty, compPriceProperty, compQuantityProperty, compColorProperty, compMaterialProperty));
            countProducts();
            // cartProduct.setText( "Navn" + compNameProperty  );
        }

        if (customerSpoilerView.getSelectionModel().isSelected(spoilerindex)) {
            Spoiler item = customerSpoilerView.getItems().get(spoilerindex);
            StringProperty compNameProperty = new SimpleStringProperty(item.getCompName());
            StringProperty compSpoilerSideProperty = new SimpleStringProperty(item.getSpoilerSide());
            DoubleProperty compPriceProperty = new SimpleDoubleProperty(item.getCompPrice());
            IntegerProperty compQuantityProperty = new SimpleIntegerProperty(item.getCompQuantity());
            carComponents = getComponentsCart();
            componentsCart.add(new Spoiler(compNameProperty, compPriceProperty, compQuantityProperty,compSpoilerSideProperty));
            countProducts();

        }
    }


    //for å hente ut i tableview
    public ObservableList<CarComponent> cartTable() {
       // CarComponent carComp;
        ObservableList<CarComponent> carComps = FXCollections.observableArrayList();
        carComponents = retrievedCompMap.get("CarComp");


        for (CarComponent carComponent : componentsCart) {

            compNameColumn.setCellValueFactory(new PropertyValueFactory<CarComponent, String>("compType"));
            compTypeColumn.setCellValueFactory(new PropertyValueFactory<CarComponent, String>("compType"));
            compPriceColumn.setCellValueFactory(new PropertyValueFactory<CarComponent, Double>("compPrice"));
            compQuantityColumn.setCellValueFactory(new PropertyValueFactory<CarComponent, Integer>("compQuantity"));
            carComps.add(carComponent);

        }
            return carComps;

    }

    /*public void orderdCart() {
        for (CarComponent carComponent : componentsCart) {


        }
    }*/



    public static List<CarComponent> getComponentsCart() {
        return componentsCart;
    }

    public static void setComponentsCart(List<CarComponent> componentsCart) {
        UserViewCartController.componentsCart = componentsCart;

    }

}