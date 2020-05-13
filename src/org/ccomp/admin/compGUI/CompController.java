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
import org.ccomp.model.component.engine.GasolineEngine;
import org.ccomp.model.component.engine.HybridEngine;

import java.net.URL;
import java.util.*;

public class CompController {

    @FXML
    private Button backToAdd;

    @FXML
    private Button addEngine, addSeat, addSpoiler, addSteeringWheel, addWheelRim, ok;

    @FXML
    private Label label;

    @FXML
    private TextField engineName, enginePrice, enginePower, engineQuantity,
            seatName, seatPrice, seatColor, seatMaterial, seatQuantity,
            spoilerName, spoilerPrice, spoilerColor, spoilerSide, spoilerQuantity,
            steeringWheelName, steeringWheelPrice, steeringWheelColor, steeringWheelMaterial, steeringWheelQuantity,
            wheelRimName, wheelRimPrice, wheelRimColor, wheelRimDimension, wheelRimQuantity;

    private final String ENGINE_KEY = "Engine";
    private final String SEAT_KEY = "Seat";
    private final String SPOILER_KEY = "Spoiler";
    private final String STEERING_WHEEL_KEY = "SteeringWheel";
    private final String WHEEL_RIM_KEY = "WheelRim";

    private String engineType;

    private HashMap<String, List<CarComponent>> compMap;
    private List<CarComponent> carComponents;
    private Engine engine;
   // private Engine engine;
    private Seat seat;
    private Spoiler spoiler;
    private SteeringWheel steeringWheel;
    private WheelRim wheelRim;

    private GasolineEngine gasolineEngine;
    private ElectricMotor electricMotor;
    private HybridEngine hybridEngine;

    private ComponentOBJHandler jobjHandler;


    @FXML
    public void initialize() {


        jobjHandler = new ComponentOBJHandler();

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

        //todo: remove method to deserialize compMap
        HashMap<String, List<CarComponent>> retrievedCompMap;

        if (event.getSource() == addEngine) {
            System.out.println("\nADD ENGINE PRESSED\n");

            //Getting all saved Seat components from HashMap and assigning them to new List of CarComponent
            carComponents = compMap.get(ENGINE_KEY);

            // Init CarComponents List if List is Null
            if (carComponents == null) carComponents = new ArrayList<>();


            System.out.println("ENGINE TYPE: " + getEngineType());

            engine = (Engine) makeComponent(ENGINE_KEY, engine);

            System.out.println("ENGINE: " + engine.toString());

            System.out.println("CARCOMP SIZE: " + carComponents.size());
            carComponents.add(engine);
            System.out.println("CARCOMP SIZE: " + carComponents.size());





            compMap.put(ENGINE_KEY, carComponents);


            //serialize compMap
            jobjHandler.writeComponent(compMap);


            //deserialize compMap
            retrievedCompMap = new HashMap<>();
            retrievedCompMap = jobjHandler.readComponent(retrievedCompMap);


            System.out.println("ENGINE MAP SIZE: " + retrievedCompMap.get(ENGINE_KEY).size());

           // carComponents.add(new Engine())

        } else if (event.getSource() == addSeat) {
            System.out.println("\nADD SEAT PRESSED\n");

            //Getting all saved Seat components from HashMap and assigning them to new List of CarComponent
            carComponents = compMap.get(SEAT_KEY);

            // Init CarComponents List if List is Null
            if (carComponents == null) carComponents = new ArrayList<>();

            //up casting parent reference to child reference based on componentType
            seat = (Seat) makeComponent(SEAT_KEY, seat);

            carComponents.add(seat);

            compMap.put(SEAT_KEY, carComponents);


            //serialize compMap
            jobjHandler.writeComponent(compMap);


            //deserialize compMap
            retrievedCompMap = new HashMap<>();
            retrievedCompMap = jobjHandler.readComponent(retrievedCompMap);


            System.out.println("(RETRIEVED) HASH MAP: " + retrievedCompMap);
          //  iterateHashMapList(retrievedCompMap);
        } else if (event.getSource() == addSpoiler) {
            System.out.println("\nADD SPOILER PRESSED\n");

            //Getting all saved Spoiler components from HashMap and assigning them to new List of CarComponent
            carComponents = compMap.get(SPOILER_KEY);

            // Init CarComponents List if List is Null
            if (carComponents == null) carComponents = new ArrayList<>();

            //up casting parent reference to child reference based on componentType
            spoiler = (Spoiler) makeComponent(SPOILER_KEY, spoiler);

            if (carComponents == null) carComponents = new ArrayList<>();

            carComponents.add(spoiler);

            compMap.put(SPOILER_KEY, carComponents);


            // Serialize compMap
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

            // Init CarComponents List if List is Null
            if (carComponents == null) carComponents = new ArrayList<>();

            //up casting parent reference to child reference based on componentType
            wheelRim = (WheelRim) makeComponent(WHEEL_RIM_KEY, wheelRim);

            System.out.println("("+WHEEL_RIM_KEY+") CarComponents size: " + carComponents.size());
            carComponents.add(wheelRim);
            System.out.println("("+WHEEL_RIM_KEY+") CarComponents size: " + carComponents.size());

            compMap.put(WHEEL_RIM_KEY, carComponents);
            System.out.println("("+WHEEL_RIM_KEY+") HASH MAP: " + compMap);

            //serialize compMap
            jobjHandler.writeComponent(compMap);

            //deserialize compMap
            retrievedCompMap = new HashMap<>();
            retrievedCompMap = jobjHandler.readComponent(retrievedCompMap);

            System.out.println("(RETRIEVED) HASH MAP: " + retrievedCompMap);
        } else System.out.println("\nNO SUCH ADD BTN TO PRESS\n");

        System.out.println("\nADDING COMPONENT..\n");


    }



