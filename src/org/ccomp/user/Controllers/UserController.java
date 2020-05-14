package org.ccomp.user.Controllers;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;
import org.ccomp.admin.compGUI.CompController;

import java.net.URL;
import java.util.ResourceBundle;

import static org.ccomp.user.Controllers.UserRegController.alert;

public class UserController implements Initializable {

    String selectedCarType;

    @FXML
    Button backBtn, sokProd;

    @FXML
    RadioButton gasoline,electric,hybrid;

    @FXML
    ToggleGroup carTypeGroup;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    /**
     * Method that opens main.fxml when the Back Button is clicked
     */
    @FXML
    public void backToMain() {
        try{
            Stage stage = (Stage) backBtn.getScene().getWindow();
            URL url = getClass().getResource("/org/ccomp/main/main.fxml");
            FXMLLoader loader = new FXMLLoader(url);
            Parent root = loader.load();
            Scene scene = new Scene(root, 500, 500);
            stage.setScene(scene);
            stage.show();
        } catch (Exception e){
            e.printStackTrace();
        }
    }

    @FXML
    public void toViewProduct() {

        ToggleGroup carType = new ToggleGroup();
        gasoline.setToggleGroup(carType);
        electric.setToggleGroup(carType);
        hybrid.setToggleGroup(carType);

        if (carType.getSelectedToggle() == null) {
            alert("Vennligst velg hvilke biltype du eier for å søke komponent!");
        }

        else {
            try {
                Stage stage = (Stage) sokProd.getScene().getWindow();
                URL url = getClass().getResource("/org/ccomp/user/viewProduct.fxml");
                FXMLLoader loader = new FXMLLoader(url);
                Parent root = loader.load();

                RadioButton selectedRadioButton = (RadioButton) carType.getSelectedToggle();

                System.out.println("SELECTED RADIO BTN: " + selectedRadioButton.getText());

                if (selectedRadioButton != null)
                    selectedCarType = selectedRadioButton.getText();



                UserViewCartController userViewCartController = loader.getController();
                userViewCartController.setSelectedcartype(selectedCarType);


                Scene scene = new Scene(root, 800, 600);
                stage.setScene(scene);
                stage.show();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
