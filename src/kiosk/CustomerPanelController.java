package kiosk;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

import java.net.URL;
import java.util.ResourceBundle;

public class CustomerPanelController implements Initializable {
    private Scene scene;

    @FXML
    private Label label_display;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
    }

    public void numberButtonPressed(ActionEvent actionEvent) {
        label_display.setText(((Button) actionEvent.getSource()).getId());
    }

    public void setScene(Scene scene) {
        this.scene = scene;

    }
}
