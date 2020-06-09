package main.sample.util;

import main.sample.model.User;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.*;
import java.nio.charset.StandardCharsets;

public class RestService {
    public String postForString(String requestUrl, String data, String requestMethod) {
        try {
            URL url = new URL (requestUrl);
//            Http response = HttpRequest.newBuilder(new URI(requestUrl)).headers("username", "qqq", "password", "qqq").GET().build();
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("POST");
            connection.setRequestProperty("Content-Type", "application/json; utf-8");
            connection.setDoOutput(true);
            connection.setDoInput(true);
            connection.connect();
            try(OutputStream os = connection.getOutputStream()) {
                byte[] input = data.getBytes(StandardCharsets.UTF_8);
                os.write(input, 0, input.length);
                os.flush();
                os.close();
            }
            try {
                BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                StringBuilder results = new StringBuilder();
                String line;
                while ((line = reader.readLine()) != null) {
                    results.append(line);
                }
                reader.close();
                System.out.println(results.toString());
                connection.disconnect();
                return results.toString();
            } catch (IOException e) {
                e.printStackTrace();
            }
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "Error";
    }


/*
    public String getResquest(String requestUrl, String data) {
        try {
            URL url = new URL(requestUrl);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            connection.setRequestProperty("Content-Type", "application/json; utf-8");
            connection.setDoOutput(true);
            connection.setDoInput(true);
            connection.connect();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
*/

}
