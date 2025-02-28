package com.adeptus.adeptusfe.controller.classes;

import com.adeptus.adeptusfe.dto.request.CreateNewClassRequest;
import com.adeptus.adeptusfe.dto.response.CourseResponse;
import com.adeptus.adeptusfe.dto.response.StaffResponse;
import com.adeptus.adeptusfe.dto.response.TeacherResponse;
import com.adeptus.adeptusfe.service.ClassesService;
import com.adeptus.adeptusfe.service.CourseService;
import com.adeptus.adeptusfe.service.StaffService;
import com.adeptus.adeptusfe.service.TeacherService;
import com.adeptus.adeptusfe.utility.Message;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.List;

public class NewClassController {
    public TextField classNameInput;
    public TextField priceInput;
    public ComboBox<TeacherResponse> teacherOption;
    public ComboBox<StaffResponse> staffOption;
    public ComboBox<CourseResponse> courseOption;
    public Button createStaffButton;
    private ClassController parentController;

    private final ClassesService classesService = new ClassesService();

    private final StaffService staffService = new StaffService();
    private final CourseService courseService = new CourseService();
    private final TeacherService teacherService = new TeacherService();

    public void setParentController(ClassController parentController) {
        this.parentController = parentController;
    }

    public void handleCreateClass(ActionEvent event) throws IOException {
        CreateNewClassRequest createNewClassRequest = CreateNewClassRequest.builder()
                .className(classNameInput.getText())
                .pricePerSession(Long.parseLong(priceInput.getText()))
                .courseId(getSelectedCourseId())
                .staffId(getSelectedStaffId())
                .teacherId(getSelectedTeacherId())
                .build();

        var response = classesService.createNewClass(createNewClassRequest);
        if(response.getCode() == 1000){
            Message.showInfo("success");
            Stage currentStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            currentStage.close();
            if(parentController != null){
                parentController.loadClasses(0);
            }
        }else{
            Message.showError(response.getMessage());
        }
    }

    public void initialize() throws IOException {
        loadTeachers();
        loadCourses();
        loadStaffs();
    }

    private void loadStaffs() throws IOException {
        List<StaffResponse> staffList = staffService.getStaffList(0,99).getResult().getContent();
        staffOption.setItems(FXCollections.observableArrayList(staffList));
        staffOption.setCellFactory(param -> new ListCell<>() {
            @Override
            protected void updateItem(StaffResponse item, boolean empty) {
                super.updateItem(item, empty);
                setText((item == null || empty) ? "" : item.getFullName());
            }
        });
        staffOption.setButtonCell(new ListCell<>() {
            @Override
            protected void updateItem(StaffResponse item, boolean empty) {
                super.updateItem(item, empty);
                setText((item == null || empty) ? "" : item.getFullName());
            }
        });
    }

    private void loadCourses() throws IOException {
        List<CourseResponse> courseList = courseService.getAllCourse().getResult();
        courseOption.setItems(FXCollections.observableArrayList(courseList));

        courseOption.setCellFactory(param -> new ListCell<>() {
            @Override
            protected void updateItem(CourseResponse item, boolean empty) {
                super.updateItem(item, empty);
                setText((item == null || empty) ? "" : item.getName());
            }
        });

        courseOption.setButtonCell(new ListCell<>() {
            @Override
            protected void updateItem(CourseResponse item, boolean empty) {
                super.updateItem(item, empty);
                setText((item == null || empty) ? "" : item.getName());
            }
        });
    }

    private void loadTeachers() throws IOException {
        List<TeacherResponse> teacherList = teacherService.getAllTeacher().getResult();
        teacherOption.setItems(FXCollections.observableArrayList(teacherList));

        // Hiển thị tên giáo viên trong danh sách ComboBox
        teacherOption.setCellFactory(param -> new ListCell<>() {
            @Override
            protected void updateItem(TeacherResponse item, boolean empty) {
                super.updateItem(item, empty);
                setText((item == null || empty) ? "" : item.getName());
            }
        });

        // Hiển thị tên giáo viên khi đã chọn
        teacherOption.setButtonCell(new ListCell<>() {
            @Override
            protected void updateItem(TeacherResponse item, boolean empty) {
                super.updateItem(item, empty);
                setText((item == null || empty) ? "" : item.getName());
            }
        });
    }

    private Long getSelectedStaffId() {
        StaffResponse selectedStaff = staffOption.getSelectionModel().getSelectedItem();
        return (selectedStaff != null) ? selectedStaff.getId() : null;
    }

    private Long getSelectedCourseId() {
        CourseResponse selectedCourse = courseOption.getSelectionModel().getSelectedItem();
        return (selectedCourse != null) ? selectedCourse.getId() : null;
    }

    private Long getSelectedTeacherId(){
        TeacherResponse selectedTeacher = teacherOption.getSelectionModel().getSelectedItem();
        return (selectedTeacher != null) ? selectedTeacher.getId() : null;
    }



}
