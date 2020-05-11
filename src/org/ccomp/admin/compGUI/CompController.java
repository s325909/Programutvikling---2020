package org.ccomp.admin.compGUI;

import javafx.beans.property.*;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;
import org.ccomp.fileHandling.ComponentCSVHandler;
import org.ccomp.fileHandling.ComponentOBJHandler;
import org.ccomp.model.MapKey;
import org.ccomp.model.Validation;
import org.ccomp.model.component.*;
import org.ccomp.model.component.engine.ElectricMotor;
import org.ccomp.model.component.engine.Engine;
import org.ccomp.model.component.engine.GasolineEngine;
import org.ccomp.model.component.engine.HybridEngine;

import java.net.URL;
import java.util.*;

public class CompController {

    @FXML
    Button addEngine, addSeat, addSpoiler, addSteeringWheel, addWheelRim, ok;

    @FXML
    Label label;

    @FXML
    TextField engineName, enginePrice, enginePower, engineQuantity,
            seatName, seatPrice, seatColor, seatMaterial, seatQuantity,
            spoilerName, spoilerPrice, spoilerColor, spoilerSide, spoilerQuantity,
            steeringWheelName, steeringWheelPrice, steeringWheelColor, steeringWheelMaterial, steeringWheelQuantity,
            wheelRimName, wheelRimPrice, wheelRimColor, wheelRimDimension, wheelRimQuantity;

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

   // private ComponentOBJHandler jobjHandler;

    private ComponentOBJHandler jobjHandler;

    private StringProperty compNameProperty, compColorProperty, compMaterialProperty, compTypeProperty;
    private DoubleProperty compPriceProperty;
    private IntegerProperty compQuantityProperty, compSizeProperty;

    private String mapKey;



    @FXML
    public void initialize() {


        jobjHandler = new ComponentOBJHandler();
      //  jobjHandler = new ComponentOBJHandler();
        //todo: Handle if file is empty
        // java.io.FileNotFoundException: testComponents.obj (Systemet finner ikke angitt fil)
        compMap = jobjHandler.readComponent(compMap);

        if (compMap == null) compMap = new HashMap<>();

        System.out.println("INITIALIZED COMP MAP SIZE: " + compMap.size());

        //spinner quantity for quantities of all types of components
        //System.out.println(spinnerQuantity.getValue());

       // mapKey = new MapKey();



    }

    @FXML
    public void checkValidation() {
            label.setText(validationSeat());
            inputEmpty();
    }

