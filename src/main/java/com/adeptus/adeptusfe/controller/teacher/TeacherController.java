package com.adeptus.adeptusfe.controller.teacher;

import com.adeptus.adeptusfe.dto.ApiResponse;
import com.adeptus.adeptusfe.dto.response.TeacherResponse;
import com.adeptus.adeptusfe.service.TeacherService;
import javafx.beans.property.SimpleLongProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.List;

public class TeacherController {
    public Button newTeacherButton;
    public TextField searchInput;
    public TableColumn<TeacherResponse, Long> idColumn;
    public TableColumn<TeacherResponse, String> teacherNameColumn;
    public TableColumn<TeacherResponse, String> phoneColumn;
    public TableColumn action;
    public TableView<TeacherResponse> teacherTable;

    private ObservableList<TeacherResponse> teacherList = FXCollections.observableArrayList();
    private int currentPage = 0;
    private int totalPages;
    private final TeacherService teacherService = new TeacherService();

    public void initialize() throws IOException {
        idColumn.setCellValueFactory(cellData -> new SimpleLongProperty(cellData.getValue().getId()).asObject());
        teacherNameColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getName()));
        phoneColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getPhone()));
        action.setCellFactory(param -> {
            return new TableCell<TeacherResponse, String>() {
                private final Button deleteButton = new Button("Delete");
                private final Button updateButton = new Button("Update");
                private final HBox buttonContainer = new HBox(5, updateButton, deleteButton); // HBox với khoảng cách 5px

                {
                    // Thêm sự kiện khi nhấn nút Delete
                    deleteButton.setOnAction(event -> {
                        TeacherResponse teacher = getTableRow().getItem();
                        if (teacher != null) {
                            try {
                                handleDelete(teacher);  // Gọi phương thức handleDelete khi nhấn nút
                            } catch (IOException e) {
                                throw new RuntimeException(e);
                            }
                        }
                    });

                    updateButton.setOnAction(event -> {
                        TeacherResponse teacher = getTableRow().getItem();
                        if (teacher != null) {
                            openUpdateTeacherWindow(teacher);
                        }
                    });
                }

                @Override
                protected void updateItem(String item, boolean empty) {
                    super.updateItem(item, empty);
                    if (empty) {
                        setGraphic(null);
                    } else {
                        setGraphic(buttonContainer);

                    }
                }
            };
        });
        loadTeacher();
    }

    private void openUpdateTeacherWindow(TeacherResponse teacher) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/teacher/update_teacher.fxml"));
            Parent root = loader.load();

            // Truyền ID vào controller của cửa sổ mới
            UpdateTeacherController controller = loader.getController();
            controller.setTeacher(teacher);


            Stage stage = new Stage();
            stage.setTitle("Teacher Detail");
            stage.setScene(new Scene(root));
            UpdateTeacherController updateTeacherController = loader.getController();
            updateTeacherController.setParentController(this); // Truyền tham chiếu StaffController
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void toNewTeacher(ActionEvent event) {
        try {
            Stage currentStage = (Stage) newTeacherButton.getScene().getWindow();
            // Tạo một FXMLLoader để load tệp FXML
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/teacher/new_teacher.fxml"));

            // Load cửa sổ mới
            Parent root = loader.load();

            // Tạo một Stage mới để hiển thị cửa sổ
            Stage stage = new Stage();
            stage.setTitle("New Teacher");
            stage.setScene(new Scene(root));

            NewTeacherController newTeacherController = loader.getController();
            newTeacherController.setParentController(this); // Truyền tham chiếu StaffController

            // Hiển thị cửa sổ mới
            stage.show();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public void loadTeacher() throws IOException {
        ApiResponse<List<TeacherResponse>> response = teacherService.getAllTeacher();
        if (response != null) {
            List<TeacherResponse> data = response.getResult();
            teacherList.setAll(data);
            teacherTable.setItems(teacherList);
        }
    }

    private void handleDelete(TeacherResponse teacher) throws IOException {
        teacherService.deleteTeacherById(teacher.getId());
        loadTeacher();
    }


    public void handleSearch(ActionEvent event) {
        String searchTerm = searchInput.getText().toLowerCase();  // Lấy từ khóa tìm kiếm
        ObservableList<TeacherResponse> filteredList = FXCollections.observableArrayList();
        if (searchTerm.isEmpty()) {
            teacherTable.setItems(teacherList); // Reset về danh sách gốc
            return;
        }
        // Lọc danh sách theo tên, email hoặc số điện thoại
        for (TeacherResponse teacher : teacherList) {
            String name = teacher.getName() != null ? teacher.getName().toLowerCase() : "";
            String phone = teacher.getPhone() != null ? teacher.getPhone().toLowerCase() : "";

            if (name.contains(searchTerm) || phone.contains(searchTerm)) {
                filteredList.add(teacher);
            }
        }
        teacherTable.setItems(filteredList);
    }


    public void openTeacherDetail(MouseEvent event) throws IOException {
        if (event.getClickCount() == 2) { // Chỉ xử lý khi double-click
            TeacherResponse selectedTeacher = teacherTable.getSelectionModel().getSelectedItem();
            if (selectedTeacher != null) {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/teacher/teacher_detail.fxml"));
                Parent root = loader.load();

                // Truyền ID vào controller của cửa sổ mới
                TeacherDetailController controller = loader.getController();
                controller.setTeacher(selectedTeacher);

                Stage stage = new Stage();
                stage.setTitle("Teacher Detail");
                stage.setScene(new Scene(root));
                stage.show();
            }
        }
    }
}

