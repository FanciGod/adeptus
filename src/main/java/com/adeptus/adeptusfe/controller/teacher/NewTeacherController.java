package com.adeptus.adeptusfe.controller.teacher;

import com.adeptus.adeptusfe.dto.ApiResponse;
import com.adeptus.adeptusfe.dto.request.CreateNewTeacherRequest;
import com.adeptus.adeptusfe.dto.response.TeacherResponse;
import com.adeptus.adeptusfe.service.TeacherService;
import com.adeptus.adeptusfe.utility.Message;
import javafx.event.ActionEvent;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;

public class NewTeacherController {
    public TextField teacherNameInput;
    public TextField phoneInput;
    public TextField salaryPerSessionInput;
    public Button createStaffButton;
    private TeacherController parentController;
    private final TeacherService teacherService = new TeacherService();
    public void setParentController(TeacherController parentController) {
        this.parentController = parentController;
    }

    public void handleCreateTeacher(ActionEvent event) throws IOException {
        CreateNewTeacherRequest request = CreateNewTeacherRequest.builder()
                .name(teacherNameInput.getText())
                .phone(phoneInput.getText())
                .salaryPerSession(Long.parseLong(salaryPerSessionInput.getText()))
                .build();

        ApiResponse<TeacherResponse> newTeacher = teacherService.createNewTeacher(request);
        if (newTeacher.getCode() == 1000) {
            Message.showInfo("created successfully");
            Stage currentStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            currentStage.close();

            if (parentController != null) {
                parentController.loadTeacher(); // Đóng cửa sổ staff cũ
            }
        }else{
            Message.showError(newTeacher.getMessage());
        }
    }
}
