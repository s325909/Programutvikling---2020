package org.ccomp.admin;

import java.net.URL;
import java.util.ResourceBundle;

//import com.sun.tools.javac.comp.Todo;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import org.ccomp.model.Car;
import org.ccomp.model.component.engine.ElectricMotor;


public class LeggeTilController implements Initializable {
    String radiotext;
    String bilTypetext;
    String motorNavn;
    String hesteKreftertxt;
    String pristxt;



    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }
//bilType
    @FXML
    RadioButton bensin,elBil,hybrid;

    //komponent
    @FXML
    RadioButton motor,seteTrekk,felg;

   //Skrive inn

    @FXML
    TextField navn,hestekrefter,pris;

    @FXML
    TextArea skrivUt;

    //leggtilKnapp
    @FXML
    Button printUt;



   @FXML
    void utText() {

       ElectricMotor electricMotor = new ElectricMotor();
       electricMotor.engineName(navn.getText());
       electricMotor.enginePower(Integer.parseInt(hestekrefter.getText()));
       electricMotor.enginePrice(Integer.parseInt(pris.getText()));


       Car car = new Car(electricMotor);
       car.builCarEngine();

       String radiotext= "";
       String bilTypetext = "";
       String motorNavn = electricMotor.getEngineName();//navn.getText();
       String hesteKreftertxt = String.valueOf(electricMotor.getHorsePower());//hestekrefter.getText();
       String pristxt = String.valueOf(electricMotor.getEnginePrice());//pris.getText();

       }


       if (elBil.isSelected()) {
           bilTypetext = elBil.getText();
           Bil bil1 = new Bil(bilTypetext,radiotext,motorNavn,hesteKreftertxt,pristxt);
           skrivUt.setText("Bil: " + bil1.getBilType()+ "\n" +
                   "Komponent: " + bil1.getKomponenter() + "\n" +
                   "Navn :  " +  bil1.getNavn() + "\n" +
                   "Hestekrefter :" + bil1.getHestekrefter() + "\n" +
                   "Pris: " + bil1.getPris()
           );


       }

       if(hybrid.isSelected()){
           bilTypetext = hybrid.getText();
           Bil bil1 = new Bil(bilTypetext,radiotext,motorNavn,hesteKreftertxt,pristxt);
           skrivUt.setText("Bil: " + bil1.getBilType()+ "\n" +
                   "Komponent: " + bil1.getKomponenter() + "\n" +
                   "Navn :  " +  bil1.getNavn() + "\n" +
                   "Hestekrefter :" + bil1.getHestekrefter() + "\n" +
                   "Pris: " + bil1.getPris()
           );

       }


       //motor
       if (motor.isSelected()){
           radiotext = motor.getText();
           Bil bil1 = new Bil(bilTypetext,radiotext,motorNavn,hesteKreftertxt,pristxt);
           skrivUt.setText("Bil: " + bil1.getBilType()+ "\n" +
                   "Komponent: " + bil1.getKomponenter() + "\n" +
                   "Navn :  " +  bil1.getNavn() + "\n" +
                   "Hestekrefter :" + bil1.getHestekrefter() + "\n" +
                   "Pris: " + bil1.getPris()
           );
       }

       if (seteTrekk.isSelected()){

           radiotext = seteTrekk.getText();
           Bil bil1 = new Bil(bilTypetext,radiotext,motorNavn,hesteKreftertxt,pristxt);
           skrivUt.setText("Bil: " + bil1.getBilType()+ "\n" +
                   "Komponent: " + bil1.getKomponenter() + "\n" +
                   "Navn :  " +  bil1.getNavn() + "\n" +
                   "Hestekrefter :" + bil1.getHestekrefter() + "\n" +
                   "Pris: " + bil1.getPris()
           );
       }

       if(felg.isSelected()){

           radiotext = felg.getText();
           Bil bil1 = new Bil(bilTypetext,radiotext,motorNavn,hesteKreftertxt,pristxt);
           skrivUt.setText("Bil: " + bil1.getBilType()+ "\n" +
                   "Komponent: " + bil1.getKomponenter() + "\n" +
                   "Navn :  " +  bil1.getNavn() + "\n" +
                   "Hestekrefter :" + bil1.getHestekrefter() + "\n" +
                   "Pris: " + bil1.getPris()
           );

       }







       //  String bensinChoosen = bensin.getText();




     // Bil bil1 = new Bil(bensinChoosen,motor.getText(),navn.getText(),hestekrefter.getText(),pris.getText());

      //skrivUt.setText("Biltype:" + bil1.getBilType());



    }


   /* public  void knappValg(RadioButton knapptype){
        bilTypetext = knapptype.getText();
        Bil bil1 = new Bil(bilTypetext,radiotext,motorNavn,hesteKreftertxt,pristxt);
        skrivUt.setText("Bil: " + bil1.getBilType()+ "\n" +
                "Komponent: " + bil1.getKomponenter() + "\n" +
                "Navn :  " +  bil1.getNavn() + "\n" +
                "Hestekrefter :" + bil1.getHestekrefter() + "\n" +
                "Pris: " + bil1.getPris()
        );

    }
*/

   /* //Kolonnene
    ArrayList<String> biltype = new ArrayList<String>();


    ArrayList<String> motor = new ArrayList<>();
    ArrayList<String> setetrekk = new ArrayList<>();
    ArrayList<String> felg = new ArrayList<>();
    ArrayList<String> ratt = new ArrayList<>();
*/






}















