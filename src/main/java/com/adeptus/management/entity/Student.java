package com.adeptus.management.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "student")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Student extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 50, nullable = false)
    @Size(max = 50, message = "Tên học viên không được vượt quá 50 ký tự")
    private String studentName;

    @Column(length = 15, nullable = false, unique = true)
    @Pattern(regexp = "^(0[3|5|7|8|9])+([0-9]{8})$", message = "Số điện thoại phải bắt đầu bằng 03, 05, 07, 08, hoặc 09 và có 10 chữ số")
    private String phone;

    @Column(length = 100, nullable = false, unique = true)
    @Email(message = "Email không hợp lệ")
    private String email;

    @Column(nullable = false)
    private LocalDate dob;  // Ngày sinh

    @OneToMany(mappedBy = "student")
    private List<StudentClass> studentClasses; // Quan hệ 1:N với StudentClass

    @OneToMany(mappedBy = "student")
    private List<Mark> marks;  // Quan hệ 1:n với Mark
}