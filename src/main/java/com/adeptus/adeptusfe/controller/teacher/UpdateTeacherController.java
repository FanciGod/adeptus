package com.adeptus.adeptusfe.controller.teacher;

import com.adeptus.adeptusfe.dto.request.UpdateTeacherRequest;
import com.adeptus.adeptusfe.dto.response.TeacherResponse;
import com.adeptus.adeptusfe.service.TeacherService;
import com.adeptus.adeptusfe.utility.Message;
import javafx.event.ActionEvent;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;

public class UpdateTeacherController {
    public TextField fullName;
    public Label headerLabel;
    public TextField phone;
    private TeacherController parentController;
    private TeacherResponse teacherResponse;
    private final TeacherService teacherService = new TeacherService();

    public void setParentController(TeacherController parentController) {
        this.parentController = parentController;
    }

    public void updateTeacher(ActionEvent event) throws IOException {
        UpdateTeacherRequest updateTeacherRequest = UpdateTeacherRequest.builder()
                .name(fullName.getText())
                .phone(phone.getText())
                .build();

       var response = teacherService.updateTeacherById(updateTeacherRequest,teacherResponse.getId());
       if(response.getCode() == 1000){
           Message.showInfo("success");
           Stage currentStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
           currentStage.close();
           if (parentController != null) {
               parentController.loadTeacher(); //
           }
       }else{
           Message.showError(response.getMessage());
       }
    }

    public void setTeacher(TeacherResponse teacher){
        teacherResponse = teacher;
        headerLabel.setText("Updating on "+teacher.getName());
        fullName.setText(teacher.getName());
        phone.setText(teacher.getPhone());
    }


}
