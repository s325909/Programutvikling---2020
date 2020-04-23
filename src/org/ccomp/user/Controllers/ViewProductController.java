package org.ccomp.user.Controllers;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;
import org.ccomp.model.component.*;
import org.ccomp.model.component.engine.Engine;

import java.net.URL;
import java.util.ResourceBundle;


public class ViewProductController implements Initializable {

    @FXML
    Button backBtn, addComp;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    @FXML
    public void backToMain() {
        try{
            Stage stage = (Stage) backBtn.getScene().getWindow();
            URL url = getClass().getResource("/org/ccomp/user/user.fxml");
            FXMLLoader loader = new FXMLLoader(url);
            Parent root = loader.load();
            Scene scene = new Scene(root, 600, 500);
            stage.setScene(scene);
            stage.show();
        } catch (Exception e){
            e.printStackTrace();
        }
    }

    @FXML
    public void toUserCart() {
        try {
            Stage stage = (Stage) addComp.getScene().getWindow();
            URL url = getClass().getResource("/org/ccomp/user/userCart.fxml");
            FXMLLoader loader = new FXMLLoader(url);
            Parent root = loader.load();
            Scene scene = new Scene(root, 800, 600);
            stage.setScene(scene);
            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
