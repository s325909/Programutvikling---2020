package org.ccomp.admin;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.util.converter.DoubleStringConverter;
import javafx.util.converter.IntegerStringConverter;
import org.ccomp.fileHandling.ComponentCSVHandler;
import org.ccomp.fileHandling.ComponentOBJHandler;
import org.ccomp.model.CompOrder;
import org.ccomp.model.CustomerOrder;
import org.ccomp.model.component.*;
import org.ccomp.model.component.engine.GasolineEngine;
import org.ccomp.user.Customer;

import java.io.IOException;
import java.net.URL;
import java.util.*;

public class AdminController implements Initializable {
    Seat seat;
    Spoiler spoiler;
    CustomerOrder customerOrder;
    int row;
    @FXML
    Button backBtn;

    @FXML
    Button addComp,backAdmin;

    @FXML
    TextField search;

    @FXML
    TabPane tabPane;

    @FXML
    Tab engineTab,seatTab,spoilerTab,steeringWTab,wheelRimTab,orderEngineTab;

    @FXML
    AnchorPane adminPane,custermerInfoPane;

    //OrderView
    @FXML
    TableView<CompOrder> carCompView;
    @FXML
    TableColumn<CompOrder, String> orderTypeColum;
    @FXML
    TableColumn<CompOrder,Integer> orderNrColum;

    @FXML
    TableColumn<CarComponent, String> orderNameColum;
    @FXML
    TableColumn<CarComponent,Double> orderPriceColum;
    @FXML
    TableColumn<CarComponent,Integer> orderQuntityColum;

    //OrderCustomer
    @FXML
    TableView<CustomerOrder> customerOrderInfoView;
    @FXML
    TableColumn<CustomerOrder,Integer> customerInfoOderNr;
    @FXML
    TableColumn<CustomerOrder,String>customerInfoOrderName,customerInfoOrderEmail;

    @FXML
    TableColumn<CustomerOrder,String> customerInfoOrderZip,customerInfoOrderMobilNr,customerInfoOrderCity;

    /*
    @FXML
    TableColumn<Customer,String>customerInfoOrderName,customerInfoOrderEmail,
            customerInfoOrderZip,customerInfoOrderMobilNr,customerInfoOrderCity,
            customerInfoOrderButton;

     */


    //Seat
    @FXML
    TableView<Seat> seatView;
    @FXML
    TableColumn<Seat, String> nameSeatColum, materiellColum, colorSeatColum;
    @FXML
    TableColumn<Seat, Double> seatPriceColum;
    @FXML
    TableColumn<Seat, Integer> quantitySeatColum;

    //Spoiler
    @FXML
    TableView<Spoiler> spoilerView;
    @FXML
    TableColumn<Spoiler, String> nameSpoilerColum, colorSpoilerColum, sideSpoilerColum;
    @FXML
    TableColumn<Spoiler, Double> priceSpoilerColum;
    @FXML
    TableColumn<Spoiler, Integer> quantitySpoilerColum;

    //SteeringWheel
    @FXML
    TableView<SteeringWheel> sWheelView;
    @FXML
    TableColumn<SteeringWheel, String> nameSWheelColum, materiellSWeel, colorSWheelColum;
    @FXML
    TableColumn<SteeringWheel, Double> priceSWeelColum;
    @FXML
    TableColumn<SteeringWheel, Integer>  quantitySWeelColum;;

    //WheelRim
    @FXML
    TableView<WheelRim> wheelRimView;
    @FXML
    TableColumn<WheelRim, String> nameWheelRimColum, dimensionWheelRim, colorWheelRim;
    @FXML
    TableColumn<WheelRim, Double> priceWheelRim;
    @FXML
    TableColumn<WheelRim,Integer> quantityWheelRim;

