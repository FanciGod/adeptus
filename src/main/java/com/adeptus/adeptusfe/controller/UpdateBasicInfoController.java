package com.adeptus.adeptusfe.controller;

import com.adeptus.adeptusfe.dto.request.UpdateStaffBasicInfoRequest;
import com.adeptus.adeptusfe.dto.response.ApiResponse;
import com.adeptus.adeptusfe.dto.response.StaffResponse;
import com.adeptus.adeptusfe.service.StaffService;
import com.adeptus.adeptusfe.utility.JwtUtils;
import com.adeptus.adeptusfe.utility.Message;
import com.adeptus.adeptusfe.utility.Session;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;

public class UpdateBasicInfoController {

    private final StaffService staffService = new StaffService();
    public DatePicker dob;
    public TextField phone;
    public TextField email;
    public TextField fullName;
    public Button submit;

    private Long staffId;
    private HomeController parentController;

    public void setParentController(HomeController parentController) {
        this.parentController = parentController;
    }

    @FXML
    public void initialize() throws Exception {
       String id = JwtUtils.getStaffIdFromJWT(Session.getToken());
        var staff = staffService.getStaffById(Long.parseLong(id)).getResult();
        staffId = staff.getId();
        dob.setValue(staff.getDob());
        phone.setText(staff.getPhone());
        email.setText(staff.getEmail());
        fullName.setText(staff.getFullName());
    }

   @FXML
    public void updateStaff(ActionEvent event) throws Exception {
        UpdateStaffBasicInfoRequest request = UpdateStaffBasicInfoRequest.builder()
                .dob(dob.getValue())
                .email(email.getText())
                .fullName(fullName.getText())
                .phone(phone.getText())
                .build();

       ApiResponse<StaffResponse> updatedStaff = staffService.updateStaffBasicInfoById(request, staffId);
       if(updatedStaff.getCode() == 1000){
           Message.showInfo("updated successfully");
           Stage currentStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
           currentStage.close();
           if (parentController != null) {
               parentController.loadStaff(); // Đóng cửa sổ staff cũ
           }
       }
    }


}
