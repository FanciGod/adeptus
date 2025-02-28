package com.adeptus.adeptusfe.controller.teacher;

import com.adeptus.adeptusfe.dto.ClassesDto;
import com.adeptus.adeptusfe.dto.TeacherSalaryDto;
import com.adeptus.adeptusfe.dto.response.TeacherResponse;
import javafx.beans.property.SimpleLongProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

import java.time.LocalDate;

public class TeacherDetailController {
    public Label headerLabel;
    public Label name;
    public Label phone;
    public TableView<TeacherSalaryDto> salaryTable;
    public TableColumn<TeacherSalaryDto, Long> salaryColumn;
    public TableColumn<TeacherSalaryDto, LocalDate> startDateColumn;
    public TableColumn<TeacherSalaryDto, LocalDate> endDateColumn;
    public TableView<ClassesDto> classTable;
    public TableColumn<ClassesDto, Long> idColumn;
    public TableColumn<ClassesDto, String> classNameColumn;
    private TeacherResponse teacherResponse;

    public void setTeacher(TeacherResponse teacher) {
        teacherResponse = teacher;
        phone.setText(teacher.getPhone());
        name.setText(teacher.getName());
        headerLabel.setText("Teacher: " + teacher.getName());
        salaryColumn.setCellValueFactory(cellData -> new SimpleLongProperty(cellData.getValue().getSalaryPerSession()).asObject());
        startDateColumn.setCellValueFactory(cellData ->
                new SimpleObjectProperty<>(cellData.getValue().getStartDate())
        );
        endDateColumn.setCellValueFactory(cellData ->
                new SimpleObjectProperty<>(cellData.getValue().getEndDate())
        );
        salaryTable.getItems().setAll(teacher.getTeacherSalaryDtoList());

        idColumn.setCellValueFactory(cellData ->
                new SimpleLongProperty(cellData.getValue().getId()).asObject()
        );
        classNameColumn.setCellValueFactory(cellData ->
                new SimpleStringProperty(cellData.getValue().getClassName())
        );


        classTable.getItems().setAll(teacher.getClassesDtoList());
    }
}