    /*
    //Engine
    @FXML
    TableView<Engine> engineView;
    @FXML
    TableColumn<Engine, String> engintypeColum, nameEngineColum, horsepowerColum;
    @FXML
    TableColumn<Engine, Double>priceEngineColum;
    @FXML
    TableColumn<Engine, Integer>quantityEngineColum;
    */

    private ComponentOBJHandler jobjHandler;

   // private ComponentOBJHandler jobjHandler;
    private HashMap<String, List<CarComponent>> compMap, retrievedCompMap;
    private List<CarComponent> carComponents;
    private List<Customer> customerList;
    private Scene setRoot;
    private ObservableList<Seat> seats;
    private ObservableList<Spoiler> spoilers;
    private SortedList<Seat> sortedData;
    private ObservableList<CustomerOrder> customerOrders;





    @Override
    public void initialize(URL location, ResourceBundle resources) {


        jobjHandler = new ComponentOBJHandler();

        //todo: fila kan være null eller tom
        retrievedCompMap = jobjHandler.readComponent(retrievedCompMap);

        System.out.println("INIT COMP MAP: " + retrievedCompMap );

    }




    //Metoden henter objektene fra arrayliste.
    public ObservableList<Seat> seatTable() {

        Seat seat;
        seats = FXCollections.observableArrayList();
       // FilteredList filteredList  = new FilteredList(seats ,e->true);

        //Henter dem først ut her
        carComponents = retrievedCompMap.get("Seat");
       if (carComponents == null) carComponents = new ArrayList<>();

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
        spoilers = FXCollections.observableArrayList();
        carComponents = retrievedCompMap.get("Spoiler");

        if (carComponents == null) carComponents = new ArrayList<>();
        for (CarComponent carComponent : carComponents) {
            spoiler = (Spoiler) carComponent;
            nameSpoilerColum.setCellValueFactory(new PropertyValueFactory<Spoiler, String>("compName"));
            colorSpoilerColum.setCellValueFactory(new PropertyValueFactory<Spoiler,String>("compName"));
            sideSpoilerColum.setCellValueFactory(new PropertyValueFactory<Spoiler, String>("spoilerSide"));
            priceSpoilerColum.setCellValueFactory(new PropertyValueFactory<Spoiler, Double>("compPrice"));
            quantitySpoilerColum.setCellValueFactory(new PropertyValueFactory<Spoiler, Integer>("compQuantity"));
            spoilers.add(spoiler);
        }

        return spoilers;


    }


    public ObservableList<SteeringWheel> sWheelTable() {


        SteeringWheel steeringWheel;
        ObservableList<SteeringWheel> steeringWheels = FXCollections.observableArrayList();

        carComponents = retrievedCompMap.get("SteeringWheel");
        if (carComponents == null) carComponents = new ArrayList<>();

        // steeringWheel = (SteeringWheel) carComponents.get(0);
        //steeringWheel.setSteeringWheelMaterial(materiellColum.getText());

        // carComponents.set(0, steeringWheel);


        for (CarComponent carComponent : carComponents) {
            steeringWheel = (SteeringWheel) carComponent;
            nameSWheelColum.setCellValueFactory(new PropertyValueFactory<SteeringWheel, String>("compName"));
            colorSWheelColum.setCellValueFactory(new PropertyValueFactory<SteeringWheel, String>("compName"));
            materiellSWeel.setCellValueFactory(new PropertyValueFactory<SteeringWheel, String>("spoilerSide"));
            priceSWeelColum.setCellValueFactory(new PropertyValueFactory<SteeringWheel, Double>("compPrice"));
            quantitySWeelColum.setCellValueFactory(new PropertyValueFactory<SteeringWheel, Integer>("compQuantity"));
            steeringWheels.add(steeringWheel);
        }

        return steeringWheels;
    }

