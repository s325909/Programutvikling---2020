package org.ccomp.admin;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
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
import org.ccomp.model.Validation;
import org.ccomp.model.component.*;

import java.io.IOException;
import java.net.URL;
import java.util.*;

import static org.ccomp.admin.compGUI.CompController.alert;

public class AdminController {

    @FXML
    private Button addComp, backAdmin, backBtn, eraseCompOrder;

    @FXML
    private TextField search, searchCompOrder;

    @FXML
    private boolean delete;

    @FXML
    private AnchorPane adminPane;

    @FXML
    private Tab engineTab, seatTab, spoilerTab, steeringWheelTab, wheelRimTab, carComponentsTab, customerOrderTab;

    //OrderView
    @FXML
    private TableView<CompOrder> carCompView;
    @FXML
    private TableColumn<CompOrder, String> orderTypeColum;
    @FXML
    private TableColumn<CompOrder,Integer> orderNrColum;

    @FXML
    private TableColumn<CarComponent, String> orderNameColum;
    @FXML
    private TableColumn<CarComponent,Double> orderPriceColum;
    @FXML
    private TableColumn<CarComponent,Integer> orderQuntityColum;

    //OrderCustomer
    @FXML
    private TableView<CustomerOrder> customerOrderInfoView;
    @FXML
    private TableColumn<CustomerOrder,Integer> customerInfoOderNr;
    @FXML
    private TableColumn<CustomerOrder,String> customerInfoOrderName, customerInfoOrderEmail;

    @FXML
    private TableColumn<CustomerOrder,String> customerInfoOrderZip, customerInfoOrderMobilNr, customerInfoOrderCity;

    //Seat
    @FXML
    private TableView<Seat> seatView;
    @FXML
    private TableColumn<Seat, String> nameSeatColum, materiellColum, colorSeatColum;
    @FXML
    private TableColumn<Seat, Double> seatPriceColum;
    @FXML
    private TableColumn<Seat, Integer> quantitySeatColum;

    //Spoiler
    @FXML
    private TableView<Spoiler> spoilerView;
    @FXML
    private TableColumn<Spoiler, String> nameSpoilerColum, colorSpoilerColum, sideSpoilerColum;
    @FXML
    private TableColumn<Spoiler, Double> priceSpoilerColum;
    @FXML
    private TableColumn<Spoiler, Integer> quantitySpoilerColum;

    //SteeringWheel
    @FXML
    private TableView<SteeringWheel> sWheelView;
    @FXML
    private TableColumn<SteeringWheel, String> nameSWheelColum, materiellSWeel, colorSWheelColum;
    @FXML
    private TableColumn<SteeringWheel, Double> priceSWeelColum;
    @FXML
    private TableColumn<SteeringWheel, Integer>  quantitySWeelColum;;

    //WheelRim
    @FXML
    private TableView<WheelRim> wheelRimView;
    @FXML
    private TableColumn<WheelRim, String> nameWheelRimColum, dimensionWheelRim, colorWheelRim;
    @FXML
    private TableColumn<WheelRim, Double> priceWheelRim;
    @FXML
    private TableColumn<WheelRim,Integer> quantityWheelRim;


    //Engine
    @FXML
    private TableView<Engine> engineView;
    @FXML
    private TableColumn<Engine, String> engintypeColum, nameEngineColum;
    @FXML
    private TableColumn<Engine, Double>priceEngineColum;
    @FXML
    private TableColumn<Engine, Integer> horsepowerColum,quantityEngineColum;


    private ComponentOBJHandler jobjHandler;
    private ComponentCSVHandler csvHandler;

    private HashMap<String, List<CarComponent>> retrievedCompMap;
    private List<CarComponent> carComponents;
    private List<CustomerOrder> customerOrderList;

    private ObservableList<Engine> engines;
    private ObservableList<Seat> seats;
    private ObservableList<Spoiler> spoilers;
    private ObservableList<SteeringWheel> steeringWheels;
    private ObservableList<WheelRim> wheelRims;
    private ObservableList<CustomerOrder> customerOrders;
    private ObservableList<CompOrder> compOrders;

    private SortedList<Seat> sortedData;
    private SortedList<Engine> sorteEngineData;
    private SortedList<Spoiler> sortedSpoilerData;
    private SortedList<SteeringWheel> sortedSwheelData;
    private SortedList<WheelRim> sortedWheelRimData;
    private SortedList<CustomerOrder> sortedCustomerOrderData;
    private SortedList<CompOrder> sortedCompOrderList;

    //Car Components
    private Engine engine;
    private Seat seat;
    private Spoiler spoiler;
    private SteeringWheel steeringWheel;
    private WheelRim wheelRim;

    private CompOrder compOrder;
    private CustomerOrder customerOrder;

    private int row;
    private boolean initTabs, deleteComponent;

    private String orderId;
    private List<CompOrder> compOrderList;
    private static int selectedOrderId;

    private boolean emptyFields;

    @FXML
    public void initialize() {
        jobjHandler = new ComponentOBJHandler();

        retrievedCompMap = jobjHandler.readComponent(retrievedCompMap);

        System.out.println("INIT COMP MAP: " + retrievedCompMap );


        csvHandler = new ComponentCSVHandler();
        customerOrderList = csvHandler.readCustomerOrder(customerOrderList);
        System.out.println("INIT CUSTOMER ORDERS: " + customerOrderList.size());

        initTabs = true;
        initAllTables();
    }

    @FXML
    public void initAllTables(){

        System.out.println("ALL TABS ; INIT == " + initTabs);
        //return if method is run before Initialize()
        if (!initTabs) return;

        if (carComponentsTab != null && carComponentsTab.isSelected()) {
            System.out.println("PRODUCTS TAB");


            if (engineTab != null && engineTab.isSelected()) {
                System.out.println("ENGINE TAB");
                engineView.setItems(engineTable());
                engineView.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
                editEngine();
                searchComp();
            }
            if (seatTab != null && seatTab.isSelected()) {
                seatView.setItems(seatTable());
                seatView.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
                editSeatTable();
                System.out.println("SEAT TAB");
                searchComp();
            }
            if (spoilerTab != null && spoilerTab.isSelected()) {
                spoilerView.setItems(spoilerTable());
                editSpoilerTable();
                spoilerView.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
                System.out.println("SPOILER TAB");
                searchComp();
            }
            if (steeringWheelTab != null && steeringWheelTab.isSelected()) {
                sWheelView.setItems(sWheelTable());
                sWheelView.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
                editSwheelTable();
                searchComp();

            }

            if (wheelRimTab != null && wheelRimTab.isSelected()) {
                wheelRimView.setItems(wheelRTable());
                wheelRimView.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
                editWheelRim();
                searchComp();
            }

        } else if (customerOrderTab != null && customerOrderTab.isSelected()) {
            System.out.println("CUSTOMER ORDERS TAB");


            //Se bestillinger
            if (customerOrderTab != null && customerOrderTab.isSelected()) {
                //  carCompView.setItems(carComTable());
                // System.out.println("Customer");
                customerOrderInfoView.setItems(OrderInfoCustomer());
                editOrderCustomer();
                searchComp();

            }

        }
    }


