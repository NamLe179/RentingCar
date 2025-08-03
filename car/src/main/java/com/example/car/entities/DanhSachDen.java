package com.example.car.entities;

import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;

@Entity
@Table(name = "danh_sach_den")
@Data
public class DanhSachDen {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false)
    private Date ngayThem;

    @Column(length = 255, nullable = true)
    private String lyDo;

    @Column(length = 1, nullable = false)
    private int trangThai;

    @OneToOne
    @JoinColumn(name = "KhachHangId", nullable = false)
    private KhachHang khachHang;

    @ManyToOne
    @JoinColumn(name = "QuanLyId", nullable = false)
    private QuanLy quanLy;

    @OneToOne
    @JoinColumn(name = "HopDongThueId", nullable = false)
    private HopDongThue hopDongThue;
}
