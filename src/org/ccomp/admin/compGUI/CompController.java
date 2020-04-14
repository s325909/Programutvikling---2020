package org.ccomp.admin.compGUI;

import javafx.beans.property.*;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;
import org.ccomp.fileHandling.ComponentOBJHandler;
import org.ccomp.model.CarComp;
import org.ccomp.model.MapKey;
import org.ccomp.model.component.*;
import org.ccomp.model.component.engine.ElectricMotor;
import org.ccomp.model.component.engine.Engine;
import org.ccomp.model.component.engine.GasolineEngine;
import org.ccomp.model.component.engine.HybridEngine;

import java.net.URL;
import java.util.*;

public class CompController {

    @FXML
    Button addEngine, addSeat, addSpoiler, addSteeringWheel, addWheelRim;

    @FXML
    TextField engineName, enginePrice, engineQuantity, enginePower,
            seatName, seatPrice, seatQuantity, seatColor, seatMaterial,
            spoilerName, spoilerPrice, spoilerQuantity, spoilerColor, spoilerSide,
            steeringWheelName, steeringWheelPrice, steeringWheelQuantity, steeringWheelColor, steeringWheelMaterial,
            wheelRimName, wheelRimPrice, wheelRimQuantity, wheelRimColor, wheelRimDimension;

    private final String ENGINE_KEY = "Engine";
    private final String SEAT_KEY = "Seat";
    private final String SPOILER_KEY = "Spoiler";
    private final String STEERING_WHEEL_KEY = "SteeringWheel";
    private final String WHEEL_RIM_KEY = "WheelRim";

    private HashMap<String, List<CarComponent>> compMap, retrievedCompMap;
    private HashMap<MapKey, List<CarComponent>> compMap2;
   // private MapKey mapKey, mapKeySeat, mapKeySpoiler;
    private List<CarComponent> carComponents;
    private Engine engine;
    private Seat seat;
    private Spoiler spoiler;
    private SteeringWheel steeringWheel;
    private WheelRim wheelRim;

    private ComponentOBJHandler jobjHandler;

    private StringProperty compNameProperty, compColorProperty, compMaterialProperty, compTypeProperty;
    private DoubleProperty compPriceProperty;
    private IntegerProperty compQuantityProperty, compSizeProperty;

    private String mapKey;



    @FXML
    public void initialize() {
        jobjHandler = new ComponentOBJHandler();
        compMap = jobjHandler.readComponent(compMap);

       // mapKey = new MapKey();
    }

    @FXML
    public void addComponent(ActionEvent event) {
        carComponents = new ArrayList<>();

       // compMap = new HashMap<>();

        if (event.getSource() == addEngine) {
            System.out.println("\nADD ENGINE PRESSED\n");
        } else if (event.getSource() == addSeat) {
            System.out.println("\nADD SEAT PRESSED\n");
            //up casting parent reference to child reference based on componentType
            seat = (Seat) makeComponent(SEAT_KEY, seat);
            carComponents.add(seat);
            System.out.println("SEAT LIST SIZE: " + carComponents.size());

            compMap.put(SEAT_KEY, carComponents);
            System.out.println("("+SEAT_KEY+") HASH MAP: " + compMap);

            //serialize compMap
            jobjHandler = new ComponentOBJHandler();
            jobjHandler.writeComponent(compMap);

            //deserialize compMap
            retrievedCompMap = new HashMap<>();
            retrievedCompMap = jobjHandler.readComponent(retrievedCompMap);

            System.out.println("(RETRIEVED) HASH MAP: " + retrievedCompMap);

          //  iterateHashMapList(retrievedCompMap);

            //todo: add to component list instead of overwriting
          //  retrievedCompMap.get("Seat").addAll(carComponents);
          //  jobjHandler.writeComponent(retrievedCompMap);
        } else if (event.getSource() == addSpoiler) {
            System.out.println("\nADD SPOILER PRESSED\n");
            //up casting parent reference to child reference based on componentType
            spoiler = (Spoiler) makeComponent(SPOILER_KEY, spoiler);
            carComponents.add(spoiler);
            System.out.println("SPOILER LIST SIZE: " + carComponents.size());

            compMap.put(SPOILER_KEY, carComponents);
            System.out.println("("+SPOILER_KEY+") HASH MAP: " + compMap);

            System.out.println(carComponents.get(0).getCompName());
            System.out.println(carComponents.get(0).getCompPrice());
            System.out.println(carComponents.get(0).getCompQuantity());

            //serialize compMap
            jobjHandler = new ComponentOBJHandler();
            jobjHandler.writeComponent(compMap);

            //deserialize compMap
            retrievedCompMap = new HashMap<>();
            retrievedCompMap = jobjHandler.readComponent(retrievedCompMap);

            System.out.println("(RETRIEVED) HASH MAP: " + retrievedCompMap);
        } else if (event.getSource() == addSteeringWheel) {
            System.out.println("\nADD STEERING WHEEL PRESSED\n");
            //up casting parent reference to child reference based on componentType
            steeringWheel = (SteeringWheel) makeComponent(STEERING_WHEEL_KEY, steeringWheel);
            carComponents.add(steeringWheel);
            System.out.println("STEERING WHEEL LIST SIZE: " + carComponents.size());

            compMap.put(STEERING_WHEEL_KEY, carComponents);
            System.out.println("("+STEERING_WHEEL_KEY+") HASH MAP: " + compMap);

            //serialize compMap
            jobjHandler = new ComponentOBJHandler();
            jobjHandler.writeComponent(compMap);

            //deserialize compMap
            retrievedCompMap = new HashMap<>();
            retrievedCompMap = jobjHandler.readComponent(retrievedCompMap);

            System.out.println("(RETRIEVED) HASH MAP: " + retrievedCompMap);
        } else if (event.getSource() == addWheelRim) {
            System.out.println("\nADD WHEEL RIM PRESSED\n");
            //up casting parent reference to child reference based on componentType
           // wheelRim = (WheelRim) makeComponent(WHEEL_RIM_KEY, wheelRim);
            carComponents.add(wheelRim);
            System.out.println("WHEEL RIM LIST SIZE: " + carComponents.size());

            compMap.put(WHEEL_RIM_KEY, carComponents);
            System.out.println("("+WHEEL_RIM_KEY+") HASH MAP: " + compMap);

            //serialize compMap
            jobjHandler = new ComponentOBJHandler();
            jobjHandler.writeComponent(compMap);

            //deserialize compMap
            retrievedCompMap = new HashMap<>();
            retrievedCompMap = jobjHandler.readComponent(retrievedCompMap);

            System.out.println("(RETRIEVED) HASH MAP: " + retrievedCompMap);
        } else System.out.println("\nNO SUCH ADD BTN TO PRESS\n");

        System.out.println("\nADDING COMPONENT..\n");
    }


