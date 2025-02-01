package com.adeptus.adeptusfe.controller;

import com.adeptus.adeptusfe.dto.request.AuthenticationRequest;
import com.adeptus.adeptusfe.dto.response.ApiResponse;
import com.adeptus.adeptusfe.dto.response.AuthenticationResponse;
import com.adeptus.adeptusfe.service.AuthenticationService;
import com.adeptus.adeptusfe.utility.Message;
import com.adeptus.adeptusfe.utility.Session;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.io.IOException;

public class LoginController {
    @FXML
    private TextField username;

    @FXML
    private PasswordField password;

    @FXML
    private Button loginButton;

    private final AuthenticationService authenticationService = new AuthenticationService();

    @FXML
    public void handleLogin(ActionEvent event) {
        String user = username.getText();
        String pass = password.getText();

        if (user.isEmpty() || pass.isEmpty()) {
            Message.showError("Username and Password cannot be empty!");
            return;
        }

        AuthenticationRequest request = new AuthenticationRequest(user, pass);

        try {
            ApiResponse<AuthenticationResponse> apiResponse = authenticationService.login(request);
            AuthenticationResponse response = apiResponse.getResult();

            if (response != null && response.getToken() != null) {
                Message.showInfo("Login successful!");
                Session.setToken(response.getToken());
                loadHome(event);
            } else {
                Message.showError("Invalid username or password!");
            }
        } catch (IOException e) {
            System.err.println(e);
            Message.showError("An error occurred while trying to login. Please try again.");
        }
    }

    private void loadHome(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/home.fxml"));
            Parent root = loader.load();

            // Lấy stage hiện tại từ sự kiện
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(new Scene(root));
            stage.setTitle("Dashboard");
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
            Message.showError("Failed to load dashboard.");
        }
    }

}
