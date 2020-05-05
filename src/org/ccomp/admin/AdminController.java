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
import javafx.stage.Stage;
import javafx.util.converter.DoubleStringConverter;
import javafx.util.converter.IntegerStringConverter;
import org.ccomp.fileHandling.ComponentObjHandler;
import org.ccomp.model.component.*;
import org.ccomp.model.component.engine.Engine;

import java.net.URL;
import java.util.*;

public class AdminController implements Initializable {
    Seat seat;
    Spoiler spoiler;
    int row;
    @FXML
    Button backBtn;

    @FXML
    Button addComp;

    @FXML
    TextField search;

    @FXML
    TabPane tabPane;

    @FXML
    Tab engineTab,seatTab,spoilerTab,steeringWTab,wheelRimTab,orderEngineTab;

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
    TableView<CarComponent> carCompView;

    @FXML
    TableColumn<CarComponent, String> orderTypeColum,orderNameColum;

    @FXML
    TableColumn<CarComponent,Double> orderPriceColum;

    @FXML
    TableColumn<CarComponent,Integer> orderQuntityColum;


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
    TableColumn<SteeringWheel, String> nameSWheelColum, materiellSWeel, colorSWheelColum, priceSWeelColum, quantitySWeelColum;
    @FXML
    TableColumn<WheelRim, String> nameWheelRimColum, dimensionWheelRim, colorWheelRim, priceWheelRim, quantityWheelRim;
    @FXML
    TableColumn<Engine, String> engintypeColum, nameEngineColum, horsepowerColum, priceEngineColum, quantityEngineColum;

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

    private ComponentObjHandler jobjHandler;

   // private ComponentOBJHandler jobjHandler;
    private HashMap<String, List<CarComponent>> compMap, retrievedCompMap;
    private List<CarComponent> carComponents;
    private Object TableColumn;
   // ObservableList<Seat> seatsList;






    @Override
    public void initialize(URL location, ResourceBundle resources) {


        //todo: ikke ha i initialize; fileN kan være tom eller ikke ekistere





        jobjHandler = new ComponentObjHandler();

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


    //Metoden henter objektene fra arrayliste.
    public ObservableList<Seat> seatTable() {

        Seat seat;
        ObservableList<Seat> seats = FXCollections.observableArrayList();
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
            priceSWeelColum.setCellValueFactory(new PropertyValueFactory<SteeringWheel, String>("compPrice"));
            quantitySWeelColum.setCellValueFactory(new PropertyValueFactory<SteeringWheel, String>("compQuantity"));
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

    public  ObservableList<CarComponent> carComTable(){

        StringProperty compNameProperty = new SimpleStringProperty("Jaso");
        IntegerProperty compQuantityProperty = new SimpleIntegerProperty(382);
        DoubleProperty compPriceProperty= new SimpleDoubleProperty(264);
        CarComponent component = new CarComponent("CompType", compNameProperty, compPriceProperty, compQuantityProperty);

        orderNameColum.setCellValueFactory(new PropertyValueFactory<CarComponent,String>("compName"));
        orderTypeColum.setCellValueFactory(new PropertyValueFactory<CarComponent,String>("compType"));
        orderPriceColum.setCellValueFactory(new PropertyValueFactory<CarComponent,Double>("compPrice"));
        orderQuntityColum.setCellValueFactory(new PropertyValueFactory<CarComponent,Integer>("compQuantity"));

       // CarComponent carComTest;
        ObservableList<CarComponent> carComps = FXCollections.observableArrayList();
        //carComponents = new ArrayList<>();
        carComps.add(component);
        carComps.add(component);
        carComps.add(component);
        carComps.add(component);
        carComps.add(component);
        carComps.add(component);
        return carComps ;
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
        }

        if (orderEngineTab.isSelected()){
            carCompView.setItems(carComTable());
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



    @FXML
    public void deleteSelectedRow() {

       // jobjHandler = new ComponentOBJHandler();
        retrievedCompMap = jobjHandler.readComponent(retrievedCompMap);
        carComponents = retrievedCompMap.get("Seat");
        retrievedCompMap.get("Seat").set(row, seat);
        Seat selectedRow;
        ObservableList<Seat> allseats;
        allseats = seatView.getItems();
        selectedRow = seatView.getSelectionModel().getSelectedItem();

        for (CarComponent carComponent : carComponents) {
            allseats.remove(carComponent);
            retrievedCompMap.get("Seat").set(row, seat);
        }

        System.out.println(allseats.remove(carComponents));
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
            ObservableList<Seat> seatsList = seatTable();
            FilteredList<Seat> filteredList  = new FilteredList(seatsList ,b->true);
            //predicater når filter skifter
            search.textProperty().addListener(((observable, oldValue, newValue) -> {

                filteredList.setPredicate(seat -> {

                    //Hvis filter er tommt , vis alt
                    if (newValue == null || newValue.isEmpty()){
                        return true;
                    }

                    String lowerCaseFiler = newValue.toLowerCase();

                    if (seat.getCompName().toLowerCase().indexOf(lowerCaseFiler) != -1){
                        System.out.println("Er lik");
                        return true; //Filter sammenligner navn
                    }
                    else if (seat.getColor().toLowerCase().indexOf(lowerCaseFiler) != -1){

                        return true; //Filter sammenligner farge
                    }

                    else if (String.valueOf(seat.getCompPrice()).toLowerCase().indexOf(lowerCaseFiler) != -1)
                        return true;
                    else return false; //hvis den ikke kan sammenlinges
                });
                }));

            //Setter filteredlist i en sortedlist, binder de sammen til tableviewvet og legger sorted og filterreing til tabellen,
             SortedList<Seat> sortedData = new SortedList<>(seatsList);
             sortedData.comparatorProperty().bind(seatView.comparatorProperty());
             seatView.setItems(sortedData);







        }



}


