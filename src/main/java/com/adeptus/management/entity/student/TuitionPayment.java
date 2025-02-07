package com.adeptus.management.entity.student;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Entity
@Table(name = "tuition_payment")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class TuitionPayment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "student_id", nullable = false)
    private Student student;  // Quan hệ nhiều-một với Student

    @Column(nullable = false)
    private LocalDate date;  // Ngày thanh toán

    @Column(nullable = false)
    private Long amount;  // Số tiền học phí trước khi giảm giá

    @Column(nullable = false)
    private Integer lessonPurchased; // Số buổi học mua được

    @Column(nullable = false)
    private int discount;  // Giảm giá (theo phần trăm hoặc số tiền)

    @Column(nullable = false)
    private Long total;  // Số tiền thanh toán thực tế sau giảm giá

    @Column(columnDefinition = "TEXT")
    private String note;  // Ghi chú về giao dịch thanh toán
}
