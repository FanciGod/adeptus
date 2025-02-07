package com.adeptus.management.entity.staff;

import com.adeptus.management.entity.classes.Classes;
import com.adeptus.management.entity.common.BaseEntity;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.*;

import java.time.LocalDate;
import java.util.Set;
@Entity
@Table(name = "staff")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Staff extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 20, nullable = false, unique = true)
    @Size(min = 5, max = 20, message = "Username phải từ 5 đến 20 ký tự")
    private String username;

    @Column( nullable = false)
    private String password;

    @Column(length = 50, nullable = false)
    private String fullName;

    @Column(length = 100, nullable = false, unique = true)
    @Email(message = "Email không hợp lệ")
    private String email;

    private LocalDate dob;

    private String thumbnailUrl;

    private String thumbnailPublicId;

    @OneToMany(mappedBy = "staff", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<StaffSalary> salaries;  // Quan hệ 1:n với StaffSalary

    @Column(length = 15, nullable = false, unique = true)
    @Pattern(regexp = "^(0[3|5|7|8|9])+([0-9]{8})$", message = "Số điện thoại phải bắt đầu bằng 03, 05, 07, 08, hoặc 09 và có 10 chữ số")
    private String phone;

    @ManyToMany
    @JoinTable(
            name = "staff_role",
            joinColumns = @JoinColumn(name = "staff_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id")
    )
    private Set<Role> roles;

    @OneToMany(mappedBy = "staff", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<Classes> classes;
}
