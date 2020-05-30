package main.sample.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import main.sample.BaseController;
import main.sample.model.User;

import java.net.URL;
import java.util.ResourceBundle;

public class RegisterController extends BaseController implements Initializable {

    @FXML
    public Button signUpButton;
    @FXML
    public Label errorLabel;
    @FXML
    public TextField usernameTextField;
    @FXML
    public PasswordField passwordField;
    @FXML
    public PasswordField passwordRepeatField;
    @FXML
    public Button backButton;
    @FXML
    public TextField nameTextField;
    @FXML
    public TextField surnameTextField;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        signUpButton.setOnAction(this::signUpButtonClick);
        backButton.setOnAction(event -> {
            try {
                changeSceneByEvent(event, "sample.fxml");
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

    private void signUpButtonClick(ActionEvent actionEvent) {
        hideError();
        String format = "%s can not be empty";
        if (Util.isEmptyOrNull(usernameTextField.getText())) {
            showError(String.format(format, "Username"));
            return;
        }
        if (Util.isEmptyOrNull(passwordField.getText())) {
            showError(String.format(format, "Password"));
            return;
        }
        if (Util.isEmptyOrNull(passwordRepeatField.getText())) {
            showError(String.format(format, "Password"));
            return;
        }
        if (Util.isEmptyOrNull(nameTextField.getText())) {
            showError(String.format(format, "Name"));
            return;
        }
        if (Util.isEmptyOrNull(surnameTextField.getText())) {
            showError(String.format(format, "Surname"));
            return;
        }

        final String password = passwordField.getText();
        final String passwordRepeat = passwordRepeatField.getText();

        if (!password.equals(passwordRepeat)) {
            showError("Password and Repeat password are not identical");
            return;
        }

        final String username = usernameTextField.getText();
        final String name = nameTextField.getText();
        final String surname = surnameTextField.getText();

        User user = new User(username, password, name, surname);

        // TODO: make request on user sign up
    }

    private void hideError() {
        showError("");
    }

    private void showError(String text) {
        this.errorLabel.setText(text);
    }
}
