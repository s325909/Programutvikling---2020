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

    private Thread thread;




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

    /*@FXML
    public void checkValidation() {
        labelEngine.setText(validationEngine());
        inputEmpty();

        labelSeat.setText(validationSeat());
        inputEmpty();

        labelSpoiler.setText(validationSpoiler());
        inputEmpty();

        labelSteering.setText(validationSteering());
        inputEmpty();

        labelRim.setText(validationRim());
        inputEmpty();
    }*/

    @FXML
    public void addComponent(ActionEvent event) {
       // inputEmpty();

        if (event.getSource() == addEngine) {
            System.out.println("\nADD ENGINE PRESSED\n");

            emptyFieldsEngine();

            if (!validationEngine().isEmpty()) {
                if (!engineName.getText().isEmpty() || !enginePower.getText().isEmpty() || !enginePrice.getText().isEmpty() || !engineQuantity.getText().isEmpty()) {
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
            System.out.println("\nADD SEAT PRESSED\n");

            emptyFieldsSeat();

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
            System.out.println("\nADD SPOILER PRESSED\n");

            emptyFieldsSpoiler();

            if (!validationSpoiler().isEmpty()) {
                if (!spoilerName.getText().isEmpty() || !spoilerColor.getText().isEmpty()
                        || !spoilerSide.getText().isEmpty() || !spoilerPrice.getText().isEmpty()
                        || !spoilerQuantity.getText().isEmpty()) {
                    alert(validationSpoiler());
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
            System.out.println("\nADD STEERING WHEEL PRESSED\n");

            emptyFieldsSteering();

            if (!validationSteering().isEmpty()) {
                if (!steeringWheelName.getText().isEmpty() || !steeringWheelColor.getText().isEmpty()
                        || !steeringWheelMaterial.getText().isEmpty() || !steeringWheelPrice.getText().isEmpty()
                        || !steeringWheelQuantity.getText().isEmpty()) {
                    alert(validationSteering());
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
            System.out.println("\nADD WHEEL RIM PRESSED\n");

            emptyFieldsRim();

            if (!validationRim().isEmpty()) {
                if (!wheelRimName.getText().isEmpty() || !wheelRimColor.getText().isEmpty()
                        || !wheelRimDimension.getText().isEmpty() || !wheelRimPrice.getText().isEmpty() || !wheelRimQuantity.getText().isEmpty()) {
                    alert(validationRim());
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

        System.out.println("\nADDING COMPONENT..\n");


        addComp = (Button) event.getSource();


        thread = fileOperation();


        try {
            showThreadOperationAlert(0);
            thread.start();
        } catch (IllegalThreadStateException e) {
            System.err.println("ILLEGAL THREAT STATE EXCEPTION: " + e.toString());
        }



      //  addBtn.setDisable(false);
      //  backToAdd.setDisable(false);
    }




    // create a alert
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
    public void emptyFieldsSeat() {
        if (seatName.getText().isEmpty() && seatColor.getText().isEmpty() && seatMaterial.getText().isEmpty()
                && seatPrice.getText().isEmpty() && seatQuantity.getText().isEmpty()) {
            alert("Fyll inn alle felt!");
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
        }
    }

    public String validationEngine() {
        return Validation.valAdminEngine(engineName.getText(), enginePower.getText(), enginePrice.getText(), engineQuantity.getText());
    }
    public void emptyFieldsEngine() {
        if (engineName.getText().isEmpty() && enginePower.getText().isEmpty() && enginePrice.getText().isEmpty()
                && engineQuantity.getText().isEmpty()) {
            alert("Fyll inn alle felt!");
        }
    }

    public String validationRim() {
        return Validation.valAdminRim(wheelRimName.getText(), wheelRimDimension.getText(), wheelRimColor.getText(), wheelRimPrice.getText(),
                wheelRimQuantity.getText());
    }
    public void emptyFieldsRim() {
        if (wheelRimName.getText().isEmpty() && wheelRimColor.getText().isEmpty() && wheelRimDimension.getText().isEmpty()
                && wheelRimPrice.getText().isEmpty() && wheelRimQuantity.getText().isEmpty()) {
            alert("Fyll inn alle felt!");
        }
    }

   /* //denne funker
    public void inputEmpty() {
        if (validationSeat().isEmpty()) {
            alert("Du må fylle ut alle felt!");
        }

        if(validationSpoiler().isEmpty()) {
            alert("Du må fylle ut alle felt");
        }

        if(validationSteering().isEmpty()) {
            alert("Du må fylle ut alle felt");
        }

        if(validationRim().isEmpty()) {
            alert("Du må fylle ut alle felt");
        }

        if(validationEngine().isEmpty()) {
            alert("Du må fylle ut alle felt");
        }
    }*/


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

