package org.ccomp.admin;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

public class AdminController implements Initializable {

    @FXML
    Button backBtn;

    @FXML
    Button leggTil;


    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    /**
     * Method that opens main.fxml when the Back Button is clicked
     */
    @FXML
    public void backToMain() {
        try {
            Stage stage = (Stage) backBtn.getScene().getWindow();
            URL url = getClass().getResource("/org/ccomp/main/main.fxml");
            FXMLLoader loader = new FXMLLoader(url);
            Parent root = loader.load();
            Scene scene = new Scene(root, 500, 500);
            stage.setScene(scene);
            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    public void tilLeggeTil() {
        try {
            Stage stage = (Stage) leggTil.getScene().getWindow();
            URL url = getClass().getResource("/org/ccomp/admin/leggeTil.fxml");
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
