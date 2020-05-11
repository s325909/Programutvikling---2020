package org.ccomp.admin;

import javafx.beans.property.*;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
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
import org.ccomp.model.component.engine.Engine;
import org.ccomp.user.Customer;

import java.io.IOException;
import java.net.URL;
import java.util.*;

public class AdminController implements Initializable {
    Seat seat;
    Spoiler spoiler;
    CustomerOrder customer;
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
    TableColumn<CustomerOrder,String>customerInfoOrderName,customerInfoOrderEmail,
            customerInfoOrderZip,customerInfoOrderMobilNr,customerInfoOrderCity,
            customerInfoOrderButton;

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

    //Engine
    @FXML
    TableView<Engine> engineView;
    @FXML
    TableColumn<Engine, String> engintypeColum, nameEngineColum, horsepowerColum;
    @FXML
    TableColumn<Engine, Double>priceEngineColum;
    @FXML
    TableColumn<Engine, Integer>quantityEngineColum;

    //@FXML
    //TableColumn<Seat, SimpleStringProperty> nameSeatColum,colorSeatColum,seatPriceColum,quantitySeatColum;

    //  @FXML
    //  TableColumn<Spoiler, SimpleStringProperty> nameSpoilerColum,colorSpoilerColum,sideSpoilerColum,priceSpoilerColum,quantitySpoilerColum;

    //@FXML
    //TableColumn<SteeringWheel,SimpleStringProperty> nameSWheelColum,materiellSWeel,colorSWheelColum,priceSWeelColum,quantitySWeelColum;

    //@FXML
    //TableColumn<WheelRim,SimpleStringProperty> nameWheelRimColum,dimensionWheelRim,colorWheelRim,priceWheelRim,quantityWheelRim;

    // @FXML
    //TableColumn<Engine, SimpleStringProperty> engintypeColum,nameEngineColum,horsepowerColum, priceEngineColum, quantityEngineColum;

    private ComponentOBJHandler jobjHandler;

   // private ComponentOBJHandler jobjHandler;
    private HashMap<String, List<CarComponent>> compMap, retrievedCompMap;
    private List<CarComponent> carComponents;
    private List<Customer> customerList;
    List<CustomerOrder> customerOrders1 = new ArrayList<>();
    private Object TableColumn;
    private Scene setRoot;
   // ObservableList<Seat> seatsList;






