package com.adeptus.management.entity;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;

@Entity
@Table(name = "employee_salaries")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class EmployeeSalaryHistory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private Long employeeId;  // ID của Staff hoặc Teacher

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private EmployeeType employeeType;  // Kiểu của Employee (Staff hoặc Teacher)

    @Column(nullable = false)
    private LocalDate date;  // Ngày trả lương

    @Column(nullable = false)
    private Long amount;  // Lương cơ bản

    @Column(nullable = false)
    private Long bonus;  // Tiền thưởng

    @Column(nullable = false)
    private Long total;  // Tổng lương (lương cơ bản + thưởng)

    @Column(columnDefinition = "TEXT")
    private String note;  // Ghi chú về lương

    // Enum để xác định loại employee
    public enum EmployeeType {
        STAFF, TEACHER
    }
}
