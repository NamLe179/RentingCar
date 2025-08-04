package com.example.car.entities;

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

    private Date ngayDanhGia;

    @ManyToOne
    @JoinColumn(name = "OtoId", nullable = false)
    private Oto oto;

    @ManyToOne
    @JoinColumn(name = "KhachHangId", nullable = false)
    private KhachHang khachHang;
}