    @Override
    public void initialize(URL location, ResourceBundle resources) {


        //todo: ikke ha i initialize; fileN kan være tom eller ikke ekistere





        jobjHandler = new ComponentOBJHandler();

       // jobjHandler = new ComponentOBJHandler();



        //todo: fila kan være null eller tom
        retrievedCompMap = jobjHandler.readComponent(retrievedCompMap);

        System.out.println("INIT COMP MAP: " + retrievedCompMap );




//        tables();

       // allTables();


        // retrievedCompMap.put("Seat", carComponents);
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
    public void toContact(){
        System.out.println("Til contact list ");



        try {

            URL url = getClass().getResource("/org/ccomp/admin/orderCusInfo.fxml");

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





    //Metoden henter objektene fra arrayliste.
    public ObservableList<Seat> seatTable() {

        Seat seat;
        ObservableList<Seat> seats = FXCollections.observableArrayList();
       // FilteredList filteredList  = new FilteredList(seats ,e->true);
        jobjHandler = new ComponentOBJHandler();
        retrievedCompMap = jobjHandler.readComponent(retrievedCompMap);
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
        ObservableList<Spoiler> spoilers = FXCollections.observableArrayList();
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

<<<<<<< HEAD

        customerOrders1 = csvHandler.readCustomer(customerOrders1, "testCustomerOrders.csv");
=======
        List<CustomerOrder> customerOrders1 = new ArrayList<>();
        customerOrders1 = csvHandler.readCustomerOrder(customerOrders1, "testCustomerOrders.csv");
>>>>>>> ff0a8b344e21ef3a565f8b38da2c2ba59cd35c48
        System.out.println("CUSTOMER LIST: " + customerOrders1.size());

        //Customer
        String  customerName = "Jaso";
        String customerEmail ="athisaiyan@hotmail.com";
        String customerNumber = "90243728";
        String customerZip = "1187";
        String customerCity ="Oslo";
        Customer customer = new Customer(customerName,customerEmail,customerNumber,customerZip,customerCity);

        ObservableList<CustomerOrder> customerOrders = FXCollections.observableArrayList();
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
            customerInfoOrderZip.setCellValueFactory(new PropertyValueFactory<CustomerOrder,String>("customerNumber"));
            customerInfoOrderMobilNr.setCellValueFactory(new PropertyValueFactory<CustomerOrder,String>("customerZipCode"));
            customerInfoOrderCity.setCellValueFactory(new PropertyValueFactory<CustomerOrder,String>("customerCity"));

           // customerList.add(customer);
            customerOrders.add(customerOrder1);
        }

        return customerOrders;
    }


    public  ObservableList<CompOrder> carComTable(){

        StringProperty compNameProperty = new SimpleStringProperty("Jaso");
        IntegerProperty compQuantityProperty = new SimpleIntegerProperty(382);
        DoubleProperty compPriceProperty= new SimpleDoubleProperty(264);
        CarComponent component = new CarComponent("CompType", compNameProperty, compPriceProperty, compQuantityProperty);

        CompOrder compOrder = new CompOrder(2,component);
        ObservableList<CompOrder> compOrders = FXCollections.observableArrayList();


        List<CompOrder> compOrderList = new ArrayList<>();

        compOrderList.add(compOrder);
        compOrderList.add(compOrder);
        compOrderList.add(compOrder);
        compOrderList.add(compOrder);
        compOrderList.add(compOrder);



        carComponents = new ArrayList<>();
       // if (customerOrderInfoView.getSelectionModel().isSelected(row))
        for (CompOrder compOrder1 : compOrderList) {
            CarComponent carComponent = compOrder1.getCarComponent();
            orderNameColum.setCellValueFactory(new PropertyValueFactory<CarComponent,String>("CompName"));
            orderNrColum.setCellValueFactory(new PropertyValueFactory<CompOrder,Integer>("compOrderNr"));
            orderTypeColum.setCellValueFactory(new PropertyValueFactory<CompOrder,String>(compOrder.getCompType()));
            orderPriceColum.setCellValueFactory(new PropertyValueFactory<CarComponent,Double>("compPrice"));
            orderQuntityColum.setCellValueFactory(new PropertyValueFactory<CarComponent,Integer>("compQuantity"));
            carComponents.add(carComponent);
            compOrders.add(compOrder);
        }


        // carComps.add(component);

       // CarComponent carComTest;


        //carComponents = new ArrayList<>();
      //  carComps.add(component);




        /*CompOrder compOrder;
        int orderNr = 0;
        for (CarComponent carComponent : carComps) {
            compOrder = new CompOrder(orderNr, carComponent);
            System.out.println("COMP ORDER: " + compOrder.toCSVFormat());
            orderNr++;
        }

        */



        /*
        compOrder = new CompOrder(orderNr, carComps);
        System.out.println("COMP ORDER TOSTRING(): " + compOrder.toString());
        */


        return compOrders;
    }

    @FXML
    public void clickedOrderNr(){
        //Tabl tablePosition = customerOrderInfoView.getSelectionModel().getSelectedItems().get(0);
/*
        CustomerOrder  customerOrder2 = customerOrderInfoView.getItems().get(row);
        TableColumn tableColumn = customerOrderInfoView.get
        int data = (int) tableColumn.getCellObservableValue(customerOrder2).getValue();
        System.out.println(data);*/

        int index = customerOrderInfoView.getSelectionModel().getSelectedIndex();
        CustomerOrder customerOrder3 = customerOrderInfoView.getItems().get(index);
        String txt = "Ordernr" + customerOrder3.getCustomerOrderNr();
        System.out.println(txt);

    }



    public void tables()
    {
        tabPane.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Tab>() {
            @Override
            public void changed(ObservableValue<? extends Tab> observable, Tab oldTab, Tab newTab) {
                if (newTab == seatTab) System.out.println("SEAT TAB ´" + seatTab.isSelected());
                else if (newTab == spoilerTab) System.out.println("SPILER TAB: " + spoilerTab.isSelected());
            }
        });
    }


    @FXML
    public void allTables(){


            seatView.setItems(seatTable());
            editSeatTable();
            seatView.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
            System.out.println("SEAT TAB");
            searchComp();


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
        customer = (CustomerOrder) customerOrders1.get(row);
        customerInfoOrderName.setCellFactory(new PropertyValueFactory("Navn"));
        customerInfoOrderName.setCellFactory(TextFieldTableCell.forTableColumn());
        customerInfoOrderName.setOnEditCommit((TableColumn.CellEditEvent<CustomerOrder, String> t) -> {
            t.getTableView().getItems();
            row = t.getTablePosition().getRow();
            String value = t.getNewValue();
            System.out.println(value);
            customer = ((CustomerOrder) customerOrders1.get(row));
            customer.setCustomerName(value);
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
            customer = ((CustomerOrder) customerOrders1.get(row));
            customer.setCustomerMail(value);
           // retrievedCompMap.get("CustomerOrder").set(row, seat);
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
            customer = ((CustomerOrder) customerOrders1.get(row));
            customer.setCustomerNumber(value);
            retrievedCompMap.get("CustomerOrder").set(row, seat);
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
            customer = ((CustomerOrder) customerOrders1.get(row));
            customer.setCustomerCity(value);
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

            customer = ((CustomerOrder) customerOrders1.get(row));
            customer.setCustomerZipCode(value);
            (t.getTableView().getItems().get(
                    t.getTablePosition().getRow())
            ).setCustomerZipCode(t.getNewValue()
            );
        });

    }



    @FXML
    public void deleteSelectedRow() {
        /*
        jobjHandler = new ComponentOBJHandler();
        retrievedCompMap = jobjHandler.readComponent(retrievedCompMap);
        System.out.println(retrievedCompMap.size());
        carComponents = retrievedCompMap.get("Seat");
        retrievedCompMap.get("Seat").set(row, seat);
       //ObservableList<Seat> allseats;
       //  allseats = seatView.getItems();

        ObservableList<Seat>selectedItem = seatView.getSelectionModel().getSelectedItems();

        for (CarComponent carComponent : carComponents) {

            Seat seat = (Seat) carComponent;
            seatView.getItems().removeAll(selectedItem);
            seatTable().remove(seat);
            retrievedCompMap.get("Seat").set(row, seat);
        }

         */


        jobjHandler = new ComponentOBJHandler();
        retrievedCompMap = jobjHandler.readComponent(retrievedCompMap);
        Seat selectedRow = seatView.getSelectionModel().getSelectedItem();
        System.out.println("SELECTED SEAT " + selectedRow.toCSVFormat());
        if (selectedRow != null) {
            seatView.getItems().remove(seat);
            seatView.getItems().remove(selectedRow);
            retrievedCompMap.get("Seat").remove(selectedRow);
        }




      //  System.out.println(allseats.remove(carComponents));



      // seatView.getItems().removeAll(seatView.getSelectionModel().getSelectedItem());




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
                SortedList<Seat> sortedData = new SortedList<>(filteredList);
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



}



