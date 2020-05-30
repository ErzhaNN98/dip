package main.sample;

import javafx.event.Event;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.stage.Stage;
import resources.ResourcesResolver;

import java.io.IOException;

public class BaseController {
    protected ResourcesResolver resourcesResolver = ResourcesResolver.getResourcesResolver();

    protected void changeSceneByEvent(Event event, String resource) throws Exception {
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.close();

        Scene scene = new Scene(FXMLLoader.load(resourcesResolver.getResource(resource)));
        stage.setScene(scene);
        stage.show();
    }
}
