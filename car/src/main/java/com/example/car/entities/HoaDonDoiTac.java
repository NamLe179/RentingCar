package com.example.car.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Entity
@Table(name = "hoa_don_doi_tac")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class HoaDonDoiTac {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false)
    private Float tongTien;

    @Column(nullable = false)
    private Date ngayThanhToan;

    @Column(nullable = false)
    private Date ngayBatDau;

    @Column(nullable = false)
    private Date ngayKetThuc;

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