    private CarComponent makeComponent(String compKey, CarComponent component) {
        switch (compKey) {
            case ENGINE_KEY:
                System.out.println("MAKING" + compKey);
                compNameProperty = new SimpleStringProperty(engineName.getText());
                compPriceProperty = new SimpleDoubleProperty(Double.parseDouble(enginePrice.getText()));
                compQuantityProperty = new SimpleIntegerProperty(Integer.parseInt(engineQuantity.getText()));
               // compTypeProperty = new SimpleStringProperty(spoilerSide.getText());
               // compPowerProperty
                //Todo: Determine WHICH ENGINE TYPE
               // component = new Engine(compNameProperty, compPriceProperty, compQuantityProperty, compTypeProperty);
                break;
            case SEAT_KEY:
                System.out.println("MAKING " + compKey);
                compNameProperty = new SimpleStringProperty(seatName.getText());
                compPriceProperty = new SimpleDoubleProperty(Double.parseDouble(seatPrice.getText()));
                compQuantityProperty = new SimpleIntegerProperty(Integer.parseInt(seatQuantity.getText()));
                compColorProperty = new SimpleStringProperty(seatColor.getText());
                compMaterialProperty = new SimpleStringProperty(seatMaterial.getText());
                component = new Seat(compNameProperty, compPriceProperty, compQuantityProperty, compColorProperty, compMaterialProperty);
                break;
            case SPOILER_KEY:
                System.out.println("MAKING " + compKey);
                compNameProperty = new SimpleStringProperty(spoilerName.getText());
                compPriceProperty = new SimpleDoubleProperty(Double.parseDouble(spoilerPrice.getText()));
                compQuantityProperty = new SimpleIntegerProperty(Integer.parseInt(spoilerQuantity.getText()));
                compTypeProperty = new SimpleStringProperty(spoilerSide.getText());
                component = new Spoiler(compNameProperty, compPriceProperty, compQuantityProperty, compTypeProperty);
                break;
            case STEERING_WHEEL_KEY:
                System.out.println("MAKING " + compKey);
                compNameProperty = new SimpleStringProperty(steeringWheelName.getText());
                compPriceProperty = new SimpleDoubleProperty(Double.parseDouble(steeringWheelPrice.getText()));
                compQuantityProperty = new SimpleIntegerProperty(Integer.parseInt(steeringWheelQuantity.getText()));
                compColorProperty = new SimpleStringProperty(steeringWheelColor.getText());
                compMaterialProperty = new SimpleStringProperty(steeringWheelMaterial.getText());
                component = new SteeringWheel(compNameProperty, compPriceProperty, compQuantityProperty, compColorProperty, compMaterialProperty);
                break;
            case WHEEL_RIM_KEY:
                System.out.println("MAKING " + compKey);
                compNameProperty = new SimpleStringProperty(wheelRimName.getText());
                compPriceProperty = new SimpleDoubleProperty(Double.parseDouble(wheelRimPrice.getText()));
                compQuantityProperty = new SimpleIntegerProperty(Integer.parseInt(wheelRimQuantity.getText()));
                compColorProperty = new SimpleStringProperty(wheelRimColor.getText());
                compMaterialProperty = new SimpleStringProperty(wheelRimDimension.getText());
               // compSizeProperty = new SimpleIntegerProperty(Integer.parseInt(wheelRimDimension.getText()));
                component = new WheelRim(compNameProperty, compPriceProperty, compQuantityProperty, compColorProperty, compMaterialProperty);
                break;
            default:
                System.out.println("NO SUCH COMPONENT: " + compKey);
        }
        return component;
    }






