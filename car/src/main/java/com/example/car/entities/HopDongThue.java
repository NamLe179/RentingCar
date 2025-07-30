package com.example.car.entities;

import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;

@Entity
@Table(name = "hop_dong_thue")
@Data
public class HopDongThue {
    @Id
    @Column(length = 10)
    private String id;

    @Column(nullable = false)
    private Date thoiGianNhan;

    @Column(nullable = false)
    private Date thoiGianTra;

    @Column(length = 1, nullable = false)
    private int trangThai;

    @Column(length = 200, nullable = true)
    private String moTa;

    @Column(name = "check_in", nullable = true)
    private Date checkin;

    @Column(name = "check_out", nullable = true)
    private Date checkout;

    @Column(length = 255, nullable = true)
    private String khachHangDanhGiaChu;

    @Column(length = 1, nullable = true)
    private int khachHangDanhGiaSo;

    @Column(length = 255, nullable = true)
    private String doiTacBaoCao;

    @ManyToOne
    @JoinColumn(name = "OtoId", nullable = false)
    private Oto oto;

    @ManyToOne
    @JoinColumn(name = "KhachHangId", nullable = false)
    private KhachHang khachHang;
}
