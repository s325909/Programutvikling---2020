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

import javax.swing.*;

public class AddComponentController implements Initializable {

    @FXML
    Button backAdmin;

    @FXML
    Button next;

    @FXML
    RadioButton gasoline, electric, hybrid, engine, seat, spoiler, steeringwheel, wheelrim;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    @FXML
    public void goNext() {

        if (engine.isSelected()) {
            loadWindow(next, "/org/ccomp/admin/compGUI/kEngine.fxml", "@../../../resources/style.ccs");
        }

        else if (seat.isSelected()) {
            loadWindow(next, "/org/ccomp/admin/compGUI/kSeat.fxml", "@../../../resources/style.ccs");
        }

        else if (spoiler.isSelected()) {
            loadWindow(next, "/org/ccomp/admin/compGUI/kSpoiler.fxml", "@../../../resources/style.ccs");
        }

        else if (steeringwheel.isSelected()) {
            loadWindow(next, "/org/ccomp/admin/compGUI/kSteeringwheel.fxml", "@../../../resources/style.ccs");

        }
        else if (wheelrim.isSelected()) {
            loadWindow(next, "/org/ccomp/admin/compGUI/kWheelRim.fxml", "@../../../resources/style.ccs");

        }
        //else //validation? Button ikke valgt???{


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

           // loadWindow(backAdmin, "/org/ccomp/admin/admin.fxml", "@../../../resources/style.ccs");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /***
     * Method used to create and load a window used when changing between fxml scenes when a button i clicked
     * @param button
     * @param resource
     * @param styleSheet
     */
    @FXML
    private void loadWindow(Button button, String resource, String styleSheet){
        try {
            Stage stage = (Stage) button.getScene().getWindow();
            URL url = getClass().getResource(resource);
            FXMLLoader loader = new FXMLLoader(url);
            Parent root = loader.load();
            Scene scene = new Scene(root, 600, 500);
//            scene.getStylesheets().add(getClass().getResource(styleSheet).toExternalForm());
            stage.setScene(scene);
            stage.show();
        } catch (Exception e){
            e.printStackTrace();
        }
    }

    public void motorSelected() {

    }
}














