package org.ccomp.admin.compGUI;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;
import org.ccomp.model.component.Seat;
import org.ccomp.model.component.Spoiler;
import org.ccomp.model.component.engine.ElectricMotor;
import org.ccomp.model.component.engine.Engine;
import org.ccomp.model.component.engine.GasolineEngine;
import org.ccomp.model.component.engine.HybridEngine;

import java.net.URL;
import java.util.ResourceBundle;

public class CompController implements Initializable {

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


    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    //bilType
    @FXML
    RadioButton gasoline, electric, hybrid;


    //komponent
    @FXML
    RadioButton engine, seat, spoiler, steeringwheel, wheelrim;

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
            GasolineEngine gasolineEngine = new GasolineEngine();
            selectedEngine(gasolineEngine);
            gasolineEngine.printEngine();

        } else if (electric.isSelected()) {
            System.out.println("El");
            ElectricMotor electricMotor = new ElectricMotor();
            selectedEngine(electricMotor);
            electricMotor.printEngine();

        } else if (hybrid.isSelected()) {
            System.out.println("hybrid");
            HybridEngine hybridEngine = new HybridEngine();
            selectedEngine(hybridEngine);
            hybridEngine.printEngine();
        }
    }

    private Engine selectedEngine(Engine engine) {

        namegivingEngine = navn.getText();
        horsePowertxt = String.valueOf(variable.getText());
        pricetxt = price.getText();
        engine.engineName(namegivingEngine);
        engine.enginePower(Integer.parseInt(horsePowertxt));
        engine.enginePrice(Integer.parseInt(pricetxt));
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

