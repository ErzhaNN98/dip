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
import javafx.stage.Stage;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Arrays;
import java.util.List;
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

    @FXML
    public TextField phoneNumberTextField;

    private final List<String> codeAperators = Arrays.asList("701", "702", "705", "777", "775", "776", "778", "747", "700", "707");

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        signUpButton.setOnAction(actionEvent -> {
            try {
                signUpButtonClick(actionEvent);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
        backButton.setOnAction(event -> {
            try {
                changeSceneByEvent(event, "sample.fxml");
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

    private void signUpButtonClick(ActionEvent actionEvent) throws Exception {
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

        final String phoneNumber = getNormalPhoneNumber(phoneNumberTextField.getText());

        if (phoneNumber.length() != 11) {
            showError("Phone number length must be 11");
            return;
        }

        if (phoneNumber.charAt(0) != '7') {
            showError("something wrong");
            return;
        }

        for (int i = 1; i < phoneNumber.length(); i++) {
            if (phoneNumber.charAt(i) >= '0' && phoneNumber.charAt(i) <= '9') {
                continue;
            }
            showError("something wrong1");
            return;
        }

        final String codeAperator = getCodeAperator(phoneNumber);
        boolean ok = false;
        for (int i = 0; i < codeAperators.size(); i++) {
            if (codeAperator.equals(codeAperators.get(i))) {
                ok = true;
                break;
            }
        }

        if (!ok) {
            showError("Code aperator doesn't exists");
        }


        final String username = usernameTextField.getText();
        final String name = nameTextField.getText();
        final String surname = surnameTextField.getText();

        User user = new User(null, username, password, name, surname, phoneNumber);

        String response = new RestService().postForString("http://localhost:8080/user/create", user.toString(), "POST");
        if (response.isEmpty()) {
            showError("Error");
            return;
        }

        user.setId(findId(response));

        //new RestService().postForString("http://localhost:8080/logInfo/create", user.getId().toString(), "POST");
        changeSceneByEventWithDate(actionEvent, user);
    }

    private void hideError() {
        showError("");
    }

    private void showError(String text) {
        this.errorLabel.setText(text);
    }

    private String getNormalPhoneNumber(String phoneNumber) {
        StringBuilder res = new StringBuilder();
        int i = 0;
        if (phoneNumber.charAt(0) == '+') {
            i = 1;
        } else if (phoneNumber.charAt(0) == '8') {
            i = 1;
            res.append('7');
        }
        for (; i < phoneNumber.length(); i++) {
            char ch = phoneNumber.charAt(i);
            if (ch == '(' || ch == ')') {
                continue;
            }
            res.append(ch);
        }

        return res.toString();
    }

    private String getCodeAperator(String phoneNumber) {
        return phoneNumber.substring(1, 4);
    }

    private Integer findId(String response) {
        StringBuilder res = new StringBuilder();
        int id = 0;
        for (int i = 4; i < response.length() - 1; i++) {
            if (response.charAt(i) == ',') {
                break;
            }
            res.append(response.charAt(i));
        }
        return Integer.parseInt(res.toString());
    }
}
