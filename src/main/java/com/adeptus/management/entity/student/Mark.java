package com.adeptus.management.entity.student;

import com.adeptus.management.entity.classes.Course;
import com.adeptus.management.entity.common.BaseEntity;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "mark")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Mark extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "student_id", nullable = false)
    private Student student;  // Quan hệ nhiều-một với Student

    @ManyToOne
    @JoinColumn(name = "course_id", nullable = false)
    private Course course;  // Quan hệ nhiều-một với Course


    @Column(length = 100)
    private String examName;  // Tên của đề bài (bài kiểm tra, bài tập, v.v.)

    @Column(nullable = false)
    private Float mark;  // Điểm của sinh viên trong khóa học
}