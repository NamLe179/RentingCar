package com.example.car.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "oto")
@Data
public class Oto {
    @Id
    @Column(length = 10)
    private String id;

    @Column(length = 4, nullable = false)
    private int namSanXuat;

    @Column(length = 20, nullable = false)
    private String truyenDong;

    @Column(length = 20, nullable = false)
    private String loaiNhienLieu;

    @Column(length = 2, nullable = false)
    private int mucTieuThu;

    @Column(length = 10, nullable = false)
    private String bienSo;

    @Column(length = 1, nullable = false)
    private int trangThai;

    @Column(nullable = false)
    private float gia;

    @ManyToOne
    @JoinColumn(name = "MauXeId", nullable = false)
    private MauXe mauXe;

    @OneToOne
    @JoinColumn(name = "DiaChiId", nullable = false)
    private DiaChi diaChi;
}
