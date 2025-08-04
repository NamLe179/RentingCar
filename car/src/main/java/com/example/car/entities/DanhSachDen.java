package com.example.car.entities;

import com.example.car.enums.DanhSachDenStatus;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Entity
@Table(name = "danh_sach_den")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class DanhSachDen {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false)
    private Date ngayThem;

    @Column(length = 255, nullable = true)
    private String lyDo;

    @Column(length = 1, nullable = false)
    private DanhSachDenStatus trangThai;

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