    public ObservableList<WheelRim> wheelRTable() {

        WheelRim wheelRim;
        ObservableList<WheelRim> wheelRims = FXCollections.observableArrayList();
        if (carComponents == null) carComponents = new ArrayList<>();
        carComponents = retrievedCompMap.get("WheelRim");


        for (CarComponent carComponent : carComponents) {
            wheelRim = (WheelRim) carComponent;
            nameWheelRimColum.setCellValueFactory(new PropertyValueFactory<WheelRim, String>("compName"));
            //dimensionWheelRim.setCellValueFactory(new PropertyValueFactory<SteeringWheel,SimpleStringProperty>("compName"));
            wheelRims.add(wheelRim);


        }

        return wheelRims;
    }

    public ObservableList<CustomerOrder> OrderInfoCustomer(){

        ComponentCSVHandler csvHandler = new ComponentCSVHandler();

        List<CustomerOrder> customerOrders1 = new ArrayList<>();
        customerOrders1 = csvHandler.readCustomerOrder(customerOrders1, "testCustomerOrders.csv");
        System.out.println("CUSTOMER LIST: " + customerOrders1.size());

        //Customer
        String  customerName = "Jaso";
        String customerEmail ="athisaiyan@hotmail.com";
        String customerNumber = "90243728";
        String customerZip = "1187";
        String customerCity ="Oslo";
        Customer customer = new Customer(customerName,customerEmail,customerNumber,customerZip,customerCity);

        customerOrders = FXCollections.observableArrayList();
       // CustomerOrder customerOrder = new CustomerOrder(2,customer);
        CustomerOrder customerOrder = new CustomerOrder(5, customerName, customerEmail, customerNumber, customerZip, customerCity);

        List<CustomerOrder> customerOrderList = new ArrayList<>();


        customerOrderList.add(customerOrder);
        customerOrderList.add(customerOrder);
        customerOrderList.add(customerOrder);
        customerOrderList.add(customerOrder);
        customerOrderList.add(customerOrder);

        customerList = new ArrayList<>();





        for (CustomerOrder customerOrder1 : customerOrders1) {
           // customer = customerOrder1.getCustomer();
            customerInfoOderNr.setCellValueFactory(new PropertyValueFactory<CustomerOrder,Integer>("customerOrderNr"));
            customerInfoOrderName.setCellValueFactory(new PropertyValueFactory<CustomerOrder,String>("customerName"));
            customerInfoOrderEmail.setCellValueFactory(new PropertyValueFactory<CustomerOrder,String>("customerMail"));
            customerInfoOrderZip.setCellValueFactory(new PropertyValueFactory<CustomerOrder,String>("customerZipCode"));
            customerInfoOrderMobilNr.setCellValueFactory(new PropertyValueFactory<CustomerOrder,String>("customerNumber"));
            customerInfoOrderCity.setCellValueFactory(new PropertyValueFactory<CustomerOrder,String>("customerCity"));

           // customerList.add(customer);
            customerOrders.add(customerOrder1);
        }

        return customerOrders;
    }



    private String orderId;
    private List<CompOrder> compOrderList;

