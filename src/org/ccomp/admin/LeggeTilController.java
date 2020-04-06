package org.ccomp.admin;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.URL;
import java.util.ResourceBundle;

//import com.sun.tools.javac.comp.Todo;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import org.ccomp.model.Car;
import org.ccomp.model.component.Seat;
import org.ccomp.model.component.Spoiler;
import org.ccomp.model.component.SteeringWheel;
import org.ccomp.model.component.engine.ElectricMotor;
import org.ccomp.model.component.engine.Engine;
import org.ccomp.model.component.engine.GasolineEngine;
import org.ccomp.model.component.engine.HybridEngine;

import javax.swing.*;


public class LeggeTilController implements Initializable {

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
    RadioButton engine, seat, spoiler;

    //Skrive inn

    @FXML
    TextField navn, /*horsepower*/
            variable, price;
    //her må det komme inn en felles verdig for tekstfelt 2

    @FXML
    TextArea skrivUt;

    //leggtilKnapp
    @FXML
    Button printUt;

    @FXML
    ToggleGroup carTypeGroup, componentTypeGroup;


    @FXML
    void utText() {

        /*
        ElectricMotor electricMotor = new ElectricMotor();
        electricMotor.engineName(navn.getText());
        electricMotor.enginePower(Integer.parseInt(hestekrefter.getText()));
        electricMotor.enginePrice(Integer.parseInt(pris.getText()));

         */


        // Car car = new Car(electricMotor);
        // car.builCarEngine();

      /*  String komponentType = "";
        String bilTypetext = "";
        String engineName = electricMotor.getEngineName();//navn.getText();
        String hesteKreftertxt = String.valueOf(electricMotor.getHorsePower());//hestekrefter.getText();
        String pristxt = String.valueOf(electricMotor.getEnginePrice());//pris.getText();*/

      /* if (carTypeGroup.getSelectedToggle().isSelected() && componentTypeGroup.getSelectedToggle().isSelected() ) {
            selectedCarType();


        }*/


        selectedCarType();


        //skrivUt.setText("Biltype:" + namegivingEngine + "\n");


        //if elbil
        //kaller selectedENgine
        // new electro motor , etc
    }


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


    /*private void selectedAthoerComp(){
        if (seat.isSelected()){
            Seat seat1 = new Seat();
            selectedSeat(seat1);
            System.out.println("ok");
        }

        if(spoiler.isSelected()){
            Spoiler spoiler = new Spoiler();
            selectedSpoiler(spoiler);

        }*/


    //TODO må bare forsette videre med flere komp

    

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
    private Engine selectedEngine(Engine engine) {

        namegivingEngine = navn.getText();
        horsePowertxt = String.valueOf(variable.getText());
        pricetxt = price.getText();
        engine.engineName(namegivingEngine);
        engine.enginePower(Integer.parseInt(horsePowertxt));
        engine.enginePrice(Integer.parseInt(pricetxt));
        return engine;
    }


   /* public void valgKnapp(){
        bensin.setUserData("Bensin");
        elBil.setUserData("Elbil");
        hybrid.setUserData("Hybrid");
        motor.setUserData("Motor");
        seteTrekk.setUserData("Setetrekk");
        felg.setUserData("Felg");
        bilTypetext = carTypeGroup.getSelectedToggle().getUserData().toString();
        komponentType = componentTypeGroup.getSelectedToggle().getUserData().toString() ;
        Bil bil1 = new Bil(bilTypetext, komponentType, motorNavn, hesteKreftertxt, pristxt);
        skrivUt.setText("Bil: " + bil1.getBilType() + "\n" +
                "Komponent: " + bil1.getKomponenter() + "\n" +
                "Navn :  " + bil1.getNavn()+ "\n" +
                "Hestekrefter :" +  bil1.getHestekrefter() + "\n" +
                "Pris: " + bil1.getPris()
        );*/





    /*public void knappValg(RadioButton knapptype) {
        bilTypetext = knapptype.getText();
        Bil bil1 = new Bil(bilTypetext, radiotext, motorNavn, hesteKreftertxt, pristxt);
        skrivUt.setText("Bil: " + bil1.getBilType() + "\n" +
                "Komponent: " + bil1.getKomponenter() + "\n" +
                "Navn :  " + bil1.getNavn() + "\n" +
                "Hestekrefter :" + bil1.getHestekrefter() + "\n" +
                "Pris: " + bil1.getPris()
        );

    }*/


}














