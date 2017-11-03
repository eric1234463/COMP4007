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


    public void setScene(Scene scene) {
        this.scene = scene;
    }

    public void numberButtonPressed(ActionEvent actionEvent) {
        if (label_display.getText().length() < 2) {
            if (((Button) actionEvent.getSource()).getId().equals("0")) {
                if (!label_display.getText().equals("")) {
                    label_display.setText(label_display.getText() + ((Button) actionEvent.getSource()).getId());
                }
            } else {
                label_display.setText(label_display.getText() + ((Button) actionEvent.getSource()).getId());
            }
        }
    }

    public void cancel() {
        label_display.setText("");
    }

    public void enter() {
        int nPerson = Integer.parseInt(label_display.getText());
        if (nPerson >= 1 && nPerson <= 10) {
            //TODO: Handle customer panel input
        }
    }
}
