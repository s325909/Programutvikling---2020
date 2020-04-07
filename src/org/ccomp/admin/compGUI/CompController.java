package org.ccomp.admin.compGUI;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

public class CompController implements Initializable {

    @FXML
    Button backBtn;

    @FXML
    Button next;

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

            else if (steeringW.isSelected()) {
                URL url = getClass().getResource("/org/ccomp/admin/compGUI/kSteeringwheel.fxml");
                FXMLLoader loader = new FXMLLoader(url);
                Parent root = loader.load();
                Scene scene = new Scene(root, 600, 500);
                stage.setScene(scene);
                stage.show();
            }
            else if (kWheelrim.isSelected()) {
                URL url = getClass().getResource("/org/ccomp/admin/compGUI/kWheelrim.fxml");
                FXMLLoader loader = new FXMLLoader(url);
                Parent root = loader.load();
                Scene scene = new Scene(root, 600, 500);
                stage.setScene(scene);
                stage.show();
            }
            else //validation? Button ikke valgt???

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

