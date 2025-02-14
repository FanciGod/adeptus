package com.adeptus.adeptusfe.controller;

import com.adeptus.adeptusfe.dto.request.LogoutRequest;
import com.adeptus.adeptusfe.dto.response.StaffResponse;
import com.adeptus.adeptusfe.service.AuthenticationService;
import com.adeptus.adeptusfe.service.StaffService;
import com.adeptus.adeptusfe.utility.JwtUtils;
import com.adeptus.adeptusfe.utility.Message;
import com.adeptus.adeptusfe.utility.Session;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.io.IOException;

public class HomeController {
    private final AuthenticationService authenticationService = new AuthenticationService();
    private final StaffService staffService = new StaffService();
    @FXML
    public AnchorPane centerPane;
    public ImageView image;
    public Label name;


    @FXML
    public void initialize() throws Exception {
        loadStaff();
    }

    public void loadStaff() throws Exception {
        var staff = getStaffByToken(Session.getToken());
        name.setText(staff.getFullName());
        Image thumbnail = new Image(staff.getThumbnailUrl());
        image.setImage(thumbnail);
    }

    private StaffResponse getStaffByToken(String token) throws Exception {
        String id = JwtUtils.getStaffIdFromJWT(token);
        return staffService.getStaffById(Long.parseLong(id)).getResult();

    }

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

    @FXML
    private void openUpdateStaff() {
        try {
            Stage currentStage = (Stage) image.getScene().getWindow();
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/update_basic_info.fxml"));
            Stage stage = new Stage();
            stage.setScene(new Scene(loader.load()));
            stage.setTitle("Update Info");

            UpdateBasicInfoController updateBasicInfoController = loader.getController();
            updateBasicInfoController.setParentController(this);

            stage.show();


        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
