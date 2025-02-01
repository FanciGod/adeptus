package com.adeptus.management.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "expenses_category")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ExpensesCategory extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 30, nullable = false, unique = true)
    private String name;  // Tên danh mục chi tiêu

    @Column(columnDefinition = "TEXT")
    private String description;  // Mô tả về danh mục chi tiêu
}
