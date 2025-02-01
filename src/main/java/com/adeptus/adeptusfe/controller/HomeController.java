package com.adeptus.adeptusfe.controller;

import com.adeptus.adeptusfe.dto.request.AuthenticationRequest;
import com.adeptus.adeptusfe.dto.request.LogoutRequest;
import com.adeptus.adeptusfe.dto.response.ApiResponse;
import com.adeptus.adeptusfe.dto.response.AuthenticationResponse;
import com.adeptus.adeptusfe.service.AuthenticationService;
import com.adeptus.adeptusfe.utility.Message;
import com.adeptus.adeptusfe.utility.Session;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;

import java.io.IOException;

public class HomeController {
    private final AuthenticationService authenticationService = new AuthenticationService();
    @FXML
    public AnchorPane centerPane;

    @FXML
    public void handleNavBarClick(ActionEvent event) throws IOException {
        String buttonText = ((Button) event.getSource()).getText();
        Pane newPane = null;

        switch (buttonText) {
            case "Dashboard":
                newPane = FXMLLoader.load(getClass().getResource("/view/dashboard.fxml"));
                break;
            case "Class":
                newPane = FXMLLoader.load(getClass().getResource("/view/class.fxml"));
                break;
            case "Course":
                newPane = FXMLLoader.load(getClass().getResource("/view/course.fxml"));
                break;
            case "Teacher":
                newPane = FXMLLoader.load(getClass().getResource("/view/teacher.fxml"));
                break;
            case "Student":
                newPane = FXMLLoader.load(getClass().getResource("/view/student.fxml"));
                break;
            case "Staff":
                newPane = FXMLLoader.load(getClass().getResource("/view/staff.fxml"));
                break;
            case "Transaction":
                newPane = FXMLLoader.load(getClass().getResource("/view/transaction.fxml"));
                break;
            case "Mark":
                newPane = FXMLLoader.load(getClass().getResource("/view/mark.fxml"));
                break;
            case "Class History":
                newPane = FXMLLoader.load(getClass().getResource("/view/class_history.fxml"));
                break;
            case "Log out":
                handleLogout(); // Gọi hàm xử lý đăng xuất
                return; // Không cần tiếp tục thay đổi giao diện
            default:
                return;
        }

        // Thay thế nội dung của centerPane bằng newPane
        if (newPane != null) {
            centerPane.getChildren().clear();  // Xóa tất cả các node hiện tại trong centerPane
            centerPane.getChildren().add(newPane);  // Thêm newPane vào centerPane
        }
    }

    private void handleLogout() {
        LogoutRequest request = new LogoutRequest(Session.getToken());
        try {
            authenticationService.logout(request);
            Platform.exit();
        } catch (IOException e) {
            System.err.println(e);
            Message.showError("An error occurred while trying to logout. Please try again.");
        }
    }
}
