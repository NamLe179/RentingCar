package com.example.car.entities;

import com.example.car.enums.TaiSanCamCoStatus;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Entity
@Table(name = "tai_san_cam_co")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class TaiSanCamCo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(length = 20, nullable = false)
    private String ten;

    @Column(length = 20, nullable = false)
    private String loaiTaiSan;

    @Column(length = 15, nullable = false)
    private float gia;

    @Column(length = 200, nullable = true)
    private String moTa;

    @Column(length = 1, nullable = false)
    private TaiSanCamCoStatus trangThai;

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
    @JoinColumn(name = "doi_tac_id", nullable = false)
    private DoiTac doiTac;
}
