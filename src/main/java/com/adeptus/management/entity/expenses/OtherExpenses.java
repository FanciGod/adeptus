package com.adeptus.management.entity.expenses;

import com.adeptus.management.entity.common.BaseEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Entity
@Table(name = "other_expenses")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class OtherExpenses extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "expenses_category_id", nullable = false)
    private ExpensesCategory expensesCategory;  // Quan hệ nhiều-một với ExpensesCategory

    @Column(nullable = false)
    private Long total;  // Tổng chi tiêu

    @Column(nullable = false)
    private LocalDate date;  // Ngày chi tiêu

    @Column(columnDefinition = "TEXT")
    private String description;  // Mô tả chi tiết về chi tiêu
}