    public  ObservableList<CompOrder> carComTable(){

        ObservableList<CompOrder> compOrders = FXCollections.observableArrayList();

        int index = customerOrderInfoView.getSelectionModel().getSelectedIndex();
        System.out.println("SELECTED CUSTOMER INDEX: " + index);

        boolean orderSelected = true;
        if (index == -1) {
            System.out.println("NO INDEX SELECTED!!!");
            orderSelected = false;
        }


        if (orderSelected) {
            CustomerOrder selectedCustomerOrder = customerOrderInfoView.getItems().get(index);
            orderId = String.valueOf(selectedCustomerOrder.getOrderId());
            System.out.println("SELECTED CUSTOMER ORDER ID: " + orderId);
        }



        ComponentCSVHandler csvHandler = new ComponentCSVHandler();
        if (orderSelected) compOrderList = csvHandler.searchOrderRow("testCompOrders.csv", orderId);
        else {
            System.out.println("NO CUSTOMER ORDER SELECTED!!!");
            compOrderList = csvHandler.readCompOrder(compOrderList, "testCompOrders.csv");
        }


        for (CompOrder compOrder : compOrders) {
            System.out.println(compOrder.toCSVFormat());
        }


        carComponents = new ArrayList<>();
       // if (customerOrderInfoView.getSelectionModel().isSelected(row))
        for (CompOrder compOrder : compOrderList) {
            CarComponent carComponent = compOrder.getCarComponent();
            orderNameColum.setCellValueFactory(new PropertyValueFactory<CarComponent,String>("CompName"));
            orderNrColum.setCellValueFactory(new PropertyValueFactory<CompOrder,Integer>("compOrderNr"));
            orderTypeColum.setCellValueFactory(new PropertyValueFactory<CompOrder,String>("CompType"));
            orderPriceColum.setCellValueFactory(new PropertyValueFactory<CarComponent,Double>("compPrice"));
            orderQuntityColum.setCellValueFactory(new PropertyValueFactory<CarComponent,Integer>("compQuantity"));
            carComponents.add(carComponent);

            compOrders.add(compOrder);
        }


        return compOrders;
    }


    @FXML
    public void allTables(){

        if (seatTab.isSelected()){
            seatView.setItems(seatTable());
            editSeatTable();
            seatView.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
            System.out.println("SEAT TAB");
            searchComp();
        }
        if (spoilerTab.isSelected()){
            spoilerView.setItems(spoilerTable());
            editSpoilerTable();
            spoilerView.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
            System.out.println("SPOILER SATB");
            searchComp();
        }
        if (steeringWTab.isSelected()){
            sWheelView.setItems(sWheelTable());
            sWheelView.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
        }

        if (orderEngineTab.isSelected()){
           // carCompView.setItems(carComTable());
           // System.out.println("Customer");
            customerOrderInfoView.setItems(OrderInfoCustomer());
            editOrderCustomer();

        }

       // sWheelView.setItems(sWheelTable());
        //wheelRimView.setItems(wheelRTable());

    }