    /*
    private List<CarComponent> initSeatList(List<CarComponent> seatList) {
        seatList.add(new Seat(seatName.getText(), Double.parseDouble(seatPrice.getText()), 1,
                seatColor.getText(), seatMaterial.getText()));
        seatList.add(new Seat(seatName.getText(), Double.parseDouble(seatPrice.getText()), 3,
                seatColor.getText(), seatMaterial.getText()));
        seatList.add(new Seat(seatName.getText(), Double.parseDouble(seatPrice.getText()), 5,
                seatColor.getText(), seatMaterial.getText()));
        return seatList;
    }

    private List<CarComponent> initSpoilerList(List<CarComponent> spoilerList) {
        spoilerList.add(new Spoiler(seatName.getText(), Double.parseDouble(seatPrice.getText()), 1,
                seatMaterial.getText()));
        spoilerList.add(new Spoiler(seatName.getText(), Double.parseDouble(seatPrice.getText()), 1,
                seatMaterial.getText()));
        return spoilerList;
    }
     */

    private void iterateHashMapList(HashMap<String, List<CarComponent>> compMap) {
        //Iterate over HashMap of ArrayList to get each component and its values
        for (String key : compMap.keySet()) {
            for (CarComponent component : compMap.get(key)) {
                System.out.println("CompMAP KEY/TYPE: " + key);
               // switchComponentType(key, component);
            }
        }

    }

    /*

    private void switchComponentType(String componentType, CarComponent component) {
        //up casting parent reference to child reference based on componentType
        switch (componentType) {
           // case "Engine": return engine = (Engine) component;
            case "Engine":
                //todo: determine which engine to cast
                gasolineEngine = (GasolineEngine) component;
                System.out.println(gasoline.toString());
                break;
            case "Seat":
                seat = (Seat) component;
                System.out.println(componentType + " {" + "\n" +
                        "component='" + seat.getCompName() + "'\n" +
                        "material='" + seat.getMaterial() + "'\n" +
                        "color='" + seat.getColor() + "'\n" +
                        "price='" + seat.getCompPrice() + "'\n" +
                        "quantity='" + seat.getCompQuantity() + "'\n" +
                        '}' + "\n"
                );
                break;
            case "Spoiler":
                spoiler = (Spoiler) component;
                System.out.println(componentType + " {" + "\n" +
                        "component='" + spoiler.getCompName() + "'\n" +
                        "type='" + spoiler.getType() + "'\n" +
                        "price='" + spoiler.getCompPrice() + "'\n" +
                        "quantity='" + spoiler.getCompQuantity() + "'\n" +
                        '}' + "\n"
                );
                break;
            case "SteeringWheel":
                steeringWheel = (SteeringWheel) component;
                System.out.println(steeringWheel.toString());
            case "WheelRim":
                wheelRim = (WheelRim) component;
                System.out.println(wheelRim.toString());
                break;
            default: component.toString();
        }
    }


     */

