package com.adeptus.adeptusfe.controller.course;

import com.adeptus.adeptusfe.controller.staff.NewStaffController;
import com.adeptus.adeptusfe.controller.staff.UpdateStaffController;
import com.adeptus.adeptusfe.dto.ApiResponse;
import com.adeptus.adeptusfe.dto.response.CourseResponse;
import com.adeptus.adeptusfe.service.CourseService;
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

public class CourseController {
    public TableView<CourseResponse> courseTable;
    public TableColumn<CourseResponse, Long> idColumn;
    public TableColumn<CourseResponse, String> nameColumn;
    public TableColumn<CourseResponse, String> descriptionColumn;
    public TableColumn action;
    public Button newCourseButton;
    public TextField searchInput;
    public Button findButton;
    private ObservableList<CourseResponse> courseList = FXCollections.observableArrayList();
    private CourseService courseService = new CourseService();

    public void openCourseDetail(MouseEvent event) throws IOException {
        if (event.getClickCount() == 2) { // Chỉ xử lý khi double-click
            CourseResponse courseResponse = courseTable.getSelectionModel().getSelectedItem();
            if (courseResponse != null) {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/course/course_detail.fxml"));
                Parent root = loader.load();

                // Truyền ID vào controller của cửa sổ mới
                CourseDetailController controller = loader.getController();
                controller.setCourse(courseResponse);

                Stage stage = new Stage();
                stage.setTitle("Course Detail");
                stage.setScene(new Scene(root));
                stage.show();
            }
        }
    }

    public void toNewCourse(ActionEvent event) throws IOException {
        Stage currentStage = (Stage) newCourseButton.getScene().getWindow();
        // Tạo một FXMLLoader để load tệp FXML
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/course/new_course.fxml"));

        // Load cửa sổ mới
        Parent root = loader.load();

        // Tạo một Stage mới để hiển thị cửa sổ
        Stage stage = new Stage();
        stage.setTitle("New Course");
        stage.setScene(new Scene(root));

        NewCourseController newCourseController = loader.getController();
        newCourseController.setParentController(this); // Truyền tham chiếu StaffController

        // Hiển thị cửa sổ mới
        stage.show();
    }

    public void handleSearch(ActionEvent event) {
        String searchTerm = searchInput.getText().toLowerCase();  // Lấy từ khóa tìm kiếm
        ObservableList<CourseResponse> filteredList = FXCollections.observableArrayList();

        if (searchTerm.isEmpty()) {
            courseTable.setItems(courseList); // Reset về danh sách gốc
            return;
        }

        // Lọc danh sách theo tên, email hoặc số điện thoại
        for (CourseResponse course : courseList) {
            if (course.getName().toLowerCase().contains(searchTerm)) {
                filteredList.add(course);  // Thêm kết quả tìm kiếm vào danh sách
            }
        }

        courseTable.setItems(filteredList);  // Cập nhật bảng với kết quả tìm kiếm
    }

    public void initialize() throws IOException {
        idColumn.setCellValueFactory(cellData -> new SimpleLongProperty(cellData.getValue().getId()).asObject());
        nameColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getName()));
        descriptionColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getDescription()));
        action.setCellFactory(param -> {
            return new TableCell<CourseResponse, String>() {
                private final Button deleteButton = new Button("Delete");
                private final Button updateButton = new Button("Update");
                private final HBox buttonContainer = new HBox(5, updateButton, deleteButton); // HBox với khoảng cách 5px

                {
                    // Thêm sự kiện khi nhấn nút Delete
                    deleteButton.setOnAction(event -> {
                        CourseResponse course = getTableRow().getItem();
                        if (course != null) {
                            try {
                                handleDelete(course);  // Gọi phương thức handleDelete khi nhấn nút
                            } catch (IOException e) {
                                throw new RuntimeException(e);
                            }
                        }
                    });

                    updateButton.setOnAction(event -> {
                        CourseResponse course = getTableRow().getItem();
                        if (course != null) {
                            try {
                                openUpdateCourseWindow(course);
                            } catch (IOException e) {
                                throw new RuntimeException(e);
                            }
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
        loadCourse();
    }

    private void openUpdateCourseWindow(CourseResponse course) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/course/update_course.fxml"));
        Parent root = loader.load();

        // Truyền ID vào controller của cửa sổ mới
        UpdateCourseController controller = loader.getController();
        controller.setCourse(course);


        Stage stage = new Stage();
        stage.setTitle("Course Detail");
        stage.setScene(new Scene(root));
        UpdateCourseController updateCourseController = loader.getController();
        updateCourseController.setParentController(this); // Truyền tham chiếu StaffController
        stage.show();
    }

    private void handleDelete(CourseResponse course) throws IOException {
        courseService.deleteCourseById(course.getId());
        loadCourse();
    }

    public void loadCourse() throws IOException {
        ApiResponse<List<CourseResponse>> response = courseService.getAllCourse();
        if (response != null) {
            List<CourseResponse> data = response.getResult();
            courseList.setAll(data);
            courseTable.setItems(courseList);
        }
    }
}
