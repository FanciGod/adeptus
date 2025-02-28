package com.adeptus.adeptusfe.controller.course;

import com.adeptus.adeptusfe.dto.ApiResponse;
import com.adeptus.adeptusfe.dto.request.UpdateCourseRequest;
import com.adeptus.adeptusfe.dto.response.CourseResponse;
import com.adeptus.adeptusfe.service.CourseService;
import com.adeptus.adeptusfe.utility.Message;
import javafx.event.ActionEvent;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;

public class UpdateCourseController {
    public Label headerLabel;
    public TextField courseName;
    public TextField description;
    private CourseController parentController;
    private CourseResponse courseResponse;
    private final CourseService courseService = new CourseService();
    public void setCourse(CourseResponse course) {
        courseResponse = course;
        headerLabel.setText("Updating on "+ course.getName());
        courseName.setText(course.getName());
        description.setText(course.getDescription());
    }

    public void setParentController(CourseController parentController) {
        this.parentController = parentController;
    }

    public void updateCourse(ActionEvent event) throws IOException {
        UpdateCourseRequest request = UpdateCourseRequest.builder()
                .name(courseName.getText())
                .description(description.getText())
                .build();

        ApiResponse<CourseResponse> updatedCourse = courseService.updateCourseById(request,courseResponse.getId());
        if(updatedCourse.getCode() == 1000){
            Message.showInfo("updated successfully");
            Stage currentStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            currentStage.close();
            if (parentController != null) {
                parentController.loadCourse(); // Đóng cửa sổ staff cũ
            }
        }else{
            Message.showError(updatedCourse.getMessage());
        }
    }
}
