package org.ccomp.admin;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

public class AddComponentController implements Initializable {

    @FXML
    Button backAdmin;

    @FXML
    Button next;

    @FXML
    RadioButton engine, seat, spoiler, steeringwheel, wheelrim;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    @FXML
    public void goNext() {
        try {
            Stage stage = (Stage) next.getScene().getWindow();

            if (engine.isSelected()) {
                URL url = getClass().getResource("/org/ccomp/admin/compGUI/kEngine.fxml");
                FXMLLoader loader = new FXMLLoader(url);
                Parent root = loader.load();
                Scene scene = new Scene(root, 600, 500);
                stage.setScene(scene);
                stage.show();
            }

            else if (seat.isSelected()) {
                URL url = getClass().getResource("/org/ccomp/admin/compGUI/kSeat.fxml");
                FXMLLoader loader = new FXMLLoader(url);
                Parent root = loader.load();
                Scene scene = new Scene(root, 600, 500);
                stage.setScene(scene);
                stage.show();
            }

            else if (spoiler.isSelected()) {
                URL url = getClass().getResource("/org/ccomp/admin/compGUI/kSpoiler.fxml");
                FXMLLoader loader = new FXMLLoader(url);
                Parent root = loader.load();
                Scene scene = new Scene(root, 600, 500);
                stage.setScene(scene);
                stage.show();
            }

            else if (steeringwheel.isSelected()) {
                URL url = getClass().getResource("/org/ccomp/admin/compGUI/kSteeringwheel.fxml");
                FXMLLoader loader = new FXMLLoader(url);
                Parent root = loader.load();
                Scene scene = new Scene(root, 600, 500);
                stage.setScene(scene);
                stage.show();
            }
            else if (wheelrim.isSelected()) {
                URL url = getClass().getResource("/org/ccomp/admin/compGUI/kWheelrim.fxml");
                FXMLLoader loader = new FXMLLoader(url);
                Parent root = loader.load();
                Scene scene = new Scene(root, 600, 500);
                stage.setScene(scene);
                stage.show();
            }
            //else //validation? Button ikke valgt???{


        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    public void backToAdmin() {
        try {
            Stage stage = (Stage) backAdmin.getScene().getWindow();
            URL url = getClass().getResource("/org/ccomp/admin/admin.fxml");
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