    private CarComponent makeComponent(String compKey, CarComponent component) {
        String compType, compName, compColor, compDimension, compMaterial, compSide;
        double compPrice;
        int compQuantity, compPower;

        switch (compKey) {
            case ENGINE_KEY:
                System.out.println("MAKING" + compKey);

                compType = getEngineType();
                compName = engineName.getText();
                compPrice = Double.parseDouble(enginePrice.getText());
                compQuantity = Integer.parseInt(engineQuantity.getText());
                compPower = Integer.parseInt(enginePower.getText());

                component = new Engine(compType, compName, compPrice, compQuantity, compPower);

                /*
                switch (getEngineType()) {
                    case "Bensin":
                        System.out.println(getEngineType());
                        // component = new GasolineEngine(compName, compPrice, compQuantity);
                        break;
                    case "Elbil":
                        System.out.println(getEngineType());
                       // component = new ElectricMotor(compName, compPrice, compQuantity);
                        break;
                    case "Hybrid":
                        System.out.println(getEngineType());
                       // component = new HybridEngine(compName, compPrice, compQuantity);
                        break;
                    default:
                        System.out.println("NO SUCH ENGINE TYPE");


                }

                 */


                break;
            case SEAT_KEY:
                System.out.println("MAKING " + compKey);

                compName = seatName.getText();
                compPrice = Double.parseDouble(seatPrice.getText());
                compQuantity = Integer.parseInt(seatQuantity.getText());
                compColor = seatColor.getText();
                compMaterial = seatMaterial.getText();


                component = new Seat(compName, compPrice, compQuantity, compColor, compMaterial);
                break;
            case SPOILER_KEY:
                System.out.println("MAKING " + compKey);


                compName = spoilerName.getText();
                compPrice = Double.parseDouble(spoilerPrice.getText());
                compQuantity = Integer.parseInt(spoilerQuantity.getText());
                compColor = spoilerColor.getText();
                compSide = spoilerSide.getText();

                component = new Spoiler(compName, compPrice, compQuantity, compColor, compSide);
                break;
            case STEERING_WHEEL_KEY:
                System.out.println("MAKING " + compKey);

                compName = steeringWheelName.getText();
                compPrice = Double.parseDouble(steeringWheelPrice.getText());
                compQuantity = Integer.parseInt(steeringWheelQuantity.getText());
                compColor = steeringWheelColor.getText();
                compMaterial = steeringWheelMaterial.getText();

                component = new SteeringWheel(compName, compPrice, compQuantity, compColor, compMaterial);
                break;
            case WHEEL_RIM_KEY:
                System.out.println("MAKING " + compKey);

                compName = wheelRimName.getText();
                compPrice = Double.parseDouble(wheelRimPrice.getText());
                compQuantity = Integer.parseInt(wheelRimQuantity.getText());
                compColor = wheelRimColor.getText();
                compDimension = wheelRimDimension.getText();

                component = new WheelRim(compName, compPrice, compQuantity, compColor, compDimension);
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


    public String getEngineType() {
        return engineType;
    }

    public void setEngineType(String engineType) {
        this.engineType = engineType;
    }


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
}

