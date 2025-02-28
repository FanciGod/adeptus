package com.adeptus.adeptusfe.controller.course;

import com.adeptus.adeptusfe.dto.ApiResponse;
import com.adeptus.adeptusfe.dto.request.CreateNewCourseRequest;
import com.adeptus.adeptusfe.dto.response.CourseResponse;
import com.adeptus.adeptusfe.service.CourseService;
import com.adeptus.adeptusfe.utility.Message;
import javafx.event.ActionEvent;
import javafx.scene.Node;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;

public class NewCourseController {
    public TextField courseName;
    public TextField description;
    private CourseController parentController;
    private final CourseService courseService = new CourseService();
    public void setParentController(CourseController parentController) {
        this.parentController = parentController;

    }

    public void handleCreateCourse(ActionEvent event) throws IOException {
        CreateNewCourseRequest request = CreateNewCourseRequest.builder()
                .name(courseName.getText())
                .description(description.getText())
                .build();

        ApiResponse<CourseResponse> newCourse = courseService.createNewCourse(request);
        if(newCourse.getCode() == 1000){
            Message.showInfo("created successfully");
            Stage currentStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            currentStage.close();

            if (parentController != null) {
                parentController.loadCourse(); // Đóng cửa sổ staff cũ
            }
        }else{
            Message.showError(newCourse.getMessage());
        }
    }
}