    public void editSeatTable() {
        seatView.setEditable(true);

        if (carComponents.size() == 0) return;

        seat = (Seat) carComponents.get(row);
        materiellColum.setCellFactory(new PropertyValueFactory("Materiale"));
        materiellColum.setCellFactory(TextFieldTableCell.forTableColumn());
        materiellColum.setOnEditCommit((TableColumn.CellEditEvent<Seat, String> t) -> {
            t.getTableView().getItems();
            row = t.getTablePosition().getRow();
            String value = t.getNewValue();
            System.out.println(value);
            carComponents = retrievedCompMap.get("Seat");
            seat = ((Seat) carComponents.get(row));
            seat.setMaterial(value);
            retrievedCompMap.get("Seat").set(row, seat);
            System.out.println(seat.getMaterial());
            (t.getTableView().getItems().get(
                    t.getTablePosition().getRow())
            ).setMaterial(t.getNewValue()
            );
        });

        nameSeatColum.setCellFactory(new PropertyValueFactory("Navn"));
        nameSeatColum.setCellFactory(TextFieldTableCell.forTableColumn());
        nameSeatColum.setOnEditCommit((TableColumn.CellEditEvent<Seat, String> t) -> {
            t.getTableView().getItems();
            row = t.getTablePosition().getRow();
            String value = t.getNewValue();
            System.out.println(value);
            carComponents = retrievedCompMap.get("Seat");
            seat = ((Seat) carComponents.get(row));
            seat.setCompName(value);
            retrievedCompMap.get("Seat").set(row, seat);
            (t.getTableView().getItems().get(
                    t.getTablePosition().getRow())
            ).setMaterial(t.getNewValue()
            );
        });

        colorSeatColum.setCellFactory(new PropertyValueFactory("Farge"));
        colorSeatColum.setCellFactory(TextFieldTableCell.forTableColumn());
        colorSeatColum.setOnEditCommit((TableColumn.CellEditEvent<Seat, String> t) -> {
            t.getTableView().getItems();
            row = t.getTablePosition().getRow();
            String value = t.getNewValue();
            System.out.println(value);
            carComponents = retrievedCompMap.get("Seat");
            seat = ((Seat) carComponents.get(row));
            seat.setColor(value);
            retrievedCompMap.get("Seat").set(row, seat);
            (t.getTableView().getItems().get(
                    t.getTablePosition().getRow())
            ).setMaterial(t.getNewValue()
            );
        });

        seatPriceColum.setCellFactory(new PropertyValueFactory("Pris"));
        seatPriceColum.setCellFactory(TextFieldTableCell.forTableColumn(new DoubleStringConverter()));
        seatPriceColum.setOnEditCommit((TableColumn.CellEditEvent<Seat, Double> t) -> {
            t.getTableView().getItems();
            row = t.getTablePosition().getRow();
            Double value = t.getNewValue();
            System.out.println(value);
            carComponents = retrievedCompMap.get("Seat");
            seat = ((Seat) carComponents.get(row));
            seat.setCompPrice(value);
            retrievedCompMap.get("Seat").set(row, seat);
            (t.getTableView().getItems().get(
                    t.getTablePosition().getRow())
            ).setCompPrice(t.getNewValue()
            );

        });

        quantitySeatColum.setCellFactory(new PropertyValueFactory("Navn"));
        quantitySeatColum.setCellFactory(TextFieldTableCell.forTableColumn(new IntegerStringConverter()));

        quantitySeatColum.setOnEditCommit((TableColumn.CellEditEvent<Seat, Integer> t) -> {
            t.getTableView().getItems();
            row = t.getTablePosition().getRow();
            Integer value = t.getNewValue();
            System.out.println(value);
            carComponents = retrievedCompMap.get("Seat");
            seat = ((Seat) carComponents.get(row));
            seat.setCompPrice(value);
            retrievedCompMap.get("Seat").set(row, seat);
            (t.getTableView().getItems().get(
                    t.getTablePosition().getRow())
            ).setCompQuantity(t.getNewValue()
            );
        });

    }


