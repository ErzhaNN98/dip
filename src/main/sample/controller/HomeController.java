package main.sample.controller;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextArea;
import javafx.scene.text.Text;
import main.sample.BaseController;
import javafx.scene.control.Button;
import main.sample.model.LogInfo;
import main.sample.model.User;
import main.sample.util.RestService;

import java.io.IOException;
import java.net.URL;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

import static java.lang.System.lineSeparator;

public class HomeController extends BaseController implements Initializable {

    @FXML
    public Button quit;
    @FXML
    public Text userFullName;

    @FXML
    TextArea logInfo;

    @FXML
    public Button shutDown;

    private List<LogInfo> q;
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

        shutDown.setOnAction(event -> {
            try {
                Process shutdown = Runtime.getRuntime().exec(new String[]{"shutdown", "-s"});
            } catch (IOException e) {
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
        try {
            q = parseResponse(response);
            StringBuilder text = new StringBuilder();
            for (LogInfo li : q) {
                SimpleDateFormat changeFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");
                String createTimeStr = changeFormat.format(li.getCreateTime());
                logInfo.appendText(li.getLog());
                logInfo.appendText("   ");
                logInfo.appendText(createTimeStr);
                logInfo.appendText(lineSeparator());
            }
        } catch (ParseException e) {
            q = new ArrayList<>();
        }
    }

    private List<LogInfo> parseResponse(String response) throws ParseException {
        List<LogInfo> res = new ArrayList<>();
        LogInfo logInfo = new LogInfo();
        logInfo.setUserId(user.getId());
        StringBuilder str = new StringBuilder();
        for (int i = 2; i < response.length()- 1; i++) {
            if (response.charAt(i) == ',') {
                logInfo.setLog(str.toString());
                str = new StringBuilder();
            }
            else if (response.charAt(i) == '}') {
                SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");
                Date date = simpleDateFormat.parse(str.toString());
                logInfo.setCreateTime(date);
                str = new StringBuilder();
                res.add(logInfo);
                logInfo = new LogInfo();
                logInfo.setUserId(user.getId());
                i += 3;
            } else {
                str.append(response.charAt(i));
            }
        }
        return res;
    }
}
