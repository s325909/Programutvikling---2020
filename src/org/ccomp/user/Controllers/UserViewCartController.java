package org.ccomp.user.Controllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import org.ccomp.fileHandling.ComponentOBJHandler;
import org.ccomp.model.component.*;

import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;


public class UserViewCartController {

    @FXML
    AnchorPane contentProducts, contentCart;

    @FXML
    Label viewCart, numberofProduct, sumText, sumAntall;

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


    private ComponentOBJHandler jobjHandler;

    //  private OLDComponentOBJHandlerOLD jobjHandler;
    private HashMap<String, List<CarComponent>> compMap, retrievedCompMap;
    private List<CarComponent> carComponents;
    private static String selectedCarType;


    CarComponent carComponent;

    private Scene scene;

    private boolean addedComp;

    private CarComponent selectedCarComponent;

    static List<CarComponent> componentsCart = new ArrayList<>();



    @FXML
    public void initialize() {
        jobjHandler = new ComponentOBJHandler();
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

        if (numberofProduct != null) countProducts();
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

            try {
                URL url = getClass().getResource("/org/ccomp/user/userCart.fxml");


                FXMLLoader loader = new FXMLLoader(url);
                scene = contentProducts.getScene();
                scene.setRoot(loader.load());

                //sende inn kolonnene og viewet
                cartTable = (TableView<CarComponent>) loader.getNamespace().get("cartTable");
                compTypeColumn = (TableColumn<CarComponent, String>) loader.getNamespace().get("compTypeColumn");
                compNameColumn = (TableColumn<CarComponent, String>) loader.getNamespace().get("compNameColumn");
                compPriceColumn = (TableColumn<CarComponent, Double>) loader.getNamespace().get("compPriceColumn");
                compQuantityColumn = (TableColumn<CarComponent, Integer>) loader.getNamespace().get("compQuantityColumn");


                //legger inn i table
                cartTable.setItems(cartTable());

                //sender inn totalsum
                sumAntall = (Label) loader.getNamespace().get("sumAntall");
                int antall = totalQuantity();
                sumAntall.setText(String.valueOf(antall));


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

                Scene scene = new Scene(root, 500, 600);
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

        carComponents = retrievedCompMap.get("Engine");
        if (carComponents == null) carComponents = new ArrayList<>();

        //Presenter objektene i tableview ved sette inn riktige verdier til riktig tablecolonne
        for (CarComponent carComponent : carComponents) {
            if (carComponent.getCompType().equals(getSelectedcartype())) {
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

        carComponents = retrievedCompMap.get("Seat");
        if (carComponents == null) carComponents = new ArrayList<>();


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

        carComponents = retrievedCompMap.get("Spoiler");
        if (carComponents == null) carComponents = new ArrayList<>();

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
        if (carComponents == null) carComponents = new ArrayList<>();

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

        carComponents = retrievedCompMap.get("WheelRim");
        if (carComponents == null) carComponents = new ArrayList<>();

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

        int products = 0;

        for (CarComponent carComponent : componentsCart) {
            System.out.println(products + " += " + carComponent.getCompQuantity());
            products += carComponent.getCompQuantity();
        }

        System.out.println("PRODUCTS: " + products);

       // numberofProduct.setText("Antall lagt til: " + componentsCart.size());

        numberofProduct.setText("Antall lagt til: " + products);
    }

    @FXML
    public void chooseProduct() {
        int engineindex = customorEngineView.getSelectionModel().getSelectedIndex();
        int seatindex = customerSeatView.getSelectionModel().getSelectedIndex();
        int spoilerindex = customerSpoilerView.getSelectionModel().getSelectedIndex();
        int swheelindedx = customerSWheelView.getSelectionModel().getSelectedIndex();
        int wheelRimindex = customerWheelRimView.getSelectionModel().getSelectedIndex();


        addedComp = false;

        selectedCarComponent = null;

        //Finner den riktige valgte produktet i viewvet og legger den inn i handlerkurv ved å bruke index
        if (customorEngineView.getSelectionModel().isSelected(engineindex) && enginePane.isExpanded()){

            Engine engineItem = customorEngineView.getItems().get(engineindex);

            selectedCarComponent = createSelectedComp(engineItem);

            System.out.println("SELECTED ENGINE: " + selectedCarComponent.toCSVFormat());
        } else if (customerSeatView.getSelectionModel().isSelected(seatindex) && seatPane.isExpanded()) {

            Seat seatItem = customerSeatView.getItems().get(seatindex);

            selectedCarComponent = createSelectedComp(seatItem);

            System.out.println("SELECTED SEAT: " + selectedCarComponent.toCSVFormat());
        } else if (customerSpoilerView.getSelectionModel().isSelected(spoilerindex) && spoilerPane.isExpanded()) {
            Spoiler spoilerItem = customerSpoilerView.getItems().get(spoilerindex);

            selectedCarComponent = createSelectedComp(spoilerItem);

            System.out.println("SELECTED SPOILER: " + selectedCarComponent.toCSVFormat());
        } else if (customerSWheelView.getSelectionModel().isSelected(swheelindedx) && sWheelPane.isExpanded()){
            SteeringWheel steeringWheelItem = customerSWheelView.getItems().get(swheelindedx);

            selectedCarComponent = createSelectedComp(steeringWheelItem);

            System.out.println("SELECTED STEERING WHEEL: " + selectedCarComponent.toCSVFormat());
        } else if (customerWheelRimView.getSelectionModel().isSelected(wheelRimindex) && wheelRimPane.isExpanded()){
            WheelRim wheelRimItem = customerWheelRimView.getItems().get(wheelRimindex);

            selectedCarComponent = createSelectedComp(wheelRimItem);

            System.out.println("SELECTED WHEEL RIM: " + selectedCarComponent.toCSVFormat());
        } else {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setHeaderText("Kunne ikke legge til i handlekurv!");
            alert.setContentText("Du har ikke valgt produkt, vennligst velg komponent for å legge til!");
            Image image = new Image("https://www.pinclipart.com/picdir/middle/201-2018325_img-empty-shopping-cart-gif-clipart.png");
            ImageView imageView = new ImageView(image);
            imageView.setFitHeight(60);
            imageView.setFitWidth(90);
            alert.setGraphic(imageView);
            alert.showAndWait();
            System.out.println("NO PRODUCT SELECTED...");
        }


        addSelectedComponentToCart(selectedCarComponent);
    }

    private void addSelectedComponentToCart(CarComponent selectedCarComponent) {
        if (selectedCarComponent != null) {
            System.out.println("PRODUCT CHOSEN: " + selectedCarComponent.toCSVFormat());

            increaseQuantity(selectedCarComponent);

            if (!addedComp) {
                System.out.println("ADDING COMPONENT TO CART: " + selectedCarComponent.toCSVFormat());
                componentsCart.add(selectedCarComponent);
            } else System.out.println("CART QUANTITY INCREASED");

            countProducts();

        } else System.out.println("PRODUCT NOT CHOSEN");
    }

    private void increaseQuantity(CarComponent selectedCarComponent) {
        int count = 0;
        for(CarComponent carComponent : componentsCart){
            System.out.println("COUNT: " + count + " / " + componentsCart.size());
            if (carComponent.getCompType().equals(selectedCarComponent.getCompType())){

                if (carComponent.getCompName().equals(selectedCarComponent.getCompName())
                        && carComponent.getCompPrice() == selectedCarComponent.getCompPrice()) {

                    int quantity = carComponent.getCompQuantity() + 1;

                    System.out.println("SELECTED QUANTITY: " + selectedCarComponent.getCompQuantity());

                    if (selectedCarComponent.getCompQuantity() >= quantity) componentsCart.get(count).setCompQuantity(quantity);
                    else {
                        Alert alert = new Alert(Alert.AlertType.INFORMATION);
                        alert.setHeaderText("Det er ikke flere produkter av komponentet tilgjengelig.");
                        alert.showAndWait();
                        System.out.println("MAX ANTALL: " + quantity + " > " + selectedCarComponent.getCompQuantity());
                    }


                    System.out.println("COMPONENT FOUND IN CART: " + carComponent.toCSVFormat() + " ; " + addedComp);

                    addedComp = true;
                    break;
                }

            }
            count++;
        }

        if (!addedComp) selectedCarComponent.setCompQuantity(1);
    }


    private CarComponent createSelectedComp(CarComponent comp) {
        return new CarComponent(comp.getCompType(), comp.getCompName(), comp.getCompPrice(), comp.getCompQuantity());
    }


    //for å hente ut i tableview fra viewSiden til cartsiden
    public ObservableList<CarComponent> cartTable() {
        ObservableList<CarComponent> carComps = FXCollections.observableArrayList();
        //  carComponents = retrievedCompMap.get("CarComp");


        for (CarComponent carComponent : componentsCart) {
            compTypeColumn.setCellValueFactory(new PropertyValueFactory<CarComponent, String>("compType"));
            compNameColumn.setCellValueFactory(new PropertyValueFactory<CarComponent, String>("compName"));
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

        updateTotal();
    }

    public void deleteAllAlert(){

        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setHeaderText("Du er i ferd med å tømme handlekurven!");
        alert.setContentText("Er du sikker på at du vil fortsette?");


        ButtonType cancelButton = new ButtonType("Avbryt", ButtonBar.ButtonData.CANCEL_CLOSE);
        alert.getDialogPane().getButtonTypes().add(cancelButton);

        Optional<ButtonType> result = alert.showAndWait();

        if (result.isPresent() && result.get() == ButtonType.OK) {
            deleteCartAll();
        }
    }

    public void deleteCartAll() {
        //Remove all components added to the componentsCart List from CartTable
           cartTable.getItems().removeAll(componentsCart);
           //Clear ComponentsCart List
           componentsCart.clear();

           updateTotal();
    }


    private void updateTotal() {
        int antall = totalQuantity();
        sumAntall.setText(String.valueOf(antall));

        double sum = totalPrice();
        sumText.setText(String.valueOf(sum));
    }

    public static List<CarComponent> getComponentsCart() {
        return componentsCart;
    }

    public static void setComponentsCart(List<CarComponent> componentsCart) {
        UserViewCartController.componentsCart = componentsCart;

    }


    private int totalQuantity(){

        int total = 0;

        for (CarComponent carComponent : componentsCart) {
            System.out.println(total + " += " + (carComponent.getCompQuantity()));
            total += carComponent.getCompQuantity();
        }

        System.out.println("TOTAL QUANTITY: " + total);

        return total;
    }


    //metode for å regne ut totalsummen av

    public double totalPrice(){

        double sum = 0;

        for (CarComponent carComponent : componentsCart) {
            System.out.println(sum + " += " + (carComponent.getCompPrice() * carComponent.getCompQuantity()));
            sum += (carComponent.getCompPrice() * carComponent.getCompQuantity()) ;
        }

        System.out.println("TOTAL PRICE: " + sum);

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
        return selectedCarType;
    }

    public void setSelectedcartype(String selectedcartype) {
        this.selectedCarType = selectedcartype;
    }

}