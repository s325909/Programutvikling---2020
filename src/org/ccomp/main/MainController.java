package org.ccomp.main;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

public class MainController implements Initializable {

    @FXML
    Button adminBtn, userBtn;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    /**
     * Method that opens admin.fxml when the Admin Button is clicked
     */
    @FXML
    public void openAdminPage() {
        try{
            Stage stage = (Stage) adminBtn.getScene().getWindow();
            URL url = getClass().getResource("/org/ccomp/admin/admin.fxml");
            FXMLLoader loader = new FXMLLoader(url);
            Parent root = loader.load();
            Scene scene = new Scene(root, 800, 600);
            stage.setScene(scene);
            stage.show();
        } catch (Exception e){
            e.printStackTrace();
        }
    }

    /**
     * Method that opens user.fxml when the User Button is clicked
     */
    @FXML
    public void openUserPage() {
        try{
            Stage stage = (Stage) userBtn.getScene().getWindow();
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

}
