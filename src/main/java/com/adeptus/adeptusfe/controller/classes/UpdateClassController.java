package com.adeptus.adeptusfe.controller.classes;

import com.adeptus.adeptusfe.dto.request.UpdateClassRequest;
import com.adeptus.adeptusfe.dto.response.ClassesResponse;
import com.adeptus.adeptusfe.service.ClassesService;
import com.adeptus.adeptusfe.service.StaffService;
import com.adeptus.adeptusfe.utility.Message;
import javafx.event.ActionEvent;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;

public class UpdateClassController {
    public TextField pricePerSession;
    public TextField className;
    public Label headerLabel;
    private ClassesResponse classesResponse;
    private ClassController parentController;
    private final ClassesService classesService = new ClassesService();

    public void setParentController(ClassController parentController) {
        this.parentController = parentController;
    }

    private final StaffService staffService = new StaffService();

    public void setClass(ClassesResponse classes) {
        classesResponse = classes;
        headerLabel.setText("Updating on " + classes.getClassName());
        className.setText(classes.getClassName());
        pricePerSession.setText(classes.getPricePerSession().toString());

    }

    public void updateClass(ActionEvent event) throws IOException {
        UpdateClassRequest updateClassRequest = UpdateClassRequest.builder()
                .className(className.getText())
                .pricePerSession(Long.parseLong(pricePerSession.getText()))
                .build();

       var response = classesService.updateClassById(updateClassRequest,classesResponse.getId());
       if(response.getCode() == 1000){
           Message.showInfo("success");
           Stage currentStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
           currentStage.close();
           if (parentController != null) {
               parentController.loadClasses(0); // Đóng cửa sổ staff cũ
           }
       }else{
           Message.showError(response.getMessage());
       }
    }
}
