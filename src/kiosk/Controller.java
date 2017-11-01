package kiosk;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import java.net.*;
import java.io.*;
import java.util.ResourceBundle;

public class Controller implements Initializable {
    @FXML private GridPane gridPane;
    private Scene scene;

    @Override
    public void initialize(URL url, ResourceBundle rb) {


    }
    public void setScene(Scene scene){
        this.scene = scene;

    }
    public void setSeat(Table table){
        Integer minRow = 0;
        Integer maxRow = 0;
        Integer maxCol = 0;
        switch (table.getnPersons()){
            case 1:
            case 2:
                minRow = 0;
                maxRow = 1;
                maxCol = 5;
                break;
            case 3:
            case 4:
                minRow = 2;
                maxRow = 3;
                maxCol = 5;
                break;
            case 5:
            case 6:
                minRow = 4;
                maxRow = 5;
                maxCol = 4;
                break;
            case 7:
            case 8:
                minRow = 6;
                maxRow = 7;
                maxCol = 1;
                break;
            case 9:
            case 10:
                minRow = 8;
                maxRow = 9;
                maxCol = 1;
                break;
        }
        for(int i = minRow; i<maxRow; i++) {
            for(int j = 1; j< maxCol ; j++) {
                String id = "#"+ i + "_" + "j";
                ImageView imageView = (ImageView) scene.lookup(id);
                Image image = new Image(getClass().getResourceAsStream("assets/table_red.png"));
                imageView.setImage(image);
            }

        }
    }


}
