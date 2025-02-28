package com.adeptus.adeptusfe.controller.staff;

import com.adeptus.adeptusfe.dto.request.UpdateStaffBasicInfoRequest;
import com.adeptus.adeptusfe.dto.response.ApiResponse;
import com.adeptus.adeptusfe.dto.response.StaffResponse;
import com.adeptus.adeptusfe.service.StaffService;
import com.adeptus.adeptusfe.utility.Message;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class UpdateStaffController {


    public Label headerLabel;
    public TextField fullName;
    public TextField email;
    public TextField phone;
    public DatePicker dob;

    private StaffResponse staffResponse;
    private StaffController parentController;

    public void setParentController(StaffController parentController) {
        this.parentController = parentController;
    }

    private final StaffService staffService = new StaffService();

    public void setStaff(StaffResponse staff) {
        staffResponse = staff;
        headerLabel.setText("Updating on "+ staff.getFullName());
        dob.setValue(staff.getDob());
        phone.setText(staff.getPhone());
        email.setText(staff.getEmail());
        fullName.setText(staff.getFullName());
    }

    @FXML
    public void updateStaff(ActionEvent event) throws Exception {
        UpdateStaffBasicInfoRequest request = UpdateStaffBasicInfoRequest.builder()
                .email(email.getText())
                .dob(dob.getValue())
                .fullName(fullName.getText())
                .phone(phone.getText())
                .build();

        ApiResponse<StaffResponse> updatedStaff = staffService.updateStaffBasicInfoById(request, staffResponse.getId());
        if (updatedStaff.getCode() == 1000) {
            Message.showInfo("updated successfully");
            Stage currentStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            currentStage.close();
            if (parentController != null) {
                parentController.loadStaff(0); // Đóng cửa sổ staff cũ
            }
        }
    }
}