package org.ccomp.admin;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import com.sun.tools.javac.comp.Todo;
import com.sun.xml.internal.bind.v2.TODO;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import javax.xml.soap.Text;

public class LeggeTilController implements Initializable {
    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }
//bilType
    @FXML
    RadioButton bensin;
    @FXML
    RadioButton elBil;
    @FXML
    RadioButton hybrid;

    //komponent
   @FXML
   RadioButton motor;

   @FXML
    RadioButton seteTrekk;

   @FXML
    RadioButton felg;

   //Skrive inn

    @FXML
    TextField navn;

    @FXML
    TextField hestekrefter;

    @FXML
    TextField pris;

    @FXML
    TextArea skrivUt;

    //leggtilKnapp
    @FXML
    Button printUt;

   @FXML
    void utText() {

       String radiotext= "";
       String bilTypetext = "";
       String motorNavn = navn.getText();
       String hesteKreftertxt = hestekrefter.getText();
       String pristxt = pris.getText();


  //   Bil bil1 = new Bil(bilTypetext,radiotext,navn.getText(),hestekrefter.getText(),pris.getText());
       //When choosing buttons
       // 1st CarType

       //TODO for motor blir det navn, hestekrefter og pris. For setetrekk blir det valg av skinn/stoff, farge , pris og det samme for felge.
       // Vil at de attrbuttene kommer kun når vi f.eks velger motor/setetrekk etc.

       //TODO prøvd å forenkle den lange koden , men vet ikke helt hvordan jeg skal gjøre det

     /*  if(bensin.isSelected() || elBil.isSelected() || hybrid.isSelected() && motor.isSelected() || seteTrekk.isSelected() || felg.isSelected()){

           String bilTypetext = "";
           bilTypetext = bensin.getText() ,elBil.getText(),hybrid.getText() ;
           Bil bil1 = new Bil(bilTypetext,radiotext,motorNavn,hesteKreftertxt,pristxt);
           skrivUt.setText("Bil: " + bil1.getBilType()+ "\n" +
                   "Komponent: " + bil1.getKomponenter() + "\n" +
                   "Navn :  " +  bil1.getNavn() + "\n" +
                   "Hestekrefter :" + bil1.getHestekrefter() + "\n" +
                   "Pris " + bil1.getPris()
                   );


       }*/


       if (bensin.isSelected()){
           bilTypetext = bensin.getText();
           Bil bil1 = new Bil(bilTypetext,radiotext,motorNavn,hesteKreftertxt,pristxt);
           skrivUt.setText("Bil: " + bil1.getBilType()+ "\n" +
                   "Komponent: " + bil1.getKomponenter() + "\n" +
                   "Navn :  " +  bil1.getNavn() + "\n" +
                   "Hestekrefter :" + bil1.getHestekrefter() + "\n" +
                   "Pris: " + bil1.getPris()
           );

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


   /* //Kolonnene
    ArrayList<String> biltype = new ArrayList<String>();


    ArrayList<String> motor = new ArrayList<>();
    ArrayList<String> setetrekk = new ArrayList<>();
    ArrayList<String> felg = new ArrayList<>();
    ArrayList<String> ratt = new ArrayList<>();
*/






}















