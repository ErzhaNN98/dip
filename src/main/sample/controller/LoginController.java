package main.sample.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
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

    @FXML
    public Label errorLabel;

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

        hideError();

        final String username = usernameTextField.getText();
        final String password = passwordField.getText();

        if (username == null || username.isEmpty()) {
            showError("Username can not be empty");
            return;
        }

        if (password == null || password.isEmpty()) {
            showError("Password can not be empty");
            return;
        }

        // TODO: make request to server
    }

    private void hideError() {
        this.errorLabel.setText("");
    }

    private void showError(String errorText) {
        this.errorLabel.setText(errorText);
    }
}
