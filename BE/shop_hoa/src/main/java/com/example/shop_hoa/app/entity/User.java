package com.example.shop_hoa.app.entity;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String hoTen;

    @Column(nullable = false, unique = true)
    private String email;

    private String sdt;

    @Column(nullable = false)
    private String password;
}