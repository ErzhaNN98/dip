package main.sample.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import main.sample.BaseController;
import main.sample.model.LogInfo;
import main.sample.model.User;
import main.sample.util.RestService;

import java.net.URL;
import java.util.Arrays;
import java.util.List;
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
        signInButton.setOnAction(event -> {
            try {
                onSignInButtonClick(event);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

    private void onSignInButtonClick(ActionEvent event) throws Exception {

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

        String params = "{" +
                        "\"username\": \"" + username + "\"," +
                        "\"password\": \"" + password + "\"" +
                        "}";

        String response = new RestService().postForString("http://localhost:8080/user/login", params, "GET");
        if (response.isEmpty()) {
            showError("Username or password is incorrect");
            return;
        }

        User user = getUserFromResponse(response);
        LogInfo logInfo = new LogInfo(user.getId(), "Success");
        new RestService().postForString("http://localhost:8080/logInfo/create", user.getId().toString(), "POST");

        changeSceneByEventWithDate(event, user);
    }

    private void hideError() {
        this.errorLabel.setText("");
    }

    private void showError(String errorText) {
        this.errorLabel.setText(errorText);
    }

    private User getUserFromResponse(String response) {
        List<String> res = Arrays.asList("", "", "", "", "", "");
        int id = 0;
        for (int i = 4; i < response.length() - 1; i++) {
            if (response.charAt(i) == ',') {
                id++;
                if (id == 1) {
                    i += 7;
                } if (id == 2) {
                    i += 10;
                } if (id == 3) {
                    i += 6;
                } if (id == 4) {
                    i += 9;
                } if (id == 5) {
                    i += 7;
                }
            }
            res.set(id, res.get(id) + response.charAt(i));
        }
        return new User(Integer.parseInt(res.get(0)), res.get(1), res.get(2), res.get(3), res.get(4), res.get(5));
    }
}
