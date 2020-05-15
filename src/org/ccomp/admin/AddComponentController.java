package org.ccomp.admin;

import java.awt.event.ActionListener;
import java.awt.event.MouseListener;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import org.ccomp.admin.compGUI.CompController;
import org.ccomp.user.Controllers.UserRegController;

import javax.swing.*;

public class AddComponentController {

    @FXML
    Button backAdmin, next;

    @FXML
    Label out;

    @FXML
    RadioButton gasoline, electric, hybrid, engine, seat, spoiler, steeringwheel, wheelrim;

    @FXML
    ToggleGroup carTypeGroup, componentTypeGroup;

    private String selectedCarType;

    public void disableToggegroup(){
        carTypeGroup.getSelectedToggle();
    }

    @FXML
    public void goNext() {

        //Adds each button to a togglegroup
        componentTypeGroup = new ToggleGroup();
        engine.setToggleGroup(componentTypeGroup);
        seat.setToggleGroup(componentTypeGroup);
        spoiler.setToggleGroup(componentTypeGroup);
        steeringwheel.setToggleGroup(componentTypeGroup);
        wheelrim.setToggleGroup(componentTypeGroup);

        carTypeGroup = new ToggleGroup();
        gasoline.setToggleGroup(carTypeGroup);
        electric.setToggleGroup(carTypeGroup);
        hybrid.setToggleGroup(carTypeGroup);


        if (engine.isSelected()) {
            //If engine is selected and cartypegroup is not selected: Message
            if (carTypeGroup.getSelectedToggle() == null) {
                JOptionPane.showMessageDialog(null, "Du må velge biltype for komponentet motor!");
            } else
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
        else {
            //If no component is selected, message:
            if (componentTypeGroup.getSelectedToggle() == null) {
                JOptionPane.showMessageDialog(null, "Du må velge et komponent for å kunne legge til! \n");
            }
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
    private void loadWindow(Button button, String resource, String styleSheet) {
        try {
            Stage stage = (Stage) button.getScene().getWindow();
            URL url = getClass().getResource(resource);
            FXMLLoader loader = new FXMLLoader(url);
            Parent root = loader.load();


            RadioButton selectedRadioButton = (RadioButton) carTypeGroup.getSelectedToggle();

            if (selectedRadioButton != null)
                selectedCarType = selectedRadioButton.getText();

            CompController componentsCart = loader.getController();
            componentsCart.setEngineType(selectedCarType);


            Scene scene = new Scene(root, 600, 500);
            stage.setScene(scene);
            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}














