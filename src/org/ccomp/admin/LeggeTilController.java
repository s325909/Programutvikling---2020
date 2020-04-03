package org.ccomp.admin;

import java.net.URL;
import java.util.ResourceBundle;

//import com.sun.tools.javac.comp.Todo;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import org.ccomp.model.Car;
import org.ccomp.model.component.engine.ElectricMotor;
import org.ccomp.model.component.engine.Engine;
import org.ccomp.model.component.engine.GasolineEngine;


public class LeggeTilController implements Initializable {
    String komponentType;
    String bilTypetext;
    String motorNavn;
    String hesteKreftertxt;
    String pristxt;


    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    //bilType
    @FXML
    RadioButton bensin, elBil, hybrid;


    //komponent
    @FXML
    RadioButton motor, seteTrekk, felg;

    //Skrive inn

    @FXML
    TextField navn, hestekrefter, pris;

    @FXML
    TextArea skrivUt;

    //leggtilKnapp
    @FXML
    Button printUt;

    @FXML
    ToggleGroup carTypeGroup,componentTypeGroup;


    @FXML
    void utText() {

        ElectricMotor electricMotor = new ElectricMotor();
        electricMotor.engineName(navn.getText());
        electricMotor.enginePower(Integer.parseInt(hestekrefter.getText()));
        electricMotor.enginePrice(Integer.parseInt(pris.getText()));

        Car car = new Car(electricMotor);
        car.builCarEngine();

        String komponentType = "";
        String bilTypetext = "";
        String motorNavn = electricMotor.getEngineName();//navn.getText();
        String hesteKreftertxt = String.valueOf(electricMotor.getHorsePower());//hestekrefter.getText();
        String pristxt = String.valueOf(electricMotor.getEnginePrice());//pris.getText();

       if (carTypeGroup.getSelectedToggle().isSelected() && componentTypeGroup.getSelectedToggle().isSelected() ) {
            valgKnapp();


        }







        //if elbil
        //kaller selectedENgine
        // new electro motor , etc
    }

   /* private void selectedEngine(Engine engine1 ){

        engine1.engineName(motorNavn);
        engine1.enginePower(Integer.parseInt(hesteKreftertxt));
        engine1.enginePrice(Integer.parseInt(pristxt));






    }*/

    public void valgKnapp(){
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
        );

    }

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















