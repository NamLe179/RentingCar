package com.example.car.entities;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "mau_xe")
@Data
public class MauXe {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(length = 20, nullable = false)
    private String ten;

    @Column(nullable = false)
    private int soGhe;

    @Column(length = 200, nullable = true)
    private String moTa;

    @ManyToOne
    @JoinColumn(name = "HangXeId", nullable = false)
    private HangXe hangXe;
}
