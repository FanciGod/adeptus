package com.adeptus.management.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
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

    private String username;

    @Column( nullable = false)
    private String password;

    @Column(length = 50, nullable = false)
    private String fullName;

    @Column(length = 100, nullable = false, unique = true)

    private String email;

    private LocalDate dob;

    private String thumbnailUrl;

    private String thumbnailPublicId;

    @OneToMany(mappedBy = "staff", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<StaffSalary> salaries;  // Quan hệ 1:n với StaffSalary

    @Column(length = 15, nullable = false, unique = true)
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
