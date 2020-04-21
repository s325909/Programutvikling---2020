package org.ccomp.admin;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import javax.swing.*;

public class AddComponentController implements Initializable {

    @FXML
    Button backAdmin, next;

    @FXML
    Label out;

    @FXML
    RadioButton gasoline, electric, hybrid, engine, seat, spoiler, steeringwheel, wheelrim;

    @FXML
    ToggleGroup carTypeGroup, componentTypeGroup;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    @FXML
    public void disableCarTypes() {
        final ToggleGroup carTypes = new ToggleGroup();
        final VBox vbox = new VBox(5);

        for (int i = 1; i <= 3; i++) {
            vbox.getChildren().add(gasoline);
            vbox.getChildren().add(electric);
            vbox.getChildren().add(hybrid);

            for (Toggle t : carTypes.getToggles()) {
                if (t instanceof RadioButton) {
                    ((RadioButton) t).setVisible(true);
                }
            }
        }
    }

    @FXML
    public void goNext() {

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
            if (carTypeGroup.getSelectedToggle() == null ) {
                JOptionPane.showMessageDialog(null, "Du må velge biltype for komponentet motor!");
            }
            else
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
            if (componentTypeGroup.getSelectedToggle() == null ) {
                JOptionPane.showMessageDialog(null, "Du må velge et komponent for å kunne legge til! \n" +
                        "Du kan ikke velge biltype for andre komponenter enn motor.");            }
        }
    }



    @FXML
    public void backToAdmin () {
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
        private void loadWindow (Button button, String resource, String styleSheet){
            try {
                Stage stage = (Stage) button.getScene().getWindow();
                URL url = getClass().getResource(resource);
                FXMLLoader loader = new FXMLLoader(url);
                Parent root = loader.load();
                Scene scene = new Scene(root, 600, 500);
//            scene.getStylesheets().add(getClass().getResource(styleSheet).toExternalForm());
                stage.setScene(scene);
                stage.show();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

    public void motorSelected () {

    }
}














