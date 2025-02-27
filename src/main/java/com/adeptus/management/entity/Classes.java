package com.adeptus.management.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity
@Table(name = "class")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Classes extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 20, nullable = false, unique = true)
    @Size(max = 20, message = "Tên lớp học không được vượt quá 20 ký tự")
    private String className;

    @Column(nullable = false)
    private Long pricePerSession;  // Giá mỗi buổi học

    @OneToMany(mappedBy = "classes")
    private List<StudentClass> studentClasses; // Quan hệ 1:N với StudentClass

    @ManyToOne
    @JoinColumn(name = "teacher_id", nullable = false)
    private Teacher teacher;  // Quan hệ n:1 với Teacher

    @ManyToOne
    @JoinColumn(name = "staff_id", nullable = false)
    private Staff staff;

    @ManyToOne
    @JoinColumn(name = "course_id", nullable = false)  // Một lớp chỉ thuộc về một môn học
    private Course course;
}