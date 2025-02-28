package com.adeptus.adeptusfe.controller.classes;

import com.adeptus.adeptusfe.dto.CourseDto;
import com.adeptus.adeptusfe.dto.StaffDto;
import com.adeptus.adeptusfe.dto.TeacherDto;
import com.adeptus.adeptusfe.dto.response.ApiResponse;
import com.adeptus.adeptusfe.dto.response.ClassesResponse;
import com.adeptus.adeptusfe.dto.response.PageResponse;
import com.adeptus.adeptusfe.service.ClassesService;
import javafx.beans.property.SimpleLongProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.List;

public class ClassController {
    public Label headerLabel;
    public TableView<ClassesResponse> classTable;
    public TableColumn<ClassesResponse, Long> idColumn;
    public TableColumn<ClassesResponse, String> classNameColumn;
    public TableColumn<ClassesResponse, Long> priceColumn;
    public TableColumn<ClassesResponse, String> teacherColumn;
    public TableColumn<ClassesResponse, String> staffColumn;
    public TableColumn<ClassesResponse, String> courseColumn;
    public TableColumn<ClassesResponse, Void> actionColumn;
    public Button prev;
    public Button next;
    public Button findButton;
    public Button newClassButton;
    public TextField pageInput;

    private int currentPage = 0;
    private int totalPages;
    private ObservableList<ClassesResponse> classList = FXCollections.observableArrayList();


    private final ClassesService classesService = new ClassesService();

    @FXML
    public void initialize() {
        idColumn.setCellValueFactory(cellData -> new SimpleLongProperty(cellData.getValue().getId()).asObject());
        classNameColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getClassName()));
        priceColumn.setCellValueFactory(cellData -> new SimpleLongProperty(cellData.getValue().getPricePerSession()).asObject());

        // Lấy tên giáo viên
        teacherColumn.setCellValueFactory(cellData -> {
            TeacherDto teacher = cellData.getValue().getTeacherDto();
            return new SimpleStringProperty(teacher != null ? teacher.getName() : "");
        });

        // Lấy tên nhân viên phụ trách
        staffColumn.setCellValueFactory(cellData -> {
            StaffDto staff = cellData.getValue().getStaffDto();
            return new SimpleStringProperty(staff != null ? staff.getFullName() : "");
        });

        // Lấy tên khóa học
        courseColumn.setCellValueFactory(cellData -> {
            CourseDto course = cellData.getValue().getCourseDto();
            return new SimpleStringProperty(course != null ? course.getName() : "");
        });
        actionColumn.setCellFactory(param -> new TableCell<ClassesResponse, Void>() {
            private final Button deleteButton = new Button("Delete");
            private final Button updateButton = new Button("Update");
            private final HBox buttonContainer = new HBox(5, updateButton, deleteButton); // HBox với khoảng cách 5px

            {
                // Xử lý sự kiện xóa lớp học
                deleteButton.setOnAction(event -> {
                    ClassesResponse classes = getTableRow().getItem();
                    if (classes != null) {
                        try {
                            handleDelete(classes);
                        } catch (IOException e) {
                            throw new RuntimeException(e);
                        }
                    }
                });

                // Xử lý sự kiện cập nhật lớp học
                updateButton.setOnAction(event -> {
                    ClassesResponse classes = getTableRow().getItem();
                    if (classes != null) {
                        openUpdateClassWindow(classes);
                    }
                });
            }

            @Override
            protected void updateItem(Void item, boolean empty) {
                super.updateItem(item, empty);
                if (empty) {
                    setGraphic(null);
                } else {
                    setGraphic(buttonContainer);
                }
            }
        });
        loadClasses(0);
    }

    private void openUpdateClassWindow(ClassesResponse classes) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/class/update_class.fxml"));
            Parent root = loader.load();

            // Truyền ID vào controller của cửa sổ mới
            UpdateClassController controller = loader.getController();
            controller.setClass(classes);


            Stage stage = new Stage();
            stage.setTitle("Staff Detail");
            stage.setScene(new Scene(root));
            UpdateClassController updateClassController = loader.getController();
            updateClassController.setParentController(this); // Truyền tham chiếu StaffController
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void handleDelete(ClassesResponse classes) throws IOException {
        classesService.deleteClassById(classes.getId());
        loadClasses(0);
    }

    @FXML
    private void nextPage() {
        if (currentPage < totalPages - 1) {
            currentPage++;
            loadClasses(currentPage);
        }
    }


    @FXML
    private void prevPage() {
        if (currentPage > 0) {
            currentPage--;
            loadClasses(currentPage);
        }
    }

    public void loadClasses(int page) {
        try {
            ApiResponse<PageResponse<ClassesResponse>> response = classesService.getClassWithPagination(page, 5);
            if (response != null && response.getResult() != null) {
                List<ClassesResponse> data = response.getResult().getContent();
                totalPages = response.getResult().getTotalPages();
                classList.setAll(data);
                classTable.setItems(classList);
                pageInput.setText(String.valueOf(currentPage + 1));
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    private void toNewClass() {
        try {
            Stage currentStage = (Stage) newClassButton.getScene().getWindow();
            // Tạo một FXMLLoader để load tệp FXML
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/class/new_class.fxml"));

            // Load cửa sổ mới
            Parent root = loader.load();

            // Tạo một Stage mới để hiển thị cửa sổ
            Stage stage = new Stage();
            stage.setTitle("New Staff");
            stage.setScene(new Scene(root));

            NewClassController newClassController = loader.getController();
            newClassController.setParentController(this); // Truyền tham chiếu StaffController

            // Hiển thị cửa sổ mới
            stage.show();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    public void openClassDetail(MouseEvent event) {
        if (event.getClickCount() == 2) { // Chỉ xử lý khi double-click
            ClassesResponse selectedClass = classTable.getSelectionModel().getSelectedItem();
            if (selectedClass != null) {
                openClassDetailWindow(selectedClass);
            }
        }
    }

    private void openClassDetailWindow(ClassesResponse classes) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/class/class_detail.fxml"));
            Parent root = loader.load();

            // Truyền ID vào controller của cửa sổ mới
            ClassDetailController controller = loader.getController();
            controller.setClass(classes);

            Stage stage = new Stage();
            stage.setTitle("Class Detail");
            stage.setScene(new Scene(root));
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

