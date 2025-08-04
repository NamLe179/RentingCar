package com.example.car.entities;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
@Table(name = "thanh_vien")
@Data
@AllArgsConstructor
@NoArgsConstructor
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
    @JoinColumn(name = "dia_chi_id", referencedColumnName = "id", nullable = false)
    private DiaChi diaChi;
}
