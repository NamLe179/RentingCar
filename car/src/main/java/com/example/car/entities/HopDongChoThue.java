package com.example.car.entities;

import com.example.car.enums.HopDongChoThueStatus;
import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;

@Entity
@Table(name = "hop_dong_cho_thue")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class HopDongChoThue {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false)
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private LocalDateTime ngayBatDau;

    @Column(nullable = false)
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private LocalDateTime ngayKetThuc;

    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private LocalDateTime ngayTao;

    @Column(length = 255, nullable = true)
    private String ghiChu;

    @Column(length = 1, nullable = false)
    private HopDongChoThueStatus trangThai;

    @Column(length = 10, nullable = false)
    private Integer phanTramCuaDoiTac;

    @ManyToOne
    @JoinColumn(name = "OtoId", nullable = false)
    private Oto oto;

    @ManyToOne
    @JoinColumn(name = "quan_ly_id", nullable = false)
    private QuanLy quanLy;
}
