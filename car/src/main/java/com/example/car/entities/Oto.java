package com.example.car.entities;

import com.example.car.enums.OtoStatus;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Data;

@Entity
@Table(name = "oto")
@Data
@Builder
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

    @Enumerated(EnumType.ORDINAL)
    @Column(length = 1, nullable = false)
    private OtoStatus trangThai;

    @Column(nullable = false)
    private float gia;

    @Column(length = 500)
    private String moTa;

    @ManyToOne
    @JoinColumn(name = "MauXeId", nullable = false)
    private MauXe mauXe;

    @OneToOne
    @JoinColumn(name = "DiaChiId", nullable = false)
    private DiaChi diaChi;

    @ManyToOne
    @JoinColumn(name = "doi_tac_id", nullable = false)
    private DoiTac doiTac;
}
