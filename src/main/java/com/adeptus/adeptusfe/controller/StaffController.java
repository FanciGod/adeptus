package com.adeptus.adeptusfe.controller;

import com.adeptus.adeptusfe.dto.RoleDto;
import com.adeptus.adeptusfe.dto.response.ApiResponse;
import com.adeptus.adeptusfe.dto.response.PageResponse;
import com.adeptus.adeptusfe.dto.response.StaffResponse;
import com.adeptus.adeptusfe.service.StaffService;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

public class StaffController {
    private final StaffService staffService = new StaffService();
    public Button newStaffButton;
    public Button findButton;
    public TextField searchInput;
    public TableColumn action;
    @FXML
    private TableView<StaffResponse> staffTable;
    @FXML
    private TableColumn<StaffResponse, Long> idColumn;
    @FXML
    private TableColumn<StaffResponse, String> fullNameColumn;
    @FXML
    private TableColumn<StaffResponse, String> roleColumn;
    @FXML
    private TableColumn<StaffResponse, String> emailColumn;
    @FXML
    private TableColumn<StaffResponse, String> phoneColumn;
    @FXML
    private TableColumn<StaffResponse, String> dobColumn;
    @FXML
    private TextField pageInput;

    private ObservableList<StaffResponse> staffList = FXCollections.observableArrayList();
    private int currentPage = 0;
    private int totalPages;

    @FXML
    public void initialize() {
        // Ánh xạ cột với thuộc tính trong StaffResponse
        idColumn.setCellValueFactory(cellData -> new javafx.beans.property.SimpleLongProperty(cellData.getValue().getId()).asObject());
        fullNameColumn.setCellValueFactory(cellData -> new javafx.beans.property.SimpleStringProperty(cellData.getValue().getFullName()));
        // Nếu có nhiều role, nối các roleName lại với nhau
        roleColumn.setCellValueFactory(cellData -> {
            // Lấy danh sách các role
            List<RoleDto> roles = cellData.getValue().getRoles();

            // Nếu không có roles thì trả về chuỗi trống
            if (roles == null || roles.isEmpty()) {
                return new javafx.beans.property.SimpleStringProperty("");
            }

            // Lấy tất cả roleName và nối chúng lại bằng dấu phẩy
            String roleNames = roles.stream()
                    .map(RoleDto::getRoleName) // Lấy tên role
                    .collect(Collectors.joining(", ")); // Nối các roleName lại với nhau bằng dấu phẩy
            return new javafx.beans.property.SimpleStringProperty(roleNames);
        });
        emailColumn.setCellValueFactory(cellData -> new javafx.beans.property.SimpleStringProperty(cellData.getValue().getEmail()));
        phoneColumn.setCellValueFactory(cellData -> new javafx.beans.property.SimpleStringProperty(cellData.getValue().getPhone()));
        dobColumn.setCellValueFactory(cellData -> new javafx.beans.property.SimpleStringProperty(cellData.getValue().getDob().toString()));
        action.setCellFactory(param -> {
            return new TableCell<StaffResponse, String>() {
                private final Button deleteButton = new Button("Delete");

                {
                    // Thêm sự kiện khi nhấn nút Delete
                    deleteButton.setOnAction(event -> {
                        StaffResponse staff = getTableRow().getItem();
                        if (staff != null) {
                            handleDelete(staff);  // Gọi phương thức handleDelete khi nhấn nút
                        }
                    });
                }

                @Override
                protected void updateItem(String item, boolean empty) {
                    super.updateItem(item, empty);
                    if (empty) {
                        setGraphic(null);
                    } else {
                        setGraphic(deleteButton);
                    }
                }
            };
        });
        loadStaff(currentPage);
    }

    private void handleDelete(StaffResponse staff) {
    }

    public void loadStaff(int page) {
        try {
            ApiResponse<PageResponse<StaffResponse>> response = staffService.getStaffList(page, 5);
            if (response != null && response.getResult() != null) {
                List<StaffResponse> data = response.getResult().getContent();
                totalPages = response.getResult().getTotalPages();

                staffList.setAll(data);
                staffTable.setItems(staffList);
                pageInput.setText(String.valueOf(currentPage + 1));
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }


    @FXML
    private void nextPage() {
        if (currentPage < totalPages - 1) {
            currentPage++;
            loadStaff(currentPage);
        }
    }


    @FXML
    private void prevPage() {
        if (currentPage > 0) {
            currentPage--;
            loadStaff(currentPage);
        }
    }

    @FXML
    private void toNewStaff() {
        try {
            Stage currentStage = (Stage) newStaffButton.getScene().getWindow();
            // Tạo một FXMLLoader để load tệp FXML
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/new_staff.fxml"));

            // Load cửa sổ mới
            Parent root = loader.load();

            // Tạo một Stage mới để hiển thị cửa sổ
            Stage stage = new Stage();
            stage.setTitle("New Staff");
            stage.setScene(new Scene(root));

            NewStaffController newStaffController = loader.getController();
            newStaffController.setParentController(this); // Truyền tham chiếu StaffController

            // Hiển thị cửa sổ mới
            stage.show();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    @FXML
    private void handleSearch() {
        String searchTerm = searchInput.getText().toLowerCase();  // Lấy từ khóa tìm kiếm
        ObservableList<StaffResponse> filteredList = FXCollections.observableArrayList();

        // Lọc danh sách theo tên, email hoặc số điện thoại
        for (StaffResponse staff : staffList) {
            if (staff.getFullName().toLowerCase().contains(searchTerm) ||
                    staff.getEmail().toLowerCase().contains(searchTerm) ||
                    staff.getPhone().toLowerCase().contains(searchTerm)) {
                filteredList.add(staff);  // Thêm kết quả tìm kiếm vào danh sách
            }
        }

        staffTable.setItems(filteredList);  // Cập nhật bảng với kết quả tìm kiếm
    }
}