    public void editSpoilerTable() {
        spoilerView.setEditable(true);

        if (carComponents.size() == 0) return;


        spoiler = (Spoiler) carComponents.get(row);
        nameSpoilerColum.setCellFactory(new PropertyValueFactory("Navn"));
        nameSpoilerColum.setCellFactory(TextFieldTableCell.forTableColumn());
        nameSpoilerColum.setOnEditCommit((TableColumn.CellEditEvent<Spoiler, String> t) -> {
            t.getTableView().getItems();
            row = t.getTablePosition().getRow();
            String value = t.getNewValue();
            System.out.println(value);
            carComponents = retrievedCompMap.get("Spoiler");
            spoiler = ((Spoiler) carComponents.get(row));
            spoiler.setCompName(value);
            retrievedCompMap.get("Spoiler").set(row, seat);
            System.out.println(seat.getMaterial());
            (t.getTableView().getItems().get(
                    t.getTablePosition().getRow())
            ).setCompName(t.getNewValue()
            );
        });

        colorSpoilerColum.setCellFactory(new PropertyValueFactory("Farge"));
        colorSpoilerColum.setCellFactory(TextFieldTableCell.forTableColumn());
        colorSpoilerColum.setOnEditCommit((TableColumn.CellEditEvent<Spoiler, String> t) -> {
            t.getTableView().getItems();
            row = t.getTablePosition().getRow();
            String value = t.getNewValue();
            System.out.println(value);
            carComponents = retrievedCompMap.get("Spoiler");
            spoiler = ((Spoiler) carComponents.get(row));
            spoiler.setCompName(value);
            retrievedCompMap.get("Spoiler").set(row, seat);
            System.out.println(seat.getMaterial());
            (t.getTableView().getItems().get(
                    t.getTablePosition().getRow())
            ).setCompName(t.getNewValue()
            );
        });

        sideSpoilerColum.setCellFactory(new PropertyValueFactory("Side"));
        sideSpoilerColum.setCellFactory(TextFieldTableCell.forTableColumn());
        sideSpoilerColum.setOnEditCommit((TableColumn.CellEditEvent<Spoiler, String> t) -> {
            t.getTableView().getItems();
            row = t.getTablePosition().getRow();
            String value = t.getNewValue();
            System.out.println(value);
            carComponents = retrievedCompMap.get("Spoiler");
            spoiler = ((Spoiler) carComponents.get(row));
            spoiler.setCompName(value);
            retrievedCompMap.get("Spoiler").set(row, seat);
            System.out.println(seat.getMaterial());
            (t.getTableView().getItems().get(
                    t.getTablePosition().getRow())
            ).setCompName(t.getNewValue()
            );
        });

        priceSpoilerColum.setCellFactory(new PropertyValueFactory("Pris"));
        priceSpoilerColum.setCellFactory(TextFieldTableCell.forTableColumn(new DoubleStringConverter()));
        priceSpoilerColum.setOnEditCommit((TableColumn.CellEditEvent<Spoiler, Double> t) -> {
            t.getTableView().getItems();
            row = t.getTablePosition().getRow();
            Double value = t.getNewValue();
            System.out.println(value);
            carComponents = retrievedCompMap.get("Spoiler");
            seat = ((Seat) carComponents.get(row));
            seat.setCompPrice(value);
            retrievedCompMap.get("Spoiler").set(row, seat);
            (t.getTableView().getItems().get(
                    t.getTablePosition().getRow())
            ).setCompPrice(t.getNewValue()
            );

        });


        nameSpoilerColum.setCellFactory(new PropertyValueFactory("Navn"));
        nameSpoilerColum.setCellFactory(TextFieldTableCell.forTableColumn());
        nameSpoilerColum.setOnEditCommit((TableColumn.CellEditEvent<Spoiler, String> t) -> {
            t.getTableView().getItems();
            row = t.getTablePosition().getRow();
            String value = t.getNewValue();
            System.out.println(value);
            carComponents = retrievedCompMap.get("Spoiler");
            spoiler = ((Spoiler) carComponents.get(row));
            seat.setMaterial(value);
            retrievedCompMap.get("Seat").set(row, seat);
            System.out.println(seat.getMaterial());
            (t.getTableView().getItems().get(
                    t.getTablePosition().getRow())
            ).setCompName(t.getNewValue()
            );
        });

    }


