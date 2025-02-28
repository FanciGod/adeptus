package com.adeptus.adeptusfe.controller.classes;

import com.adeptus.adeptusfe.dto.StudentClassDto;
import com.adeptus.adeptusfe.dto.response.ClassesResponse;
import javafx.beans.property.SimpleLongProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

import java.util.ArrayList;

public class ClassDetailController {
    public Label className;
    public Label price;
    public Label teacher;
    public Label staff;
    public Label course;
    public TableView<StudentClassDto> classTable;
    public TableColumn<StudentClassDto, Long> idColumn;
    public TableColumn<StudentClassDto, String> studentColumn;
    private ClassesResponse classesResponse;

    public void setClass(ClassesResponse classes) {
        classesResponse = classes;
        className.setText(classes.getClassName() != null ? classes.getClassName() : "");
        price.setText(classes.getPricePerSession() != null ? classes.getPricePerSession().toString() : "");
        teacher.setText(classes.getTeacherDto() != null ? classes.getTeacherDto().getName() : "");
        staff.setText(classes.getStaffDto() != null ? classes.getStaffDto().getFullName() : "");
        course.setText(classes.getCourseDto() != null ? classes.getCourseDto().getName() : "");

        idColumn.setCellValueFactory(cellData ->
                new SimpleLongProperty(
                        cellData.getValue().getStudentDto() != null ? cellData.getValue().getStudentDto().getId() : 0
                ).asObject()
        );

        studentColumn.setCellValueFactory(cellData ->
                new SimpleStringProperty(
                        cellData.getValue().getStudentDto() != null ? cellData.getValue().getStudentDto().getStudentName() : ""
                )
        );

        classTable.getItems().setAll(classes.getStudentClassesDto() != null ? classes.getStudentClassesDto() : new ArrayList<>());
    }
}
