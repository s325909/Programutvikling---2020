package org.ccomp.admin.compGUI;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;
import org.ccomp.fileHandling.ComponentOBJHandler;
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
    private Button addComp, addEngine, addSeat, addSpoiler, addSteeringWheel, addWheelRim, ok;

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


    private ComponentOBJHandler jobjHandler;
    private HashMap<String, List<CarComponent>> compMap;
    private List<CarComponent> carComponents;
    private Engine engine;
    private Seat seat;
    private Spoiler spoiler;
    private SteeringWheel steeringWheel;
    private WheelRim wheelRim;

    private Thread thread;

    private boolean emptyFields;

    @FXML
    public void initialize() {

        jobjHandler = new ComponentOBJHandler();

        //Initialize HashMap with List of Car Components from file
        compMap = jobjHandler.readComponent(compMap);

        if (compMap == null) compMap = new HashMap<>();
    }

    @FXML
    public void addComponent(ActionEvent event) {
        //Booelan emptyfields set to false, only changed to true if there are empty fields, used in emptyfields methods
        emptyFields = false;

        if (event.getSource() == addEngine) {
            //When add button in engineStage is pressed, check if there if emptyfield (call on method)
            emptyFieldsEngine();
            //If emptyfields false, exit the method
            if (emptyFields) return;

            //Then checks if there is any validation in validaitonEngine method, if there is: alert with String from method in Validation class
            if (!validationEngine().isEmpty()) {
                if (!engineName.getText().isEmpty() || !enginePower.getText().isEmpty()
                        || !enginePrice.getText().isEmpty() || !engineQuantity.getText().isEmpty()) {
                    alert(validationEngine());
                    return;
                }
            }

            //Getting all saved Seat components from HashMap and assigning them to new List of CarComponent
            carComponents = compMap.get(ENGINE_KEY);

            // Init CarComponents List if List is Null
            if (carComponents == null) carComponents = new ArrayList<>();

            engine = (Engine) makeComponent(ENGINE_KEY, engine);

            carComponents.add(engine);

            compMap.put(ENGINE_KEY, carComponents);

        } else if (event.getSource() == addSeat) {
            //When add button in seatStage is pressed, check if there if emptyfield (call on method)
            emptyFieldsSeat();
            if (emptyFields) return;

            if (!validationSeat().isEmpty()) {
                if (!seatName.getText().isEmpty() || !seatColor.getText().isEmpty()
                        || !seatMaterial.getText().isEmpty() || !seatPrice.getText().isEmpty()
                        || !seatQuantity.getText().isEmpty()) {
                    alert(validationSeat());
                    return;
                }
            }

            //Getting all saved Seat components from HashMap and assigning them to new List of CarComponent
            carComponents = compMap.get(SEAT_KEY);

            // Init CarComponents List if List is Null
            if (carComponents == null) carComponents = new ArrayList<>();

            //up casting parent reference to child reference based on componentType
            seat = (Seat) makeComponent(SEAT_KEY, seat);

            carComponents.add(seat);

            compMap.put(SEAT_KEY, carComponents);

          //  iterateHashMapList(retrievedCompMap);
        } else if (event.getSource() == addSpoiler) {
            //When add button in spoilerStage is pressed, check if there if emptyfield (call on method)
            emptyFieldsSpoiler();
            if (emptyFields) return;

            if (!validationSpoiler().isEmpty()) {
                if (!spoilerName.getText().isEmpty() || !spoilerColor.getText().isEmpty()
                        || !spoilerSide.getText().isEmpty() || !spoilerPrice.getText().isEmpty()
                        || !spoilerQuantity.getText().isEmpty()) {
                    alert(validationSpoiler());
                    return;
                }
            }

            //Getting all saved Spoiler components from HashMap and assigning them to new List of CarComponent
            carComponents = compMap.get(SPOILER_KEY);

            // Init CarComponents List if List is Null
            if (carComponents == null) carComponents = new ArrayList<>();

            //up casting parent reference to child reference based on componentType
            spoiler = (Spoiler) makeComponent(SPOILER_KEY, spoiler);

            if (carComponents == null) carComponents = new ArrayList<>();

            carComponents.add(spoiler);

            compMap.put(SPOILER_KEY, carComponents);

        } else if (event.getSource() == addSteeringWheel) {
            //When add button in spoilerStage is pressed, check if there if emptyfield (call on method)
            emptyFieldsSteering();
            if (emptyFields) return;

            if (!validationSteering().isEmpty()) {
                if (!steeringWheelName.getText().isEmpty() || !steeringWheelColor.getText().isEmpty()
                        || !steeringWheelMaterial.getText().isEmpty() || !steeringWheelPrice.getText().isEmpty()
                        || !steeringWheelQuantity.getText().isEmpty()) {
                    alert(validationSteering());
                    return;
                }
            }

            //Getting all saved SteeringWheel components from HashMap and assigning them to new List of CarComponent
            carComponents = compMap.get(STEERING_WHEEL_KEY);
            if (carComponents == null) carComponents = new ArrayList<>();

            //up casting parent reference to child reference based on componentType
            steeringWheel = (SteeringWheel) makeComponent(STEERING_WHEEL_KEY, steeringWheel);

            //Add Component to List
            carComponents.add(steeringWheel);

            //Put List of Components inside HashMap use the ComponentType as KEY
            compMap.put(STEERING_WHEEL_KEY, carComponents);
        } else if (event.getSource() == addWheelRim) {
            //When add button in wheelRimStage is pressed, check if there if emptyfield (call on method)
            emptyFieldsRim();
            if (emptyFields) return;

            if (!validationRim().isEmpty()) {
                if (!wheelRimName.getText().isEmpty() || !wheelRimColor.getText().isEmpty()
                        || !wheelRimDimension.getText().isEmpty() || !wheelRimPrice.getText().isEmpty() || !wheelRimQuantity.getText().isEmpty()) {
                    alert(validationRim());
                    return;
                }
            }

            //Getting all saved Spoiler components from HashMap and assigning them to new List of CarComponent
            carComponents = compMap.get(WHEEL_RIM_KEY);

            // Init CarComponents List if List is Null
            if (carComponents == null) carComponents = new ArrayList<>();

            //up casting parent reference to child reference based on componentType
            wheelRim = (WheelRim) makeComponent(WHEEL_RIM_KEY, wheelRim);

            carComponents.add(wheelRim);

            compMap.put(WHEEL_RIM_KEY, carComponents);

        } else System.out.println("\nNO SUCH ADD BTN TO PRESS\n");

        //cast addComp Btn to the pressed Button
        addComp = (Button) event.getSource();

        //Run thread in which the file operation to save components to file will happen
        thread = fileOperation();

        try {
            showThreadOperationAlert(0);
            thread.start();
        } catch (IllegalThreadStateException e) {
            System.err.println("ILLEGAL THREAT STATE EXCEPTION: " + e.toString());
        }
    }

    Alert alert = new Alert(Alert.AlertType.NONE);

    private void showThreadOperationAlert(int state) {
        // set alert type
        alert.setAlertType(Alert.AlertType.INFORMATION);

        // set content text
        if (state == 0) alert.setContentText("FILE OPERATION ON THREAD IN PROGRESS...");
        else if (state == 1) alert.setContentText("FILE OPERATION ON THREAD FINISHED");

        // show the dialog
        alert.show();
    }

    private Thread fileOperation() {

        thread = new Thread(new Runnable() {

            private volatile boolean exit = false;

            public void stopThreadOperation() {
                exit = true;
                addComp.setDisable(false);
                backToAdd.setDisable(false);


                if (alert.isShowing()) {
                    Platform.runLater(() -> alert.close());
                }


                Platform.runLater(() -> showThreadOperationAlert(1));
            }

            @Override
            public void run() {
                while (!exit) {
                    try {
                        System.out.println("FILE OPERATION ON THREAD IN PROGRESS...");

                        addComp.setDisable(true);
                        backToAdd.setDisable(true);

                        Thread.sleep(7000);

                        jobjHandler.writeComponent(compMap);
                    } catch (InterruptedException e) {
                        System.err.println("Thread Failure: " + e.toString());
                    } finally {
                        stopThreadOperation();
                    }
                }
            }
        });

        return thread;
    }

    private CarComponent makeComponent(String compKey, CarComponent component) {
        String compType, compName, compColor, compDimension, compMaterial, compSide;
        double compPrice;
        int compQuantity, compPower;

        switch (compKey) {
            case ENGINE_KEY:
                System.out.println("MAKING " + compKey);

                compType = getEngineType();
                compName = engineName.getText();
                compPrice = Double.parseDouble(enginePrice.getText());
                compQuantity = Integer.parseInt(engineQuantity.getText());
                compPower = Integer.parseInt(enginePower.getText());

                component = new Engine(compType, compName, compPrice, compQuantity, compPower);
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

   //Alert method that returns any string message
    public static void alert(String msg) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("CCOMP");
        alert.setHeaderText("Feil!");
        alert.setContentText(msg);
        alert.showAndWait();

    }

    //Returns the method from validation with varibles from texfield in compcontroller.
    public String validationSeat() {
        return Validation.valAdminSeat( seatName.getId(),
                seatMaterial.getText(), seatColor.getText(), seatPrice.getText(), seatQuantity.getText());
    }
    //Method for checking if texfield are empty, return an alert if fields are empty.
    public void emptyFieldsSeat() {
        if (seatName.getText().isEmpty() && seatColor.getText().isEmpty() && seatMaterial.getText().isEmpty()
                && seatPrice.getText().isEmpty() && seatQuantity.getText().isEmpty()) {
            alert("Fyll inn alle felt!");
            emptyFields = true;
        }
    }

    public String validationSpoiler() {
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

