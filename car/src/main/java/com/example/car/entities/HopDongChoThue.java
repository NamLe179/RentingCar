package com.example.car.entities;

import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;

@Entity
@Table(name = "hop_dong_cho_thue")
@Data
public class HopDongChoThue {
    @Id
    @Column(length = 10)
    private String id;

    @Column(nullable = false)
    private Date ngayBatDau;

    @Column(nullable = false)
    private Date ngayKetThuc;

    @Column(length = 20, nullable = false)
    private String loaiHopDong;

    @Column(length = 255, nullable = true)
    private String ghiChu;

    @ManyToOne
    @JoinColumn(name = "OtoId", nullable = false)
    private Oto oto;

    @ManyToOne
    @JoinColumn(name = "DoiTacId", nullable = false)
    private DoiTac doiTac;

    @ManyToOne
    @JoinColumn(name = "NhanVienId", nullable = false)
    private NhanVien nhanVien;
}
