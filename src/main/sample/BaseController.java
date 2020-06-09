package main.sample;

import javafx.event.Event;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import main.sample.controller.HomeController;
import main.sample.model.User;
import resources.ResourcesResolver;

import java.io.IOException;

public class BaseController {
    protected ResourcesResolver resourcesResolver = ResourcesResolver.getResourcesResolver();

    private User user;
    protected void changeSceneByEvent(Event event, String resource) throws Exception {
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.close();

        Scene scene = new Scene(FXMLLoader.load(resourcesResolver.getResource(resource)));
        stage.setScene(scene);
        stage.show();
    }

    protected void changeSceneByEventWithDate(Event event, User user) throws IOException {
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.close();

        FXMLLoader loader = new FXMLLoader(resourcesResolver.getResource("home.fxml"));
        Parent root = loader.load();
        HomeController controllerEditBook = loader.getController(); //получаем контроллер для второй формы
        controllerEditBook.setUser(user); // передаем необходимые параметры
        stage.setScene(new Scene(root));
        stage.show();
    }
}
