package com.example.car.entities;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "phu_phi")
@Data
public class PhuPhi {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(length = 20, nullable = false)
    private String ten;

    @Column(nullable = false)
    private float gia;

    @Column(length = 200, nullable = true)
    private String moTa;
}
