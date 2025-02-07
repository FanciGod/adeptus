package com.adeptus.management.entity.teacher;

import com.adeptus.management.entity.classes.Classes;
import com.adeptus.management.entity.common.BaseEntity;
import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.util.Set;

@Entity
@Table(name = "teacher")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Teacher extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 50, nullable = false)
    @Size(max = 50, message = "Tên giáo viên không được vượt quá 50 ký tự")
    private String name;

    @OneToMany(mappedBy = "teacher")
    private Set<TeacherSalaryHistory> salaries;  // Quan hệ 1:n với TeacherSalary

    @OneToMany(mappedBy = "teacher")
    private Set<Classes> aClasses;  // Quan hệ 1:n với Classes
}
