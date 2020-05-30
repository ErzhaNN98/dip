package main.sample;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;

public class Controller extends BaseController {
    @FXML
    private Button signInButton;

    @FXML
    private Button signUpButton;

    public void initialize() {
        signInButton.setOnAction(event -> {
            try {
                changeSceneByEvent(event, "login.fxml");
            } catch (Exception e) {
                e.printStackTrace();
            }
        });

        signUpButton.setOnAction(event -> {
            try {
                changeSceneByEvent(event, "register.fxml");
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

    public void handleButtonAction(MouseEvent mouseEvent) {

    }
}
