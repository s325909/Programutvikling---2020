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

    private Scene scene;
    private boolean addedComp;
    private CarComponent selectedCarComponent;
    private static List<CarComponent> componentsCart = new ArrayList<>();
    private ComponentOBJHandler jobjHandler;
    private HashMap<String, List<CarComponent>> retrievedCompMap;
    private List<CarComponent> carComponents;
    private static String selectedCarType;

    @FXML
    public void initialize() {
        jobjHandler = new ComponentOBJHandler();
        retrievedCompMap = jobjHandler.readComponent(retrievedCompMap);
        componentsCart = getComponentsCart();

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

        if (componentsCart == null) componentsCart = new ArrayList<>();

        if (componentsCart.size() == 0) {
            alert("Din handlekurv er tom, velligst legg til vare før du går videre!");
        }

        else {
            try {
                URL url = getClass().getResource("/org/ccomp/user/userCart.fxml");

                FXMLLoader loader = new FXMLLoader(url);
                scene = contentProducts.getScene();
                scene.setRoot(loader.load());

                //Cast columns to tableview
                cartTable = (TableView<CarComponent>) loader.getNamespace().get("cartTable");
                compTypeColumn = (TableColumn<CarComponent, String>) loader.getNamespace().get("compTypeColumn");
                compNameColumn = (TableColumn<CarComponent, String>) loader.getNamespace().get("compNameColumn");
                compPriceColumn = (TableColumn<CarComponent, Double>) loader.getNamespace().get("compPriceColumn");
                compQuantityColumn = (TableColumn<CarComponent, Integer>) loader.getNamespace().get("compQuantityColumn");

                //adds to table
                cartTable.setItems(cartTable());

                //pass total quantity
                sumAntall = (Label) loader.getNamespace().get("sumAntall");
                int antall = totalQuantity();
                sumAntall.setText(String.valueOf(antall)+ " Stk");

                //pass total sum
                sumText = (Label) loader.getNamespace().get("sumText");
                double sum = totalPrice();
                sumText.setText(String.valueOf(sum) + " NOK");

            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }



    @FXML
    public void backToViewProd() {

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

    private ObservableList<Engine> engineTable() {
        Engine engine;
        ObservableList<Engine> engines = FXCollections.observableArrayList();

        carComponents = retrievedCompMap.get("Engine");
        if (carComponents == null) carComponents = new ArrayList<>();

        //Represent objects in tableview, by setting correct values in the right columns
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

    private ObservableList<Seat> seatTable() {
        Seat seat;
        ObservableList<Seat> seats = FXCollections.observableArrayList();

        carComponents = retrievedCompMap.get("Seat");
        if (carComponents == null) carComponents = new ArrayList<>();

        //Represent objects in tableview, by setting correct values in the right columns
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

    private ObservableList<Spoiler> spoilerTable() {
        Spoiler spoiler;
        ObservableList<Spoiler> spoilers = FXCollections.observableArrayList();

        carComponents = retrievedCompMap.get("Spoiler");
        if (carComponents == null) carComponents = new ArrayList<>();

        //Represent objects in tableview, by setting correct values in the right columns
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

    private ObservableList<SteeringWheel> sWheelTable(){
        SteeringWheel steeringWheel;
        ObservableList<SteeringWheel> steeringWheels = FXCollections.observableArrayList();

        carComponents = retrievedCompMap.get("SteeringWheel");
        if (carComponents == null) carComponents = new ArrayList<>();

        //Represent objects in tableview, by setting correct values in the right columns
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


    private ObservableList<WheelRim> wheelRimTable(){
        WheelRim wheelRim;
        ObservableList<WheelRim> wheelRims = FXCollections.observableArrayList();

        carComponents = retrievedCompMap.get("WheelRim");
        if (carComponents == null) carComponents = new ArrayList<>();

        //Represent objects in tableview, by setting correct values in the right columns
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

    private void countProducts() {

        int products = 0;

        for (CarComponent carComponent : componentsCart) {
            products += carComponent.getCompQuantity();
        }
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

        }
        else if (customerSeatView.getSelectionModel().isSelected(seatindex) && seatPane.isExpanded()) {
            Seat seatItem = customerSeatView.getItems().get(seatindex);
            selectedCarComponent = createSelectedComp(seatItem);

        } else if (customerSpoilerView.getSelectionModel().isSelected(spoilerindex) && spoilerPane.isExpanded()) {
            Spoiler spoilerItem = customerSpoilerView.getItems().get(spoilerindex);
            selectedCarComponent = createSelectedComp(spoilerItem);

        } else if (customerSWheelView.getSelectionModel().isSelected(swheelindedx) && sWheelPane.isExpanded()){
            SteeringWheel steeringWheelItem = customerSWheelView.getItems().get(swheelindedx);
            selectedCarComponent = createSelectedComp(steeringWheelItem);

        } else if (customerWheelRimView.getSelectionModel().isSelected(wheelRimindex) && wheelRimPane.isExpanded()){
            WheelRim wheelRimItem = customerWheelRimView.getItems().get(wheelRimindex);
            selectedCarComponent = createSelectedComp(wheelRimItem);

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
            if (carComponent.getCompType().equals(selectedCarComponent.getCompType())){

                if (carComponent.getCompName().equals(selectedCarComponent.getCompName())
                        && carComponent.getCompPrice() == selectedCarComponent.getCompPrice()) {

                    int quantity = carComponent.getCompQuantity() + 1;

                    if (selectedCarComponent.getCompQuantity() >= quantity) componentsCart.get(count).setCompQuantity(quantity);
                    else {
                        Alert alert = new Alert(Alert.AlertType.INFORMATION);
                        alert.setHeaderText("Det er ikke flere produkter av komponentet tilgjengelig.");
                        alert.showAndWait();
                    }
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

    //populate tableview from viewProduct to userCart
    private ObservableList<CarComponent> cartTable() {
        ObservableList<CarComponent> carComps = FXCollections.observableArrayList();

        for (CarComponent carComponent : componentsCart) {
            compTypeColumn.setCellValueFactory(new PropertyValueFactory<CarComponent, String>("compType"));
            compNameColumn.setCellValueFactory(new PropertyValueFactory<CarComponent, String>("compName"));
            compPriceColumn.setCellValueFactory(new PropertyValueFactory<CarComponent, Double>("compPrice"));
            compQuantityColumn.setCellValueFactory(new PropertyValueFactory<CarComponent, Integer>("compQuantity"));
            carComps.add(carComponent);
        }
        return carComps;
    }



    //Delete rows in cartTable
    public void deleteCartRow() {
        CarComponent selectedRow = cartTable.getSelectionModel().getSelectedItem();
        cartTable.getItems().remove(selectedRow);
        componentsCart.remove(selectedRow);

        updateTotal();
    }

    //Delete the whole cart alert!
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

    private void deleteCartAll() {
        //Remove all components added to the componentsCart List from CartTable
       cartTable.getItems().removeAll(componentsCart);

       //Clear ComponentsCart List
       componentsCart.clear();
       updateTotal();
    }


    private void updateTotal() {
        int antall = totalQuantity();
        sumAntall.setText(String.valueOf(antall) + " Stk");

        double sum = totalPrice();
        sumText.setText(String.valueOf(sum) + " NOK");
    }

    private int totalQuantity(){

        int total = 0;

        for (CarComponent carComponent : componentsCart) {
            total += carComponent.getCompQuantity();
        }
        return total;
    }


    //Method to sum total price of products in cart
    private double totalPrice(){

        double sum = 0;

        for (CarComponent carComponent : componentsCart) {
            sum += (carComponent.getCompPrice() * carComponent.getCompQuantity()) ;
        }
        return sum;
    }

    private static void alert(String msg) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("CCOMP");
        alert.setHeaderText("OOPS!");
        alert.setContentText(msg);
        alert.showAndWait();

    }

    public String getSelectedcartype() {
        return selectedCarType;
    }

    public void setSelectedcartype(String selectedcartype) {
        this.selectedCarType = selectedcartype;
    }

    public static List<CarComponent> getComponentsCart() {
        return componentsCart;
    }

    public static void setComponentsCart(List<CarComponent> componentsCart) {
        UserViewCartController.componentsCart = componentsCart;

    }
}