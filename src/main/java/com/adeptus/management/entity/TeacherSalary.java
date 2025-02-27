package com.adeptus.management.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Entity
@Table(name = "teacher_salary")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TeacherSalary {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "teacher_id", nullable = false)
    private Teacher teacher;  // Quan hệ nhiều-một với Teacher

    @Column(nullable = false)
    private Long salaryPerSession;  // Lương của giáo viên trong khoảng thời gian

    @Column(nullable = false)
    private LocalDate startDate;  // Ngày bắt đầu của khoảng thời gian tính lương

    @Column(nullable = false)
    private LocalDate endDate;  // Ngày kết thúc của khoảng thời gian tính lương
}
