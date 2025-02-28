package com.adeptus.adeptusfe.controller.staff;

import com.adeptus.adeptusfe.dto.request.CreateNewStaffRequest;
import com.adeptus.adeptusfe.dto.response.ApiResponse;
import com.adeptus.adeptusfe.dto.response.RoleResponse;
import com.adeptus.adeptusfe.dto.response.StaffResponse;
import com.adeptus.adeptusfe.service.RoleService;
import com.adeptus.adeptusfe.service.StaffService;
import com.adeptus.adeptusfe.utility.Message;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class NewStaffController {

    private final StaffService staffService = new StaffService();

    public TextField usernameInput;
    public PasswordField passwordInput;
    public PasswordField rePasswordInput;
    public TextField fullNameInput;
    public TextField emailInput;
    public DatePicker dobInput;
    public TextField phoneInput;
    public TextField salaryInput;
    public Button thumbnailButton;
    public HBox roleHbox;
    public CheckBox roleCheckBox;
    public Button createStaffButton;
    public Label imageName;
    private File selectedFile;
    private StaffController parentController;

    public void setParentController(StaffController parentController) {
        this.parentController = parentController;
    }


    public void initialize() throws IOException {
        var roleList = getAllRoles();
        for (RoleResponse role : roleList) {
            CheckBox checkBox = new CheckBox(role.getRoleName());
            checkBox.setUserData(role.getId()); // Gán ID để dễ xử lý sau này
            roleHbox.getChildren().add(checkBox);
        }
    }

    private Set<Long> getSelectedRoles() {
        Set<Long> selectedRoles = new HashSet<>();
        for (Node node : roleHbox.getChildren()) {
            if (node instanceof CheckBox checkBox && checkBox.isSelected()) {
                selectedRoles.add((Long) checkBox.getUserData()); // Lấy ID từ CheckBox
            }
        }
        return selectedRoles;
    }

    private List<RoleResponse> getAllRoles() throws IOException {
        RoleService roleService = new RoleService();
        return roleService.getAllRoles().getResult();
    }

    public void chooseFile(ActionEvent event) {
        // Tạo một FileChooser
        FileChooser fileChooser = new FileChooser();

        // Thiết lập bộ lọc tệp (chỉ cho phép chọn ảnh)
        FileChooser.ExtensionFilter filter = new FileChooser.ExtensionFilter("Image Files", "*.jpg", "*.png", "*.gif");
        fileChooser.getExtensionFilters().add(filter);

        // Mở FileChooser để người dùng chọn tệp
        selectedFile = fileChooser.showOpenDialog(new Stage());

        // Kiểm tra xem người dùng có chọn tệp không
        if (selectedFile != null) {
            // Lấy đường dẫn tệp ảnh đã chọn
            String fileName = selectedFile.getName();
            System.out.println("Selected file: " + fileName);
            imageName.setText(fileName);

            // Tại đây, bạn có thể sử dụng `filePath` để xử lý ảnh, ví dụ như hiển thị ảnh, lưu vào server, vv.
        }
    }

    @FXML
    private void handleCreateStaff(ActionEvent event) throws IOException {

        CreateNewStaffRequest request = CreateNewStaffRequest.builder()
                .username(usernameInput.getText())
                .password(passwordInput.getText())
                .rePassword(rePasswordInput.getText())
                .fullName(fullNameInput.getText())
                .email(emailInput.getText())
                .dob(dobInput.getValue())
                .phone(phoneInput.getText())
                .thumbnail(selectedFile)
                .salary(Long.parseLong(salaryInput.getText().trim()))
                .roleId(getSelectedRoles())
                .build();

        ApiResponse<StaffResponse> newStaff = staffService.createNewStaff(request);

        if (newStaff.getCode() == 1000) {
            Message.showInfo("created successfully");
            Stage currentStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            currentStage.close();

            if (parentController != null) {
                parentController.loadStaff(0); // Đóng cửa sổ staff cũ
            }
        }else{
            Message.showError(newStaff.getMessage());
        }
    }

}

