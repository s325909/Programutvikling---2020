package org.ccomp.user.Controllers;

import com.sun.tools.internal.xjc.reader.xmlschema.bindinfo.BIConversion;
import javafx.beans.property.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.NodeOrientation;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import org.ccomp.fileHandling.ComponentCSVHandler;
import org.ccomp.fileHandling.ComponentOBJHandler;
import org.ccomp.model.CustomerOrder;
import org.ccomp.model.component.*;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;


public class UserViewCartController {

    private ComponentOBJHandler jobjHandler;

    //  private OLDComponentOBJHandlerOLD jobjHandler;
    private HashMap<String, List<CarComponent>> compMap, retrievedCompMap;
    private List<CarComponent> carComponents;
    private String selectedcartype;


    CarComponent carComponent;

    UserController userController = new UserController();


    @FXML
    AnchorPane contentProducts, contentCart;

    @FXML
    Label viewCart, numberofProduct, sumText;

    /*@FXML
    TableColumn<Object, String> value, value2, value3, value4, value5;

    @FXML
    TableView<Object> tableView;*/

    @FXML
    Button backBtnView, backBtnCart, addToCart, userReg, toTheChart, deleteRow, deleteAll;
    @FXML
    TitledPane seatPane, spoilerPane,enginePane,sWheelPane,wheelRimPane;

    //Engine
    @FXML
    TableView<Engine> customorEngineView;
    @FXML
    TableColumn<Engine,String>nameEngineColum,engineTypeColum;
    @FXML
    TableColumn<Engine,Integer>horsepowerColum,quantityEngineColum;
    @FXML
    TableColumn<Engine,Double>priceEngineColm;


    //Seat
    @FXML
    TableView<Seat> customerSeatView;
    @FXML
    TableColumn<Seat, String> nameSeatColum, materiellColum, colorSeatColum;
    @FXML
    TableColumn<Seat, Double> seatPriceColum;
    @FXML
    TableColumn<Seat, Integer> quantitySeatColum;

    //Spoiler
    @FXML
    TableView<Spoiler> customerSpoilerView;
    @FXML
    TableColumn<Spoiler, String> nameSpoilerColum, colorSpoilerColum, sideSpoilerColum;
    @FXML
    TableColumn<Spoiler, Double> priceSpoilerColum;
    @FXML
    TableColumn<Spoiler, Integer> quantitySpoilerColum;

    //SteeringWheel
    @FXML
    TableView<SteeringWheel> customerSWheelView;
    @FXML
    TableColumn<SteeringWheel,String> nameSWheelColum,colorSwheelColum,materiellSwheelColum;
    @FXML
    TableColumn<SteeringWheel,Double> priceSwheelColum;
    @FXML
    TableColumn<SteeringWheel,Integer>quantitySwheel;

    //WheelRim
    @FXML
    TableView<WheelRim> customerWheelRimView;
    @FXML
    TableColumn<WheelRim,String>nameWheelRim,colorWheelRim,dimensionWheelRimColum;
    @FXML
    TableColumn<WheelRim,Double>priceWeelRim;
    @FXML
    TableColumn<WheelRim,Integer>quantityWeelRim;


    //Cartpage
    @FXML
    TableView<CarComponent> cartTable;
    @FXML
    TableColumn<CarComponent, String> compNameColumn, compTypeColumn;
    @FXML
    TableColumn<CarComponent, Double> compPriceColumn;
    @FXML
    TableColumn<CarComponent, Integer> compQuantityColumn;


    private Scene scene;
    static List<CarComponent> componentsCart = new ArrayList<>();


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

        if (componentsCart.size() == 0) {
            //System.out.println("VELG PRODUKT");
            alert("Din handlekurv er tom, velligst legg til vare før du går videre!");
        }