    public void editOrderCustomer(){

        customerOrderInfoView.setEditable(true);
        customerInfoOrderName.setCellFactory(new PropertyValueFactory("Navn"));
        customerInfoOrderName.setCellFactory(TextFieldTableCell.forTableColumn());
        customerInfoOrderName.setOnEditCommit((TableColumn.CellEditEvent<CustomerOrder, String> t) -> {
            t.getTableView().getItems();
            row = t.getTablePosition().getRow();
            String value = t.getNewValue();
            System.out.println(value);
            (t.getTableView().getItems().get(
                    t.getTablePosition().getRow())
            ).setCustomerName(t.getNewValue()
            );
        });


        customerInfoOrderEmail.setCellFactory(new PropertyValueFactory("E-postadresse"));
        customerInfoOrderEmail.setCellFactory(TextFieldTableCell.forTableColumn());
        customerInfoOrderEmail.setOnEditCommit((TableColumn.CellEditEvent<CustomerOrder, String> t) -> {
            t.getTableView().getItems();
            row = t.getTablePosition().getRow();
            String value = t.getNewValue();
            System.out.println(value);
            (t.getTableView().getItems().get(
                    t.getTablePosition().getRow())
            ).setCustomerMail(t.getNewValue()
            );
        });


        customerInfoOrderMobilNr.setCellFactory(new PropertyValueFactory("Mobilnummer"));
        customerInfoOrderMobilNr.setCellFactory(TextFieldTableCell.forTableColumn());
        customerInfoOrderMobilNr.setOnEditCommit((TableColumn.CellEditEvent<CustomerOrder, String> t) -> {
            t.getTableView().getItems();
            row = t.getTablePosition().getRow();
            String value = t.getNewValue();
            System.out.println(value);
            (t.getTableView().getItems().get(
                    t.getTablePosition().getRow())
            ).setCustomerNumber(t.getNewValue()
            );
        });


        customerInfoOrderCity.setCellFactory(new PropertyValueFactory("Poststed"));
        customerInfoOrderCity.setCellFactory(TextFieldTableCell.forTableColumn());
        customerInfoOrderCity.setOnEditCommit((TableColumn.CellEditEvent<CustomerOrder, String> t) -> {
            t.getTableView().getItems();
            row = t.getTablePosition().getRow();
            String value = t.getNewValue();
            System.out.println(value);
            (t.getTableView().getItems().get(
                    t.getTablePosition().getRow())
            ).setCustomerCity(t.getNewValue()
            );
        });

        customerInfoOrderZip.setCellFactory(new PropertyValueFactory("Postnummer"));
        customerInfoOrderZip.setCellFactory(TextFieldTableCell.forTableColumn());
        customerInfoOrderZip.setOnEditCommit((TableColumn.CellEditEvent<CustomerOrder, String> t) -> {
            t.getTableView().getItems();
            row = t.getTablePosition().getRow();
            String value = t.getNewValue();
            System.out.println(value);
            (t.getTableView().getItems().get(
                    t.getTablePosition().getRow())
            ).setCustomerZipCode(t.getNewValue()
            );
        });




    }



