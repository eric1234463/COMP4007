package kiosk;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.fxml.JavaFXBuilderFactory;

import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.InputStream;

public class Main extends Application {
    private Stage parentStage;
    @Override
    public void start(Stage primaryStage) throws Exception{
        this.parentStage = primaryStage;
        this.parentStage.setTitle("Kiosk");
        Contoller contoller = (Contoller) replaceSceneContent("table.fxml");

        Listener listener = new Listener(contoller);
        Thread x = new Thread(listener);
        x.start();
        this.parentStage.show();

    }

    public static void main(String[] args) {
        launch(args);
    }

    private Initializable replaceSceneContent(String fxml) throws Exception {
        FXMLLoader loader = new FXMLLoader();
        InputStream in = Main.class.getResourceAsStream(fxml);
        loader.setBuilderFactory(new JavaFXBuilderFactory());
        loader.setLocation(Main.class.getResource(fxml));
        AnchorPane page;
        try {
            page = (AnchorPane) loader.load(in);
        } finally {
            in.close();
        }
        Scene scene = new Scene(page);
        this.parentStage.setScene(scene);
        this.parentStage.sizeToScene();
        return (Initializable) loader.getController();
    }

}