        else {

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

                //sende inn kolonnene og viewet
                cartTable = (TableView<CarComponent>) loader.getNamespace().get("cartTable");
                compNameColumn = (TableColumn<CarComponent, String>) loader.getNamespace().get("compNameColumn");
                compTypeColumn = (TableColumn<CarComponent, String>) loader.getNamespace().get("compTypeColumn");
                compPriceColumn = (TableColumn<CarComponent, Double>) loader.getNamespace().get("compPriceColumn");
                compQuantityColumn = (TableColumn<CarComponent, Integer>) loader.getNamespace().get("compQuantityColumn");


                //legger inn i table
                cartTable.setItems(cartTable());

                //sender inn totalsum
                sumText = (Label) loader.getNamespace().get("sumText");
                double sum = totalPrice();
                sumText.setText(String.valueOf(sum));

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
    public void toOrder() {

        if (cartTable.getItems().isEmpty()) {
            alert("Du har ingen varer i handlekurven!");
        }

        else {
            try {
                Stage stage = (Stage) userReg.getScene().getWindow();
                URL url = getClass().getResource("/org/ccomp/user/userReg.fxml");
                FXMLLoader loader = new FXMLLoader(url);
                Parent root = loader.load();


                UserRegController userRegController = loader.getController();
                userRegController.setCarComponents(componentsCart);


                ComponentCSVHandler csvHandler = new ComponentCSVHandler();

                List<CustomerOrder> customerOrders = new ArrayList<>();
                customerOrders = csvHandler.readCustomerOrder(customerOrders, "testCustomerOrders.csv");
                System.out.println("CUSTOMER LIST: " + customerOrders.size());

                /*

                ComponentCSVHandler csvHandler = new ComponentCSVHandler();

                String[] lastRow = csvHandler.readLast();

                System.out.println("RETREIVED LAST ROW: " + Arrays.toString(lastRow));

                int orderNr = Integer.parseInt(lastRow[0]);
                orderNr++;


                List<CompOrder> compOrderList = new ArrayList<>();
               // int orderNr = 1;
                System.out.println("ORDER NR: " + orderNr);
                for (CarComponent carComponent : componentsCart) {
                    CompOrder compOrder = new CompOrder(orderNr, carComponent);
                    System.out.println(compOrder.toCSVFormat());
                    compOrderList.add(compOrder);
                }

              //  ComponentCSVHandler csvHandler = new ComponentCSVHandler();
                csvHandler.writeCompOrder(compOrderList, "testCompOrders.csv");



                 */


                Scene scene = new Scene(root, 800, 600);
                stage.setScene(scene);
                stage.show();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }



    public ObservableList<Engine> engineTable() {
        Engine engine;
        ObservableList<Engine> engines = FXCollections.observableArrayList();



                //Henter dem først ut her
      /*  if (carComponents == null) {
            carComponents = retrievedCompMap.get("Seat");
        }*/


        carComponents = retrievedCompMap.get("Engine");
        //Presenter objektene i tableview ved sette inn riktige verdier til riktig tablecolonne
        for (CarComponent carComponent : carComponents) {


            System.out.println(carComponent.getCompType() + " == " + getSelectedcartype());
            if (carComponent.getCompType().equals(getSelectedcartype()))
            {
                engine = (Engine) carComponent;
                engineTypeColum.setCellValueFactory(new PropertyValueFactory<Engine, String>("compType"));
                nameEngineColum.setCellValueFactory(new PropertyValueFactory<Engine, String>("compName"));
                horsepowerColum.setCellValueFactory(new PropertyValueFactory<Engine, Integer>("engineHorsePower"));
                priceEngineColm.setCellValueFactory(new PropertyValueFactory<Engine, Double>("compPrice"));
                quantityEngineColum.setCellValueFactory(new PropertyValueFactory<Engine, Integer>("compQuantity"));
                engines.add(engine);
            }
        }
        return engines;
    }




    public ObservableList<Seat> seatTable() {
        Seat seat;
        ObservableList<Seat> seats = FXCollections.observableArrayList();

        //Henter dem først ut her
      /*  if (carComponents == null) {
            carComponents = retrievedCompMap.get("Seat");
        }*/
        carComponents = retrievedCompMap.get("Seat");


        //Presenter objektene i tableview ved sette inn riktige verdier til riktig tablecolonne

        for (CarComponent carComponent : carComponents) {
            seat = (Seat) carComponent;
            nameSeatColum.setCellValueFactory(new PropertyValueFactory<Seat, String>("compName"));
            materiellColum.setCellValueFactory(new PropertyValueFactory<Seat, String>("seatMaterial"));
            colorSeatColum.setCellValueFactory(new PropertyValueFactory<Seat, String>("seatColor"));
            seatPriceColum.setCellValueFactory(new PropertyValueFactory<Seat, Double>("compPrice"));
            quantitySeatColum.setCellValueFactory(new PropertyValueFactory<Seat, Integer>("compQuantity"));
            seats.add(seat);
        }
        return seats;
    }


    public ObservableList<Spoiler> spoilerTable() {
        Spoiler spoiler;
        ObservableList<Spoiler> spoilers = FXCollections.observableArrayList();
/*
        if (carComponents == null) {
            carComponents = retrievedCompMap.get("Spoiler");
        }*/
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

    public ObservableList<SteeringWheel> sWheelTable(){
        SteeringWheel steeringWheel;
        ObservableList<SteeringWheel> steeringWheels = FXCollections.observableArrayList();
/*
        if (carComponents == null) {
            carComponents = retrievedCompMap.get("Spoiler");
        }*/
        carComponents = retrievedCompMap.get("SteeringWheel");

        for (CarComponent carComponent : carComponents) {
            steeringWheel = (SteeringWheel) carComponent;
            nameSWheelColum.setCellValueFactory(new PropertyValueFactory<SteeringWheel, String>("compName"));
            materiellSwheelColum.setCellValueFactory(new PropertyValueFactory<SteeringWheel, String>("steeringWheelMaterial"));
            colorSwheelColum.setCellValueFactory(new PropertyValueFactory<SteeringWheel, String>("steeringWheelColor"));
            priceSwheelColum.setCellValueFactory(new PropertyValueFactory<SteeringWheel, Double>("compPrice"));
            quantitySwheel.setCellValueFactory(new PropertyValueFactory<SteeringWheel, Integer>("compQuantity"));
            steeringWheels.add(steeringWheel);
        }
        return steeringWheels;
    }


    public ObservableList<WheelRim> wheelRimTable(){
        WheelRim wheelRim;
        ObservableList<WheelRim> wheelRims = FXCollections.observableArrayList();
/*
        if (carComponents == null) {
            carComponents = retrievedCompMap.get("Spoiler");
        }*/
        carComponents = retrievedCompMap.get("WheelRim");

        for (CarComponent carComponent : carComponents) {
            wheelRim = (WheelRim) carComponent;
            nameWheelRim.setCellValueFactory(new PropertyValueFactory<WheelRim, String>("compName"));
            dimensionWheelRimColum.setCellValueFactory(new PropertyValueFactory<WheelRim, String>("wheelRimDimension"));
            colorWheelRim.setCellValueFactory(new PropertyValueFactory<WheelRim, String>("wheelRimColor"));
            priceWeelRim.setCellValueFactory(new PropertyValueFactory<WheelRim, Double>("compPrice"));
            quantityWeelRim.setCellValueFactory(new PropertyValueFactory<WheelRim, Integer>("compQuantity"));
            wheelRims.add(wheelRim);
        }
        return wheelRims;
    }




    public void viewTheComponents() {

        if (enginePane.isExpanded()){
            customorEngineView.setItems(engineTable());
        }

        if (seatPane.isExpanded()){
            customerSeatView.setItems(seatTable());
            customerSeatView.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
        }

        if (spoilerPane.isExpanded()) {
            customerSpoilerView.setItems(spoilerTable());
            customerSpoilerView.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);

        }
        if (sWheelPane.isExpanded()){
            customerSWheelView.setItems(sWheelTable());
        }

        if (wheelRimPane.isExpanded()){
            customerWheelRimView.setItems(wheelRimTable());

        }




    }

    public void countProducts() {

        numberofProduct.setText("Antall lagt til: " + String.valueOf(componentsCart.size()));

    }

    @FXML
    public void chooseProduct() {
                int engineindex = customorEngineView.getSelectionModel().getSelectedIndex();
                int seatindex = customerSeatView.getSelectionModel().getSelectedIndex();
                int spoilerindex = customerSpoilerView.getSelectionModel().getSelectedIndex();
                int swheelindedx = customerSWheelView.getSelectionModel().getSelectedIndex();
                int wheelRimindex = customerWheelRimView.getSelectionModel().getSelectedIndex();


                    //Finner den riktige valgte produktet i viewvet og legger den inn i handlerkurv ved å bruke index
                    if (customerSeatView.getSelectionModel().isSelected(seatindex) && seatPane.isExpanded()) {
                        Seat item = customerSeatView.getItems().get(seatindex);
                        carComponents = getComponentsCart();
                       // Seat seat = new Seat(compNameProperty, compPriceProperty, compQuantityProperty, compColorProperty, compMaterialProperty);
                        Seat seat = new Seat(item.getCompName(), item.getCompPrice(), item.getCompQuantity(), item.getSeatColor(), item.getSeatColor());
                        seat.setCompQuantity(1);
                        componentsCart.add(seat);
                        countProducts();


                        int count = 0;
                        for(CarComponent carComponent : componentsCart){
                            if (carComponent.getCompType().equals("Seat")){

                                Seat tempSeat = (Seat) carComponent;

                                tempSeat.setCompQuantity(1);

                               if (tempSeat.equals(seat)){

                                   componentsCart.get(count).setCompQuantity(carComponent.getCompQuantity() + 1);


                               }


                            }
                            count++;
                        }


                        // cartProduct.setText( "Navn" + compNameProperty  );
                        countProducts();
                    }


                if (customerSpoilerView.getSelectionModel().isSelected(spoilerindex) && spoilerPane.isExpanded()) {
                    Spoiler spoileritem = customerSpoilerView.getItems().get(spoilerindex);
                    Spoiler spoiler = new Spoiler(spoileritem.getCompName(), spoileritem.getCompPrice(), spoileritem.getCompQuantity(),
                                                  spoileritem.getSpoilerColor(),spoileritem.getSpoilerSide());
                    carComponents = getComponentsCart();
                    componentsCart.add(spoiler);
                    countProducts();
                }

                if (customerSWheelView.getSelectionModel().isSelected(swheelindedx) && sWheelPane.isExpanded()){
                    SteeringWheel steeringWheelItem = customerSWheelView.getItems().get(swheelindedx);
                    SteeringWheel steeringWheel = new SteeringWheel(steeringWheelItem.getCompName(),steeringWheelItem.getCompPrice(),steeringWheelItem.getCompQuantity(),
                            steeringWheelItem.getSteeringWheelColor(),steeringWheelItem.getSteeringWheelMaterial());
                    carComponents = getComponentsCart();
                    componentsCart.add(steeringWheel);
                    countProducts();
                }
                if (customorEngineView.getSelectionModel().isSelected(engineindex) && enginePane.isExpanded()){
                    Engine engineItem = customorEngineView.getItems().get(engineindex);
                    Engine engine = new Engine(engineItem.getCompType(),engineItem.getCompName(),engineItem.getCompPrice(),
                                               engineItem.getCompQuantity(),engineItem.getEngineHorsePower());
                    carComponents = getComponentsCart();
                    componentsCart.add(engine);
                    countProducts();
                }
                if (customerWheelRimView.getSelectionModel().isSelected(wheelRimindex) && wheelRimPane.isExpanded()){
                    WheelRim wheelRimItem = customerWheelRimView.getItems().get(wheelRimindex);
                    carComponents = getComponentsCart();
                 WheelRim wheelRim = new WheelRim(wheelRimItem.getCompName(),wheelRimItem.getCompPrice(), wheelRimItem.getCompQuantity(),
                                                wheelRimItem.getWheelRimColor(),wheelRimItem.getWheelRimDimension());
                 componentsCart.add(wheelRim);
                    countProducts();
            }


    }


    //for å hente ut i tableview fra viewSiden til cartsiden
    public ObservableList<CarComponent> cartTable() {
        ObservableList<CarComponent> carComps = FXCollections.observableArrayList();
        //  carComponents = retrievedCompMap.get("CarComp");


        for (CarComponent carComponent : componentsCart) {

            compNameColumn.setCellValueFactory(new PropertyValueFactory<CarComponent, String>("compType"));
            compTypeColumn.setCellValueFactory(new PropertyValueFactory<CarComponent, String>("compType"));
            compPriceColumn.setCellValueFactory(new PropertyValueFactory<CarComponent, Double>("compPrice"));
            compQuantityColumn.setCellValueFactory(new PropertyValueFactory<CarComponent, Integer>("compQuantity"));
            carComps.add(carComponent);

        }
        return carComps;
    }



    //Sletter rader i cartTabel
    public void deleteCartRow() {
        CarComponent selectedRow = cartTable.getSelectionModel().getSelectedItem();
        cartTable.getItems().remove(selectedRow);
        componentsCart.remove(selectedRow);

        double sum = totalPrice();
        sumText.setText(String.valueOf(sum));
    }

    public void deleteCartAll() {
        //Remove all components added to the componentsCart List from CartTable
        cartTable.getItems().removeAll(componentsCart);

        //Clear ComponentsCart List
        componentsCart.clear();
    }

    public static List<CarComponent> getComponentsCart() {
        return componentsCart;
    }

    public static void setComponentsCart(List<CarComponent> componentsCart) {
        UserViewCartController.componentsCart = componentsCart;

    }

    //metode for å regne ut totalsummen av

    public double totalPrice(){

        /*ist<Double>  columnData = new ArrayList<>();

        for (CarComponent carcomponet : cartTable.getItems()) {
            columnData.add(compPriceColumn.getCellObservableValue(carcomponet).getValue());
        }

        sumText.setText(String.valueOf(columnData.get(0)));*/

        double sum = 0;

        for (CarComponent carComponent : componentsCart) {
            sum += carComponent.getCompPrice();
        }

      /*  for (int i = 0; i < cartTable.getItems().size(); i++) {
            sum = sum + Integer.parseInt(cartTable.getValueAt(i, 2).toString());
        }*/

        return sum;
    }

    public static void alert(String msg) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("CCOMP");
        alert.setHeaderText("Feil!");
        alert.setContentText(msg);
        alert.showAndWait();

    }

    public String getSelectedcartype() {
        return selectedcartype;
    }

    public void setSelectedcartype(String selectedcartype) {
        this.selectedcartype = selectedcartype;
    }

}