    @FXML
    public void deleteSelectedRow() {

        int retrievedCompMapSize = retrievedCompMap.size();

        if(seatTab.isSelected()) {

            // The index of the sorted and filtered list
            int visibleIndex = seatView.getSelectionModel().getSelectedIndex();

            // Source index of ObservableList
            int sourceIndex = sortedData.getSourceIndexFor(seats, visibleIndex);

            // Remove from ObservableList using the index
            seats.remove(sourceIndex);

            //Remove selected component from HashMap containing all components
            retrievedCompMap.get("Seat").remove(sourceIndex);


            // Write Components HashMap to file if changes has been made
            if (retrievedCompMapSize != retrievedCompMap.size())
                jobjHandler.writeComponent(retrievedCompMap);
        }

        if(spoilerTab.isSelected()) {

            // The index of the sorted and filtered list
            int visibleIndex = spoilerView.getSelectionModel().getSelectedIndex();

            // Source index of ObservableList
            int sourceIndex = sortedData.getSourceIndexFor(spoilers, visibleIndex);

            // Remove from ObservableList using the index
            spoilers.remove(sourceIndex);

            //Remove selected component from HashMap containing all components
            retrievedCompMap.get("Spoiler").remove(sourceIndex);
        }




        // Write Components HashMap to file if changes has been made
        if (retrievedCompMapSize != retrievedCompMap.size())
            jobjHandler.writeComponent(retrievedCompMap);
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

        public void  searchComp() {

            if (seatTab.isSelected()) {
                ObservableList<Seat> seatsList = seatTable();
                FilteredList<Seat> filteredList = new FilteredList(seatsList, b -> true);
                //predicater når filter skifter
                search.textProperty().addListener(((observable, oldValue, newValue) -> {

                    filteredList.setPredicate(seat -> {

                        //Hvis filter er tommt , vis alt
                        if (newValue == null || newValue.isEmpty()) {
                            return true;
                        }
                        String lowerCaseFiler = newValue.toLowerCase();
                        if (seat.getCompName().toLowerCase().indexOf(lowerCaseFiler) != -1) {
                            System.out.println("Er lik");
                            return true; //Filter sammenligner navn
                        } else if (seat.getColor().toLowerCase().indexOf(lowerCaseFiler) != -1) {
                            return true; //Filter sammenligner farge
                        } else if (String.valueOf(seat.getCompPrice()).toLowerCase().indexOf(lowerCaseFiler) != -1)
                            return true;
                        else return false; //hvis den ikke kan sammenlinges
                    });
                }));
                //Setter filteredlist i en sortedlist, binder de sammen til tableviewvet og legger sorted og filterreing til tabellen,
                sortedData = new SortedList<>(filteredList);
                sortedData.comparatorProperty().bind(seatView.comparatorProperty());
                seatView.setItems(sortedData);
            }

            if (spoilerTab.isSelected()){
                ObservableList<Spoiler> spoilerList = spoilerTable();
                FilteredList<Spoiler> filteredList = new FilteredList(spoilerList, b -> true);
                //predicater når filter skifter
                search.textProperty().addListener(((observable, oldValue, newValue) -> {

                    filteredList.setPredicate(spoiler -> {

                        //Hvis filter er tommt , vis alt
                        if (newValue == null || newValue.isEmpty()) {
                            return true;
                        }
                        String lowerCaseFiler = newValue.toLowerCase();
                        if (spoiler.getCompName().toLowerCase().indexOf(lowerCaseFiler) != -1) {
                            System.out.println("Er lik");
                            return true; //Filter sammenligner navn
                        } else if (spoiler.getSpoilerSide().toLowerCase().indexOf(lowerCaseFiler) != -1) {
                            return true; //Filter sammenligner farge
                        } else if (String.valueOf(spoiler.getCompPrice()).toLowerCase().indexOf(lowerCaseFiler) != -1)
                            return true;
                        else return false; //hvis den ikke kan sammenlinges
                    });
                }));

                //Setter filteredlist i en sortedlist, binder de sammen til tableviewvet og legger sorted og filterreing til tabellen,
                SortedList<Spoiler> sortedData = new SortedList<>(filteredList);
                sortedData.comparatorProperty().bind(spoilerView.comparatorProperty());
                spoilerView.setItems(sortedData);
            }



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

    @FXML
    public void toCompOrder(){
        System.out.println("TO COMP ORDER TABLE");
        try {

            URL url = getClass().getResource("/org/ccomp/admin/compOrderInfo.fxml");

            FXMLLoader loader = new FXMLLoader(url);
            setRoot = adminPane.getScene();
            setRoot.setRoot(loader.load());


            carCompView =  (TableView<CompOrder>) loader.getNamespace().get("carCompView");
            orderTypeColum =  (TableColumn<CompOrder,String>) loader.getNamespace().get("orderTypeColum");
            orderNrColum =  (TableColumn<CompOrder,Integer>) loader.getNamespace().get("orderNrColum");
            orderNameColum =  (TableColumn<CarComponent,String>) loader.getNamespace().get("orderNameColum");
            orderPriceColum =  (TableColumn<CarComponent,Double>) loader.getNamespace().get("orderPriceColum");
            orderQuntityColum =  (TableColumn<CarComponent,Integer>) loader.getNamespace().get("orderQuntityColum");

            carCompView.setItems(carComTable());


        } catch (IOException e) {
            e.printStackTrace();
        }


    }

    public void backToAdmin() {

        try {
            Stage stage = (Stage) backAdmin.getScene().getWindow();
            URL url = getClass().getResource("/org/ccomp/admin/admin.fxml");
            FXMLLoader loader = new FXMLLoader(url);
            Parent root = loader.load();
            Scene scene = new Scene(root, 600, 500);
            stage.setScene(scene);
            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

}



