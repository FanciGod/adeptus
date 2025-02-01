package com.adeptus.management.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "course")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Course extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 100, nullable = false)
    private String name;  // Tên khóa học

    @Column(length = 500)
    private String description;  // Mô tả khóa học

    @ManyToOne
    @JoinColumn(name = "class_id", nullable = false)
    private Classes classes;  // Đổi tên biến từ 'Classes' thành 'classes'
}
