package com.example.car.entities;

import com.fasterxml.jackson.annotation.JsonFormat;
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
//    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss", timezone = "Asia/Ho_Chi_Minh")
    private Date ngayThanhToan;

    @Column(nullable = false)
//    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss", timezone = "Asia/Ho_Chi_Minh")
    private Date ngayBatDau;

    @Column(nullable = false)
//    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss", timezone = "Asia/Ho_Chi_Minh")
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
