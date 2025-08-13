package com.example.car.entities;

import com.example.car.enums.HopDongThueStatus;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Entity
@Table(name = "hop_dong_thue")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class HopDongThue {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false)
//    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss", timezone = "Asia/Ho_Chi_Minh")
    private Date thoiGianNhan;

    @Column(nullable = false)
//    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss", timezone = "Asia/Ho_Chi_Minh")
    private Date thoiGianTra;

    @Column(length = 1, nullable = false)
    private HopDongThueStatus trangThai;

    @Column(length = 15, nullable = false)
    private float gia;

    @Column(length = 200, nullable = true)
    private String moTa;

    @Column(name = "check_in", nullable = true)
//    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss", timezone = "Asia/Ho_Chi_Minh")
    private Date checkin;

    @Column(name = "check_out", nullable = true)
//    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss", timezone = "Asia/Ho_Chi_Minh")
    private Date checkout;

    @Column(length = 255, nullable = true)
    private String khachHangDanhGiaChu;

    @Column(length = 1, nullable = true)
    private Integer khachHangDanhGiaSo;

    private Boolean daThanhToanChoDoiTac;

    private Date ngayDanhGia;

    @ManyToOne
    @JoinColumn(name = "OtoId", nullable = false)
    private Oto oto;

    @ManyToOne
    @JoinColumn(name = "KhachHangId", nullable = false)
    private KhachHang khachHang;

    @ManyToOne
    @JsonIgnore
    private HoaDonDoiTac hoaDonDoiTac;
}