    @FXML
    public void addComponent(ActionEvent event) {
       // inputEmpty();

       // carComponents = new ArrayList<>();

       // compMap = new HashMap<>();

        if (event.getSource() == addEngine) {
            System.out.println("\nADD ENGINE PRESSED\n");

            if (carComponents == null) carComponents = new ArrayList<>();


            //todo: fjern etter testing
            compNameProperty = new SimpleStringProperty(engineName.getText());
            compPriceProperty = new SimpleDoubleProperty(Double.parseDouble(enginePrice.getText()));
            compQuantityProperty = new SimpleIntegerProperty(Integer.parseInt(engineQuantity.getText()));


            carComponents.add(new CarComponent("TYPE", compNameProperty, compPriceProperty, compQuantityProperty));

            ComponentCSVHandler csvHandler = new ComponentCSVHandler();

          //  csvHandler.writeComponent(carComponents, "testComp.csv");

            csvHandler.writeComponent(carComponents, "testComponent.csv");


        } else if (event.getSource() == addSeat) {
            System.out.println("\nADD SEAT PRESSED\n");

            //Getting all saved Seat components from HashMap and assigning them to new List of CarComponent
            carComponents = compMap.get(SEAT_KEY);


            //up casting parent reference to child reference based on componentType
            seat = (Seat) makeComponent(SEAT_KEY, seat);
            if (carComponents == null) carComponents = new ArrayList<>();

            System.out.println("("+SEAT_KEY+") CarComponents size: " + carComponents.size());
            carComponents.add(seat);
            System.out.println("("+SEAT_KEY+") CarComponents size: " + carComponents.size());

            compMap.put(SEAT_KEY, carComponents);
            System.out.println("("+SEAT_KEY+") HASH MAP: " + compMap);

            //serialize compMap
            //jobjHandler = new ComponentOBJHandler();
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

            //Getting all saved Spoiler components from HashMap and assigning them to new List of CarComponent
            carComponents = compMap.get(SPOILER_KEY);
            if (carComponents == null) carComponents = new ArrayList<>();

            //up casting parent reference to child reference based on componentType
            spoiler = (Spoiler) makeComponent(SPOILER_KEY, spoiler);
            System.out.println("("+SPOILER_KEY+") CarComponents size: " + carComponents.size());
            carComponents.add(spoiler);
            System.out.println("("+SPOILER_KEY+") CarComponents size: " + carComponents.size());

            compMap.put(SPOILER_KEY, carComponents);
            System.out.println("("+SPOILER_KEY+") HASH MAP: " + compMap);

            System.out.println(carComponents.get(0).getCompName());
            System.out.println(carComponents.get(0).getCompPrice());
            System.out.println(carComponents.get(0).getCompQuantity());

            //serialize compMap
          //  jobjHandler = new ComponentOBJHandler();
            jobjHandler.writeComponent(compMap);

            //deserialize compMap
            retrievedCompMap = new HashMap<>();
            retrievedCompMap = jobjHandler.readComponent(retrievedCompMap);

            System.out.println("(RETRIEVED) HASH MAP: " + retrievedCompMap);
        } else if (event.getSource() == addSteeringWheel) {
            System.out.println("\nADD STEERING WHEEL PRESSED\n");

            //Getting all saved SteeringWheel components from HashMap and assigning them to new List of CarComponent
            carComponents = compMap.get(STEERING_WHEEL_KEY);
            if (carComponents == null) carComponents = new ArrayList<>();

            //up casting parent reference to child reference based on componentType
            steeringWheel = (SteeringWheel) makeComponent(STEERING_WHEEL_KEY, steeringWheel);
            System.out.println("("+STEERING_WHEEL_KEY+") CarComponents size: " + carComponents.size());
            carComponents.add(steeringWheel);
            System.out.println("("+STEERING_WHEEL_KEY+") CarComponents size: " + carComponents.size());

            compMap.put(STEERING_WHEEL_KEY, carComponents);
            System.out.println("("+STEERING_WHEEL_KEY+") HASH MAP: " + compMap);

            //serialize compMap
           // jobjHandler = new ComponentOBJHandler();
            jobjHandler.writeComponent(compMap);

            //deserialize compMap
            retrievedCompMap = new HashMap<>();
            retrievedCompMap = jobjHandler.readComponent(retrievedCompMap);

            System.out.println("(RETRIEVED) HASH MAP: " + retrievedCompMap);
        } else if (event.getSource() == addWheelRim) {
            System.out.println("\nADD WHEEL RIM PRESSED\n");

            //Getting all saved Spoiler components from HashMap and assigning them to new List of CarComponent
            carComponents = compMap.get(WHEEL_RIM_KEY);
            if (carComponents == null) carComponents = new ArrayList<>();

            //up casting parent reference to child reference based on componentType
            //todo: FIX serialization of WheelRim object
            // wheelRim = (WheelRim) makeComponent(WHEEL_RIM_KEY, wheelRim);
            System.out.println("("+WHEEL_RIM_KEY+") CarComponents size: " + carComponents.size());
            carComponents.add(wheelRim);
            System.out.println("("+WHEEL_RIM_KEY+") CarComponents size: " + carComponents.size());

            compMap.put(WHEEL_RIM_KEY, carComponents);
            System.out.println("("+WHEEL_RIM_KEY+") HASH MAP: " + compMap);

            //serialize compMap
           // jobjHandler = new ComponentOBJHandler();
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


   /* public void inputValidation () {

        String str = engineName.getText();

        if (str.equals("")) {  //User have not entered anything.
            JOptionPane.showMessageDialog(null, "Vennligst fyll ut alle felt!");
            engineName.requestFocusInWindow();
        }

        else {


*/
   //denne funker
    public static void alert(String msg) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("CCOMP");
        alert.setHeaderText("Feil!");
        alert.setContentText(msg);
        alert.showAndWait();

    }

    //denne funker
    public String validationSeat() {
        return Validation.valAdminSeat( seatName.getId(),
                seatMaterial.getText(), seatColor.getText(), seatPrice.getText(), seatQuantity.getText());
    }

    public String validationSpoiler() {
        return Validation.valAdminSpiler(spoilerName.getText(), spoilerColor.getText(),
                spoilerSide.getText(), spoilerPrice.getText(), spoilerQuantity.getText());
    }

    public String validationSteering() {
        return Validation.valAdminSteering(steeringWheelName.getText(), steeringWheelColor.getText(),
                steeringWheelMaterial.getText(), steeringWheelPrice.getText(), steeringWheelQuantity.getText());
    }

    public String validationEngine() {
        return Validation.valAdminEngine(engineName.getText(), enginePower.getText(), enginePrice.getText(), engineQuantity.getText());
    }

    public String validationRim() {
        return Validation.valAdminRim(wheelRimName.getText(), wheelRimDimension.getText(), wheelRimColor.getText(), wheelRimPrice.getText(),
                wheelRimQuantity.getText());
    }

    //denne funker
    public void inputEmpty() {
        if (validationSeat().isEmpty()) {
            alert("Du må fylle ut alle felt!");

        }

    }
}

