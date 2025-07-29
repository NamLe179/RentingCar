package com.example.car.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;

@Entity
@Table(name = "tai_san_cam_co")
@Data
public class TaiSanCamCo {
    @Id
    @Column(length = 10)
    private String id;

    @Column(length = 20, nullable = false)
    private String ten;

    @Column(length = 20, nullable = false)
    private String loaiTaiSan;

    @Column(length = 15, nullable = false)
    private float gia;

    @Column(length = 200, nullable = true)
    private String moTa;

    @Column(length = 1, nullable = false)
    private int trangThai;

    @Column(nullable = true)
    private Date thoiGianTra;

    @Column(nullable = false)
    private Date thoiGianNhan;

    @ManyToOne
    @JoinColumn(name = "HopDongThueId", nullable = false)
    private HopDongThue hopDongThue;

    @ManyToOne
    @JoinColumn(name = "KhachHangId", nullable = false)
    private KhachHang khachHang;

    @ManyToOne
    @JoinColumn(name = "NhanVienNhanId", nullable = false)
    private NhanVien nhanVienNhan;

    @ManyToOne
    @JoinColumn(name = "NhanvienTraId", nullable = false)
    private NhanVien nhanVienTra;
}
