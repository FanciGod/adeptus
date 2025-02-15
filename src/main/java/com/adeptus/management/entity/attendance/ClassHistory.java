package com.adeptus.management.entity.attendance;

import com.adeptus.management.entity.classes.Classes;
import com.adeptus.management.entity.student.Student;
import com.adeptus.management.entity.teacher.Teacher;
import com.adeptus.management.entity.common.BaseEntity;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.Set;

@Entity
@Table(name = "class_history")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ClassHistory extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "class_id", nullable = false)
    private Classes aClasses;  // Quan hệ nhiều-một với Classes (1 lớp học)

    @ManyToOne
    @JoinColumn(name = "teacher_id", nullable = false)
    private Teacher teacher;  // Quan hệ nhiều-một với Teacher (1 giáo viên)

    @ManyToMany
    @JoinTable(
            name = "class_history_student",
            joinColumns = @JoinColumn(name = "class_history_id"),
            inverseJoinColumns = @JoinColumn(name = "student_id")
    )
    private Set<Student> students;  // Quan hệ nhiều-nhiều với Student (nhiều sinh viên)

    @Column(nullable = false)
    private LocalDateTime date;  // Thời gian của buổi học
}
