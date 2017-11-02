package kiosk;

import javafx.scene.Scene;

import java.awt.event.ActionEvent;

public class CustomerPanelController {
    private Scene scene;

    public void numberButtonPressed(ActionEvent actionEvent){
        System.out.println(actionEvent.getID());
    }
    public void setScene(Scene scene){
        this.scene = scene;

    }
}