    @FXML
    public void addSpoilerComponent() {
       // if (addSpoiler.isPressed()) System.out.println("SPOILER BTN PRESSED!");

        List<CarComp> carComps = new ArrayList<>();

        SimpleStringProperty stringProperty = new SimpleStringProperty("COMP");
        DoubleProperty doubleProperty = new SimpleDoubleProperty(120.9);
        IntegerProperty integerProperty = new SimpleIntegerProperty(14);

        CarComp carComp = new CarComp(stringProperty, doubleProperty, integerProperty);

        carComps.add(carComp);
        carComps.add(carComp);
        carComps.add(carComp);
        carComps.add(carComp);

      //  CarComp carComp = new CarComp(spoilerName.getText(), Double.parseDouble(spoilerPrice.getText()), Integer.parseInt(spoilerQuantity.toString()));

      //  carComps.add(new CarComp("COMP0", 120.0, 15));
      //  carComps.add(new CarComp("COMP1", 135.0, 25));
      //  carComps.add(new CarComp("COMP2", 150.0, 35));

        System.out.println("SIZE: " + carComps.size());

        HashMap<String, List<CarComp>> compMap2 = new HashMap<>();

        compMap2.put("Comp", carComps);
        System.out.println("(COMP) HASH MAP: " + compMap2);


        System.out.println(carComps.get(0).getCompName());
        System.out.println(carComps.get(0).getCompPrice());
        System.out.println(carComps.get(0).getCompQuantity());


        /*

            //serialize compMap
            objectsHelper = new ReadWriteObjectsHelper();
            objectsHelper.writeComponent(compMap2);

            //deserialize compMap
            HashMap<String, List<CarComp>> retrievedCompMap2 = new HashMap<>();
            retrievedCompMap2 = objectsHelper.readComponent(retrievedCompMap2);

            System.out.println("(RETRIEVED) HASH MAP: " + retrievedCompMap2);


         */


        // iterateHashMapList(retrievedCompMap2);

        System.out.println("ADD SPOILER BUTTON PRESSED");
    }




    private GasolineEngine gasolineEngine;
    private ElectricMotor electricMotor;
    private HybridEngine hybridEngine;

    String componentType;
    String carTypetext;
    String namegivingEngine;
    String horsePowertxt;
    String pricetxt;
    String seattxt;
    String materolltxt;


  //  @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    //bilType
    @FXML
    RadioButton gasoline, electric, hybrid;


    //komponent
    @FXML
    RadioButton engine1, seat1, spoiler1, steeringwheel, wheelrim;

    //Skrive inn

    @FXML
    TextField navn, /*horsepower*/variable, price;
    //her må det komme inn en felles verdig for tekstfelt 2

    //leggtilKnapp
    @FXML
    Button backToAdd;

    @FXML
    ToggleGroup carTypeGroup, componentTypeGroup;


    @FXML
    //Metod for hvis begge knappene er valgt. Altså biltype & komponent.
    /*void utText() {

       if (carTypeGroup.getSelectedToggle().isSelected() && componentTypeGroup.getSelectedToggle().isSelected() ) {
            selectedCarType();

        }


        selectedCarType();


    }*/


    private void selectedCarType() {
        if (gasoline.isSelected()) {
            System.out.println("Bensin");
           // GasolineEngine gasolineEngine = new GasolineEngine();
            selectedEngine(gasolineEngine);
            gasolineEngine.printEngine();

        } else if (electric.isSelected()) {
            System.out.println("El");
           // ElectricMotor electricMotor = new ElectricMotor();
            selectedEngine(electricMotor);
            electricMotor.printEngine();

        } else if (hybrid.isSelected()) {
            System.out.println("hybrid");
           // HybridEngine hybridEngine = new HybridEngine();
            selectedEngine(hybridEngine);
            hybridEngine.printEngine();
        }
    }

    private Engine selectedEngine(Engine engine) {

        namegivingEngine = navn.getText();
        horsePowertxt = String.valueOf(variable.getText());
        pricetxt = price.getText();
     //   engine.engineName(namegivingEngine);
        engine.enginePower(Integer.parseInt(horsePowertxt));
     //   engine.enginePrice(Integer.parseInt(pricetxt));
        return engine;
    }


    //kode for hvis sete er valgt TODO må gjøre om på variabler ,opp til deg im du vil bruke den.
   /* private void selectedAthoerComp(){
        if (seat.isSelected()){
            Seat seat1 = new Seat();
            selectedSeat(seat1);
            System.out.println("ok");
        }

        if(spoiler.isSelected()){
            Spoiler spoiler = new Spoiler();
            selectedSpoiler(spoiler);

        }*/


    //TODO må bare forsette videre med flere komponenter, er bare å legge det inn med arraylist



    /*private Seat selectedSeat(Seat seat){

        seattxt= seatstuff.getText();
        materolltxt = materiell.getText();
        pricetxt = price.getText();
        seat.getColor(seattxt);
        seat.getMaterial(materolltxt);
        seat.getPrice(pricetxt);
        return seat;

    }*/


    /*private Spoiler selectedSpoiler(Spoiler spoiler){
        return spoiler;

    }

    private SteeringWheel selectedSteerinWheel(SteeringWheel steeringWheel){
        return steeringWheel;

    }
*/





    //Tilbake knapp
    @FXML
    public void backToAddComp() {
        try {
            Stage stage = (Stage) backToAdd.getScene().getWindow();
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


    //validation

    //hvis ikke tekstfields er fylt ut, feilmelding.

}

