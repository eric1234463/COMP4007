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
        ImageView imageView = (ImageView) scene.lookup(table.getTableNo());
        String imageUrl = "";
        if (!table.getEmpty()) {
            imageUrl = "assets/table_red.png";
        } else {
            switch (table.getnPersons()) {
                case 1:
                case 2:
                    imageUrl = "assets/table_green.png";
                    break;
                case 3:
                case 4:
                    imageUrl = "assets/table_blue.png";
                    break;
                case 5:
                case 6:
                    imageUrl = "assets/table_grey.png";
                    break;
                case 7:
                case 8:
                    imageUrl = "assets/table_purple.png";
                    break;
                case 9:
                case 10:
                    imageUrl = "assets/table_yellow.png";
                    break;
            }
        }
        Image image = new Image(getClass().getResourceAsStream(imageUrl));
        imageView.setImage(image);
    }


}
