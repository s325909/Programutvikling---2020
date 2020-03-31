package org.ccomp.admin;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

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






    Bil bil1 = new Bil(bensin.getText(),"","","","");
    






   /* //Kolonnene
    ArrayList<String> biltype = new ArrayList<String>();


    ArrayList<String> motor = new ArrayList<>();
    ArrayList<String> setetrekk = new ArrayList<>();
    ArrayList<String> felg = new ArrayList<>();
    ArrayList<String> ratt = new ArrayList<>();
*/






}















