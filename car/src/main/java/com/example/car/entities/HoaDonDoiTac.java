package com.example.car.entities;

import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;

@Entity
@Table(name = "hoa_don_doi_tac")
@Data
public class HoaDonDoiTac {
    @Id
    @Column(length = 10)
    private String id;

    @Column(nullable = false)
    private float tongTien;

    @Column(nullable = false)
    private Date ngayThanhToan;

    @Column(length = 20, nullable = false)
    private String phuongThucThanhToan;

    @Column(length = 200, nullable = true)
    private String ghiChu;

    @ManyToOne
    @JoinColumn(name = "NhanVienId", nullable = false)
    private NhanVien nhanVien;

    @ManyToOne
    @JoinColumn(name = "HopDongChoThueId", nullable = false)
    private HopDongChoThue hopDongChoThue;
}
