package kiosk;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.fxml.JavaFXBuilderFactory;

import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.InputStream;
import java.util.logging.Level;

public class Main extends Application {
    private Stage parentStage;
    private Controller controller;
    private FXMLLoader loader;
    private Scene scene;


    @Override
    public void start(Stage primaryStage) throws Exception{
        //Logging init
        Log.initLog("log.txt");
        Log.logger.setLevel(Level.FINER);

        this.parentStage = primaryStage;
        this.parentStage.setTitle("Kiosk");
        controller = (Controller) replaceSceneContent("table.fxml");
        controller.setScene(scene);

        Listener listener = new Listener(controller);
        Thread x = new Thread(listener);
        x.start();

        this.parentStage.show();


    }

    public static void main(String[] args) {
        launch(args);
    }

    /**
     * Replace JavaFx Scene Controller with a external defined one.
     * @param fxml FXML file path
     * @return External defined Initializable Controller
     * @throws Exception
     */
    private Initializable replaceSceneContent(String fxml) throws Exception {
        loader = new FXMLLoader();
        InputStream in = Main.class.getResourceAsStream(fxml);
        loader.setBuilderFactory(new JavaFXBuilderFactory());
        loader.setLocation(Main.class.getResource(fxml));
        AnchorPane page;
        try {
            page = (AnchorPane) loader.load(in);
        } finally {
            in.close();
        }
        scene = new Scene(page);
        this.parentStage.setScene(scene);
        this.parentStage.sizeToScene();

        return (Initializable) loader.getController();
    }


}
