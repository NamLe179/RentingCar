package com.example.car.entities;

import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;

@Entity
@Table(name = "hoa_don")
@Data
public class HoaDon {
    @Id
    @Column(length = 10)
    private String id;

    @Column(length = 15, nullable = false)
    private float tongTien;

    @Column(nullable = false)
    private Date ngayThanhToan;

    @Column(length = 20, nullable = false)
    private String phuongThucThanhToan;

    @Column(length = 200, nullable = true)
    private String ghiChu;

    @ManyToOne
    @JoinColumn(name = "HopDongThueId", nullable = false)
    private HopDongThue hopDongThue;

    @ManyToOne
    @JoinColumn(name = "NhanVienId", nullable = false)
    private NhanVien nhanVien;



}
