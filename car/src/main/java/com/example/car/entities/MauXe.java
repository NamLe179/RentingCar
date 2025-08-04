package com.example.car.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "mau_xe")
@Data
@AllArgsConstructor
@NoArgsConstructor
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
