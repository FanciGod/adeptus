package com.adeptus.management.entity.classes;

import com.adeptus.management.entity.common.BaseEntity;
import jakarta.persistence.*;
import lombok.*;

import java.util.Set;

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

    @OneToMany(mappedBy = "course")
    private Set<Classes> classes;
}
