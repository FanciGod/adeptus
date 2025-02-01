package com.adeptus.management.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Entity
@Table(name = "staff_salary")
@Getter
@Setter
@NoArgsConstructor
@Builder
@AllArgsConstructor
public class StaffSalary {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "staff_id", nullable = false)
    private Staff staff;  // Quan hệ nhiều-một với Staff

    @Column(nullable = false)
    private Long salary;  // Lương của nhân viên trong khoảng thời gian

    @Column(nullable = false)
    private LocalDate startDate;  // Ngày bắt đầu của khoảng thời gian tính lương

    @Column(nullable = false)
    private LocalDate endDate;  // Ngày kết thúc của khoảng thời gian tính lương
}