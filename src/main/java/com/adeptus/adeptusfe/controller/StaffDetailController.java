package com.adeptus.adeptusfe.controller;

import com.adeptus.adeptusfe.dto.RoleDto;
import com.adeptus.adeptusfe.dto.response.StaffResponse;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

import java.util.stream.Collectors;

public class StaffDetailController {
    public Label headerLabel;
    public Label phone;
    public Label dob;
    public Label roles;
    public Label username;
    public Label fullName;
    public Label email;
    public TableColumn salaryColumn;
    public TableColumn startDateColumn;
    public TableColumn endDateColumn;
    public TableColumn idColumn;
    public TableColumn classNameColumn;
    public TableColumn teacherNameColumn;

    private StaffResponse staffResponse;

    public void setStaff(StaffResponse staff) {
        staffResponse = staff;
        phone.setText(staff.getPhone());
        dob.setText(staff.getDob().toString());
        roles.setText(staff.getRoles().stream().map(RoleDto::getRoleName).collect(Collectors.joining(",")));
        username.setText(staff.getFullName());
        fullName.setText(staff.getFullName());
        email.setText(staff.getEmail());
        headerLabel.setText("Staff: " + fullName);
        
        
    }
}
