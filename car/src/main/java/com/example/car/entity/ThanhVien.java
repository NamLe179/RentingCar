package com.example.car.entity;


import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "thanh_vien")
@Data
public class ThanhVien {
    @Id
    @Column(length = 10)
    private String id;

    @Column(length = 40, nullable = false)
    private String username;

    @Column(length = 50, nullable = false)
    private String password;

    @Column(length = 50, nullable = false)
    private String hoTen;

    @Column(length = 10, nullable = false)
    private String sdt;

    @Column(length = 50, nullable = false)
    private String email;

    @OneToOne
    @JoinColumn(name = "DiaChiId", nullable = false)
    private DiaChi diaChi;
}
