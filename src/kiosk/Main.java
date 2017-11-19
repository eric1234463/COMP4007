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

//        this.customerPanelStage = new Stage();
//        this.customerPanelStage.setTitle("Customer Panel");
//        customerPanelController = replaceCustomerSceneContent("customerPanel.fxml");
//        customerPanelController.setScene(customerPanelScene);
//        this.customerPanelStage.show();

    }

    public static void main(String[] args) {
        launch(args);
    }

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

    private CustomerPanelController replaceCustomerSceneContent(String fxml) throws Exception {
        this.customerPanelLoader = new FXMLLoader();
        InputStream in = Main.class.getResourceAsStream(fxml);
        this.customerPanelLoader.setBuilderFactory(new JavaFXBuilderFactory());
        this.customerPanelLoader.setLocation(Main.class.getResource(fxml));
        AnchorPane page;
        try {
            page = (AnchorPane) customerPanelLoader.load(in);
        } finally {
            in.close();
        }
        customerPanelScene = new Scene(page);
        this.customerPanelStage.setScene(customerPanelScene);
        this.customerPanelStage.sizeToScene();

        return customerPanelLoader.getController();
    }

}
