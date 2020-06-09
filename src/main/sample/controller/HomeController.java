package main.sample.controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.stage.Window;
import main.sample.BaseController;
import javafx.scene.control.Button;
import main.sample.model.User;
import main.sample.util.RestService;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class HomeController extends BaseController implements Initializable {

    @FXML
    public Button quit;
    @FXML
    public Text userFullName;

    @FXML Text logInfo;

    private List<String> q;
    private User user;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        q = new ArrayList<>();

        quit.setOnAction(event -> {
            try {
                changeSceneByEvent(event, "sample.fxml");
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

    /*
     * Methods
     */
    public void setUser(User user) {
        this.user = user;
        userFullName.setText(user.getName() + " " + user.getSurname());
        String response = new RestService().postForString("http://localhost:8080/logInfo/get-by-user", user.getId().toString(), "GET");

    }

    /*
     * Getters & Setters
     */
}
