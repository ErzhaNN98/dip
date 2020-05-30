package main.sample.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import main.sample.BaseController;

import java.net.URL;
import java.util.ResourceBundle;

public class LoginController extends BaseController implements Initializable {
    @FXML
    public TextField usernameTextField;
    @FXML
    public Button signInButton;
    @FXML
    public PasswordField passwordField;
    @FXML
    public Button backButton;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        backButton.setOnAction(event -> {
            try {
                changeSceneByEvent(event, "sample.fxml");
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
        signInButton.setOnAction(this::onSignInButtonClick);
    }

    private void onSignInButtonClick(ActionEvent event) {
        System.out.println("username: " + usernameTextField.getText());
        System.out.println("password: " + passwordField.getText());
    }
}
