package com.adeptus.adeptusfe.controller.course;

import com.adeptus.adeptusfe.dto.response.ClassesResponse;
import com.adeptus.adeptusfe.dto.response.CourseResponse;
import javafx.beans.property.SimpleLongProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

public class CourseDetailController {
    public Label name;
    public Label description;


    public void setCourse(CourseResponse courseResponse) {
        name.setText(courseResponse.getName());
        description.setText(courseResponse.getDescription());

    }
}