    public ObservableList<Engine> engineTable(){
        engines = FXCollections.observableArrayList();
        //Getting the component Engine
        carComponents = retrievedCompMap.get("Engine");
        if (carComponents == null) carComponents = new ArrayList<>();
        //Presenting engines in a tableview with right values to right tablecloums.
        for (CarComponent carComponent : carComponents) {
            engine = (Engine) carComponent;
            engintypeColum.setCellValueFactory(new PropertyValueFactory<Engine, String>("compType"));
            nameEngineColum.setCellValueFactory(new PropertyValueFactory<Engine, String>("compName"));
            horsepowerColum.setCellValueFactory(new PropertyValueFactory<Engine, Integer>("engineHorsePower"));
            priceEngineColum.setCellValueFactory(new PropertyValueFactory<Engine, Double>("compPrice"));
            quantityEngineColum.setCellValueFactory(new PropertyValueFactory<Engine, Integer>("compQuantity"));
            engines.add(engine);
        }

        return engines;
    }


    public ObservableList<Seat> seatTable() {
        seats = FXCollections.observableArrayList();
        //Getting the component Seat
        carComponents = retrievedCompMap.get("Seat");
       if (carComponents == null) carComponents = new ArrayList<>();
        //Presenting seats in a tableview with right values to right tablecloums.
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
        spoilers = FXCollections.observableArrayList();
        //Getting the component Spoiler
        carComponents = retrievedCompMap.get("Spoiler");
        if (carComponents == null) carComponents = new ArrayList<>();
        //Presenting seats in a tableview with right values to right tablecloums.
        for (CarComponent carComponent : carComponents) {
            spoiler = (Spoiler) carComponent;
            nameSpoilerColum.setCellValueFactory(new PropertyValueFactory<Spoiler, String>("compName"));
            colorSpoilerColum.setCellValueFactory(new PropertyValueFactory<Spoiler,String>("spoilerColor"));
            sideSpoilerColum.setCellValueFactory(new PropertyValueFactory<Spoiler, String>("spoilerSide"));
            priceSpoilerColum.setCellValueFactory(new PropertyValueFactory<Spoiler, Double>("compPrice"));
            quantitySpoilerColum.setCellValueFactory(new PropertyValueFactory<Spoiler, Integer>("compQuantity"));
            spoilers.add(spoiler);
        }
        return spoilers;
    }

    public ObservableList<SteeringWheel> sWheelTable() {
        steeringWheels = FXCollections.observableArrayList();
        //Getting the component steeringwheel
        carComponents = retrievedCompMap.get("SteeringWheel");
        if (carComponents == null) carComponents = new ArrayList<>();
        //Presenting steeeringwheels in a tableview with right values to right tablecloums.
        for (CarComponent carComponent : carComponents) {
            steeringWheel = (SteeringWheel) carComponent;
            nameSWheelColum.setCellValueFactory(new PropertyValueFactory<SteeringWheel, String>("compName"));
            colorSWheelColum.setCellValueFactory(new PropertyValueFactory<SteeringWheel, String>("steeringWheelColor"));
            materiellSWeel.setCellValueFactory(new PropertyValueFactory<SteeringWheel, String>("steeringWheelMaterial"));
            priceSWeelColum.setCellValueFactory(new PropertyValueFactory<SteeringWheel, Double>("compPrice"));
            quantitySWeelColum.setCellValueFactory(new PropertyValueFactory<SteeringWheel, Integer>("compQuantity"));
            steeringWheels.add(steeringWheel);
        }

        return steeringWheels;
    }

    public ObservableList<WheelRim> wheelRTable() {
        wheelRims = FXCollections.observableArrayList();
        //Getting the component wheelrim
        carComponents = retrievedCompMap.get("WheelRim");
        if (carComponents == null) carComponents = new ArrayList<>();
        //Presenting wheelrims in a tableview with right values to right tablecloums.
        for (CarComponent carComponent : carComponents) {
            wheelRim = (WheelRim) carComponent;
            nameWheelRimColum.setCellValueFactory(new PropertyValueFactory<WheelRim, String>("compName"));
            dimensionWheelRim.setCellValueFactory(new PropertyValueFactory<WheelRim, String>("wheelRimDimension"));
            colorWheelRim.setCellValueFactory(new PropertyValueFactory<WheelRim, String>("wheelRimColor"));
            priceWheelRim.setCellValueFactory(new PropertyValueFactory<WheelRim, Double>("compPrice"));
            quantityWheelRim.setCellValueFactory(new PropertyValueFactory<WheelRim, Integer>("compQuantity"));
            wheelRims.add(wheelRim);
        }

        return wheelRims;
    }

    public ObservableList<CustomerOrder> OrderInfoCustomer(){
     //   if (customerOrderList.size() == 0) return null;
        customerOrders = FXCollections.observableArrayList();
        //Presenting the customer registrated info in a tablview with right values to the right tableclum
        for (CustomerOrder customerOrder : customerOrderList) {
           // customer = customerOrder.getCustomer();
            customerInfoOderNr.setCellValueFactory(new PropertyValueFactory<CustomerOrder,Integer>("customerOrderNr"));
            customerInfoOrderName.setCellValueFactory(new PropertyValueFactory<CustomerOrder,String>("customerName"));
            customerInfoOrderEmail.setCellValueFactory(new PropertyValueFactory<CustomerOrder,String>("customerMail"));
            customerInfoOrderZip.setCellValueFactory(new PropertyValueFactory<CustomerOrder,String>("customerZipCode"));
            customerInfoOrderMobilNr.setCellValueFactory(new PropertyValueFactory<CustomerOrder,String>("customerNumber"));
            customerInfoOrderCity.setCellValueFactory(new PropertyValueFactory<CustomerOrder,String>("customerCity"));
            customerOrders.add(customerOrder);
        }

        return customerOrders;
    }

