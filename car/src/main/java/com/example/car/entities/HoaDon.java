package com.example.car.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Entity
@Table(name = "hoa_don")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class HoaDon {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

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
