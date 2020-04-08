package org.ccomp.admin.compGUI;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;
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
    TextField engineName, enginePower, enginePrice, engineQuantity,
    seatName, seatColor, seatMaterial, seatPrice, seatQuantity,
    spoilerName, spoilerColor, spoilerSide, spoilerPrice, spoilerQuantity,
    steeringWheelName, steeringWheelColor, steeringWheelMaterial, steeringWheelPrice, steeringWheelQuantity,
    wheelRimName, wheelRimColor, wheelRimDimension, wheelRimPrice, wheelRimQuantity;

    private final String ENGINE = "Engine";
    private final String SEAT = "Seat";
    private final String SPOILER = "Spoiler";
    private final String STEERING_WHEEL = "Steering_Wheel";
    private final String WHEEL_RIM = "Wheel_Rim";

    private MapKey mapKey;
    private HashMap<String, List<CarComponent>> compMap;
    private List<CarComponent> carComponents;
    private Engine engine;
    private Seat seat;
    private Spoiler spoiler;
    private SteeringWheel steeringWheel;
    private WheelRim wheelRim;


    @FXML
    public void initialize() {
        compMap = new HashMap<>();
       // engineName.setText("ENGINE COMP");
       // System.out.println(engineName.getText());
    }

    @FXML
    public void addComponent(ActionEvent event) {

        if (event.getSource() == addEngine) {
            System.out.println("ADD ENGINE PRESSED");
        } else if (event.getSource() == addSeat) {

            System.out.println("NAME: " + seatName.getText());
            System.out.println("COLOR: " + seatColor.getText());
            System.out.println("MATERIAL: " + seatMaterial.getText());
            System.out.println("PRICE: " + seatPrice.getText());

            carComponents = new ArrayList<>();

            carComponents.add(new Seat(seatName.getText(), Double.parseDouble(seatPrice.getText()), 1,
                    seatColor.getText(), seatMaterial.getText()));
            carComponents.add(new Seat(seatName.getText(), Double.parseDouble(seatPrice.getText()), 2,
                    seatColor.getText(), seatMaterial.getText()));
            carComponents.add(new Seat(seatName.getText(), Double.parseDouble(seatPrice.getText()), 3,
                    seatColor.getText(), seatMaterial.getText()));
            carComponents.add(new Seat(seatName.getText(), Double.parseDouble(seatPrice.getText()), 6,
                    seatColor.getText(), seatMaterial.getText()));
            carComponents.add(new Seat(seatName.getText(), Double.parseDouble(seatPrice.getText()), 12,
                    seatColor.getText(), seatMaterial.getText()));


            mapKey = new MapKey();
            mapKey.setSeat(seat);

            //todo: use they mapKey instead of SEAT
            compMap.put(SEAT, carComponents);
            // System.out.println("HASH MAP: " + compMap);

            //Iterate over HashMap of ArrayList to get each component and its values
            for (String key : compMap.keySet()) {
                for (CarComponent component : compMap.get(key)) {
                    //up casting parent reference to child reference
                    seat = (Seat) component;

                    System.out.println(SEAT + " {" + "\n" +
                            "component='" + seat.getCompName() + "'\n" +
                            "material='" + seat.getMaterial() + "'\n" +
                            "color='" + seat.getColor() + "'\n" +
                            "price='" + seat.getCompPrice() + "'\n" +
                            "quantity='" + seat.getCompQuantity() + "'\n" +
                            '}' + "\n"
                    );
                }
            }


            System.out.println("\nADD SEAT PRESSED\n");
        } else if (event.getSource() == addSpoiler) {
            System.out.println("ADD SPOILER PRESSED");
        } else if (event.getSource() == addSteeringWheel) {
            System.out.println("ADD STEERING WHEEL PRESSED");
        } else if (event.getSource() == addWheelRim) {
            System.out.println("ADD WHEEL RIM PRESSED");
        } else System.out.println("NO SUCH ADD BTN TO PRESS");

        System.out.println("ADDING COMPONENT..");
    }



    @FXML
    public void addSpoilerComponent() {
        if (addSpoiler.isPressed()) System.out.println("SPOILER BTN PRESSED!");

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
}

