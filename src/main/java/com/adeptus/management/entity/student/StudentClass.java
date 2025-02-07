package com.adeptus.management.entity.student;

import com.adeptus.management.entity.classes.Classes;
import com.adeptus.management.entity.common.BaseEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class StudentClass extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "student_id", nullable = false)
    private Student student;

    @ManyToOne
    @JoinColumn(name = "class_id", nullable = false)
    private Classes classes;

    @Column(nullable = false)
    private Integer lessonRemain; // Số buổi học còn lại cho lớp này

    @Column(nullable = false)
    private Long totalPaid; // Số tiền đã đóng
}