    public  ObservableList<CompOrder> carComTable(){

        compOrders = FXCollections.observableArrayList();

        orderId = String.valueOf(selectedOrderId);
        if (selectedOrderId != -1) compOrderList = csvHandler.searchOrderRow(orderId);
        else {
            System.out.println("NO CUSTOMER ORDER SELECTED!!!");
            compOrderList = csvHandler.readCompOrder(compOrderList);
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
    public void deleteSelectedRow() {

        if (carComponentsTab != null && carComponentsTab.isSelected()) {
            System.out.println("PRODUCTS TAB");

            deleteComponent = false;

            //Engine
            if (engineTab.isSelected()) {
                int visibleIndex = engineView.getSelectionModel().getSelectedIndex();

                //return if nothing selected
                if (visibleIndex == -1) return;

                // Source index of ObservableList
                int sourceIndex = sorteEngineData.getSourceIndexFor(engines, visibleIndex);

                // Remove from ObservableList using the index
                engines.remove(sourceIndex);

                //Remove selected component from HashMap containing all components
                retrievedCompMap.get("Engine").remove(sourceIndex);


                deleteComponent = true;
            }

            //Seat
            if (seatTab.isSelected()) {
                int visibleIndex = seatView.getSelectionModel().getSelectedIndex();

                //return if nothing selected
                if (visibleIndex == -1) return;

                int sourceIndex = sortedData.getSourceIndexFor(seats, visibleIndex);
                seats.remove(sourceIndex);
                retrievedCompMap.get("Seat").remove(sourceIndex);
                deleteComponent = true;
            }

            //Spoiler
            if (spoilerTab.isSelected()) {
                int visibleIndex = spoilerView.getSelectionModel().getSelectedIndex();

                //return if nothing selected
                if (visibleIndex == -1) return;

                int sourceIndex = sortedSpoilerData.getSourceIndexFor(spoilers, visibleIndex);
                spoilers.remove(sourceIndex);
                retrievedCompMap.get("Spoiler").remove(sourceIndex);
                deleteComponent = true;
            }

            //SteeringWheel
            if (steeringWheelTab.isSelected()) {
                int visibleIndex = sWheelView.getSelectionModel().getSelectedIndex();

                //return if nothing selected
                if (visibleIndex == -1) return;

                int sourceIndex = sortedSwheelData.getSourceIndexFor(steeringWheels, visibleIndex);
                steeringWheels.remove(sourceIndex);
                retrievedCompMap.get("SteeringWheel").remove(sourceIndex);
                deleteComponent = true;
            }

            //WheelRim
            if (wheelRimTab.isSelected()) {
                int visibleIndex = wheelRimView.getSelectionModel().getSelectedIndex();

                //return if nothing selected
                if (visibleIndex == -1) return;

                int sourceIndex = sortedWheelRimData.getSourceIndexFor(wheelRims, visibleIndex);
                wheelRims.remove(sourceIndex);
                retrievedCompMap.get("WheelRim").remove(sourceIndex);
                deleteComponent = true;
            }


            // Write Components HashMap to file if changes has been made
            if (deleteComponent) jobjHandler.writeComponent(retrievedCompMap);

        } else if (customerOrderTab != null && customerOrderTab.isSelected()) {
            System.out.println("CUSTOMER ORDERS TAB");


            if (customerOrderTab.isSelected()) {
                int visibleIndex = customerOrderInfoView.getSelectionModel().getSelectedIndex();

                //return if nothing selected
                if (visibleIndex == -1) return;

                int sourceIndex = sortedCustomerOrderData.getSourceIndexFor(customerOrders, visibleIndex);


                customerOrder = customerOrderList.get(sourceIndex);
                System.out.println("DELETE CUSTOMER ORDER: " + customerOrder.toCSVFormat());

                customerOrders.remove(sourceIndex);
                customerOrderList.remove(sourceIndex);


                //Remove selected Customer Order from file
                csvHandler.removeCustomerOrder(customerOrder);

                compOrderList = csvHandler.readCompOrder(compOrderList);
                System.out.println("COMP ORDER LIST SIZE: " + compOrderList.size());

                //Remove CompOrders whith same OrderNr from file
                for (CompOrder compOrder : compOrderList) {
                    if (compOrder.getOrderId() == customerOrder.getOrderId()) {
                        System.out.println("REMOVE COMP ORDER: " + compOrder.toCSVFormat());
                        csvHandler.removeCompOrder(compOrder);
                    }
                }
            }
        }
    }


    public void deleteCompOrder(){


        int visibleIndex = carCompView.getSelectionModel().getSelectedIndex();

        //return if nothing selected
        if (visibleIndex == -1) return;



        if (compOrders == null) compOrders = carComTable();


        int sourceIndex;

        if (sortedCompOrderList != null) {
            sourceIndex = sortedCompOrderList.getSourceIndexFor(compOrders, visibleIndex);
            compOrder = compOrderList.get(sourceIndex);
        } else {
            compOrder = carCompView.getSelectionModel().getSelectedItem();
            carCompView.getItems().remove(compOrder);
        }


        System.out.println("DELETE SELECTED COMP ORDER: " + compOrder.toCSVFormat());


        csvHandler.removeCompOrder(compOrder);


        compOrders.remove(compOrder);
        compOrderList.remove(compOrder);


        //todo: Delete CustomerOrder if all CompOrders with same OrderNr is deleted
    }

    public void searchCompOrders(){

        if (compOrders == null) compOrders = carComTable();

       // ObservableList<CompOrder> compOrdersList = compOrders;


        FilteredList<CompOrder> filteredList = new FilteredList(compOrders, b -> true);
        searchCompOrder.textProperty().addListener(((observable, oldValue, newValue) -> {
            filteredList.setPredicate( carcomp-> {
                if (newValue == null || newValue.isEmpty()) {
                    return true;
                }
                String lowerCaseFiler = newValue.toLowerCase();
                if (carcomp.getCompName().toLowerCase().indexOf(lowerCaseFiler) != -1) {
                    return true;
                } else if (carcomp.getCompType().toLowerCase().indexOf(lowerCaseFiler) != -1) {
                    return true;
                } else if (String.valueOf(carcomp.getOrderId()).toLowerCase().indexOf(lowerCaseFiler) != -1)
                    return true;
                else return false;
            });
        }));

        sortedCompOrderList = new SortedList<>(filteredList);
        sortedCompOrderList.comparatorProperty().bind(carCompView.comparatorProperty());
        carCompView.setItems(sortedCompOrderList);

    }

    public void  searchComp() {
        //Engine
        if (engineTab.isSelected()) {
            ObservableList<Engine> engineList = engineTable();
            FilteredList<Engine> filteredList = new FilteredList(engineList, b -> true);
            //predicater when filter shifts
            search.textProperty().addListener(((observable, oldValue, newValue) -> {

                filteredList.setPredicate(engine -> {

                    //If filter is empty , show the already existing table
                    if (newValue == null || newValue.isEmpty()) {
                        return true;
                    }
                    String lowerCaseFiler = newValue.toLowerCase();
                    if (engine.getCompName().toLowerCase().indexOf(lowerCaseFiler) != -1) {

                        return true; //Filter comparing  name
                    } else if (engine.getCompType().toLowerCase().indexOf(lowerCaseFiler) != -1) {
                        return true; //Filter comparing color
                    } else if (String.valueOf(engine.getCompPrice()).toLowerCase().indexOf(lowerCaseFiler) != -1)
                        return true;
                    else return false; //if it can not compare
                });
            }));
            //Setting the filteredlistin to an sortedlist and binds them togheter to the tablview and putting sorted & filtering to the table
            sorteEngineData = new SortedList<>(filteredList);
            sorteEngineData.comparatorProperty().bind(engineView.comparatorProperty());
            engineView.setItems(sorteEngineData);
        }



        //Seat
        if (seatTab.isSelected()) {
            ObservableList<Seat> seatsList = seatTable();
            FilteredList<Seat> filteredList = new FilteredList(seatsList, b -> true);
            search.textProperty().addListener(((observable, oldValue, newValue) -> {
                filteredList.setPredicate(seat -> {
                    if (newValue == null || newValue.isEmpty()) {
                        return true;
                    }
                    String lowerCaseFiler = newValue.toLowerCase();
                    if (seat.getCompName().toLowerCase().indexOf(lowerCaseFiler) != -1) {

                        return true;
                    } else if (seat.getSeatColor().toLowerCase().indexOf(lowerCaseFiler) != -1) {
                        return true;
                    } else if (String.valueOf(seat.getCompPrice()).toLowerCase().indexOf(lowerCaseFiler) != -1)
                        return true;
                    else return false;
                });
            }));
            sortedData = new SortedList<>(filteredList);
            sortedData.comparatorProperty().bind(seatView.comparatorProperty());
            seatView.setItems(sortedData);
        }
        //Spoiler
        if (spoilerTab.isSelected()){
            ObservableList<Spoiler> spoilerList = spoilerTable();
            FilteredList<Spoiler> filteredList = new FilteredList(spoilerList, b -> true);
            search.textProperty().addListener(((observable, oldValue, newValue) -> {
                filteredList.setPredicate(spoiler -> {
                    if (newValue == null || newValue.isEmpty()) {
                        return true;
                    }
                    String lowerCaseFiler = newValue.toLowerCase();
                    if (spoiler.getCompName().toLowerCase().indexOf(lowerCaseFiler) != -1) {
                        return true;
                    } else if (spoiler.getSpoilerSide().toLowerCase().indexOf(lowerCaseFiler) != -1) {
                        return true;
                    } else if (String.valueOf(spoiler.getCompPrice()).toLowerCase().indexOf(lowerCaseFiler) != -1)
                        return true;
                    else return false;
                });
            }));
            sortedSpoilerData = new SortedList<>(filteredList);
            sortedSpoilerData.comparatorProperty().bind(spoilerView.comparatorProperty());
            spoilerView.setItems(sortedSpoilerData);
        }

        //SteeringWheel
        if (steeringWheelTab.isSelected()){
            ObservableList<SteeringWheel> steeringWheelList = sWheelTable();
            FilteredList<SteeringWheel> filteredList = new FilteredList(steeringWheelList, b -> true);
            //predicater når filter skifter
            search.textProperty().addListener(((observable, oldValue, newValue) -> {

                filteredList.setPredicate(steeringWheel -> {
                    if (newValue == null || newValue.isEmpty()) {
                        return true;
                    }
                    String lowerCaseFiler = newValue.toLowerCase();
                    if (steeringWheel.getCompName().toLowerCase().indexOf(lowerCaseFiler) != -1) {
                        return true;
                    } else if (steeringWheel.getSteeringWheelColor().toLowerCase().indexOf(lowerCaseFiler) != -1) {
                        return true;
                    } else if (String.valueOf(steeringWheel.getCompPrice()).toLowerCase().indexOf(lowerCaseFiler) != -1)
                        return true;
                    else return false;
                });
            }));
            sortedSwheelData = new SortedList<>(filteredList);
            sortedSwheelData.comparatorProperty().bind(sWheelView.comparatorProperty());
            sWheelView.setItems(sortedSwheelData);

        }
        //WheelRim
        if (wheelRimTab.isSelected()){
            ObservableList<WheelRim> wheelRimList = wheelRTable();
            FilteredList<WheelRim> filteredList = new FilteredList(wheelRimList, b -> true);
            search.textProperty().addListener(((observable, oldValue, newValue) -> {
                filteredList.setPredicate(wheelRim -> {
                    if (newValue == null || newValue.isEmpty()) {
                        return true;
                    }
                    String lowerCaseFiler = newValue.toLowerCase();
                    if (wheelRim.getCompName().toLowerCase().indexOf(lowerCaseFiler) != -1) {
                        return true;
                    } else if (wheelRim.getWheelRimColor().toLowerCase().indexOf(lowerCaseFiler) != -1) {
                        return true;
                    } else if (String.valueOf(wheelRim.getCompPrice()).toLowerCase().indexOf(lowerCaseFiler) != -1)
                        return true;
                    else return false;
                });
            }));
            sortedWheelRimData = new SortedList<>(filteredList);
            sortedWheelRimData.comparatorProperty().bind(wheelRimView.comparatorProperty());
            wheelRimView.setItems(sortedWheelRimData);

        }

        if (customerOrderTab.isSelected()){
            ObservableList<CustomerOrder> customerOrderList =OrderInfoCustomer();
            FilteredList<CustomerOrder> filteredList = new FilteredList(customerOrderList, b -> true);
            search.textProperty().addListener(((observable, oldValue, newValue) -> {
                filteredList.setPredicate( customerOrder-> {
                    if (newValue == null || newValue.isEmpty()) {
                        return true;
                    }
                    String lowerCaseFiler = newValue.toLowerCase();
                    if (customerOrder.getCustomerName().toLowerCase().indexOf(lowerCaseFiler) != -1) {
                        return true;
                    } else if (customerOrder.getCustomerNumber().toLowerCase().indexOf(lowerCaseFiler) != -1) {
                        return true;
                    } else if (String.valueOf(customerOrder.getOrderId()).toLowerCase().indexOf(lowerCaseFiler) != -1)
                        return true;
                    else return false;
                });
            }));

            sortedCustomerOrderData = new SortedList<>(filteredList);
            sortedCustomerOrderData.comparatorProperty().bind(customerOrderInfoView.comparatorProperty());
            customerOrderInfoView.setItems(sortedCustomerOrderData);
        }
    }

    public void editEngine() {
        engineView.setEditable(true);

        if (carComponents.size() == 0) return;
        engine = (Engine) carComponents.get(row);
        //When editing a tablecolum it will set and get the value when editing in the right colum
        engintypeColum.setCellFactory(TextFieldTableCell.forTableColumn());
        engintypeColum.setOnEditCommit((TableColumn.CellEditEvent<Engine, String> t) -> {
            t.getTableView().getItems();
            row = t.getTablePosition().getRow();
            String value = t.getNewValue().replaceAll("[-+.^:,]","");
            System.out.println(value);
            carComponents = retrievedCompMap.get("Engine");
            engine = ((Engine) carComponents.get(row));
            engine.setCompType(value);
            retrievedCompMap.get("Engine").set(row, engine);
            jobjHandler.writeComponent(retrievedCompMap);
            System.out.println(engine.getCompType());
            (t.getTableView().getItems().get(
                    t.getTablePosition().getRow())
            ).setCompType(t.getNewValue());
        });


        nameEngineColum.setCellFactory(TextFieldTableCell.forTableColumn());
        nameEngineColum.setOnEditCommit((TableColumn.CellEditEvent<Engine, String> t) -> {
            t.getTableView().getItems();
            row = t.getTablePosition().getRow();
            String value = t.getNewValue().replaceAll("[-+.^:,]","");
            System.out.println(value);
            carComponents = retrievedCompMap.get("Engine");
            engine = ((Engine) carComponents.get(row));
            engine.setCompName(value);
            retrievedCompMap.get("Engine").set(row, engine);
            jobjHandler.writeComponent(retrievedCompMap);
            System.out.println(engine.getCompType());
            (t.getTableView().getItems().get(
                    t.getTablePosition().getRow())
            ).setCompName(t.getNewValue());
        });


        horsepowerColum.setCellFactory(TextFieldTableCell.forTableColumn(new IntegerStringConverter()));
        horsepowerColum.setOnEditCommit((TableColumn.CellEditEvent<Engine, Integer> t) -> {
            t.getTableView().getItems();
            row = t.getTablePosition().getRow();
            //Integer value = t.getNewValue();
            String newValue = String.valueOf(t.getNewValue()).trim();
            int value = validateIntegerColumn(newValue, 0);

            System.out.println(value);
            carComponents = retrievedCompMap.get("Engine");
            engine = ((Engine) carComponents.get(row));
            engine.setEngineHorsePower(value);
            retrievedCompMap.get("Engine").set(row, engine);
            jobjHandler.writeComponent(retrievedCompMap);
            (t.getTableView().getItems().get(
                    t.getTablePosition().getRow())
            ).setEngineHorsePower(value
            );
        });

        priceEngineColum.setCellFactory(TextFieldTableCell.forTableColumn(new DoubleStringConverter()));
        priceEngineColum.setOnEditCommit((TableColumn.CellEditEvent<Engine, Double> t) -> {
            t.getTableView().getItems();
            row = t.getTablePosition().getRow();
            Double value = t.getNewValue();
            System.out.println(value);
            carComponents = retrievedCompMap.get("Engine");
            engine = ((Engine) carComponents.get(row));
            engine.setCompPrice(value);
            retrievedCompMap.get("Engine").set(row, engine);
            jobjHandler.writeComponent(retrievedCompMap);
            (t.getTableView().getItems().get(
                    t.getTablePosition().getRow())
            ).setCompPrice(t.getNewValue()
            );
        });

        quantityEngineColum.setCellFactory(TextFieldTableCell.forTableColumn(new IntegerStringConverter()));
        quantityEngineColum.setOnEditCommit((TableColumn.CellEditEvent<Engine, Integer> t) -> {
            t.getTableView().getItems();
            row = t.getTablePosition().getRow();
            Integer value = t.getNewValue();
            System.out.println(value);
            carComponents = retrievedCompMap.get("Engine");
            engine = ((Engine) carComponents.get(row));
            engine.setCompQuantity(value);
            retrievedCompMap.get("Engine").set(row, engine);
            jobjHandler.writeComponent(retrievedCompMap);
            (t.getTableView().getItems().get(
                    t.getTablePosition().getRow())
            ).setCompQuantity(t.getNewValue()
            );
        });

        //todo: validation before writing to file!!!
       // jobjHandler.writeComponent(retrievedCompMap);
    }

    public void editSeatTable() {
        seatView.setEditable(true);

        if (carComponents.size() == 0) return;

        seat = (Seat) carComponents.get(row);

        nameSeatColum.setCellFactory(TextFieldTableCell.forTableColumn());
        nameSeatColum.setOnEditCommit((TableColumn.CellEditEvent<Seat, String> t) -> {
            t.getTableView().getItems();
            row = t.getTablePosition().getRow();
            String value = t.getNewValue().replaceAll("[-+.^:,]","");
            System.out.println(value);
            carComponents = retrievedCompMap.get("Seat");
            seat = ((Seat) carComponents.get(row));
            seat.setCompName(value);
            retrievedCompMap.get("Seat").set(row, seat);
            jobjHandler.writeComponent(retrievedCompMap);
            (t.getTableView().getItems().get(
                    t.getTablePosition().getRow())
            ).setCompName(t.getNewValue()
            );
        });

        materiellColum.setCellFactory(TextFieldTableCell.forTableColumn());
        materiellColum.setOnEditCommit((TableColumn.CellEditEvent<Seat, String> t) -> {
            t.getTableView().getItems();
            row = t.getTablePosition().getRow();
            String value = t.getNewValue().replaceAll("[-+.^:,]","");
            System.out.println(value);
            carComponents = retrievedCompMap.get("Seat");
            seat = ((Seat) carComponents.get(row));
            seat.setSeatMaterial(value);
            retrievedCompMap.get("Seat").set(row, seat);
            jobjHandler.writeComponent(retrievedCompMap);
            System.out.println(seat.getSeatMaterial());
            (t.getTableView().getItems().get(
                    t.getTablePosition().getRow())
            ).setSeatMaterial(t.getNewValue()
            );
        });

        colorSeatColum.setCellFactory(TextFieldTableCell.forTableColumn());
        colorSeatColum.setOnEditCommit((TableColumn.CellEditEvent<Seat, String> t) -> {
            t.getTableView().getItems();
            row = t.getTablePosition().getRow();
            String value = t.getNewValue().replaceAll("[-+.^:,]","");
            System.out.println(value);
            carComponents = retrievedCompMap.get("Seat");
            seat = ((Seat) carComponents.get(row));
            seat.setSeatColor(value);
            retrievedCompMap.get("Seat").set(row, seat);
            jobjHandler.writeComponent(retrievedCompMap);
            (t.getTableView().getItems().get(
                    t.getTablePosition().getRow())
            ).setSeatColor(t.getNewValue()
            );
        });

        seatPriceColum.setCellFactory(TextFieldTableCell.forTableColumn(new DoubleStringConverter()));
        seatPriceColum.setOnEditCommit((TableColumn.CellEditEvent<Seat, Double> t) -> {
            t.getTableView().getItems();
            row = t.getTablePosition().getRow();

            String doubleValue = String.valueOf(t.getNewValue()).trim();

            Double value;
            try {
                value = Double.parseDouble(doubleValue);
            } catch (NumberFormatException e) {
                value = Double.parseDouble("0");
            }
            System.out.println(value);
            carComponents = retrievedCompMap.get("Seat");
            seat = ((Seat) carComponents.get(row));
            seat.setCompPrice(value);
            retrievedCompMap.get("Seat").set(row, seat);
            jobjHandler.writeComponent(retrievedCompMap);
            (t.getTableView().getItems().get(
                    t.getTablePosition().getRow())
            ).setCompPrice(t.getNewValue()
            );

        });

        quantitySeatColum.setCellFactory(TextFieldTableCell.forTableColumn(new IntegerStringConverter()));
        quantitySeatColum.setOnEditCommit((TableColumn.CellEditEvent<Seat, Integer> t) -> {
            t.getTableView().getItems();
            row = t.getTablePosition().getRow();
            Integer value = t.getNewValue();
            System.out.println(value);
            carComponents = retrievedCompMap.get("Seat");
            seat = ((Seat) carComponents.get(row));
            seat.setCompQuantity(value);
            retrievedCompMap.get("Seat").set(row, seat);
            jobjHandler.writeComponent(retrievedCompMap);
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

        nameSpoilerColum.setCellFactory(TextFieldTableCell.forTableColumn());
        nameSpoilerColum.setOnEditCommit((TableColumn.CellEditEvent<Spoiler, String> t) -> {
            t.getTableView().getItems();
            row = t.getTablePosition().getRow();
            String value = t.getNewValue().replaceAll("[-+.^:,]","");
            System.out.println(value);
            carComponents = retrievedCompMap.get("Spoiler");
            spoiler = ((Spoiler) carComponents.get(row));
            spoiler.setCompName(value);
            retrievedCompMap.get("Spoiler").set(row, spoiler);
            jobjHandler.writeComponent(retrievedCompMap);
            (t.getTableView().getItems().get(
                    t.getTablePosition().getRow())
            ).setCompName(t.getNewValue()
            );
        });

        colorSpoilerColum.setCellFactory(TextFieldTableCell.forTableColumn());
        colorSpoilerColum.setOnEditCommit((TableColumn.CellEditEvent<Spoiler, String> t) -> {
            t.getTableView().getItems();
            row = t.getTablePosition().getRow();
            String value = t.getNewValue().replaceAll("[-+.^:,]","");
            System.out.println(value);
            carComponents = retrievedCompMap.get("Spoiler");
            spoiler = ((Spoiler) carComponents.get(row));
            spoiler.setSpoilerColor(value);
            retrievedCompMap.get("Spoiler").set(row, spoiler);
            jobjHandler.writeComponent(retrievedCompMap);
            (t.getTableView().getItems().get(
                    t.getTablePosition().getRow())
            ).setSpoilerColor(t.getNewValue()
            );
        });

        sideSpoilerColum.setCellFactory(TextFieldTableCell.forTableColumn());
        sideSpoilerColum.setOnEditCommit((TableColumn.CellEditEvent<Spoiler, String> t) -> {
            t.getTableView().getItems();
            row = t.getTablePosition().getRow();
            String value = t.getNewValue().replaceAll("[-+.^:,]","");
            System.out.println(value);
            carComponents = retrievedCompMap.get("Spoiler");
            spoiler = ((Spoiler) carComponents.get(row));
            spoiler.setSpoilerSide(value);
            retrievedCompMap.get("Spoiler").set(row, spoiler);
            jobjHandler.writeComponent(retrievedCompMap);
            (t.getTableView().getItems().get(
                    t.getTablePosition().getRow())
            ).setSpoilerSide(t.getNewValue()
            );
        });

        priceSpoilerColum.setCellFactory(TextFieldTableCell.forTableColumn(new DoubleStringConverter()));
        priceSpoilerColum.setOnEditCommit((TableColumn.CellEditEvent<Spoiler, Double> t) -> {
            t.getTableView().getItems();
            row = t.getTablePosition().getRow();
            Double value = t.getNewValue();
            System.out.println(value);
            carComponents = retrievedCompMap.get("Spoiler");
            spoiler = ((Spoiler) carComponents.get(row));
            spoiler.setCompPrice(value);
            retrievedCompMap.get("Spoiler").set(row, spoiler);
            jobjHandler.writeComponent(retrievedCompMap);
            (t.getTableView().getItems().get(
                    t.getTablePosition().getRow())
            ).setCompPrice(t.getNewValue()
            );
        });

        quantitySpoilerColum.setCellFactory(TextFieldTableCell.forTableColumn(new IntegerStringConverter()));
        quantitySpoilerColum.setOnEditCommit((TableColumn.CellEditEvent<Spoiler, Integer> t) -> {
            t.getTableView().getItems();
            row = t.getTablePosition().getRow();
            Integer value = t.getNewValue();
            System.out.println(value);
            carComponents = retrievedCompMap.get("Spoiler");
            spoiler = ((Spoiler) carComponents.get(row));
            spoiler.setCompQuantity(value);
            retrievedCompMap.get("Spoiler").set(row, spoiler);
            jobjHandler.writeComponent(retrievedCompMap);
            (t.getTableView().getItems().get(
                    t.getTablePosition().getRow())
            ).setCompQuantity(t.getNewValue()
            );
        });
    }

    public void editSwheelTable(){
        sWheelView.setEditable(true);
        if (carComponents.size() == 0) return;

        steeringWheel = (SteeringWheel) carComponents.get(row);

        nameSWheelColum.setCellFactory(TextFieldTableCell.forTableColumn());
        nameSWheelColum.setOnEditCommit((TableColumn.CellEditEvent<SteeringWheel, String> t) -> {
            t.getTableView().getItems();
            row = t.getTablePosition().getRow();
            String value = t.getNewValue().replaceAll("[-+.^:,]","");
            System.out.println(value);
            carComponents = retrievedCompMap.get("SteeringWheel");
            steeringWheel = ((SteeringWheel) carComponents.get(row));
            steeringWheel.setCompName(value);
            retrievedCompMap.get("SteeringWheel").set(row, steeringWheel);
            jobjHandler.writeComponent(retrievedCompMap);
            (t.getTableView().getItems().get(
                    t.getTablePosition().getRow())
            ).setCompName(t.getNewValue()
            );
        });

        materiellSWeel.setCellFactory(TextFieldTableCell.forTableColumn());
        materiellSWeel.setOnEditCommit((TableColumn.CellEditEvent<SteeringWheel, String> t) -> {
            t.getTableView().getItems();
            row = t.getTablePosition().getRow();
            String value = t.getNewValue().replaceAll("[-+.^:,]","");
            System.out.println(value);
            carComponents = retrievedCompMap.get("SteeringWheel");
            steeringWheel = ((SteeringWheel) carComponents.get(row));
            steeringWheel.setSteeringWheelMaterial(value);
            retrievedCompMap.get("SteeringWheel").set(row, steeringWheel);
            jobjHandler.writeComponent(retrievedCompMap);
            (t.getTableView().getItems().get(
                    t.getTablePosition().getRow())
            ).setSteeringWheelMaterial(t.getNewValue()
            );
        });

        colorSWheelColum.setCellFactory(TextFieldTableCell.forTableColumn());
        colorSWheelColum.setOnEditCommit((TableColumn.CellEditEvent<SteeringWheel, String> t) -> {
            t.getTableView().getItems();
            row = t.getTablePosition().getRow();
            String value = t.getNewValue().replaceAll("[-+.^:,]","");
            System.out.println(value);
            carComponents = retrievedCompMap.get("SteeringWheel");
            steeringWheel = ((SteeringWheel) carComponents.get(row));
            steeringWheel.setSteeringWheelColor(value);
            retrievedCompMap.get("SteeringWheel").set(row, steeringWheel);
            jobjHandler.writeComponent(retrievedCompMap);
            (t.getTableView().getItems().get(
                    t.getTablePosition().getRow())
            ).setSteeringWheelColor(t.getNewValue()
            );
        });

        priceSWeelColum.setCellFactory(TextFieldTableCell.forTableColumn(new DoubleStringConverter()));
        priceSWeelColum.setOnEditCommit((TableColumn.CellEditEvent<SteeringWheel, Double> t) -> {
            t.getTableView().getItems();
            row = t.getTablePosition().getRow();
            Double value = t.getNewValue();
            System.out.println(value);
            carComponents = retrievedCompMap.get("SteeringWheel");
            steeringWheel = ((SteeringWheel) carComponents.get(row));
            steeringWheel.setCompPrice(value);
            retrievedCompMap.get("SteeringWheel").set(row, steeringWheel);
            jobjHandler.writeComponent(retrievedCompMap);
            (t.getTableView().getItems().get(
                    t.getTablePosition().getRow())
            ).setCompPrice(t.getNewValue()
            );
        });

        quantitySWeelColum.setCellFactory(TextFieldTableCell.forTableColumn(new IntegerStringConverter()));
        quantitySWeelColum.setOnEditCommit((TableColumn.CellEditEvent<SteeringWheel, Integer> t) -> {
            t.getTableView().getItems();
            row = t.getTablePosition().getRow();
            Integer value = t.getNewValue();
            System.out.println(value);
            carComponents = retrievedCompMap.get("SteeringWheel");
            steeringWheel = ((SteeringWheel) carComponents.get(row));
            steeringWheel.setCompQuantity(value);
            retrievedCompMap.get("SteeringWheel").set(row, steeringWheel);
            jobjHandler.writeComponent(retrievedCompMap);
            (t.getTableView().getItems().get(
                    t.getTablePosition().getRow())
            ).setCompQuantity(t.getNewValue()
            );
        });
    }

    public void editWheelRim(){
        wheelRimView.setEditable(true);
        if (carComponents.size() == 0) return;

        wheelRim = (WheelRim) carComponents.get(row);

        nameWheelRimColum.setCellFactory(TextFieldTableCell.forTableColumn());
        nameWheelRimColum.setOnEditCommit((TableColumn.CellEditEvent<WheelRim, String> t) -> {
            t.getTableView().getItems();
            row = t.getTablePosition().getRow();
            String value = t.getNewValue().replaceAll("[-+.^:,]","");
            System.out.println(value);
            carComponents = retrievedCompMap.get("WheelRim");
            wheelRim = ((WheelRim) carComponents.get(row));
            wheelRim.setCompName(value);
            retrievedCompMap.get("WheelRim").set(row, wheelRim);
            jobjHandler.writeComponent(retrievedCompMap);
            (t.getTableView().getItems().get(
                    t.getTablePosition().getRow())
            ).setCompName(t.getNewValue()
            );
        });

        dimensionWheelRim.setCellFactory(TextFieldTableCell.forTableColumn());
        dimensionWheelRim.setOnEditCommit((TableColumn.CellEditEvent<WheelRim, String> t) -> {
            t.getTableView().getItems();
            row = t.getTablePosition().getRow();
            String value = t.getNewValue().replaceAll("[-+.^:,]","");
            System.out.println(value);
            carComponents = retrievedCompMap.get("WheelRim");
            wheelRim = ((WheelRim) carComponents.get(row));
            wheelRim.setWheelRimDimension(value);
            retrievedCompMap.get("WheelRim").set(row, wheelRim);
            jobjHandler.writeComponent(retrievedCompMap);
            (t.getTableView().getItems().get(
                    t.getTablePosition().getRow())
            ).setWheelRimDimension(t.getNewValue()
            );
        });

        colorWheelRim.setCellFactory(TextFieldTableCell.forTableColumn());
        colorWheelRim.setOnEditCommit((TableColumn.CellEditEvent<WheelRim, String> t) -> {
            t.getTableView().getItems();
            row = t.getTablePosition().getRow();
            String value = t.getNewValue().replaceAll("[-+.^:,]","");
            System.out.println(value);
            carComponents = retrievedCompMap.get("WheelRim");
            wheelRim = ((WheelRim) carComponents.get(row));
            wheelRim.setWheelRimColor(value);
            retrievedCompMap.get("WheelRim").set(row, wheelRim);
            jobjHandler.writeComponent(retrievedCompMap);
            (t.getTableView().getItems().get(
                    t.getTablePosition().getRow())
            ).setWheelRimColor(t.getNewValue()
            );
        });

        priceWheelRim.setCellFactory(TextFieldTableCell.forTableColumn(new DoubleStringConverter()));
        priceWheelRim.setOnEditCommit((TableColumn.CellEditEvent<WheelRim, Double> t) -> {
            t.getTableView().getItems();
            row = t.getTablePosition().getRow();
            Double value = t.getNewValue();
            System.out.println(value);
            carComponents = retrievedCompMap.get("WheelRim");
            wheelRim = ((WheelRim) carComponents.get(row));
            wheelRim.setCompPrice(value);
            retrievedCompMap.get("WheelRim").set(row, wheelRim);
            (t.getTableView().getItems().get(
                    t.getTablePosition().getRow())
            ).setCompPrice(t.getNewValue()
            );
        });

        quantityWheelRim.setCellFactory(TextFieldTableCell.forTableColumn(new IntegerStringConverter()));
        quantityWheelRim.setOnEditCommit((TableColumn.CellEditEvent<WheelRim, Integer> t) -> {
            t.getTableView().getItems();
            row = t.getTablePosition().getRow();
            Integer value = t.getNewValue();
            System.out.println(value);
            carComponents = retrievedCompMap.get("WheelRim");
            wheelRim = ((WheelRim) carComponents.get(row));
            wheelRim.setCompQuantity(value);
            retrievedCompMap.get("WheelRim").set(row, wheelRim);
            jobjHandler.writeComponent(retrievedCompMap);
            (t.getTableView().getItems().get(
                    t.getTablePosition().getRow())
            ).setCompQuantity(t.getNewValue()
            );
        });
    }

    public void editOrderCustomer(){

        customerOrderInfoView.setEditable(true);

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

        //todo: Update CustomerOrder.csv file with changes
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

        selectedCustomerOrderId();
        System.out.println("SELECTED CUSTOMER ID: " + selectedOrderId);

        try {

            URL url = getClass().getResource("/org/ccomp/admin/compOrderInfo.fxml");

            FXMLLoader loader = new FXMLLoader(url);
            Scene setRoot = adminPane.getScene();
            setRoot.setRoot(loader.load());

            System.out.println("SELECTED CUSTOMER ID: " + selectedOrderId);
            if (selectedOrderId == -1) System.out.println("NONE SELECTED: " + selectedOrderId);
            else System.out.println("SELECTED: " + selectedOrderId);

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

    private void selectedCustomerOrderId() {
        int index = customerOrderInfoView.getSelectionModel().getSelectedIndex();
       // int index = carCompView.getSelectionModel().getSelectedIndex();
        System.out.println("SELECTED CUSTOMER INDEX: " + index);

        boolean orderSelected = true;
        if (index == -1) {
            System.out.println("NO INDEX SELECTED!!!");
            orderSelected = false;
        }


        if (orderSelected) {
            CustomerOrder selectedCustomerOrder = customerOrderInfoView.getItems().get(index);
            selectedOrderId = selectedCustomerOrder.getOrderId();
            System.out.println("SELECTED COMP ORDER ID: " + selectedOrderId);
        } else selectedOrderId = -1;
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

    public void deleteRowAdmin() {

        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setHeaderText("Du er i ferd med å slette!");
        alert.setContentText("Er du sikker på at du vil fortsette?");

        if (customerOrderTab != null && customerOrderTab.isSelected()) {

            if (customerOrderTab.isSelected()) {
                int visibleIndex = customerOrderInfoView.getSelectionModel().getSelectedIndex();
                //return if nothing selected
                if (visibleIndex == -1)
                    return;

                int sourceIndex = sortedCustomerOrderData.getSourceIndexFor(customerOrders, visibleIndex);
                customerOrder = customerOrderList.get(sourceIndex);

                alert.setHeaderText("Du er i ferd med å slette hele bestillingen med alle komponenter tilhørende dette ordrenummer: " + customerOrder.getOrderId());
            }
        }

        ButtonType cancelButton = new ButtonType("Avbryt", ButtonBar.ButtonData.CANCEL_CLOSE);
        alert.getDialogPane().getButtonTypes().add(cancelButton);

        Optional<ButtonType> result = alert.showAndWait();

        if (result.isPresent() && result.get() == ButtonType.OK) {
            delete = true;
            deleteSelectedRow();
        }
    }

    public void deleteRowOrders(){

        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setHeaderText("Du er i ferd med å slette!");
        alert.setContentText("Er du sikker på at du vil fortsette?");


        ButtonType cancelButton = new ButtonType("Avbryt", ButtonBar.ButtonData.CANCEL_CLOSE);
        alert.getDialogPane().getButtonTypes().add(cancelButton);

        Optional<ButtonType> result = alert.showAndWait();

        if (result.isPresent() && result.get() == ButtonType.OK) {
            delete = true;
            deleteCompOrder();
        }
    }


    private int validateIntegerColumn(String value, int def) {
        try {
            return Integer.parseInt(value);
        }
        catch (NumberFormatException e) {
            return def;
        }
    }



    //Returns the method from validation with varibles from texfield in compcontroller.
    public String validationSeat(Seat seat) {
        return Validation.valAdminSeat(seat.getCompName(),
                seat.getSeatMaterial(), seat.getSeatColor(), String.valueOf(seat.getCompPrice()), String.valueOf(seat.getCompQuantity()));
    }
    //Method for checking if texfield are empty, return an alert if fields are empty.
    public void emptyFieldsSeat(Seat seat) {
        if (seat.getCompName().isEmpty() && seat.getSeatColor().isEmpty() && seat.getSeatMaterial().isEmpty()
                && String.valueOf(seat.getCompPrice()).isEmpty() && String.valueOf(seat.getCompQuantity()).isEmpty()) {
            alert("Fyll inn alle felt!");
            emptyFields = true;
        }
    }

   /* public String validationSpoiler() {
        return Validation.valAdminSpiler(spoilerName.getText(), spoilerColor.getText(),
                spoilerSide.getText(), spoilerPrice.getText(), spoilerQuantity.getText());
    }
    public void emptyFieldsSpoiler() {
        if (spoilerName.getText().isEmpty() && spoilerColor.getText().isEmpty() && spoilerSide.getText().isEmpty()
                && spoilerPrice.getText().isEmpty() && spoilerQuantity.getText().isEmpty()) {
            alert("Fyll inn alle felt!");
            emptyFields = true;
        }
    }

    public String validationSteering() {
        return Validation.valAdminSteering(steeringWheelName.getText(), steeringWheelColor.getText(),
                steeringWheelMaterial.getText(), steeringWheelPrice.getText(), steeringWheelQuantity.getText());
    }
    public void emptyFieldsSteering() {
        if (steeringWheelName.getText().isEmpty() && steeringWheelColor.getText().isEmpty() && steeringWheelMaterial.getText().isEmpty()
                && steeringWheelPrice.getText().isEmpty() && steeringWheelQuantity.getText().isEmpty()) {
            alert("Fyll inn alle felt!");
            emptyFields = true;
        }
    }

    public String validationEngine() {
        return Validation.valAdminEngine(engineName.getText(), enginePower.getText(), enginePrice.getText(), engineQuantity.getText());
    }
    public void emptyFieldsEngine() {
        if (engineName.getText().isEmpty() && enginePower.getText().isEmpty() && enginePrice.getText().isEmpty()
                && engineQuantity.getText().isEmpty()) {
            alert("Fyll inn alle felt!");
            emptyFields = true;
        }
    }

    public String validationRim() {
        return Validation.valAdminRim(wheelRimName.getText(), wheelRimColor.getText(), wheelRimDimension.getText(),  wheelRimPrice.getText(),
                wheelRimQuantity.getText());
    }
    public void emptyFieldsRim() {
        if (wheelRimName.getText().isEmpty() && wheelRimColor.getText().isEmpty() && wheelRimDimension.getText().isEmpty()
                && wheelRimPrice.getText().isEmpty() && wheelRimQuantity.getText().isEmpty()) {
            alert("Fyll inn alle felt!");
            emptyFields = true;
        }
    }*/

}

