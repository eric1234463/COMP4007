package kiosk;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;

import java.net.*;
import java.util.ResourceBundle;

public class Controller implements Initializable {
    @FXML
    private Scene scene;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
    }

    public void setScene(Scene scene) {
        this.scene = scene;
    }

    public void setSeat(Table table) {
        ImageView imageView = (ImageView) scene.lookup(table.getTableNo());
        String imageUrl = "";
        if (!table.getEmpty() && table.getTicketNo() != null) {
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
        Platform.runLater(() -> {
            imageView.setImage(image);
        });
//        imageView.setImage(image);
    }

    public void updateQueue(String queueId, int queueSize) {
        Text queueElement = (Text) scene.lookup(queueId);
        Platform.runLater(() -> {
            queueElement.setText(Integer.toString(queueSize));
        });
//        queueElement.setText(Integer.toString(queueSize));
    }

    public void updateLastTicketCall(int queueIndex, String ticketNo) {
        String index = "#ticket_" + queueIndex;
        Text tableElement = (Text) scene.lookup(index);
        Platform.runLater(() -> {
        tableElement.setText("Ticket - " + ticketNo);
        });
//        tableElement.setText("Ticket - " + ticketNo);
    }


}
