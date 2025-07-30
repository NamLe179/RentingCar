package com.example.car.entities;

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
    @JoinColumn(name = "hop_dong_thue_id", nullable = false)
    private HopDongThue hopDongThue;

    @ManyToOne
    @JoinColumn(name = "khach_hang_id", nullable = false)
    private KhachHang khachHang;

    @ManyToOne
    @JoinColumn(name = "nhan_vien_nhan_id", nullable = false)
    private NhanVien nhanVienNhan;

    @ManyToOne
    @JoinColumn(name = "nhan_vien_tra_id", nullable = false)
    private NhanVien nhanVienTra;
}
