package com.adeptus.adeptusfe.controller.staff;

import com.adeptus.adeptusfe.dto.ClassesDto;
import com.adeptus.adeptusfe.dto.RoleDto;
import com.adeptus.adeptusfe.dto.StaffSalaryDto;
import com.adeptus.adeptusfe.dto.response.StaffResponse;
import javafx.beans.property.SimpleLongProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

import java.time.LocalDate;
import java.util.stream.Collectors;

public class StaffDetailController {
    public Label headerLabel;
    public Label phone;
    public Label dob;
    public Label roles;
    public Label username;
    public Label fullName;
    public Label email;
    public TableColumn<StaffSalaryDto,Long> salaryColumn;
    public TableColumn<StaffSalaryDto, LocalDate> startDateColumn;
    public TableColumn<StaffSalaryDto, LocalDate> endDateColumn;
    public TableColumn<ClassesDto,Long> idColumn;
    public TableColumn<ClassesDto,String> classNameColumn;
    public TableColumn<ClassesDto,String> teacherNameColumn;
    public TableView<StaffSalaryDto> salaryTable;
    public TableView<ClassesDto> classTable;



    private StaffResponse staffResponse;

    public void setStaff(StaffResponse staff) {
        staffResponse = staff;
        phone.setText(staff.getPhone());
        dob.setText(staff.getDob().toString());
        roles.setText(staff.getRoles().stream().map(RoleDto::getRoleName).collect(Collectors.joining(",")));
        username.setText(staff.getUsername());
        fullName.setText(staff.getFullName());
        email.setText(staff.getEmail());
        headerLabel.setText("Staff: " + fullName.getText());

        salaryColumn.setCellValueFactory(cellData -> new SimpleLongProperty(cellData.getValue().getSalary()).asObject());
        startDateColumn.setCellValueFactory(cellData ->
                new SimpleObjectProperty<>(cellData.getValue().getStartDate())
        );
        endDateColumn.setCellValueFactory(cellData ->
                new SimpleObjectProperty<>(cellData.getValue().getEndDate())
        );

        salaryTable.getItems().setAll(staff.getSalaries());

        // Cấu hình cột cho bảng lớp học
        idColumn.setCellValueFactory(cellData ->
                new SimpleLongProperty(cellData.getValue().getId()).asObject()
        );
        classNameColumn.setCellValueFactory(cellData ->
                new SimpleStringProperty(cellData.getValue().getClassName())
        );
        teacherNameColumn.setCellValueFactory(cellData ->
                new SimpleStringProperty(cellData.getValue().getTeacherName())
        );

        classTable.getItems().setAll(staff.getClasses());
    }
}
