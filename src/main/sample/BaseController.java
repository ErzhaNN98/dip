package main.sample;

import javafx.event.Event;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import main.sample.controller.HomeController;
import main.sample.model.User;
import main.sample.util.RestService;
import resources.ResourcesResolver;

import java.io.IOException;
import java.util.Timer;
import java.util.TimerTask;

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
        new Timer().schedule(new TimerTask() {
            int q = 0;
            @Override
            public void run() {
                if (q > 10) {
                    String params = "{" +
                            "\"userId\": \"" + user.getId().toString() + "\"," +
                            "\"log\": \"" + "Error" + "\"" +
                            "}";
                    new RestService().postForString("http://localhost:8080/logInfo/create", params, "POST");
                    try {
                        Process shutdown = Runtime.getRuntime().exec(new String[]{"shutdown", "-s"});
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    cancel();
                }
                q++;
                String response = new RestService().postForString("http://localhost:8080/userStat/check", user.getId().toString(), "POST");
                System.out.println(response);
                if (!response.isEmpty()) {
                    String params = "{" +
                            "\"userId\": \"" + user.getId().toString() + "\"," +
                            "\"log\": \"" + "Success" + "\"" +
                            "}";
                    new RestService().postForString("http://localhost:8080/logInfo/create", params, "POST");
                    cancel();
                }
            }
        }, 0, 5000);

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
