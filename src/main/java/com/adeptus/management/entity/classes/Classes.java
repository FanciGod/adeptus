package com.adeptus.management.entity.classes;

import com.adeptus.management.entity.staff.Staff;
import com.adeptus.management.entity.student.StudentClass;
import com.adeptus.management.entity.teacher.Teacher;
import com.adeptus.management.entity.common.BaseEntity;
import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.util.Set;

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
    private Set<StudentClass> studentClasses; // Quan hệ 1:N với StudentClass

    @ManyToOne
    @JoinColumn(name = "teacher_id", nullable = false)
    private Teacher teacher;  // Quan hệ n:1 với Teacher

    @ManyToOne
    @JoinColumn(name = "staff_id", nullable = false)
    private Staff staff;

    @OneToMany(mappedBy = "classes")  // mappedBy phải trùng với tên thuộc tính trong Course
    private Set<Course> courses;
}