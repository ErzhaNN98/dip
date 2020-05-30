package main.sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import resources.ResourcesResolver;

public class Main extends Application {

    ResourcesResolver resourcesResolver = ResourcesResolver.getResourcesResolver();

    @Override
    public void start(Stage primaryStage) throws Exception {
        FXMLLoader fxmlLoader = new FXMLLoader(resourcesResolver.getResource("sample.fxml"));
        Parent root = fxmlLoader.load();
        primaryStage.setTitle("Hello World");

        Controller controller = fxmlLoader.getController();

        Scene primaryScene = new Scene(root);
        primaryStage.setScene(primaryScene);
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
